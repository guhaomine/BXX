package com.list;

import java.util.ArrayList;

import com.view.View;

final public class ViewList {

	private static ArrayList<View> list;
	static{
		if (list==null) {
			System.out.println("����ҳ��ɹ�");
			list=new ArrayList<View>();
		}
	}
	public static void add(View v){	
		list.add(v);
	}	
	public static ArrayList<View> getList() {
		return list;
	}
	
}
