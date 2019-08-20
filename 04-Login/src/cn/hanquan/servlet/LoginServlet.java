package cn.hanquan.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.hanquan.orm.core.Query;
import cn.hanquan.orm.core.QueryFactory;
import cn.hanquan.orm.po.User;

/**
 * 接收提交的表单，并根据登陆结果重定向
 * 
 * @author Buuug
 *
 */
public class LoginServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置请求、响应编码格式
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		// 获取表单请求信息
		String uname = req.getParameter("uname");
		String upwd = req.getParameter("upwd");
		System.out.println("获取到uname=" + uname + "upwd=" + upwd);

		// 处理请求信息
		if (uname == null || upwd == null) {// 避免直接访问此页报空指针
			resp.sendRedirect("PageServlet");
			return;
		}
		User u = new User();
		u.setUname(uname);
		u.setUpwd(upwd);

		Query q = QueryFactory.createQuery();
		Object result = q.queryUniqueRow("select * from user where uname=? and upwd=?", User.class,
				new String[] { uname, upwd });
		System.out.println(result);

		// 登录结果
		if (result != null) {// right
			Cookie c = new Cookie("uid", ((User) result).getUid() + "");
			c.setMaxAge(5 * 60);
			c.setPath("CookieServlet");
			resp.addCookie(c);

			// 登录数据存到session
			HttpSession hs = req.getSession();
			hs.setAttribute("user", u);

			// 计数器
			ServletContext sc = this.getServletContext();
			if (sc.getAttribute("nums") != null) {
				int nums = Integer.parseInt((String) sc.getAttribute("nums"));
				nums += 1;
				sc.setAttribute("nums", nums + "");
			} else {
				sc.setAttribute("nums", "1");
			}

			// 重定向
			resp.sendRedirect("MainServlet");
			return;
		} else {// wrong
			// 请求转发
			req.setAttribute("str", "用户名或密码错误");
			req.getRequestDispatcher("PageServlet").forward(req, resp);
			return;
		}
	}
}