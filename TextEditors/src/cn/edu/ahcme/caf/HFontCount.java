package cn.edu.ahcme.caf;

import java.awt.Font;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
public class HFontCount {
	public HFontCount(JTextArea textArea){
		String line=textArea.getText();
		JFrame f = new JFrame("统计");
		//弹出无提示图片的消息窗口
		JOptionPane.showMessageDialog(
                f,
                "字数："+countChinese(line)+"\n字符数："+line.length()+"\n行数："+textArea.getLineCount(),
                "统计",
                JOptionPane.PLAIN_MESSAGE
        );
	}
	public static int countChinese(String str) {
        int count = 0;
        //正则表达式匹配汉字|全角符号|数字|。|英文字母
        Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]|[\\uFE30-\\uFFA0]|[0-9]|[\\u3001-\\u3002]|[a-zA-Z]");
        Matcher m = p.matcher(str);
        while(m.find()){
            count++;
        }
        return count;
    } 
}
