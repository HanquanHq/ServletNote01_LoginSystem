package cn.hanquan.orm.test;

import cn.hanquan.orm.core.Query;
import cn.hanquan.orm.core.QueryFactory;
import cn.hanquan.orm.po.User;

public class MyTest {
	public static void main(String[] args) {
		Query q = QueryFactory.createQuery();

		User u1 = new User();
		u1.setUname("老大");
		u1.setUpwd("123456");
		q.insert(u1);
	}
}
