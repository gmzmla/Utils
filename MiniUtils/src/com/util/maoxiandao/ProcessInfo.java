package com.util.maoxiandao;

import java.io.BufferedReader;  
import java.io.BufferedWriter;  
import java.io.File;  
import java.io.FileWriter;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.text.SimpleDateFormat;  
import java.util.Date;  

public class ProcessInfo implements Runnable{
    private boolean [] on_off=null;  
    
    public ProcessInfo(boolean [] on_off){  
        this.on_off = on_off;  
    }  
  
    public void run() {  
        BufferedReader input = null;  
        Process process = null;  
        BufferedWriter bw=null;  
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");  
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        String fileName=null;  
        String time=null;  
        try {  
            while(on_off[0]){  
                fileName=df1.format(new Date());  
                time=df2.format(new Date());  
                bw=new BufferedWriter(new FileWriter(new File("D://log//"+fileName+"_ProcessInfo.txt"),true));  
                Thread.sleep(60000);  
                process = Runtime.getRuntime().exec("cmd.exe   /c   tasklist");  
                input =new BufferedReader(  
                        new InputStreamReader(process.getInputStream()));  
                String line = " ";  
                int i=0;  
                input.readLine();  
                input.readLine();  
                input.readLine();  
                while ((line = input.readLine()) != null) {  
                    System.out.println(time+"  ####  "+line+"\r\n");
                    bw.write(time+"  ####  "+line+"\r\n");  
                    bw.flush();  
                    i++;  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally{  
            try {  
                bw.close();  
                input.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}
