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

public class HReplaceText {
	public HReplaceText(JFrame f,JTextArea textArea) {
		JDialog jdlg = new JDialog(f, "替换", true);
        jdlg.setSize(453, 200);
        jdlg.setLocationRelativeTo(null);
        jdlg.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        JPanel panel=new JPanel();
        panel.setLayout(null);
        JLabel label=new JLabel("查找内容：");
        JLabel label2=new JLabel("替换为：");
        JLabel count=new JLabel("找到0个");
        JTextArea ftext=new JTextArea();
        JTextArea rtext=new JTextArea();
        JButton findnext=new JButton("查找下一个");
        JButton replace=new JButton("替换");
        JButton allreplace=new JButton("全部替换");
        JButton no=new JButton("取消");
        JCheckBox matchcase=new JCheckBox("区分大小写");
        //创建两个单选按钮
        JRadioButton up = new JRadioButton("向上替换");
        JRadioButton down = new JRadioButton("向下替换");
        //创建按钮组，把两个单选按钮添加到该组
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(up);
        btnGroup.add(down);
        // 设置第一个单选按钮选中
        up.setSelected(true);
        
        label.setBounds(20,20,93,22);
        label2.setBounds(20,45,93,22);
        count.setBounds(20, 55, 80, 85);
        ftext.setBounds(100,20,200,22);
        rtext.setBounds(100,48,200,22);
        findnext.setBounds(320, 18, 105, 32);
        replace.setBounds(320, 55, 105, 32);
        allreplace.setBounds(320, 92, 105, 32);
        no.setBounds(320, 128, 105, 32);
        matchcase.setBounds(15, 120, 100, 55);
        up.setBounds(130, 120, 80, 55);
        down.setBounds(230, 120, 80, 55);
        
        //初始化按键状态
        findnext.setEnabled(false);
        replace.setEnabled(false);
        allreplace.setEnabled(false);
        
        //查找文本框的监听器，改变按钮状态
        ftext.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(ftext.getText().equals("")) {
		        	findnext.setEnabled(false);
		        	replace.setEnabled(false);
		            allreplace.setEnabled(false);
				}
				
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				findnext.setEnabled(true);
				replace.setEnabled(true);
		        allreplace.setEnabled(true);
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO 自动生成的方法存根
			}
		});
        //查找下一个的监听器
        findnext.addActionListener(new ActionListener() {
			@Override
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
        
        //替换的监听器
        replace.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (rtext.getText().length() == 0 && textArea.getSelectedText() != null) {
					textArea.replaceSelection("");
				}
				if (rtext.getText().length() > 0 && textArea.getSelectedText() != null) {
					textArea.replaceSelection(rtext.getText());
				}
			}
		});
        //替换全部的监听器
        allreplace.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//显示关键字的总数量
				int c=0;
				Pattern p=Pattern.compile(ftext.getText());
				Matcher m=p.matcher(textArea.getText());
				while(m.find()) {
					c++;
				}
				count.setText("替换"+c+"个");
				//替换全部关键字
				String str=textArea.getText();
				str=str.replace(ftext.getText(), rtext.getText());
				textArea.setText(str);
				
			}
		});
        
        //取消的监听器
        no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jdlg.setVisible(false);
			}
		});
        
        label.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        count.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        ftext.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        rtext.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        findnext.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
        replace.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
        allreplace.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
        no.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
        
        panel.add(label);
        panel.add(label2);
        panel.add(count);
        panel.add(ftext);
        panel.add(rtext);
        panel.add(findnext);
        panel.add(no);
        panel.add(replace);
        panel.add(allreplace);
        panel.add(matchcase);
        panel.add(up);
        panel.add(down);
        
        jdlg.setResizable(false);
        jdlg.setContentPane(panel);
        jdlg.setVisible(true);
	}
}
