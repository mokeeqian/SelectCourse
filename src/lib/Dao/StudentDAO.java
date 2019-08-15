package lib.Dao;

import lib.Model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ttop5 on 16-4-18.
 */
public class StudentDAO {
//    public String getNotes() throws SQLException {
//        Statement stmt = null;
//        Dbutil dbutil = new Dbutil();
//        Connection con = null;
//        ResultSet rs = null;
//        String str = "<table class=\"table table-bordered\" id=\"outside\">" +
//                "<tr><th>标题</th><th>时间</th><th>公告内容</th></tr>";
//        try{
//            con = dbutil.getCon();
//            stmt = con.createStatement();
//            String sql = "select title, start_time, description from notes" + ";";
//            rs = stmt.executeQuery(sql);
//            while(rs.next()) {
//                str = str + "<tr>" + "<td>" + rs.getString("title") + "</td>" + "<td>" + rs.getDate("start_time") + "</td>" + "<td>" + rs.getString("description") + "</td>" + "</tr>";
//            }
//            return str + "</table>";
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        return str;
//    }

    /**
     * @brife 获取通知信息，在页面中展示
     * @return html 标签语言
     * @throws SQLException
     */
    public String getNotes() throws SQLException{
        Statement stmt = null;
//        Dbutil dbutil = new Dbutil();
        Connection con = null;
        ResultSet rs = null;
        String str = "<table class=\"table table-bordered\" id=\"outside\">" +
                "<tr><th>标题</th><th>开始时间</th><th>结束时间</th><th>公告内容</th></tr>";
        try{
            con = Dbutil.getCon();
            stmt = con.createStatement();
            String sql = "select * from notes;";
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                str = String.format("%s<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>",
                        str, rs.getString("title"), rs.getString("start_time"),
                        rs.getString("stop_time"), rs.getString("description"));
            }
            return str + "</table>";
        }catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * @brife 获取分数，在页面中展示
     * @param email, 用户的email
     * @return html 标签语言
     * @throws SQLException
     */
    public String getScore(String email) throws SQLException{
        Statement stmt = null;
//        Dbutil dbutil = new Dbutil();
        Connection con = null;
        ResultSet rs = null;
        String str = "<table class=\"table table-bordered\" id=\"outside\">" +
                "<tr><th>课程号</th><th>课程名</th><th>学分</th><th>平时成绩</th><th>期末成绩</th><th>最终成绩</th></tr>";
        try{
            con = Dbutil.getCon();
            stmt = con.createStatement();
            String sql = String.format("select cource_id, cource_name, credit, pingshi_score, " +
                    "qimo_score, final_score from score, user, cource where student=user_id " +
                    "and cource=cource_id AND email='%s';", email);
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                str = String.format("%s<tr><td>%d</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>",
                        str, rs.getInt("cource_id"), rs.getString("cource_name"),
                        rs.getString("credit"), rs.getString("pingshi_score"),
                        rs.getString("qimo_score"), rs.getString("final_score"));
            }
            return str + "</table>";
        }catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public String getScource(String email) throws SQLException{
        Statement stmt = null;
//        Dbutil dbutil = new Dbutil();
        Connection con = null;
        ResultSet rs = null;
        String str = "<table class=\"table table-bordered\" id=\"outside\">" +
                "<tr><th>课程号</th><th>课程名称</th><th>学分</th><th>上课时间</th><th>上课地点</th></tr>";
        try{
            con = Dbutil.getCon();
            stmt = con.createStatement();
            String sql = String.format("select cource_id, cource_name, credit, schooltime, location from score, user, cource, classroom where student=user_id and cource=cource_id and classroom=classroom_id AND email='%s';", email);
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                str = String.format("%s<tr><td>%d</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>",
                        str, rs.getInt("cource_id"), rs.getString("cource_name"),
                        rs.getString("credit"), rs.getString("schooltime"),
                        rs.getString("location"));
            }
            return str + "</table>";
        }catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public String getUser(String email) throws SQLException{
        Statement stmt = null;
//        Dbutil dbutil = new Dbutil();
        Connection con = null;
        ResultSet rs = null;
        String str = "<table class=\"table table-bordered\" id=\"outside\">" +
                "<tr><th>学号</th><th>姓名</th><th>性别</th><th>年级</th><th>学院</th><th>专业</th><th>班级</th><th>QQ</th><th>电话</th><th>邮箱</th><th>地址</th><th>角色</th></tr>";
        try{
            con = Dbutil.getCon();
            stmt = con.createStatement();
            String sql = String.format("select * from user WHERE email='%s';", email);
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                str = String.format("%s<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td><button type=\"button\" class=\"btn btn-success\">编辑</button></td></tr>", str, rs.getString("school_num"), rs.getString("name"), rs.getString("sex"), rs.getString("grade"), rs.getString("school"), rs.getString("major"), rs.getString("class"), rs.getString("qq"), rs.getString("phone"), rs.getString("email"), rs.getString("adress"), rs.getString("role"));
            }
            return str + "</table>";
        }catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
