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

public class CookieServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		Cookie[] cks = req.getCookies();
		if (cks != null) {// 有cookie
			String uid = "";
			for (Cookie c : cks) {
				if ("uid".equals(c.getName())) {
					uid = c.getValue();
				}
			}

			if ("".equals(uid)) {// cookie不完整
				req.setAttribute("str", "You have cleaned cookie, please login again.");
				req.getRequestDispatcher("/PageServlet").forward(req, resp);
				return;
			} else {// 二次验证用户存在
				User u = new User();
				u.setUid(Integer.parseInt(uid));
				Query q = QueryFactory.createQuery();
				Object o = q.queryUniqueRow("select * from user where uid=?", User.class, new String[] { uid });

				if (o != null) {
					u = (User) o;
					// 登录数据存入session
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
				} else {
					resp.sendRedirect("MainServlet");
					return;
				}
			}
		} else {// 没有cookie
			resp.sendRedirect("MainServlet");
			return;
		}
	}
}
