package com.util;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * 使用java 操作剪切板
 */
public class CopyPasteOperation {
     public static void main(String[] args) {
         Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();  //获取系统剪切板
         copy(clipboard,"aaaaa");
         System.out.println(paste(clipboard));
    }
     
     /**
      * 往剪切板写入文本
      */
     public static void copy(Clipboard clipboard,String str){
         Transferable tText = new StringSelection(str);   
         clipboard.setContents(tText, null); 
     }
     
     /**
      * 粘贴剪切板文本
      */
     public static String paste(Clipboard clipboard){
         Transferable clipT = clipboard.getContents(null); //获取文本中的Transferable对象  
         //1.stringFlavor：字符串数据     2.imageFlavor：图片数据
         if(clipT!=null&&clipT.isDataFlavorSupported(DataFlavor.stringFlavor)){   //判断粘贴的是否是文本
            try {
                return (String)clipT.getTransferData(DataFlavor.stringFlavor); //返回粘贴里的数据  
            } catch (UnsupportedFlavorException | IOException e) {
                e.printStackTrace();
            } 
         }
         return "";
     }
}
