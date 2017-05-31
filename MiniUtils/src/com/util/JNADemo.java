package com.util;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class JNADemo {
    
    //继承Library，用于加载库文件
    public interface Clibrary extends Library{  
        //加载DllDemo链接库 
        Clibrary INSTANTCE = (Clibrary) Native.loadLibrary("DllDemo", Clibrary.class); 
        //此方法为链接库中的方法
        void removePwd(String file,String pwd);
    }  
    
    public static void main(String[] args){
        //调用方法
        Clibrary.INSTANTCE.removePwd("C:/work/20170526165416.db","LoveR1314");
    }
}
