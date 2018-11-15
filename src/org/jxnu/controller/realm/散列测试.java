package org.jxnu.controller.realm;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

public class 散列测试 {
	public static void main(String[] args){
		//Md5Hash md5Hash = new Md5Hash("123456", "eteokues");
		//加盐eteokues、散列一次：48e1a118a99e59fb09254e42a0335bc8
		SimpleHash simpleHash = new SimpleHash("md5", "123456", "eteokues", 1);
		System.out.println(simpleHash.toString());
		
	}
}
