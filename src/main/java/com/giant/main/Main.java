package com.giant.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class Main {

    static {
        a = "hehe";
    }
//    static String b= a +"哦哦哦";
    static String a = null;


    public static void main(String[] args) throws Exception {
        Main.class.getClassLoader().loadClass("com.giant.main.HelloJNI");
//        Class.forName("com.giant.main.HelloJNI");
        System.out.println(HelloJNI.class.getClassLoader().getClass());
        System.out.println(ClassLoader.getSystemClassLoader()==Main.class.getClassLoader());
        System.out.println(Object.class.getClassLoader());
    }

    static void print() throws Exception {
        int width = 80;
        int height = 32;
        //create the image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        // set the background color
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, width, height);
        // draw the border
        g.setColor(Color.black);
        g.drawRect(0, 0, width - 1, height - 1);
        // create a random instance to generate the codes
        Random rdm = new Random();
        String hash1 = Integer.toHexString(rdm.nextInt());
        System.out.println(hash1);
        // make some confusion
        for (int i = 0; i < 50; i++) {
            int x = rdm.nextInt(width);
            int y = rdm.nextInt(height);
//            g.drawOval(x, y, 0, 0);
        }
        // generate a random code
        String capstr = hash1.substring(0, 4);
//        session.setAttribute("key", capstr);
        g.setColor(new Color(0, 255, 0));
        g.setFont(new Font("Arial", Font.BOLD, 24));
//        System.out.println(Arrays.toString(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()));
        g.drawString(capstr, 8, 24);
        g.dispose();
//        response.setContentType("image/jpeg");
//        out.clear();
//        out = pageContext.pushBody();
//        OutputStream strm = response.getOutputStream();
        File strm = new File("capture.jpeg");
        ImageIO.write(image, "jpeg", strm);
//        strm.close();}
    }
}