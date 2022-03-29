package cn.edu.ahcme.caf;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class HSearchText {
	public HSearchText(JFrame f,JTextArea textArea) {
		JDialog jdlg = new JDialog(f, "查找", true);
        jdlg.setSize(453, 150);
        jdlg.setLocationRelativeTo(null);
        jdlg.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        JPanel panel=new JPanel();
        panel.setLayout(null);
        JLabel label=new JLabel("查找内容：");
        JLabel count=new JLabel("找到0个");
        JTextArea ftext=new JTextArea();
        JButton findnext=new JButton("查找下一个");
        JButton no=new JButton("取消");
        JCheckBox matchcase=new JCheckBox("区分大小写");
        //创建两个单选按钮
        JRadioButton up = new JRadioButton("向上查找");
        JRadioButton down = new JRadioButton("向下查找");
        //创建按钮组，把两个单选按钮添加到该组
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(up);
        btnGroup.add(down);
        // 设置第一个单选按钮选中
        up.setSelected(true);
        
        label.setBounds(20,20,93,22);
        count.setBounds(20, 55, 80, 35);
        ftext.setBounds(100,20,200,22);
        findnext.setBounds(320, 18, 105, 32);
        no.setBounds(320, 53, 105, 32);
        matchcase.setBounds(15, 90, 100, 25);
        up.setBounds(130, 90, 80, 25);
        down.setBounds(230, 90, 80, 25);
        
        //初始化查找按键
        findnext.setEnabled(false);
        //设置文本框和按钮的状态
        ftext.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(ftext.getText().equals(""))
		        	findnext.setEnabled(false);
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				findnext.setEnabled(true);
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO 自动生成的方法存根
			}
		});
		//查找下一个的监听器
        findnext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int c=0;
				int a = 0, b = 0;
				int FindStartPos = textArea.getCaretPosition();
                String strA,strB;
                // 选中区分大小写,大小写转换
                if (matchcase.isSelected()) {
                    strA = textArea.getText();
                    strB = ftext.getText();
                } else {
                    strA = textArea.getText().toLowerCase();
                    strB = ftext.getText().toLowerCase();
                }
                //向上查找，否则向下查找
                if (up.isSelected()) {
                	a = strA.lastIndexOf(strB, FindStartPos - ftext.getText().length() - 1);
                } else if (down.isSelected()) {
                	a = strA.indexOf(strB, FindStartPos - ftext.getText().length() + 1);
                }
                //查找到边界
                if (a > -1) {
            		if (up.isSelected()) {
            	  		textArea.setCaretPosition(a);
            	  		b = ftext.getText().length();
            	  		textArea.select(a, a + b);
            	  	} else if (down.isSelected()) {
            	  		textArea.setCaretPosition(a);
            	  		b = ftext.getText().length();
            	  		textArea.select(a, a + b);
            		}
            	} else {
            	  		JOptionPane.showMessageDialog(null, "找不到查找的内容", 
            	  				"查找", JOptionPane.INFORMATION_MESSAGE);
            	}
                //显示关键字的总数量
				Pattern p=Pattern.compile(ftext.getText());
				Matcher m=p.matcher(textArea.getText());
				while(m.find()) {
					c++;
				}
				count.setText("找到"+c+"个");
			}
		});
        //取消的监听器
        no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jdlg.setVisible(false);
			}
		});
        
        label.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        count.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        ftext.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        findnext.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
        no.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
        
        panel.add(label);
        panel.add(count);
        panel.add(ftext);
        panel.add(findnext);
        panel.add(no);
        panel.add(matchcase);
        panel.add(up);
        panel.add(down);
        
        jdlg.setResizable(false);
        jdlg.setContentPane(panel);
        jdlg.setVisible(true);
	}
}
