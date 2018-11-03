package util;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
* 目标：1.解决表名与javabean名不一致的问题
*       2.解决javabean属性名与表属性名不一致
*
* */
@SuppressWarnings("unchecked")
public class BaseDao<T> {
    private Class targetClass;
    private String tableName;
    public BaseDao(){
        Class clazz = this.getClass();
        Type type = clazz.getGenericSuperclass();
        ParameterizedType type1 = (ParameterizedType) type;
        Type[] actualTypeArguments = type1.getActualTypeArguments();
        Type actualTypeArgument = actualTypeArguments[0];
        targetClass = (Class) actualTypeArgument;
        //必须返回一个特定的注解类才能得到起方法
        TableName tn = (TableName) targetClass.getAnnotation(TableName.class);
        tableName=tn.name();
    }
    public List<T> queryAll() throws SQLException {
        QueryRunner qr=new QueryRunner(JdbcUitl.getDataSource());
        String sql="select * from "+tableName;
        List<T> query = (List<T>) qr.query(sql,new MyBeanHandeler());
        return query;
    }
   public T  query(int id) throws SQLException {
       QueryRunner qr=new QueryRunner(JdbcUitl.getDataSource());
       String sql="select * from "+tableName+" where id=? ";
       T query = (T) qr.query(sql,new MyBeanHandeler(),new Object[]{id});
       return query;
   }
   public void insert(T t){
       try {
           QueryRunner qr=new QueryRunner(JdbcUitl.getDataSource());
           String parameter = getParameter();
           String str = replaceNotComma(parameter);
           Object[] value = getValue(t);
           String sql="insert into "+tableName+" ("+parameter+") value ("+str+")";
           qr.update(sql,value);
       } catch (SQLException e) {
           e.printStackTrace();
           throw new RuntimeException(e);
       }
   }
    public void delete(int id){
        try {
            QueryRunner qr=new QueryRunner(JdbcUitl.getDataSource());
            String sql = "delete from "+tableName+" where id=?";
            qr.update(sql,new Object[]{id});
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public void update(T t){
        try {
            QueryRunner qr= new QueryRunner(JdbcUitl.getDataSource());
            String updateSql = getUpdateSql();
            String sql="update "+tableName+" set "+updateSql;
            Object[] objArr = getWithID(t);
            qr.update(sql,objArr);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    class MyBeanHandeler implements ResultSetHandler {
        @Override
        @SuppressWarnings("unchecked")
        public Object handle(ResultSet rs) throws SQLException {
            //构造一个列表来存放对象
            List<T> list = new ArrayList<>();
            //得到表的元数据
            ResultSetMetaData metaData = rs.getMetaData();
            //得到表的column的长度
            int columnCount = metaData.getColumnCount();
            //进行分装,遍历
            T t=null;
            while (rs.next()) {
                try {
                    //通过放射的方法，构造一个对象
                    t = (T) targetClass.getDeclaredConstructor().newInstance();
                    //遍历同一行
                    for (int i = 1; i <= columnCount; i++) {
                        //得到第i列的值
                        Object value = rs.getObject(i);
                        //得到列名
                        String columnName = metaData.getColumnName(i).toLowerCase();
                        //得到所有域对象，遍历得其注解上的列名
                        Field[] declaredFields = targetClass.getDeclaredFields();
                        //遍历域对象
                        for (Field field : declaredFields) {
                            //通过放射注解，得到注解
                            ColumnName annotation = field.getAnnotation(ColumnName.class);
                            //得到绑定的列名
                            String name = annotation.name().toLowerCase();
                            //与之前得到的列名作比较，如果相同，即可将其value封装到该对象中
                            if (columnName.equals(name)) {
                                field.setAccessible(true);//设置可以访问
                                field.set(t, value);//设置属性value
                                break;
                            }
                        }
                    }
                    list.add(t);//添加对象
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
                return list;//返回对象

        }
    }
    private String getParameter(){
        StringBuffer str=new StringBuffer();
        String parameter;
        Field[] declaredFields = targetClass.getDeclaredFields();
        for(Field field:declaredFields){
            if (!field.getName().contains("id")){
                ColumnName annotation = field.getAnnotation(ColumnName.class);
                str=str.append(annotation.name()+",");
            }
        }
        parameter=str.substring(0,str.length()-1);
        return parameter;
    }
    private String replaceNotComma(String parameter){
        String[] split = parameter.split(",");
        StringBuffer sb=new StringBuffer();
        String str;
        for (int i = 0; i <split.length ; i++) {
            sb.append("?,");
        }
        str=sb.substring(0,sb.length()-1);
        return str;
    }
    private Object[] getValue(T t){
        try {
            Field[] declaredFields = targetClass.getDeclaredFields();
            Object [] objArr=new Object[declaredFields.length-1];
            int count=0;
            for (int i = 0; i <declaredFields.length ; i++) {
                if (!declaredFields[i].getName().contains("id")) {
                    declaredFields[i].setAccessible(true);
                    Object o1 = declaredFields[i].get(t);
                    objArr[count] = o1;
                    count++;
                }
            }
            return objArr;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private Object[] getWithID(T t){
        try {
            Field[] declaredFields = targetClass.getDeclaredFields();
            Object [] objArr=new Object[declaredFields.length];
            int count =0;
            Object o=null;
            for (int i = 0; i <declaredFields.length ; i++) {
                if (!declaredFields[i].getName().contains("id")) {
                    declaredFields[i].setAccessible(true);
                    Object o1 = declaredFields[i].get(t);
                    objArr[count] = o1;
                    count++;
                }else{
                    declaredFields[i].setAccessible(true);
                     o = declaredFields[i].get(t);
                }
            }
            objArr[declaredFields.length-1]=o;
            return objArr;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    private String getUpdateSql(){
        Field[] declaredFields = targetClass.getDeclaredFields();
        StringBuffer sb = new StringBuffer();
        String str;
        String str2=null;
        for (int i = 0; i <declaredFields.length ; i++) {
            ColumnName annotation = declaredFields[i].getAnnotation(ColumnName.class);
            if (!declaredFields[i].getName().contains("id")){
                sb=sb.append(annotation.name()+"=?,");
            }else{
                str2=" where "+annotation.name()+" =? ";
            }
        }
        str=sb.substring(0,sb.length()-1);
        str=str+str2;
        return str;
    }

}