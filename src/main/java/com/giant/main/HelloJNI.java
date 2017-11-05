package com.giant.main;


public class HelloJNI{
	static {
		System.out.println("HelloJNI.static initializer");
	}
	private HelloJNI(){}
	public static native int sayHello(int i);
	public static void main(String[] args) throws Exception {
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		System.load("F:\\15756\\Workspaces\\C\\HelloJNI\\bin\\Debug\\HelloJNI.dll"
//		System.out.println(sayHello(1));
	}
}