package com.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 把图片 打出到控制台
 * @author Administrator
 *
 */
public class writingUtil {
    public static void main(String[] args) {
        File file = new File("D:\\2.png");  
        try {
            BufferedImage image = ImageIO.read(file);
            int y=image.getHeight();
            int x=image.getWidth();
            for(int i=0;i<y;i++){
                for(int j=0;j<x;j++){
                    int rgb=image.getRGB(j,i);
                    if((rgb & 0xff0000) >> 16 <=150&&(rgb & 0xff00) >> 8 <=255 && (rgb & 0xff) <=255){
                        System.out.print("*");
                    }else{
                        System.out.print("-");
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}
