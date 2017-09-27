package com.util.maoxiandao;

public class Monitor {
    public Monitor()  {    
        boolean [] on_off={true};  
//        new Thread(new ProcessInfo(on_off)).start();  
        new Thread(new KeyboardHook(on_off)).start();  
        
    }  
      
    public static void main(String[] args)  {    
        new Monitor();    
    }    
}
