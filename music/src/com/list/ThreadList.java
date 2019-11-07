package com.list;

import java.util.ArrayList;

import com.service.Player;


final public class ThreadList {
	private static ArrayList<Player> list;
	static{
		if (list==null) {
			list=new ArrayList<Player>();
		}
	}
	
	public static void add(Player player){
		
		list.add(player);
	}
	
	public static ArrayList<Player> getList() {
		return list;
	}
	
}
