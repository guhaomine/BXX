package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellRenderer;



import com.list.MusicList;
import com.list.ThreadList;
import com.list.ViewList;
import com.model.Model;
import com.model.Music;
import com.service.Player;
import com.service.Setting;
import com.util.DirInput;
import com.util.FileList;
import com.util.List_File;


public class View extends JFrame implements MouseListener,ActionListener,WindowListener {
	
	private JButton stop, open,del,next,pre;
	private  Player p;
	private JPanel[] jPanels;
	private MusicList list;
	private JScrollPane jsp;
	private JTable jt;
	private JRootPane j;
	private Model model;
	private JSlider jSliderVolume;
	private JSlider jSliderPlayProgress;
	private DirInput dirInput;
    private JMenuBar jb;
    private JMenu jm;
    private JMenuItem dm;
    private JComboBox jBox;
    
	public View(){
		System.out.println(ViewList.getList().size());
		if (ViewList.getList().size()==0) {
			Open();
		}
	}	
	private void Open() {
		
	//	this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		
		//this.setUndecorated(true);
       //菜单选项		
		jb=new JMenuBar();
		jm=new JMenu("打开");
		dm=new JMenuItem("文件夹");
		dm.addActionListener(this);
		jb.add(jm);
		jm.add(dm);
		//this.setJMenuBar(jb);	
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JPanel p3=new JPanel();		
		//增加菜单选项
		j=new JRootPane();		
		j.setJMenuBar(jb);
		
		
		p2.add(j);
		
		
		 open=new JButton("播放");
		 stop=new JButton("停止");
		 open.addMouseListener(this);
		 stop.addMouseListener(this);
		 pre=new JButton("上一首");
		 next=new JButton("下一首");
		 pre.addMouseListener(this);
		 next.addMouseListener(this);
		p1.setLayout(new GridLayout(2,1));		
		JPanel jPanel2=new JPanel();
		jPanel2.add(open);
		jPanel2.add(stop);
		jPanel2.add(pre);
		jPanel2.add(next);
		
		p1.add(jPanel2);	
		del=new JButton("删除");
		del.addMouseListener(this);
		
		 jSliderPlayProgress = new JSlider();   //播放进度条
	        jSliderPlayProgress.setValue(0);
	        jSliderPlayProgress.setEnabled(false);
	        jSliderPlayProgress.setPreferredSize(new Dimension(200, 20));
	        
	        
	        p1.add(jSliderPlayProgress);
	        
		
		jSliderVolume = new JSlider();   //音量进度条
		jSliderVolume.setValue(0);
		jSliderVolume.setPreferredSize(new Dimension(100, 20));//设置滚动条长度
	     
	     
	     
		jSliderVolume.addChangeListener(new ChangeListener()
	        {
	            public void stateChanged(ChangeEvent evt) 
	            {
	            	System.out.println(jSliderVolume.getValue());
	            	if (ThreadList.getList().size()!=0) {
	            		ThreadList.getList().get(0).getVolume().setValue((float)jSliderVolume.getValue());
					}
	            	
	            	
	            }
	        });
		String[] v={"顺序播放","随机播放","单曲循环"};
		
		jBox=new JComboBox(v);
		
		jBox.addActionListener(this);
	        
        p2.add(jBox);		
		p2.add(del);
		
		
		 p2.add(jSliderVolume);
		
		jPanels=new JPanel[list.getList().size()];
		
		for (int i = 0; i < list.getList().size(); i++) {
			
			Music music=list.getList().get(i);
			
			JPanel jPanel=new MyJPanel(music);
			
			
			JLabel jLabel=new JLabel(music.getName(),SwingConstants.CENTER);
			jLabel.setSize(300, 10);
			jLabel.setHorizontalTextPosition(JLabel.CENTER);			
			jPanel.setBackground(Color.WHITE);			
			jPanels[i]=jPanel;			
			jPanel.addMouseListener(this);			
			jPanel.add(jLabel);			
			p3.add(jPanel);			
		}
		
		
		p3.setBackground(Color.BLACK);
		
	    p3.setLayout(new GridLayout(10, 1));	
		p3.setSize(320, 500);		
		this.add(p1,BorderLayout.SOUTH);
		this.add(p2,BorderLayout.NORTH);
		
		
	        
	        
		
		
		model=new Model();			//添加表
		
		jt=new JTable(model){ // 设置jtable的单元格为透明的
			   public Component prepareRenderer(TableCellRenderer renderer,
					     int row, int column) {
					    Component c = super.prepareRenderer(renderer, row, column);
					    if (c instanceof JComponent) {
					     ((JComponent) c).setOpaque(false);
					    }
					    return c;
					   }
					  };;
					  
		jt.setOpaque(false);
		
		jt.setRowHeight(30);
		jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		jt.setShowHorizontalLines(false);
		jt.addMouseListener(this);
		
		jsp = new JScrollPane(jt);
		
		jsp.setOpaque(false);
		jsp.getViewport().setOpaque(false);	
		this.add(jsp,BorderLayout.CENTER);
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.addWindowListener(this);
		
      Image image=this.getToolkit().getImage("img/icon.jpg");
	    
	    this.setIconImage(image);
		
		this.setTitle("音乐播放器");
		
		  ImageIcon icon = new ImageIcon("img/bg.jpg"); 
		  JLabel lab = new JLabel(icon); // 将图片放入到jlabel中
		  lab.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight()); // 设置放有图片的label的位置
		  this.getContentPane().add(lab, -1); // jthis本身是窗体，不能放置任何组件，用getContentPane()方法得到this的默认内容面板，将lab放入其中，-1表示放入面板的下层
		  this.getContentPane().add(jsp, 0); //  0表示放在面板的最顶层
		  Container con = this.getContentPane();
		  ((JPanel)con).setOpaque(false); // 设置面板为透明的
		  p2.setOpaque(false);
         jSliderVolume.setOpaque(false);
         jPanel2.setOpaque(false);
         p1.setOpaque(false);
         p3.setOpaque(false);
         jSliderPlayProgress.setOpaque(false);
		this.setLocation(700, 200);
		this.setSize(337, 525);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {		
		if (e.getSource()==open) {			
			if (p==null) {//开始
			  p=new Player(jSliderVolume,jSliderPlayProgress);
			  p.setMusic(MusicList.getList().get(0));
			  jt.setRowSelectionInterval(0,0);
			  ThreadList.add(p);
			  open.setText("暂停");
			  p.start();
			}else{//继续
				if (ThreadList.getList().size()!=0) {
					p=ThreadList.getList().get(0);
				}				
				String s=p.isPaused()?"暂停":"播放";
				open.setText(s);
				p.Pause();
			}
			
		}else if (e.getSource()==stop) {
			if (ThreadList.getList().size()!=0) {
				p=ThreadList.getList().get(0);
			}
			if (p!=null) {
				p.stopplay();
				p=null;
				open.setText("播放");
			}
		
			
		}else if (e.getSource()==pre) {//上一首
			premusic();

		}else if (e.getSource()==next) {//下一首
			nextmusic();
		}else if (e.getSource()==del) {
			
			delmusic();
	  			
		}else if (e.getSource()==jt&&e.getClickCount()==2) {//双击
			
			clickmusic();
			
			
			
		}

		
	}

	private void clickmusic() {
		//双击Jtable
		System.out.println("点击了");
		
		int rowNum = this.jt.getSelectedRow();
		System.out.println(rowNum);
		if(rowNum == -1) {
			JOptionPane.showMessageDialog(this, "你没有选择一项");
			return;
		}
		ArrayList<Player> list=ThreadList.getList();
		 if (list.size()==0) {
			 
			 p=new Player(jSliderVolume,jSliderPlayProgress);
			  p.setMusic(MusicList.getList().get(rowNum));
			  ThreadList.add(p);
			  open.setText("暂停");
			  p.start();
		}else{
			System.out.println("停止");
		list.get(0).stopplay();
		list.clear();
		p=new Player(jSliderVolume,jSliderPlayProgress);
		    p.setMusic(MusicList.getList().get(rowNum));
		    open.setText("暂停");
		    list.add(p);
		    p.start();
		}
	}

	private void delmusic() {
		int rowNum = this.jt.getSelectedRow();		
		MusicList.getList().remove(rowNum);		
		System.out.println(MusicList.getList().size());		
		jt.setModel(new Model());		
		   ArrayList<Player> list=ThreadList.getList();
		   p=new Player(jSliderVolume,jSliderPlayProgress);
		   if (list.size()!=0) {
		    	list.get(0).stopplay();
				list.clear();
				open.setText("暂停");
				if(rowNum==0){
					System.out.println("第一个");
			  			    jt.setRowSelectionInterval(0,0);
						    p.setMusic(MusicList.getList().get(rowNum));					   
				}else if(rowNum==MusicList.getList().size()){
						System.out.println("最后一个");
			  			
			  			jt.setRowSelectionInterval(rowNum-2,rowNum-1);
						    p.setMusic(MusicList.getList().get(rowNum-1));
						    
				}else {					System.out.println("中间");
					
			  			jt.setRowSelectionInterval(rowNum-1,rowNum);
						p.setMusic(MusicList.getList().get(rowNum));
						    
				}
				 list.add(p);
				 p.start();
		   }
	}

	public JTable getJt() {
		return jt;
	}
//上一首
	private void premusic() {
		System.out.println("上一首");
		
		ArrayList<Player> list=ThreadList.getList();
		
		int id=Integer.parseInt(list.get(0).getMusic().getId());
		
		if(id!=0){
		if (id==1) {
			jt.setRowSelectionInterval(0,0);
		}else{
			jt.setRowSelectionInterval(id-2,id-1);
		}
		System.out.println(id);
		
		list.get(0).stopplay();
		list.clear();
		
		  p=new Player(jSliderVolume,jSliderPlayProgress);

		  p.setMusic(MusicList.getList().get(id-1));
		  System.out.println(id-1);
		  
		  open.setText("暂停");
		  list.add(p);
		  p.start();
		}
	}
//下一首
	private void nextmusic() {
		System.out.println("下一首");
		ArrayList<Player> list=ThreadList.getList();
		int id=Integer.parseInt(list.get(0).getMusic().getId());
		
		System.out.println(id);
		if(id!=MusicList.getList().size()-1){ 
			
		jt.setRowSelectionInterval(id,id+1);  
		
		list.get(0).stopplay();
		list.clear();
		
		p=new Player(jSliderVolume,jSliderPlayProgress);
		
		  p.setMusic(MusicList.getList().get(id+1));
		  System.out.println(id+1);
		  
		  open.setText("暂停");
		  list.add(p);
		  p.start();
}
	}
	
	private void addmusic(String path) {//增加MP3文件夹

		ArrayList<Music> musiclist=MusicList.getList();	
		    List_File fm = new List_File();
		    ArrayList<String[]> FileList = fm.serachFiles(path);
	        
	        for (int i = 0; i < FileList.size(); i++) {
	        	Music music= new Music();
		        	music.setId(musiclist.size()+"");
		        	String[] s=(String[]) FileList.get(i);
		        	
		        	music.setName(s[0]);
		        	music.setPath(s[1]);
	        	musiclist.add(music);
			}
	        
				jt.setModel(new Model());
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		 if (e.getSource()==dm) {		
			
			if(dirInput==null) dirInput = new DirInput(this);
			
			dirInput.open();
        	
        	File s=dirInput.getFile();
      	
        	if(s!=null){
        	    	
        	    	addmusic(s.getAbsolutePath());
        	    }		
		}else if (e.getSource()==jBox) {
			//顺序播放 (默认)default   随机rand      单曲循环 onecircle       列表循环 morecircle      单曲播放  one          
      if (ThreadList.getList().size()!=0) {
		p=ThreadList.getList().get(0);
	  }else {
		  p=new Player(jSliderVolume,jSliderPlayProgress);
		  ThreadList.getList().add(p);
	  }
      
      String[] s={"default","rand","onecircle"};
      
	  Setting.setMode(s[jBox.getSelectedIndex()]);
		
		
		}
	}



	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub		
		if (MusicList.getList().size()!=0) {
			//清空之前的内容
			FileList.clear("file/musiclist.txt");
			ArrayList<Music> list=MusicList.getList();
			for (int i = 0; i < list.size(); i++) {
				FileList.writeFile("file/musiclist.txt",list.get(i).getId()+","+list.get(i).getName()+","+list.get(i).getPath()+"\n");
			}			
		}			
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("open");
		
		File file=new File("file/musiclist.txt");
		
		if (file.exists()==false) {
			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else {
			FileList.readFileByLines("file/musiclist.txt");
			jt.setModel(new Model());
		}  	
	}

}
