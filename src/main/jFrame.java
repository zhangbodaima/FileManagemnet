package main;

import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import function.CopyFile;
import function.CreateFile;
import function.DeleteFile;
import function.EncryptionFile;
import function.ZipFileUtil;


public class jFrame extends JFrame {
    /*
     * 设置全局路径，通过此路径实现其他所有的文件操作
     */
    static Path path = new Path();
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                jFrame frame = new jFrame();
//                frame.add(jta,BorderLayout.SOUTH);
                frame.setVisible(true);
                JTextArea jta=new JTextArea();
                jta.append("双击鼠标左键可打开文件或文件夹，单击鼠标右键弹出功能菜单执行更多操作！\n");
                jta.append("您也可以点击选择文件/文件夹按钮，此时将弹出文件选择对话框JFileChooser\n如果您在JFileChooser中选择的是文件，将会打开文件；如果您选择的是文件夹，则会进入该文件夹下的目录.\n");
                jta.append("本文件管理器没有使用常见的JTree组件，而是另辟蹊径使用了JList与JFileChooser\n");
                jta.append("如果您在使用过程中遇到什么问题，请联系19计卓01班张博 qq:2737353951\n");
                JOptionPane.showMessageDialog(null, jta.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    	private JPanel InnerPane;
    	private JLabel label = new JLabel("请选择要进入的盘符：");
    	private JLabel label_1 = new JLabel("您当前所处的路径为：");
    	private jFrame() {
        InnerPane = new JPanel();
        InnerPane.setBorder(new TitledBorder(new EmptyBorder(22,22,22,22)," O(∩_∩)O双击可以打开文件，右键弹出菜单后可以执行更多操作，更多信息请点击帮助按钮，感谢您的使用与支持！O(∩_∩)O",TitledBorder.LEFT, TitledBorder.TOP,new Font("宋体",Font.PLAIN,12), Color.RED));
        setBackground(Color.white);
        setTitle("文件管理器v1.0  Copyright by ZhangBo");
        setBounds(300, 100, 1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setContentPane(InnerPane);
        //默认显示 
        JTextField lableForPath = new JTextField();
        lableForPath.setFont(new Font("楷书", Font.PLAIN, 13));
        //一个叫做contentPane的Jpanel，随后上面又放了一个panel(铺满了)，
        //然后又放了一个叫fileList的列表框，把fileList放到了panel上 
        //显示文件
        JPanel panel = new JPanel();
 
        JList fileList = new JList();
        fileList.setCellRenderer(new CellRenderer());
        
        fileList.setBackground(Color.white);
        fileList.setFont(new Font("宋体", Font.PLAIN, 20));
//      fileList.setVisibleRowCount(30);
        panel.setBackground(Color.white);
        InnerPane.add(panel, BorderLayout.CENTER);
        try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        
        fileList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    File mytempfile;
                    //如果此时位于盘符下
                    if (String.valueOf(path.getPath().charAt(path.getPath().length() - 1)).equals(File.separator)) {
                        mytempfile = new File(path.getPath() + fileList.getSelectedValue().toString());
                    //如果此时位于某一文件夹下
                    } else {                           //返回选中项对应的字符串值，返回的是一个对象 但调用了toString方法
                        mytempfile = new File(path.getPath() + File.separator + fileList.getSelectedValue().toString());
                    }
                    String strtmp=mytempfile.getAbsolutePath();
                    if(strtmp.endsWith("Msi")||strtmp.endsWith(" Information")||strtmp.endsWith("Recovery")||strtmp.endsWith(".sys")||strtmp.endsWith(".dll")||strtmp.endsWith(".db")||strtmp.endsWith("Documents and Settings")||fileList.getSelectedValue().toString().startsWith("S-1-5")||fileList.getSelectedValue().toString().startsWith("PerfLogs")) {
                    	JOptionPane.showMessageDialog(null,"对不起,此类型文件无法打开!","提醒",JOptionPane.WARNING_MESSAGE);
                    	return ;
                    }
                    if (mytempfile.isDirectory()) {
                    	//更新绝对路径
                        path.updatePath(fileList.getSelectedValue().toString());
                        //更新列表视图
                        ShowFile(fileList);
                        lableForPath.setText(path.getPath());
                        
                        //更改为打开文件  成功 嘿嘿
                    } else if (mytempfile.isFile()) {
//                        String pattern = "[\\w.]*\\.txt";
//                        if (Pattern.matches(pattern, mytempfile.getName())) {
//                            String str = JOptionPane.showInputDialog("请输入内容");
//                            Write write = new Write();
//                            if (write.write(mytempfile, str)) {
//                                ShowFile(fileList);
//                            }
//                        } else {
//                        	try {
//								java.awt.Desktop.getDesktop().open(mytempfile);
//							} catch (IOException e1) {
//								// TODO Auto-generated catch block
//								e1.printStackTrace();
//							}
//                            JOptionPane.showMessageDialog(null, "暂时无法写入该类型的文件");
//                        }
                    	try {
							java.awt.Desktop.getDesktop().open(mytempfile);
						} catch (IOException e1) {
							//  
							e1.printStackTrace();
						}
                    }
                }
            }
        });
        
        panel.add(fileList);
        File[] roots = File.listRoots();
        int rootlen=roots.length;
        //选择盘符的按钮
        JButton btnDiskC = new JButton("C盘");
        //btnDiskC.setBackground(Color.yellow);
        path.setPath("C:" + File.separator);
        lableForPath.setText((new File(path.getPath())).getAbsolutePath());
        ShowFile(fileList);
        
        btnDiskC.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                path.setPath("C:" + File.separator);
                lableForPath.setText((new File(path.getPath())).getAbsolutePath());
                ShowFile(fileList);
            }
        });
        
        JButton btnDiskD = new JButton("D盘");
       // btnDiskD.setBackground(Color.yellow);
        btnDiskD.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                path.setPath("D:" + File.separator);
                lableForPath.setText((new File(path.getPath())).getAbsolutePath());
                ShowFile(fileList);

            }
        });
        JButton btnDiskE = new JButton("E盘");
        if(rootlen>=3) {
        btnDiskE.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                path.setPath("E:" + File.separator);
                lableForPath.setText(String.valueOf((new File(path.getPath()))));
                ShowFile(fileList);
            }
        });
        }else {
        	btnDiskE.setVisible(false);
        }
       
        
        JButton btnDiskF = new JButton("F盘");
        if(rootlen>=4) {
        btnDiskF.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                path.setPath("F:" + File.separator);
                lableForPath.setText((new File(path.getPath())).getAbsolutePath());
                ShowFile(fileList);
            }}
        );
        }else {
        	btnDiskF.setVisible(false);
        }
        
        ;

//        File[] roots = File.listRoots();
//        if(roots.length<4) 
//        	btnDiskF.setEnabled(false);
//        else
//        	btnDiskF.setEnabled(true);
//        if(roots.length<3) 
//        	btnDiskE.setEnabled(false);
//        else
//       	btnDiskE.setEnabled(true);
        JButton btnBack = new JButton("<-返回上级目录");
        btnBack.addActionListener(e -> {
            File file = new File(path.getPath());
            if (file.getParentFile() != null) {
                path.returnBack();
                lableForPath.setText((new File(path.getPath())).getAbsolutePath());
                ShowFile(fileList);
            }
        });
        
        JButton chooseFileBtn=new JButton("选择文件/文件夹");
        chooseFileBtn.addMouseListener(new MouseAdapter() {
        @Override
                public void mouseClicked(MouseEvent arg0) {
               	JFileChooser jfc=new JFileChooser();
             	jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
             	int result=jfc.showDialog(new JLabel(), "选择");
             	File file=jfc.getSelectedFile();
             	if(result!=1&&file!=null) {
             	if(file.isDirectory()){
                System.out.println("文件夹:"+file.getAbsolutePath());
                path.updateByAbsolutePath(file.getAbsolutePath());
                 //更新列表视图
                ShowFile(fileList);
                    lableForPath.setText(path.getPath());
                }else if(file.isFile()){
                System.out.println("文件:"+file.getAbsolutePath());
                String strtmp1=file.getAbsolutePath();
                if(strtmp1.endsWith("Msi")||strtmp1.endsWith(" Information")||strtmp1.endsWith(".sys")||strtmp1.endsWith(".dll")||strtmp1.endsWith(".db")) {
                    JOptionPane.showMessageDialog(null,"对不起,此类型文件无法打开!","提醒",JOptionPane.WARNING_MESSAGE);
                 return ;}
                else {
                      try {
    					java.awt.Desktop.getDesktop().open(file);
    			      } catch (IOException e1) {
    							//  
    						e1.printStackTrace();
    						}
                        }
             		}
             		System.out.println(jfc.getSelectedFile().getName());
                }}
            });
  
        JButton helpButton=new JButton("帮助");
        {
        	helpButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent arg0) {
                    JTextArea jta=new JTextArea();
                    jta.append("双击鼠标左键可打开文件或文件夹，单击鼠标右键弹出功能菜单执行更多操作！\n");
                    jta.append("您也可以点击选择文件/文件夹按钮，此时将弹出文件选择对话框\n如果您在JFileChooser中选择的是文件，将会打开文件；如果您选择的是文件夹，则会进入该文件夹下的目录.\n");
                    jta.append("本文件管理器没有使用常见的JTree组件，而是另辟蹊径使用了JList与JFileChooser\n");
                    jta.append("如果您在使用过程中遇到什么问题，请联系19计卓01班张博 qq:2737353951\n");
                    JOptionPane.showMessageDialog(null, jta.getText());
                }});
        }
        
        GroupLayout contentPaneGroupLayout = new GroupLayout(InnerPane);
        		contentPaneGroupLayout.setHorizontalGroup(
                contentPaneGroupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(contentPaneGroupLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPaneGroupLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                .addGroup(contentPaneGroupLayout.createSequentialGroup()
                .addComponent(label)
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addComponent(btnDiskC)
                .addGap(18)
                .addComponent(btnDiskD)
                .addGap(18)
                .addComponent(btnDiskE)
                .addGap(18)
                .addComponent(btnDiskF)
                .addPreferredGap(ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                .addComponent(chooseFileBtn)//1
                .addGap(18)      //2
                .addComponent(helpButton)//1
                .addGap(18)      //2
                .addComponent(btnBack))
                .addGroup(contentPaneGroupLayout.createSequentialGroup()
                .addComponent(label_1)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(lableForPath, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)))
                .addContainerGap())
        );
        contentPaneGroupLayout.setVerticalGroup(
                contentPaneGroupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(contentPaneGroupLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPaneGroupLayout.createParallelGroup(Alignment.BASELINE)
                .addComponent(label)
                .addComponent(btnDiskC)
                .addComponent(btnDiskD)
                .addComponent(btnDiskE)
                .addComponent(btnDiskF)
                .addComponent(chooseFileBtn)//3
                .addComponent(helpButton)
                .addComponent(btnBack))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(contentPaneGroupLayout.createParallelGroup(Alignment.BASELINE)
                .addComponent(label_1)
                .addComponent(lableForPath, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 350, Short.MAX_VALUE)
                .addContainerGap())
        );

        JScrollPane scrollPane = new JScrollPane(fileList);
        //此处以下均为右键实现的功能
        JPopupMenu popupMenu = new JPopupMenu();
        addPopItem(fileList, popupMenu);
        JMenuItem openFile = new JMenuItem("打开");
        openFile.addActionListener(e-> {
        	 if(fileList.getSelectedValue()!=null) {
        		 File mytempfile;
                 //如果此时位于盘符下
                 if (String.valueOf(path.getPath().charAt(path.getPath().length() - 1)).equals(File.separator)) {
                     mytempfile = new File(path.getPath() + fileList.getSelectedValue().toString());
                 //如果此时位于某一文件夹下
                 } else {                           //返回选中项对应的字符串值，返回的是一个对象 但调用了toString方法
                     mytempfile = new File(path.getPath() + File.separator + fileList.getSelectedValue().toString());
                 }
                 String strtmp=mytempfile.getAbsolutePath();
                 if(strtmp.endsWith("Msi")||strtmp.endsWith(" Information")||strtmp.endsWith(".db")||strtmp.endsWith("Recovery")||strtmp.endsWith(".sys")||strtmp.endsWith(".dll")||strtmp.endsWith("Documents and Settings")||fileList.getSelectedValue().toString().startsWith("S-1-5")||fileList.getSelectedValue().toString().startsWith("PerfLogs")) {
                 	JOptionPane.showMessageDialog(null,"对不起,此类型文件无法打开!","提醒",JOptionPane.WARNING_MESSAGE);
                 	return ;
                 }
                 if (mytempfile.isDirectory()) {
                 	//更新绝对路径
                     path.updatePath(fileList.getSelectedValue().toString());
                     //更新列表视图
                     ShowFile(fileList);
                     lableForPath.setText(path.getPath());
                     
                     //更改为打开文件  成功 嘿嘿
                 } else if (mytempfile.isFile()) {
//                     String pattern = "[\\w.]*\\.txt";
//                     if (Pattern.matches(pattern, mytempfile.getName())) {
//                         String str = JOptionPane.showInputDialog("请输入内容");
//                         Write write = new Write();
//                         if (write.write(mytempfile, str)) {
//                             ShowFile(fileList);
//                         }
//                     } else {
//                     	try {
//								java.awt.Desktop.getDesktop().open(mytempfile);
//							} catch (IOException e1) {
//								// TODO Auto-generated catch block
//								e1.printStackTrace();
//							}
//                         JOptionPane.showMessageDialog(null, "暂时无法写入该类型的文件");
//                     }
                 	try {
							java.awt.Desktop.getDesktop().open(mytempfile);
						} catch (IOException e1) {
							//  
							e1.printStackTrace();
						}
                 }
        }else {
        	 JOptionPane.showMessageDialog(panel, "请先选中要打开的文件或文件夹!","提醒",JOptionPane.WARNING_MESSAGE);
        }
        	 
        });
        popupMenu.add(openFile);
        popupMenu.addSeparator();
        JMenuItem natureFile = new JMenuItem("属性");
        natureFile.addActionListener(e-> {
        	 if(fileList.getSelectedValue()!=null) {
        	 String chooseFileName = fileList.getSelectedValue().toString();
             File file = new File(path.getPath() + File.separator + chooseFileName);
             JTextArea jta = new JTextArea();
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             jta.append("文件名:        " + file.getName()+"\n");
             jta.append("是否可读:      " + file.canRead()+"\n");
             jta.append("是否可写:      " + file.canWrite()+"\n");
             jta.append("是否为目录:    "+file.isDirectory()+"\n");
             jta.append("是否为文件:    " + file.isFile()+"\n");
             jta.append("是否为隐藏文件:" + file.isHidden()+"\n");
             jta.append("最后修改时间:  " + sdf.format(new Date(file.lastModified()))+"\n");
             jta.append("文件长度:      " + file.length());
             JOptionPane.showMessageDialog(panel, jta.getText(),"文件属性",JOptionPane.INFORMATION_MESSAGE);
		}
        	 else {
        		 JOptionPane.showMessageDialog(panel, "请先选中文件或文件夹!","提醒",JOptionPane.WARNING_MESSAGE);
        	 }
        });
        popupMenu.add(natureFile);
        popupMenu.addSeparator();;
        
        JMenuItem itemForNewFile = new JMenuItem("新建文件");
        itemForNewFile.addActionListener(e -> {
            String str = JOptionPane.showInputDialog("请输入文件名(需要加后缀)");
            if (str != null) {
                try {
                    CreateFile createFile = new CreateFile();
                    if (createFile.createFile(new File(path.getPath() + File.separator + str))) {
                        ShowFile(fileList);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally {
                    ShowFile(fileList);
                }
            }
        });
        popupMenu.add(itemForNewFile);
    
        JMenuItem itemForNewFolder = new JMenuItem("新建文件夹");
        itemForNewFolder.addActionListener(e -> {
            String str = JOptionPane.showInputDialog("请输入文件夹名");
            CreateFile createFile = new CreateFile();
            if (str != null) {
                if (createFile.mkdir(new File(path.getPath() + File.separator + str + File.separator))) {
                    ShowFile(fileList);
                }
                ShowFile(fileList);
            }
        });
        popupMenu.add(itemForNewFolder);
        popupMenu.addSeparator();
        
        JMenuItem itemForDelete = new JMenuItem("删除");
        itemForDelete.addActionListener(e -> {
        	if(fileList.getSelectedValue()!=null) { 
        	int result=JOptionPane.showConfirmDialog(InnerPane, "确定要删除吗?","提示",JOptionPane.YES_NO_OPTION);
        	if(result==JOptionPane.YES_OPTION) {
            String chooseFileName = fileList.getSelectedValue().toString();
            File file = new File(path.getPath() + File.separator + chooseFileName);
            DeleteFile deleteFileFunc = new DeleteFile();
            if (file.exists() && file.isDirectory()) {
                deleteFileFunc.deleteDirectory(file);
            } else if (file.exists() && file.isFile()) {
                deleteFileFunc.deleteFile(file);
            }
           
            try {
                Thread.sleep(1);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            ShowFile(fileList);
        }
        	}
        	else {
        		JOptionPane.showMessageDialog(panel, "请先选择要删除的文件或文件夹!","提醒",JOptionPane.WARNING_MESSAGE);
        	}
        	});
        popupMenu.add(itemForDelete);
        popupMenu.addSeparator();
        
        /*
         * 复制粘贴
         * @param flag 控制paste的功能
         * @param copyPath 源文件的路径
         * @param pastePath 输入的路径
         */


        Path copyFilePath = new Path();
        Path copyFolderPath = new Path();
        //Path pastePath = new Path();
        JMenuItem itemForCopy = new JMenuItem("复制");
        JMenuItem itemForPaste = new JMenuItem("粘贴");
        itemForPaste.setEnabled(false);
        
        itemForCopy.addActionListener(e -> {
        	if(fileList.getSelectedValue()!=null) {
            String chooseFileName = fileList.getSelectedValue().toString();
            if (chooseFileName != null) {
                File file = new File(path.getPath() + File.separator + chooseFileName);
                if (file.exists() && file.isFile()) {
                    copyFilePath.setPath(file.getAbsolutePath());
                    itemForPaste.setEnabled(true);
                    copyFolderPath.setPath(null);
                } else if (file.exists() && file.isDirectory()) {
                    copyFolderPath.setPath(file.getAbsolutePath());
                    copyFilePath.setPath(null);
                    itemForPaste.setEnabled(true);
                }
            }
        }
        	else {
        		JOptionPane.showMessageDialog(null, "请先选中要复制的文件或文件夹!","提醒",JOptionPane.WARNING_MESSAGE);
        	}
        });

        itemForPaste.addActionListener(e -> {

            File file = new File(path.getPath());

            if (copyFilePath.getPath() != null) {
                File copyName = new File(copyFilePath.getPath());
                try {
                    CopyFile copy = new CopyFile();
                    CopyFile.copyFile(copyFilePath.getPath(), file.getAbsolutePath() + File.separator + copyName.getName());
                    ShowFile(fileList);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            } else if (copyFolderPath.getPath() != null) {
                try {
                    File file1 = new File(path.getPath());
                    CopyFile copy = new CopyFile();
                    CopyFile.copyDir(copyFolderPath.getPath(), file1.getAbsolutePath());
                    System.out.println(path.getPath());
                    ShowFile(fileList);

                } catch (IOException e1) {

                    e1.printStackTrace();
                } catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });


        popupMenu.add(itemForCopy);
   
        popupMenu.add(itemForPaste);
        popupMenu.addSeparator();
      
        JMenuItem addPassword = new JMenuItem("加密");
        addPassword.addActionListener(e -> {
        	if(fileList.getSelectedValue()!=null) {
            String str = path.getPath() + File.separator + fileList.getSelectedValue().toString();
            File file = new File(str);
            if (file.isDirectory() && file.exists()) {
                JOptionPane.showMessageDialog(null, "暂时无法加密文件夹！");
            } else if (file.exists() && file.isFile()) {
            	int confirmRes=JOptionPane.showConfirmDialog(null, "确认要加密吗？(加密或解密大文件时用时可能较长，请耐心等待!)","提示", JOptionPane.YES_NO_OPTION);
            	if(confirmRes==JOptionPane.NO_OPTION) return;
            	if(confirmRes==JOptionPane.YES_OPTION) {
                EncryptionFile mytempfile = new EncryptionFile();
                try {
                    mytempfile.encrypt(file);
                    System.out.println("加密成功");
                    JOptionPane.showMessageDialog(null, "加密成功!请牢记文件所在位置！");
                    ShowFile(fileList);
                } catch (Exception e1) {
                    System.out.println("加密失败");
                    e1.printStackTrace();
                }
            }
            }
        	}
        	else {
        		JOptionPane.showMessageDialog(null, "请先选中要加密的文件或文件夹!","提醒",JOptionPane.WARNING_MESSAGE);
        	}
        }
        );
        popupMenu.add(addPassword);
        JMenuItem mntmDecrypted = new JMenuItem("解密");
        mntmDecrypted.addActionListener(e -> {
        	if(fileList.getSelectedValue()!=null) {
            String str = path.getPath() + File.separator + fileList.getSelectedValue().toString();
            File file = new File(str);
            if (file.isDirectory() && file.exists()) {
                JOptionPane.showMessageDialog(null, "无法解密的类型");
            } else if (file.exists() && file.isFile()) {
                EncryptionFile mytempfile = new EncryptionFile();
                try {
                    mytempfile.decrypt(file);
                    System.out.println("解密成功");
                    JOptionPane.showMessageDialog(null, "解密成功!");
                    ShowFile(fileList);
                } catch (Exception e1) {
                    System.out.println("解密失败");
                    e1.printStackTrace();
                }
            }
        }
        	else {
        		JOptionPane.showMessageDialog(null, "请先选中要解密的文件或文件夹!","提醒",JOptionPane.WARNING_MESSAGE);
        	}
        });
        popupMenu.add(mntmDecrypted);
        popupMenu.addSeparator();
        JMenuItem mntmCompress = new JMenuItem("压缩");
        mntmCompress.addActionListener(e -> {
        	if(fileList.getSelectedValue()!=null) {
            String str = path.getPath() + File.separator + fileList.getSelectedValue().toString();
            File file = new File(str);
            String name = JOptionPane.showInputDialog("请输入压缩文件的名称(无需后缀名)");
            try {
                if (name!=null&&!name.equals("")) {
                    ZipFileUtil zipUtil = new ZipFileUtil();
                    if (zipUtil.zipMultiFile(file, name, true)) {
                        JOptionPane.showMessageDialog(null, "压缩成功");
                        ShowFile(fileList);
                    } else {
                        JOptionPane.showMessageDialog(null, "压缩失败");
                    }
                } else {
                    System.out.println("取消压缩");
                }
            } catch (Exception e1) {

                e1.printStackTrace();
            }
        }else {
    		JOptionPane.showMessageDialog(null, "请先选中要压缩的文件或文件夹!","提醒",JOptionPane.WARNING_MESSAGE);
    	}
        });
        popupMenu.add(mntmCompress);
        //解压按钮以及监听事件
        JMenuItem mntmDecompress = new JMenuItem("解压");
        mntmDecompress.addActionListener(e -> {
        	if(fileList.getSelectedValue()!=null) {
            String str = path.getPath() + File.separator + fileList.getSelectedValue().toString();
            File file = new File(str);
            
            if (file.isFile() && file.getName().endsWith(".zip")||file.getName().endsWith(".rar")) {
                try {
                    if (file.isFile()) {
                        String out = JOptionPane.showInputDialog("请输入解压后文件夹的名称");
                        CreateFile createFile = new CreateFile();
                        createFile.mkdir(new File(path.getPath() + File.separator + out));
                        if (new ZipFileUtil().unZip(file, new File(path.getPath() + File.separator + out))) {
                            JOptionPane.showMessageDialog(null, "解压成功");
                            ShowFile(fileList);
                        } else {
                            JOptionPane.showMessageDialog(null, "解压失败");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "不是压缩文件！");
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "不是压缩文件！");
            }
        }
        	else {
        		JOptionPane.showMessageDialog(null, "请先选中要解压的文件或文件夹!","提醒",JOptionPane.WARNING_MESSAGE);
        	}
        });
        popupMenu.add(mntmDecompress);
        

        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.TRAILING)
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)//水平布局
        );
        gl_panel.setVerticalGroup(
        		gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup().addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 538, GroupLayout.PREFERRED_SIZE).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);
        InnerPane.setLayout(contentPaneGroupLayout);
    }
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void ShowFile(JList list1) {
        if (path.fileList() != null) {
            String[] strings = path.fileList();
            System.out.println(path.getPath());
            list1.setListData(strings);
        }
    }
    private void addPopItem(Component component, final JPopupMenu popup) {
        component.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }
            private void showMenu(MouseEvent e) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }
}
