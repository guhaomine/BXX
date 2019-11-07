package com.service;
 
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
 
import javax.sound.sampled.*;
import javax.swing.JSlider;
import javax.swing.JTable;

import com.list.MusicList;
import com.list.ThreadList;
import com.list.ViewList;
import com.model.Model;
import com.model.Music;
import com.view.View;
 


/*"duration"	
"author"	
"title"	
"copyright"	
"date"	
"comment"*/
public class Player extends Thread{
	public Player(JSlider jSliderVolume,JSlider jSliderPlayProgress) {
		super();
		this.jSliderVolume = jSliderVolume;
		this.jSliderPlayProgress=jSliderPlayProgress;
	}

	private Player p;
	private long time = 0;
	// some lock somewhere...
	Object lock = new Object();
	// some paused variable   暂停
	private boolean paused = false;
	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	 private JSlider jSliderPlayProgress;//播放进度
	private boolean over = false;
	//是否播放下一曲
	private boolean isNext=true;
	AudioInputStream din = null;
	SourceDataLine line=null;
	private FloatControl volume = null;

	private JSlider jSliderVolume; 
	
	public JSlider getjSliderVolume() {
		return jSliderVolume;
	}

	public void setjSliderVolume(JSlider jSliderVolume) {
		this.jSliderVolume = jSliderVolume;
	
		
	}


	private Music music;
	
	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}
	
	   public FloatControl getVolume(){
	        return volume;
	    }

	   public void setVolume()
	    {
	    	if(line!=null)
	    	{
	    		if(line.isControlSupported(FloatControl.Type.MASTER_GAIN))
	         	{
	        		jSliderVolume.setEnabled(true);
	        		volume= (FloatControl)line.getControl( FloatControl.Type.MASTER_GAIN );
	        		jSliderVolume.setMinimum((int)volume.getMinimum());
	            	jSliderVolume.setMaximum((int)volume.getMaximum());
	            	volume.setValue((float)(volume.getMinimum()+(4*(volume.getMaximum()-volume.getMinimum()))/5));
	         	}
	    		System.out.println("1");
	        }
	        else
	        {
	         	volume=null;
	         	jSliderVolume.setEnabled(false);
	        System.out.println("2");
	        }	
	    }
	   
	   //播放音乐   
	public  void run(){
		
		AudioInputStream in=null;
		
		try {
			File file = new File(music.getPath());
			//播放不了的歌曲，直接下一首,并且在音乐列表中删除
			try {
				 in = AudioSystem.getAudioInputStream(file);
			} catch (Exception e) {
				//删除有点小问题
				MusicList.getList().remove(music.getId());				
				ViewList.getList().get(0).getJt().setModel(new Model());	
				nextmusic();
			}
			
			AudioFormat baseFormat = in.getFormat();
			AudioFormat decodedFormat = new AudioFormat(
					AudioFormat.Encoding.PCM_SIGNED,
					baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
					baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
					false);
			if(baseFormat.getEncoding()==AudioFormat.Encoding.PCM_UNSIGNED || baseFormat.getEncoding()==AudioFormat.Encoding.ULAW ||
					baseFormat.getEncoding()==AudioFormat.Encoding.ALAW || baseFormat.getEncoding()==AudioFormat.Encoding.PCM_SIGNED){
	             		time=(file.length()*8000000)/((int)(decodedFormat.getSampleRate()*baseFormat.getSampleSizeInBits()));
	            }else{
	                 int bitrate=0;
	                 if(baseFormat.properties().get("bitrate")!=null){
	                     //取得播放速度（）
	                     bitrate=(int)((Integer)(baseFormat.properties().get("bitrate")));
	                     if(bitrate!=0)
	                     time=(file.length()*8000000)/bitrate;
	                   
	                 }
	                 
	            }
			
			
			din = AudioSystem.getAudioInputStream(decodedFormat, in);
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, decodedFormat);
			line = (SourceDataLine) AudioSystem.getLine(info);
			line.open();
			setVolume();
			jSliderPlayProgress.setMaximum((int)time);
	    	jSliderPlayProgress.setValue(0);
			if(line!=null){
				line.open(decodedFormat);
				byte[] data = new byte[4096];
				int nBytesRead;
				
			synchronized (lock) {
				while ((nBytesRead = din.read(data, 0, data.length)) != -1) {
					//System.out.println(line.getMicrosecondPosition());
					while (paused) {
						if(line.isRunning()) {
							line.stop();
							System.out.println("暂停");
						}
						try {
							lock.wait();
							System.out.println("等待");
						}
						catch(InterruptedException e) {
						}
					}
					if(!line.isRunning()&&!over) {
						System.out.println("开始播放");
						line.start();
						
					}
					
					if (over&&line.isRunning()) {
						System.out.println("停止播放");
						jSliderPlayProgress.setValue(0);
						isNext=false;
						line.drain();
						line.stop();
						line.close();
					}
					
					jSliderPlayProgress.setValue((int)line.getMicrosecondPosition());
					line.write(data, 0, nBytesRead);
				}
		//根据播放模式选择下一首歌
				nextmusic();
			}		
		}			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(din != null) {
				try { din.close(); } catch(IOException e) { }
			}
		}
	}

	private void nextmusic() {
		String mode=Setting.getMode();
		if (isNext&&!mode.equals("one")) {//单曲播放就不执行
			int nextid=0;//将要播放的id
			int currentid=Integer.parseInt(this.music.getId());
			System.out.println(mode);
			if (mode.equals("default")&&(currentid==MusicList.getList().size()-1)){
				return;
			}
			
			if (mode.equals("rand")) {
				 Random random = new Random();
				 nextid=Math.abs(random.nextInt())%MusicList.getList().size();
			}else if (mode.equals("onecircle")) {
				nextid=currentid;
			}else if (mode.equals("default")&&!(currentid==MusicList.getList().size()-1)) {
				 nextid=currentid+1;
			}else if (mode.equals("morecircle")) {
				
				nextid=(currentid==MusicList.getList().size()-1)?0:currentid+1;
			}
			System.out.println(nextid+"----"+MusicList.getList().size());
			JTable jTable=ViewList.getList().get(0).getJt();
			if(nextid==0){//第一个
				
				jTable.setRowSelectionInterval(0,0);
				
			}else {
					jTable.setRowSelectionInterval(nextid-1,nextid); 
			}
				  this.stopplay();
				  ThreadList.getList().clear();
				  p=new Player(jSliderVolume,jSliderPlayProgress);
				  p.setMusic(MusicList.getList().get(nextid));
				  ThreadList.getList().add(p);
				  p.start();
		}
	}
	
	//开始
	public void startplay(){
		over=false;
	}

	//停止
	
	public void stopplay(){
		over=true;
	}
	// 暂停
	public void userPressedPause() {
	 paused = true;
	} 
	//继续
	public void userPressedPlay() {
			 synchronized(lock) {
				  paused = false;
				  lock.notifyAll();
		}
	}
	public void Pause(){
		if (paused) {
			 synchronized(lock) {
				  paused = false;
				  lock.notifyAll();
				 }
		}else{
			 paused = true;
		}
		
	}
	

	
 
}