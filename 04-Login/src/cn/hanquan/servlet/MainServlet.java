package cn.hanquan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hanquan.orm.po.User;

public class MainServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// 设置请求、响应编码格式
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		// 获取Session用户信息
		Object o = req.getSession().getAttribute("user");
		String uname = "";
		if (o != null) {
			uname = ((User) o).getUname();
		} else {
			resp.sendRedirect("LoginServlet");
			return;
		}

		// 获取网页浏览次数
		int nums = Integer.parseInt((String) this.getServletContext().getAttribute("nums"));

		// 响应处理结果
		resp.getWriter().write("<html>");
		resp.getWriter().write("<head>");
		resp.getWriter().write("</head>");
		resp.getWriter().write("<body>");
		resp.getWriter().write("<h3>欢迎 " + uname + " 访问尚学堂管理系统</h3>"); // 只有请求转发才有效
		resp.getWriter().write("所有用户总登录次数为:" + nums);
		resp.getWriter().write("<hr>");

		resp.getWriter().write("<form action='ShowServlet' method='get'>");
		resp.getWriter().write("<input type='submit' value='查看个人信息'>");
		resp.getWriter().write("</form>");

		resp.getWriter().write("</body>");
		resp.getWriter().write("</html>");
	}
}
