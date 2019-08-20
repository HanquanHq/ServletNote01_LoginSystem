package cn.hanquan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hanquan.orm.core.Query;
import cn.hanquan.orm.core.QueryFactory;
import cn.hanquan.orm.po.User;

public class CookieServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		Cookie[] cks = req.getCookies();
		if (cks != null) {
			String uid = "";
			for (Cookie c : cks) {
				if ("uid".equals(c.getName())) {
					uid = c.getValue();
				}
			}

			if ("".equals(uid)) {// 部分cookie被用户删除
				req.setAttribute("str", "什么鬼，你清理了cookie吗");
				req.getRequestDispatcher("page").forward(req, resp);
				return;
			} else {// 二次验证用户存在
				User u = new User();
				u.setUid(Integer.parseInt(uid));
				Query q = QueryFactory.createQuery();
				Object result = q.queryUniqueRow("select * from user where uid=?", User.class, new String[] { uid });

				if (result != null) {
					resp.sendRedirect("MainServlet");
					return;
				} else {
					req.setAttribute("str", "你似乎不在数据库里了");
					req.getRequestDispatcher("/PageServlet").forward(req, resp);
					return;
				}
			}
		} else {
			req.getRequestDispatcher("page").forward(req, resp);
			return;
		}
	}
}
