package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class EncryptDecriptImage {

    public static Font font = new Font("Roboto", Font.BOLD, 18);
    public static JTextField field = new JTextField(10);
    public static Logger log = LoggerFactory.getLogger(EncryptDecriptImage.class);
    public static String text;


    public static void main(String[] args) {

        /**
         * Create Frame
         */
        JFrame frame = new JFrame();
        frame.setTitle("Encript/Decript");
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /**
         * Create Button
         */
        JButton button = new JButton();
        button.setText("Chose Image");
        button.setFont(font);


        /**
         *  Create Text Field
         */
        field.setFont(font);

        frame.setLayout(new FlowLayout());
        frame.add(button);
        frame.add(field);

        /**
         * Button action
         */
        button.addActionListener(e -> {
            log.info("Button Clicked");
            text = field.getText();
            int key = Integer.parseInt(text);
            encriptDecript(key);
        });

        frame.setVisible(true);

    }

    public static void encriptDecript (Integer key){
        JFileChooser choser = new JFileChooser();
        choser.showOpenDialog(null);
        File file = choser.getSelectedFile();

        try {
            FileInputStream fileInputStream =new FileInputStream(file);
            byte[] datas = new byte[fileInputStream.available()];
            fileInputStream.read(datas);

            int i = 0;

            for (var data: datas) {
                System.out.print(data+", ");
                datas[i] = (byte) (data ^ key);
                i++;
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(datas);

            fileInputStream.close();
            fileOutputStream.close();

            JOptionPane.showMessageDialog(null, "Succes to Computed");
            System.out.println("");
            log.info("You'r Key is : "+text);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
