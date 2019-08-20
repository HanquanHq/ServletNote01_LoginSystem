package cn.hanquan.orm.test;

import static org.junit.Assert.fail;

import org.junit.Before;

import cn.hanquan.orm.core.Query;
import cn.hanquan.orm.po.User;

public class Test {
	Query q;
	
	@org.junit.Test
	public void testInsert() {
		User u1=new User();
		u1.setUname("老大");
		u1.setUpwd("123456");
		q.insert(u1);
	}

//	@org.junit.Test
//	public void testDeleteClassObject() {
//		fail("Not yet implemented");
//	}
//
//	@org.junit.Test
//	public void testDeleteObject() {
//		fail("Not yet implemented");
//	}
//
//	@org.junit.Test
//	public void testUpdate() {
//		fail("Not yet implemented");
//	}
//
//	@org.junit.Test
//	public void testQueryRows() {
//		fail("Not yet implemented");
//	}
//
//	@org.junit.Test
//	public void testQueryUniqueRow() {
//		fail("Not yet implemented");
//	}
//
//	@org.junit.Test
//	public void testQueryValue() {
//		fail("Not yet implemented");
//	}
//
//	@org.junit.Test
//	public void testQueryNumber() {
//		fail("Not yet implemented");
//	}

}
