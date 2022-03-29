package cn.edu.ahcme.caf;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
public class TestGui extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private int flagFile=0;
	public TestGui() {
		JFrame f = new JFrame("文本编辑器");
        f.setSize(800, 600);
        f.setLocation(200, 200);
 
        JMenuBar mb = new JMenuBar();
        //菜单栏
        JMenu mFile = new JMenu("文件 (F)");
        mFile.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        JMenu mEdit = new JMenu("编辑 (E)");
        mEdit.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        JMenu mForm = new JMenu("格式 (O)");
        mForm.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        JMenu mHelp = new JMenu("帮助 (H)");
        mHelp.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        
        // 菜单项
        JMenuItem NewFile =new JMenuItem("新建(N)");
        NewFile.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        mFile.add(NewFile);
        
        JMenuItem OpenFile=new JMenuItem("打开(O)");
        OpenFile.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        mFile.add(OpenFile);
        
        JMenuItem SaveFile=new JMenuItem("保存(S)");
        SaveFile.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        mFile.add(SaveFile);
        
        JMenuItem mnCut = new JMenuItem("剪切(T)");
		mnCut.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		mEdit.add(mnCut);
		
		JMenuItem mnCopy = new JMenuItem("复制(C)");
		mnCopy.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		mEdit.add(mnCopy);
		
		JMenuItem mnPaste = new JMenuItem("粘贴(P)");
		mnPaste.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		mEdit.add(mnPaste);
		
		JMenuItem mnDelete = new JMenuItem("删除(D)");
		mnDelete.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		mEdit.add(mnDelete);
        
        JMenuItem Search=new JMenuItem("查找(F)");
        Search.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        mEdit.add(Search);
        
        JMenuItem Replace=new JMenuItem("替换(G)");
        Replace.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        mEdit.add(Replace);
        
        JCheckBoxMenuItem FontChange=new JCheckBoxMenuItem("大小写转换(R)");
        FontChange.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        mEdit.add(FontChange);
        
        JMenuItem FontCount=new JMenuItem("统计(W)");
        FontCount.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        mEdit.add(FontCount);
        
        JMenuItem SetFont=new JMenuItem("字体设置");
        SetFont.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        mForm.add(SetFont);
        
        JMenuItem SetBackColor=new JMenuItem("设置背景颜色");
        SetBackColor.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        mForm.add(SetBackColor);
        
        JMenuItem About=new JMenuItem("关于文本编辑器");
        About.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        mHelp.add(About);
        
        JTextArea textArea = new JTextArea();
        //文本框自动换行
        textArea.setLineWrap(true);
      	//设置文本框字体
      	textArea.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 26));
        JPanel panel = new JPanel(new BorderLayout());//使用边界布局
        JLabel label=new JLabel(" ");
		label.setHorizontalAlignment(label.RIGHT);//右对齐
		panel.add(label,BorderLayout.SOUTH);//向窗口添加状态栏标签
		//实例化滚动面板
        JScrollPane scrollPane = new JScrollPane();
        //显示垂直滚动条
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //文本框填入滚动面板
		scrollPane.setViewportView(textArea);
		//动态面板添加入窗口
		f.getContentPane().add(scrollPane);
		panel.add(scrollPane,BorderLayout.CENTER);
		f.setContentPane(panel);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//设置文本框内容改变的监听器
		textArea.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				flagFile=1;
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				flagFile=1;
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO 自动生成的方法存根
			}
		});
		
		//查找的监听器
		Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HSearchText(f,textArea);
			}
		});
		
		//替换的监听器
		Replace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HReplaceText(f,textArea);
			}
		});
		
		
		//新建文件的监听器
		NewFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HFile hFile=new HFile();
				if(flagFile==1){
		            int result=JOptionPane.showConfirmDialog(f, 
		            		"是否保存更改?", 
		            		"记事本", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		            if(result==JOptionPane.OK_OPTION){
		            	hFile.saveFile(f, textArea);//另存为 
		            	
		            }else if(result==JOptionPane.NO_OPTION){
		                textArea.setText("");
		            }
		            flagFile=0;
				}
				else {
					textArea.setText("");
					f.setTitle("新建记事本");
				}
			}
		});
		
		//打开文件的监听器
		OpenFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HFile hFile=new HFile();
				if(flagFile==1) {
					int result=JOptionPane.showConfirmDialog(f, 
		            		"是否保存更改?", 
		            		"记事本-保存", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		            if(result==JOptionPane.OK_OPTION){
		            	hFile.saveFile(f, textArea);
		            	flagFile=0;
		            }
		            else if(result==JOptionPane.NO_OPTION)	
		            	flagFile=0;
				}
				else if(flagFile==0) {
					hFile.openFile(f, textArea);
				}
					
			}
		});
		//保存文件的监听器
		SaveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new HFile().saveFile(f, textArea);
            	flagFile=0;
            }
        });
		//统计的监听器
		FontCount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new HFontCount(textArea);	
			}
		});
		
		//字体设置的监听器
		SetFont.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new HSetFont(f,textArea);
			}
		});
		//大小写转换的监听器
		FontChange.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            	if(FontChange.isSelected()) {
            		String string=textArea.getText();
            		char[] chars = string.toCharArray();
                    for (int i = 0, length = chars.length; i < length; i++) {
                        char c = chars[i];
                        //判断字母是不是大写，如果是大写变为小写
                        if (Character.isUpperCase(c)){
                            chars[i] = Character.toLowerCase(c);
                            continue;
                        }
                        //如果为小写，变为大写
                        chars[i] = Character.toUpperCase(c);
                    }
                    String str1 = new String(chars);
                	textArea.setText(str1);
            	}
            }
        });
		
        //设置背景颜色的监听器
        SetBackColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(f, "选取颜色", null);
				// 如果用户取消或关闭窗口, 则返回的 color 为 null
				if (color == null) {
					return;
				}
				textArea.setBackground(color);
			}
		});
        
        //关于文本编辑器的监听器
        About.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f = new JFrame("关于文本编辑器");
				//弹出无提示图片的消息窗口
                JOptionPane.showMessageDialog(
                        f,
                        "软件测试实训制作",
                        "文本编辑器",
                        JOptionPane.PLAIN_MESSAGE
                );
			}
		});
        
        //窗口关闭的监听器，提示保存
        f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO 自动生成的方法存根
			}
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO 自动生成的方法存根
			}
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO 自动生成的方法存根
			}
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO 自动生成的方法存根
			}
			@Override
			public void windowClosing(WindowEvent e) {
				HFile hFile=new HFile();
				if(flagFile==1){
		            int result=JOptionPane.showConfirmDialog(f, 
		            		"是否将更改保存到新建记事本?", 
		            		"记事本", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		            if(result==JOptionPane.OK_OPTION){
		            	hFile.saveFile(f, textArea);//另存为 
		            	flagFile=0;
		            }
				}
			}
			@Override
			public void windowClosed(WindowEvent e) {
				
			}
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO 自动生成的方法存根
			}
		});
        
        textArea.addKeyListener(new KeyAdapter() {
        //按下某键
        	public void keyPressed(KeyEvent e) {
        		int row = 0;
        		int col = 0;
        		int pos = textArea.getCaretPosition(); //获得光标位置            
        		try {
        			row = textArea.getLineOfOffset(pos) + 1; //返回行从0算起,要+1
        			col = pos-textArea.getLineStartOffset(row-1)+1; 
        		}catch(Exception exception) {
            	
        		}
        		label.setText("第" + row+"行，" +"第"+col+"列      " ); 
        }
        
        	// 释放某键
        	public void keyReleased(KeyEvent e) {
        		int row = 0;
        		int col = 0;
        		int pos = textArea.getCaretPosition(); //获得光标位置            
        		try {
        			row = textArea.getLineOfOffset(pos) + 1; //返回行从0算起,要+1
        			col = pos-textArea.getLineStartOffset(row-1)+1; 
        		}catch(Exception exception) {
        			
        		}
        		label.setText("第" + row+"行，" +"第"+col+"列      " ); 
        		}
        });
        
        //新建文件的快捷键
        NewFile.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N,
        		ActionEvent.CTRL_MASK));
        NewFile.setMnemonic('N');
        //打开文件的快捷键
        OpenFile.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O,
        		ActionEvent.CTRL_MASK));
        OpenFile.setMnemonic('O');
        //保存文件的快捷键
        SaveFile.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S,
        		ActionEvent.CTRL_MASK));
        SaveFile.setMnemonic('S');
        //统计字数的快捷键
        FontCount.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, 
        		ActionEvent.CTRL_MASK));
        FontCount.setMnemonic('W');
        
		/*
		 * JMenuItem mnPaste = new JMenuItem("粘贴(P)"); mnPaste.setMnemonic('P');
		 * mnPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.
		 * CTRL_MASK)); mnEdit.add(mnPaste);
		 */
        
        //剪切的快捷键
		mnCut.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X,
	                ActionEvent.CTRL_MASK));
	        mnCut.setMnemonic('X');
        
        //复制的快捷键
	        mnCopy.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C,
	                ActionEvent.CTRL_MASK));
	        mnCopy.setMnemonic('V');
	    //粘贴的快捷键
	        mnPaste.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V,
	                ActionEvent.CTRL_MASK));
	        mnPaste.setMnemonic('P');
	    //删除
	        mnDelete.setMnemonic('D');
			mnDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0));
			mEdit.add(mnDelete);
        //查找的快捷键
        Search.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F,
                ActionEvent.CTRL_MASK));
        Search.setMnemonic('F');
        //替换的快捷键
        Replace.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G,
        		ActionEvent.CTRL_MASK));
        Replace.setMnemonic('G');
        //大小写转换的快捷键
        FontChange.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R,
        		ActionEvent.CTRL_MASK));
        FontChange.setMnemonic('R');
        
        //载入4个一级菜单
        mb.add(mFile);
        mb.add(mEdit);
        mb.add(mForm);
        mb.add(mHelp);
        
        f.setJMenuBar(mb);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
	
    public static void main(String[] args) {
    	new TestGui();
    }
}


