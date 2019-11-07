package com.model;
import javax.swing.*;

import java.util.*;
import javax.swing.table.*;

import com.list.MusicList;

public class Model extends AbstractTableModel{

	//初始
	Vector rowData, columnNames;
	JTable jt = null;
	JScrollPane jsp = null;	
	private MusicList list;
	public Model() {
		columnNames = new Vector();
		//音乐列表
		columnNames.add("音乐列表");

		rowData = new Vector();
			
			for (int i = 0; i < list.getList().size(); i++) {
				Vector hang=new Vector();
				String num=i<10?"0"+(i+1):(i+1)+"";
				hang.add(num+"  "+list.getList().get(i).getName());
				rowData.add(hang);
			}
	}
	

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.size();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int column) {
		// TODO Auto-generated method stub
		return ((Vector)this.rowData.get(rowIndex)).get(column);
	}

	@Override
	public String getColumnName(int arg0) {
		// TODO Auto-generated method stub
		return (String) this.columnNames.get(arg0);
	}
	

}
