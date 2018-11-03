package util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

public class JdbcUitl {
        private static DataSource dataSource=new ComboPooledDataSource();
        public static DataSource getDataSource(){
            return dataSource;
        }
}
