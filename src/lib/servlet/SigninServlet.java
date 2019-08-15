package lib.servlet;

import lib.Dao.Dbutil;
import lib.Dao.UserDao;
import lib.Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by ttop5 on 16-4-20.
 */
@WebServlet(urlPatterns = "/signin", name = "signin")
public class SigninServlet extends HttpServlet {
//    Dbutil dbutil = new Dbutil();     // 改为了静态方法
    private UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        req.setAttribute("email", email);
        req.setAttribute("password", password);

        // FIXME: 模糊提示输入错误，防止暴力破解密码
        if (email.isEmpty() || password.isEmpty()){
            req.setAttribute("error", "用户名或密码为空!");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            return;
        }

        User user = new User(email, password);
        Connection con = null;
        try {
            con = Dbutil.getCon();
            User currentUser = userDao.signin(con, user);
            if (currentUser == null){
                req.setAttribute("error", "用户名或密码错误！");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
            else {
                HttpSession session = req.getSession();
                session.setAttribute("currentUser", currentUser);
                if (currentUser.getRole() == 0) {       // admin
                    resp.sendRedirect("admin/index.jsp");
                } else if (currentUser.getRole() == 1){     // teacher
                    resp.sendRedirect("teacher/index.jsp");
                } else {        // student
                    resp.sendRedirect("student/index.jsp");
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
//                dbutil.closeCon(con);
                Dbutil.closeCon(con);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
