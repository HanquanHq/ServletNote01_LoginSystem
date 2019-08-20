package cn.hanquan.orm.po;

import java.sql.*;
import java.util.*;

public class User {

	private Integer uid;
	private String uname;
	private String upwd;


	public Integer getUid(){
		return uid;
	}
	public String getUname(){
		return uname;
	}
	public String getUpwd(){
		return upwd;
	}
	public void setUid(Integer uid){
		this.uid=uid;
	}
	public void setUname(String uname){
		this.uname=uname;
	}
	public void setUpwd(String upwd){
		this.upwd=upwd;
	}
	public String toString() {
		return "" + uid+"\t"+uname+"\t"+upwd;
	}
}