package com.list;

import java.util.ArrayList;

import com.model.Music;


final public class MusicList {

	private static ArrayList<Music> list;
	
	static{
		
		
		if (list==null) {
			System.out.println("创建列表");
			list=new ArrayList<Music>();
		}
	}
	
   public static void add(Music music){
		
		list.add(music);
	}
	
	public static ArrayList<Music> getList() {
		return list;
	}

	public static Music get(int id){
		return list.get(id);
	}
	
}
