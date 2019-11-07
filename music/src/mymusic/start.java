package mymusic;

import javax.swing.UIManager;

import com.list.ViewList;
import com.view.View;

public class start {

	public static void main(String[] args) {
		if (ViewList.getList().size()==0) {
			
		 View v=new View();
		 
		 ViewList.add(v);
		 
		 //设置风格
			try
	    	{
	    		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        }catch(Exception exception){
	        	exception.printStackTrace();
	        }
		 
		}
		
		System.out.println("启动");
	}
}
