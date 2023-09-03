import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class id {
    public static void main(String[] args) {
        //1、创建一个window对象，因为panel以及其他的容器都不能独立存在，必须依附于window上
        JFrame frame=new JFrame("身份证识别");
        //2、创建一个Panel对象
        Panel p =new Panel();
        //3、创建一个文本框和一个按钮，并把它们放到Panel容器中
        JFileChooser jc=new JFileChooser();

        JLabel logoLabel = new JLabel();

        TextField name=new TextField();
        TextField mingzu=new TextField();
        TextField dizhi=new TextField();
        TextField idcard=new TextField();
        TextField brithday=new TextField();
        TextField sex=new TextField();

        Button b1=new Button("选择图片");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

              //jc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
              jc.showOpenDialog(null);
                File f =  jc.getSelectedFile();
                String s = f.getAbsolutePath();
                JSONObject jb1=Idcard.idcard(s);

                JSONObject jb2=jb1.getJSONObject("姓名");
                name.setText(jb2.getString("words"));
                jb2=jb1.getJSONObject("民族");
                mingzu.setText(jb2.getString("words"));
                jb2=jb1.getJSONObject("住址");
                dizhi.setText(jb2.getString("words"));
                jb2=jb1.getJSONObject("公民身份号码");
                idcard.setText(jb2.getString("words"));
                jb2=jb1.getJSONObject("出生");
                brithday.setText(jb2.getString("words"));
                jb2=jb1.getJSONObject("性别");
                sex.setText(jb2.getString("words"));

                ImageIcon logoIcon = new ImageIcon(s);
                logoIcon.setImage(logoIcon.getImage().getScaledInstance(300,250,Image.SCALE_DEFAULT));
                logoLabel.setIcon(logoIcon);

            }
        });
        p.add(logoLabel);
        p.add(b1);
        p.add(new TextField("识别结果："));
        p.add(name);
        p.add(mingzu);
        p.add(dizhi);
        p.add(idcard);
        p.add(brithday);
        p.add(sex);
        //4、把panel放到window中
        frame.add(p);
        //frame.add(logoLabel);
        //5、设置window的位置以及大小
        frame.setBounds(100, 100, 600, 400);
        //6、设置window可见
        frame.setVisible(true);
    }
}
