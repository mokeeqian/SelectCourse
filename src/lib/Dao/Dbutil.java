package lib.Dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

/**
 * 数据库连接工具
 */
public class Dbutil {

    private static String url = "jdbc:mysql://localhost:3306/sc?"
            + "user=root&password=990505&useUnicode=true&characterEncoding=UTF8";

    public static Connection getCon() throws Exception{
        String jdbcName = "com.mysql.jdbc.Driver";
        Class.forName(jdbcName);
        return DriverManager.getConnection(url);
    }

    public static void closeCon(Connection con) throws Exception{
        if(con!=null){
            con.close();
        }
    }

    public static void main(String[] args){
//        Dbutil dbutil = new Dbutil();

        try {
            Connection connection = getCon();
            closeCon(connection);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
