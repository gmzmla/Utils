package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.jdesktop.swingx.util.OS;

/**
 * 基于tesseract-OCR 的图文识别，使用前必须安装好tesseract-OCR
 * 
 * 中文识别包http://note.youdao.com/groupshare/?token=69E4E031CB234B0A9E55CC21DAD0A41B&gid=4383092
 * 
 */
public class ImageOCR {
	private static final String LANG_OPTION = "-l";  
    private static final String EOL = System.getProperty("line.separator");  
    /** 
     * tesseract 安装目录 
     */  
    private static String tessPath = "C:\\Program Files (x86)\\Tesseract-OCR";  
  
    /** 
     * @param imageFile 
     *            传入的图像文件 
     * @return 识别后的字符串 
     */  
    public static String recognizeText(File imageFile) throws Exception  
    {  
        /** 
         * 设置输出文件的保存的文件目录 
         */  
        File outputFile = new File(imageFile.getParentFile(), "output");  
  
        StringBuffer strB = new StringBuffer();  
        List<String> cmd = new ArrayList<String>();  
        if (OS.isWindowsXP())  
        {  
            cmd.add(tessPath + "\\tesseract");  
        } else if (OS.isLinux())  
        {  
            cmd.add("tesseract");  
        } else  
        {  
            cmd.add(tessPath + "\\tesseract");  
        }  
        cmd.add("");  
        cmd.add(outputFile.getName());  
        cmd.add(LANG_OPTION);  
        cmd.add("eng"); 	//英文识别包
//        cmd.add("chi_sim");	//中文语言识别包，并不好用
//        cmd.add("num");  
        
  
        ProcessBuilder pb = new ProcessBuilder();  
        /** 
         *Sets this process builder's working directory. 
         */  
        pb.directory(imageFile.getParentFile());  
        cmd.set(1, imageFile.getName());  
        pb.command(cmd);  
        pb.redirectErrorStream(true);  
        Process process = pb.start();  
        // tesseract.exe 1.jpg 1 -l chi_sim  
        // Runtime.getRuntime().exec("tesseract.exe 1.jpg 1 -l chi_sim");  
        /** 
         * the exit value of the process. By convention, 0 indicates normal 
         * termination. 
         */  
        int w = process.waitFor();  
        if (w == 0)// 0代表正常退出  
        {  
            BufferedReader in = new BufferedReader(new InputStreamReader(  
                    new FileInputStream(outputFile.getAbsolutePath() + ".txt"),  
                    "UTF-8"));  
            String str;  
  
            while ((str = in.readLine()) != null)  
            {  
                strB.append(str).append(EOL);  
            }  
            in.close();  
        } else  
        {  
            String msg;  
            switch (w)  
            {  
            case 1:  
                msg = "错误的文件访问. 你的图像文件名中可能有空格。";  
                break;  
            case 29:  
                msg = "Cannot recognize the image or its selected region.";  
                break;  
            case 31:  
                msg = "Unsupported image format.";  
                break;  
            default:  
                msg = "Errors occurred.";  
            }  
            throw new RuntimeException(msg);  
        }  
        return strB.toString();  
    }  
    
    public static void main(String[] args) {
    	try {
			System.out.println(recognizeText(new File("D://1.tif")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
