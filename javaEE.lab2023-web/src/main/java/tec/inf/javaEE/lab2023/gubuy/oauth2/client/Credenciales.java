package tec.inf.javaEE.lab2023.gubuy.oauth2.client;

import javax.ejb.Singleton;

@Singleton
public class Credenciales {
	private static String accesToken;
	private static String callBackGubUyInfo;
	private static String empresaUserLogged;

	public static String getAccesToken() {
		return accesToken;
	}

	public static void setAccesToken(String accesToken) {
		Credenciales.accesToken = accesToken;
	}

	public static String getCallBackGubUyInfo() {
		return callBackGubUyInfo;
	}

	public static void setCallBackGubUyInfo(String callBackGubUyInfo) {
		Credenciales.callBackGubUyInfo = callBackGubUyInfo;
	}

	public static String getEmpresaUserLogged() {
		return empresaUserLogged;
	}

	public static void setEmpresaUserLogged(String empresaUserLogged) {
		Credenciales.empresaUserLogged = empresaUserLogged;
	}
	
}
