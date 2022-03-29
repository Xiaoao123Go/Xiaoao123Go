package cn.edu.ahcme.caf;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HSetFont extends JDialog{
	public HSetFont(JFrame f,JTextArea textArea) {
		
		//获取系统字体库
    	GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = e.getAvailableFontFamilyNames();
        String[] fontStyle=new String[]{"常规","粗体","斜体","粗斜体"};
        Integer[] fontsize=new Integer[] {8,9,10,11,12,13,14,16,18,20,22,24,26,28,30,32,36,48,72};
        
        JDialog jdlg = new JDialog(f, "字体设置", true);
        jdlg.setSize(453, 325);
        jdlg.setLocationRelativeTo(null);
        jdlg.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        
        JPanel panel=new JPanel();
        panel.setLayout(null);
        
        JButton yes=new JButton("确定");
        JButton no=new JButton("取消");
        JButton setfontcolor=new JButton("字体颜色");
        JLabel fonts=new JLabel("字体:");
        JLabel style=new JLabel("样式:");
        JLabel size=new JLabel("大小:");
        JLabel cg=new JLabel("AaBbCc");
        
        // 创建下拉列表框
        final JComboBox<String> comboBox = new JComboBox<String>(fontNames);
        final JComboBox<String> comboBox2=new JComboBox<String>(fontStyle);
        final JComboBox<Integer> comboBox3=new JComboBox<Integer>(fontsize);

        
        
        // 添加条目选中状态改变的监听器
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // 只处理选中的状态
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    cg.setFont(new Font((String) comboBox.getSelectedItem(), 
                    		cg.getFont().getStyle(), cg.getFont().getSize()));
                }
            }
        });
        comboBox2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					if(comboBox2.getSelectedItem().equals("常规")) {
						cg.setFont(new Font(cg.getFont().getName(), Font.PLAIN, 
								cg.getFont().getSize()));
					}
					else if(comboBox2.getSelectedItem().equals("粗体")){
						cg.setFont(new Font(cg.getFont().getName(), Font.BOLD, 
								cg.getFont().getSize()));
					}
					else if(comboBox2.getSelectedItem().equals("斜体")){
						cg.setFont(new Font(cg.getFont().getName(), Font.ITALIC, 
								cg.getFont().getSize()));
					}
					else if(comboBox2.getSelectedItem().equals("粗斜体")) {
						cg.setFont(new Font(cg.getFont().getName(), Font.BOLD+ Font.ITALIC, 
								cg.getFont().getSize()));
					}
				}
			}
		});
        comboBox3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					cg.setFont(new Font(cg.getFont().getName(), cg.getFont().getStyle(), 
							(int) comboBox3.getSelectedItem()));
					
				}
			}
		});
        
        setfontcolor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(f, "选取颜色", null);
				// 如果用户取消或关闭窗口, 则返回的 color 为 null
				if (color == null) {
					return;
				}
				textArea.setForeground(color);
			}
		});
        
        yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setFont(cg.getFont());
				jdlg.setVisible(false);
			}
        });
        
        no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jdlg.setVisible(false);
			}
		});
        
        //设置元件位置和宽高
        fonts.setBounds(20,20,45,18);
        style.setBounds(190,20,45,18);
        size.setBounds(313,20,45,18);
        comboBox.setBounds(20,42,170,22);
        comboBox2.setBounds(190,42,119,22);
        comboBox3.setBounds(309, 42, 119, 22);
        cg.setBounds(30,89,378,137);
        setfontcolor.setBounds(20,249,102,30);
        yes.setBounds(300,249,62,30);
        no.setBounds(366,249,62,30);
        
        fonts.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        style.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        size.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        cg.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
        setfontcolor.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
        yes.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
        no.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
        comboBox.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        comboBox2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        comboBox3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
 
        // 设置字体下拉框为文本框的字体样式
        comboBox.setSelectedItem(textArea.getFont().getFontName());
        comboBox3.setSelectedItem(textArea.getFont().getSize());
        comboBox2.setSelectedIndex(textArea.getFont().getStyle());
        // 添加到内容面板
        panel.add(fonts);
        panel.add(style);
        panel.add(size);
        panel.add(comboBox);
        panel.add(comboBox2);
        panel.add(comboBox3);
        panel.add(setfontcolor);
        panel.add(yes);
        panel.add(no);
        panel.add(cg);
             
        jdlg.setResizable(false);
        jdlg.setContentPane(panel);
        jdlg.setVisible(true);    
    }
}

