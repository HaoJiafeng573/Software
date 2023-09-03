import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class car {
    public static void main(String[] args) {//1、创建一个window对象，因为panel以及其他的容器都不能独立存在，必须依附于window上

        JFrame frame = new JFrame("车牌号识别");//2、创建一个Panel对象

        Panel p = new Panel();//3、创建一个文本框和一个按钮，并把它们放到Panel容器中

        JFileChooser jc = new JFileChooser();//文件选择器

        JLabel logoLabel = new JLabel();//创建标签

        TextField name = new TextField();//结果显示文本框


        Button b1 = new Button("上传图片");

        b1.addActionListener(new ActionListener() {//为按钮b设置触发事件
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                //jc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                jc.showOpenDialog(null);//文件打开对话框
                File f = jc.getSelectedFile();
                String s = f.getAbsolutePath();//获得路径
                String end = LicensePlate.licensePlate(s);
                name.setText(end);
                ImageIcon logoIcon = new ImageIcon(s);
                logoIcon.setImage(logoIcon.getImage().getScaledInstance(300, 250, Image.SCALE_DEFAULT));
                logoLabel.setIcon(logoIcon);

            }
        });
        p.add(logoLabel);
        p.add(b1);
        p.add(new TextField("识别结果："));
        p.add(name);


        //4、把panel放到window中
        frame.add(p);
        //frame.add(logoLabel);
        //5、设置window的位置以及大小
        frame.setBounds(100, 100, 600, 400);
        //6、设置window可见
        frame.setVisible(true);
    }
}
