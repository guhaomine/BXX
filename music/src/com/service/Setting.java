package com.service;

public class Setting {

	private static String mode="default";//顺序播放 (默认)default   随机rand      单曲循环 onecircle       列表循环 morecircle      单曲播放  one  

	public static String getMode() {
		return mode;
	}
	public static void setMode(String mode) {
		Setting.mode = mode;
	}
	
}
