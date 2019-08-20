package cn.hanquan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html;charset=utf-8");

		String str = (String) req.getAttribute("str") == null ? "" : (String) req.getAttribute("str");// 错误提示
		resp.getWriter().write("<html>");
		resp.getWriter().write("<head>");
		resp.getWriter().write("</head>");
		resp.getWriter().write("<body>");
		resp.getWriter().write("<font color='red' size='20px'>" + str + "</font>");
		resp.getWriter().write("<form action='LoginServlet' method='get'>");
		resp.getWriter().write("用户名:<input type='text' name='uname' value=''/><br/>");
		resp.getWriter().write("密码:<input type='password' name='upwd' value=''/><br/>");
		resp.getWriter().write("<input type='submit'  value='登录'/><br/>");
		resp.getWriter().write("</form>");
		resp.getWriter().write("</body>");
		resp.getWriter().write("</html>");
	}
}
