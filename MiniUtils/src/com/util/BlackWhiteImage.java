package com.util;
import java.awt.Image;  
import java.awt.color.ColorSpace;  
import java.awt.image.BufferedImage;  
import java.awt.image.ColorConvertOp;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
  
import javax.imageio.ImageIO;  
  
import com.sun.image.codec.jpeg.JPEGCodec;  
import com.sun.image.codec.jpeg.JPEGImageEncoder;  
  
/**
 * 图片转换成黑白图
 */
public class BlackWhiteImage {  
      
    public static void main(String[] args) {  
        File file=new File("D:\\3.png");  
        changeImge(file);         
    }  
      
    /** 
     * * 转换图片 * * 
     */  
    public static void changeImge(File img) {  
        try {  
            Image image = ImageIO.read(img);  
            int srcH = image.getHeight(null);  
            int srcW = image.getWidth(null);  
            BufferedImage bufferedImage = new BufferedImage(srcW, srcH,BufferedImage.TYPE_3BYTE_BGR);  
            bufferedImage.getGraphics().drawImage(image, 0,0, srcW, srcH, null);  
            bufferedImage=new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY),null).filter (bufferedImage,null);   
            
            ImageIO.write(bufferedImage, "jpg", new File("D:\\22.jpg"));
//            FileOutputStream fos = new FileOutputStream(new File("D:\\11.jpg"));  
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);  
//            encoder.encode(bufferedImage);  
//            fos.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
            throw new IllegalStateException("图片转换出错！", e);  
        }  
    }  
  
}  