package main;
import java.awt.*;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
@SuppressWarnings("serial")
public class CellRenderer extends DefaultListCellRenderer {
	public Component getListCellRendererComponent(JList<? extends Object> list,Object value,int index,boolean isSelected,boolean cellHasFocus) {
			setText(value.toString());		//设置文字
			String sss=value.toString();
			if(sss.endsWith(".dll")) {
			ImageIcon ico=new ImageIcon("src\\iconResource\\dll图标.PNG");		//实例化一个ImageIcon对象
			Image img=ico.getImage();		//实例化Image对象获取ico对象的内容 
			img=img.getScaledInstance(25,25,Image.SCALE_DEFAULT);
			ico.setImage(img);		//ImageIcon对象重新获取缩放后图标
			setIcon(ico);		//设置图标
			}else
			if(sss.endsWith(".index")||sss.endsWith(".jar")||sss.endsWith(".xz")||sss.endsWith(".Msi")||sss.endsWith(".class")||sss.endsWith(".classpath")||sss.endsWith(".project")||sss.endsWith(".log")||sss.endsWith(".lic")||sss.endsWith(".inf")||sss.endsWith(".cat")||sss.endsWith(".sys")||sss.endsWith(".list")||sss.endsWith(".dat")||sss.endsWith(".ini"))
			{
				ImageIcon ico=new ImageIcon("src\\iconResource\\未知文件图标.PNG");		//实例化一个ImageIcon对象
				Image img=ico.getImage();		//实例化Image对象获取ico对象的内容 
				img=img.getScaledInstance(25,25,Image.SCALE_DEFAULT);
				ico.setImage(img);		//ImageIcon对象重新获取缩放后图标
				setIcon(ico);		//设置图标
			}else if(sss.endsWith(".exe")||sss.endsWith(".cmd")||sss.endsWith(".bat")){
				ImageIcon ico=new ImageIcon("src\\iconResource\\exe文件图标.PNG");		//实例化一个ImageIcon对象
				Image img=ico.getImage();		//实例化Image对象获取ico对象的内容 
				img=img.getScaledInstance(25,25,Image.SCALE_DEFAULT);
				ico.setImage(img);		//ImageIcon对象重新获取缩放后图标
				setIcon(ico);		//设置图标
			}
			else if(sss.endsWith(".txt")){
				ImageIcon ico=new ImageIcon("src\\iconResource\\txt文件图标.PNG");		//实例化一个ImageIcon对象
				Image img=ico.getImage();		//实例化Image对象获取ico对象的内容 
				img=img.getScaledInstance(25,25,Image.SCALE_DEFAULT);
				ico.setImage(img);		//ImageIcon对象重新获取缩放后图标
				setIcon(ico);		//设置图标
			}
			else if(sss.endsWith(".doc")||sss.endsWith(".docx")){
				ImageIcon ico=new ImageIcon("src\\iconResource\\doc文件图标.PNG");		//实例化一个ImageIcon对象
				Image img=ico.getImage();		//实例化Image对象获取ico对象的内容 
				img=img.getScaledInstance(25,25,Image.SCALE_DEFAULT);
				ico.setImage(img);		//ImageIcon对象重新获取缩放后图标
				setIcon(ico);		//设置图标
			}
			else if(sss.endsWith(".zip")||sss.endsWith(".rar")){
				ImageIcon ico=new ImageIcon("src\\iconResource\\zip文件图标.PNG");		//实例化一个ImageIcon对象
				Image img=ico.getImage();		//实例化Image对象获取ico对象的内容 
				img=img.getScaledInstance(25,25,Image.SCALE_DEFAULT);
				ico.setImage(img);		//ImageIcon对象重新获取缩放后图标
				setIcon(ico);		//设置图标
			}
			else if(sss.endsWith(".png")||sss.endsWith(".bmp")||sss.endsWith(".jpeg")||sss.endsWith(".jpg")||sss.endsWith(".gif")||sss.endsWith(".PNG")){
				ImageIcon ico=new ImageIcon("src\\iconResource\\图片文件图标.PNG");		//实例化一个ImageIcon对象
				Image img=ico.getImage();		//实例化Image对象获取ico对象的内容 
				img=img.getScaledInstance(25,25,Image.SCALE_DEFAULT);
				ico.setImage(img);		//ImageIcon对象重新获取缩放后图标
				setIcon(ico);		//设置图标
			}
			else if(sss.endsWith(".mp4")||sss.endsWith(".avi")||sss.endsWith(".mov")||sss.endsWith(".mpeg")){
				ImageIcon ico=new ImageIcon("src\\iconResource\\视频文件图标.PNG");		//实例化一个ImageIcon对象
				Image img=ico.getImage();		//实例化Image对象获取ico对象的内容 
				img=img.getScaledInstance(25,25,Image.SCALE_DEFAULT);
				ico.setImage(img);		//ImageIcon对象重新获取缩放后图标
				setIcon(ico);		//设置图标
			}
			else if(sss.endsWith(".xlsx")){
				ImageIcon ico=new ImageIcon("src\\iconResource\\xlsx文件图标.PNG");		//实例化一个ImageIcon对象
				Image img=ico.getImage();		//实例化Image对象获取ico对象的内容 
				img=img.getScaledInstance(25,25,Image.SCALE_DEFAULT);
				ico.setImage(img);		//ImageIcon对象重新获取缩放后图标
				setIcon(ico);		//设置图标
			}
			else if(sss.endsWith(".pptx")){
				ImageIcon ico=new ImageIcon("src\\iconResource\\ppt文件图标.PNG");		//实例化一个ImageIcon对象
				Image img=ico.getImage();		//实例化Image对象获取ico对象的内容 
				img=img.getScaledInstance(25,25,Image.SCALE_DEFAULT);
				ico.setImage(img);		//ImageIcon对象重新获取缩放后图标
				setIcon(ico);		//设置图标
			}
			else if(sss.endsWith(".html")||sss.endsWith(".htm")){
				ImageIcon ico=new ImageIcon("src\\iconResource\\html文件图标.PNG");		//实例化一个ImageIcon对象
				Image img=ico.getImage();		//实例化Image对象获取ico对象的内容 
				img=img.getScaledInstance(25,25,Image.SCALE_DEFAULT);
				ico.setImage(img);		//ImageIcon对象重新获取缩放后图标
				setIcon(ico);		//设置图标
			}
			else if(sss.endsWith(".xml")){
				ImageIcon ico=new ImageIcon("src\\iconResource\\xml文件图标.jpg");		//实例化一个ImageIcon对象
				Image img=ico.getImage();		//实例化Image对象获取ico对象的内容 
				img=img.getScaledInstance(25,25,Image.SCALE_DEFAULT);
				ico.setImage(img);		//ImageIcon对象重新获取缩放后图标
				setIcon(ico);		//设置图标
			}
			else if(sss.endsWith(".cpp")){
				ImageIcon ico=new ImageIcon("src\\iconResource\\cpp文件图标.PNG");		//实例化一个ImageIcon对象
				Image img=ico.getImage();		//实例化Image对象获取ico对象的内容 
				img=img.getScaledInstance(25,25,Image.SCALE_DEFAULT);
				ico.setImage(img);		//ImageIcon对象重新获取缩放后图标
				setIcon(ico);		//设置图标
			}
			else 
			{
				ImageIcon ico=new ImageIcon("src\\iconResource\\文件夹图标.PNG");		//实例化一个ImageIcon对象
				Image img=ico.getImage();		//实例化Image对象获取ico对象的内容 
				img=img.getScaledInstance(25,25,Image.SCALE_DEFAULT);
				ico.setImage(img);		//ImageIcon对象重新获取缩放后图标
				setIcon(ico);		//设置图标
			}
			if(isSelected) {		//当某个元素被选中时
			setForeground(Color.WHITE);		//设置前景色（文字颜色）为白色
			setBackground(Color.gray);		//设置背景色为蓝色
		    } else {		                //某个元素未被选中时（取消选中）
			setForeground(Color.BLACK);		//设置前景色（文字颜色）为黑色
			setBackground(Color.WHITE);		//设置背景色为白色
		}
		return this;
	}
}