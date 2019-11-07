package com.util;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

/**
 *class FileInput 获得歌曲文件
 */
public class FileInput
{
   private JFileChooser fdialog=null;
   private File f[];
   private String name[];
   private JFrame jf;
   public FileInput(JFrame jf)
   {    
       this.jf=jf;
       fdialog=new JFileChooser("D:");
       fdialog.setFileFilter(new FileFilter() {
		
		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return ".mp3";
		}
		
		@Override
		public boolean accept(File f) {
			// TODO Auto-generated method stub
			if (f.isDirectory())return true;
			return f.getName().endsWith(".mp3");
		}
		
	});
       fdialog.setAcceptAllFileFilterUsed(false);
       
		 fdialog.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	       fdialog.setMultiSelectionEnabled(true);
      
   }
   public void open()
   {
      f=null;
      int result = fdialog.showOpenDialog(jf);
      if(result == fdialog.APPROVE_OPTION){
    	  
    	  f=fdialog.getSelectedFiles();
    	  
      }
   }
   public String[] getFileNames()
   {
   		name=null;
   		if(f!=null)
   		{
   			name=new String[f.length];
   		    for(int i=0;i<f.length;i++)
   		    name[i]=f[i].getPath();
   		}
   		return name;
   }
   public File[] getFiles()
   {
   		return f;
   }
   
}
