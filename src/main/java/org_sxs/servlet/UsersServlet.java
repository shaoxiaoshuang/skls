package org_sxs.servlet;

import org_sxs.biz.UsersBiz;
import org_sxs.pojo.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet(name = "UsersServlet", urlPatterns = "*.do")
public class UsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //获取请求路径   /add.do
        String path = request.getServletPath();
        //截取 add
        String pathName = path.substring(1, path.lastIndexOf("."));
        //利用反射机制
        try {
            //pathName方法名; 后面2个是参数
            Method method = getClass().getDeclaredMethod(pathName, HttpServletRequest.class, HttpServletResponse.class);
            //调用
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //关闭
        out.flush();
        out.close();

    }

    /**
     * 注册、添加
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void reg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //请求数据
        String name = request.getParameter("textfield");
        String password = request.getParameter("textfield2");
        //封装数据
        Users user = new Users(name, password);
        //调用业务对象
        UsersBiz biz = new UsersBiz();
        //调用业务方法
        int count = biz.add(user);
        //判断
        if (count > 0) {
            response.sendRedirect("index.html");
        } else {
            response.sendRedirect("addUser.html");
        }

        //关闭
        out.flush();
        out.close();
    }

    /**
     * 登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //请求数据
        String name = request.getParameter("userName");
        String password = request.getParameter("userPass");
        //封装数据
        Users user = new Users(name, password);
        //调用业务对象
        UsersBiz biz = new UsersBiz();
        //调用业务方法
        int count = biz.isLogin(user);
        //判断
        if (count > 0) {
            response.sendRedirect("welcome.html");
        } else {
            response.sendRedirect("addUser.html");
        }

        //关闭
        out.flush();
        out.close();
    }

    /**
     * 显示数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    protected void all(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //调用业务对象
        UsersBiz biz = new UsersBiz();
        //调用业务方法
        List<Users> list = biz.all();
        //保存数据
        request.setAttribute("lists", list);
        request.getRequestDispatcher("listUsers.jsp").forward(request, response);
        //关闭
        out.flush();
        out.close();
    }

    /**
     * 修改、删除
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //请求数据
        String Id = request.getParameter("userId");
        String name = request.getParameter("userName");
        String password = request.getParameter("password");
        //提交按钮有两个时，获取参数值
        String sub = request.getParameter("Submit");
        //变量
        int count = 0;
        UsersBiz biz = null;
        //判断
        if ("修改".equals(sub)) {
            //封装数据
            Users user = new Users(Integer.parseInt(Id), name, password);
            //调用业务对象
            biz = new UsersBiz();
            //调用业务方法
            count = biz.update(user);
        } else {
            //做删除处理
            //调用业务对象
            biz = new UsersBiz();
            //调用业务方法
            count = biz.delete(Integer.parseInt(Id));
        }

        //判断
        if (count > 0) {
            response.sendRedirect("all.do");
        } else {
            response.sendRedirect("userInfo.jsp");
        }

        //关闭
        out.flush();
        out.close();
    }

}
