package com.banorte.ws.esb.reconciliaciones.util;

public class Singleton_handling_errors {

	String Id;
	String Messange_user;
	String Message_detail;
		
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getMessange_user() {
		return Messange_user;
	}

	public void setMessange_user(String messange_user) {
		Messange_user = messange_user;
	}

	public String getMessage_detail() {
		return Message_detail;
	}

	public void setMessage_detail(String message_detail) {
		Message_detail = message_detail;
	}

	public static Singleton_handling_errors getInstance() {
		return SingletonHolder.instance;
	}
	
	private static class SingletonHolder {
	      public static Singleton_handling_errors instance = new Singleton_handling_errors();
	}

	private Singleton_handling_errors() {}
	
}
