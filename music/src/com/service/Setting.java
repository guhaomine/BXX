package com.service;

public class Setting {

	private static String mode="default";//˳�򲥷� (Ĭ��)default   ���rand      ����ѭ�� onecircle       �б�ѭ�� morecircle      ��������  one  

	public static String getMode() {
		return mode;
	}
	public static void setMode(String mode) {
		Setting.mode = mode;
	}
	
}
