package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static void main(String[] args) {
        System.out.println(md5("123456"));
    }
     public static String md5(String password){
         try {
             //加密对象
             MessageDigest md=MessageDigest.getInstance("md5");
             //加密
             byte[] digest = md.digest(password.getBytes());
             StringBuffer sb = new StringBuffer();
              String str;
             for (int i = 0; i <digest.length ; i++) {
                if (digest[i]>=0){
                       sb.append(Integer.toHexString(digest[i]));
                 }else{
                    str=Integer.toHexString(digest[i]);
                    str=str.substring(str.length()-2,str.length());
                      sb.append(str);
                 }
             }
             return sb.toString();
         } catch (NoSuchAlgorithmException e) {
             e.printStackTrace();
             throw new RuntimeException(e);
         }
     }
}
