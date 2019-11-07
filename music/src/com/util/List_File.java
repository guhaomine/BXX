package com.util;

import java.io.File;
import java.util.ArrayList;


public class List_File {
    String dir ="";
    String temp ="";   
    private String[] info;    
    private  ArrayList<String[]> FileList=null;

    public ArrayList<String[]> serachFiles(String dir) {
        File root = new File(dir);
        File[] filesOrDirs = root.listFiles();      
        FileList=new ArrayList<String[]>();
        for (int i = 0;i<filesOrDirs.length; i++) {
        	
        	File file=filesOrDirs[i];
        	
      
        	if (file.isFile()&&isMusic(file.getName())) {
        	  	info=new String[2];
            	info[0]=file.getName();
            	info[1]=file.getAbsolutePath();
            	FileList.add(info);
			}
        }
        return FileList;

    }
    //¼òµ¥µÄ¹ýÂËMP3   
    public boolean isMusic(String s){   	
      String type=s.substring(s.indexOf(".")+1,s.length());
    			if("mp3".equals(type)){
    				return true;
    			}
    			else if("wav".equals(type)){
    				return true;
    			}
    			else if("au".equals(type)){
    				return true;
    			}
    			return false;	
    }   
}
