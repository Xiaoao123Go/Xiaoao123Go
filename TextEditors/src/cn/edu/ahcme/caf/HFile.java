package cn.edu.ahcme.caf;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class HFile {
	
	public void openFile(JFrame f,JTextArea textArea) {
		// 创建一个默认的文件选取器
        JFileChooser fileChooser = new JFileChooser();
        // 设置默认显示的文件夹为当前文件夹
        fileChooser.setCurrentDirectory(new File("."));
        // 设置文件选择的模式（只选文件、只选文件夹、文件和文件均可选）
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // 设置是否允许多选
        fileChooser.setMultiSelectionEnabled(false);
        // 设置默认使用的文件过滤器
        fileChooser.setFileFilter(new FileNameExtensionFilter("txt(*.txt)", "txt"));
        // 打开文件选择框
        int result = fileChooser.showOpenDialog(f);
        if(result==1)
        	return ;
        if (result == JFileChooser.APPROVE_OPTION) {
        	try {  
        		textArea.setText("");
        		File file = fileChooser.getSelectedFile();
        		FileInputStream fis=new FileInputStream(file);
                try{
                    //读取数据，并将读取到的数据存储到数组中
                    byte[] data = new byte[1024]; //数据存储的数组
                    int i = fis.read(data);
                    //解析数据
                    String s = new String(data,0,i);
                    textArea.append(s);
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    try{
                        fis.close();
                    }catch(Exception e){}
                }
                f.setTitle(file.getName()+" - 文本编辑器");
        	} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void saveFile(JFrame f,JTextArea textArea) {
		// 创建一个默认的文件选取器
        JFileChooser fileChooser = new JFileChooser();
        // 设置默认显示的文件夹为当前文件夹
        fileChooser.setCurrentDirectory(new File("."));
        // 设置打开文件选择框后默认输入的文件名
        fileChooser.setSelectedFile(new File("新建记事本.txt"));
        // 打开文件选择框
        int select = fileChooser.showSaveDialog(f);
        if (select == JFileChooser.APPROVE_OPTION) {
            // 保存文本内容
            File file = fileChooser.getSelectedFile();
            try {
            	//覆盖文件式写入
            	OutputStream outToFileEnd = new FileOutputStream(file);
                String string = textArea.getText();
                byte[] bs = string.getBytes();
                outToFileEnd.write(bs);
                outToFileEnd.close();
                f.setTitle(file.getName()+" - 文本编辑器");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
	}
}
