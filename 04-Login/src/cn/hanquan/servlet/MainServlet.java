package cn.hanquan.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 设置请求、响应编码格式
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		// 获取请求信息
		// 处理请求信息
		// 响应处理结果
		resp.getWriter().write("<html>");
		resp.getWriter().write("<head>");
		resp.getWriter().write("</head>");
		resp.getWriter().write("<body>");
		resp.getWriter().write("<h3>欢迎 " + req.getParameter("uname") + " 访问尚学堂管理系统</h3>"); // 只有请求转发才有效
		resp.getWriter().write("<hr>");
		resp.getWriter().write("</body>");
		resp.getWriter().write("</html>");
	}
}
