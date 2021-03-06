package com.util.maoxiandao;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.BufferedWriter;  
import java.io.File;  
import java.io.FileWriter;  
import java.io.IOException;  
import java.text.SimpleDateFormat;  
import java.util.Date;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.KBDLLHOOKSTRUCT;
import com.sun.jna.platform.win32.WinUser.LowLevelKeyboardProc;
import com.sun.jna.platform.win32.WinUser.MSG;  


public class Keyboard implements Runnable{
    private static HHOOK hhk;  
    private static LowLevelKeyboardProc keyboardHook;  
    private Thread th=new Thread(new AIOperation(true));
    final static User32 lib = User32.INSTANCE;  
    private boolean [] on_off=null;  
  
    public Keyboard(boolean [] on_off){  
        this.on_off = on_off;  
    }  
    
    public void run() {  
        HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null);  
        keyboardHook = new LowLevelKeyboardProc() {  
            public LRESULT callback(int nCode, WPARAM wParam, KBDLLHOOKSTRUCT info) { 
                
                if(info.flags==0&&info.vkCode==27){ //停止线程
                    System.out.println("停止线程");
                    th.stop();
                    try {
						Robot robot = new Robot();
						robot.keyRelease(192);
					} catch (AWTException e) {
						e.printStackTrace();
					}
                    return null;
                }
                if(info.flags!=0||info.vkCode!=192){
                    return null;
                }
                if(!th.isAlive()){
                    th=new Thread(new AIOperation(true));
                    th.start();
                }
                SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");  
                SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                String fileName=df1.format(new Date());  
                String time=df2.format(new Date());  
                BufferedWriter bw1=null;  
                try {  
                    bw1=new BufferedWriter(new FileWriter(new File("D://log//"+fileName+"_Keyboard.txt"),true));  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
                if (on_off[0] == false) {  
                    System.exit(0);  
                }  
                try {  
                    bw1.write(time+"  ####  "+info.vkCode+"\r\n");  
                    System.out.println(info.vkCode);
                    bw1.flush();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
                return lib.CallNextHookEx(hhk, nCode, wParam, info.getPointer());  
            }  
        };  
        hhk = lib.SetWindowsHookEx(User32.WH_KEYBOARD_LL, keyboardHook, hMod, 0);  
        int result;  
        MSG msg = new MSG();  
        while ((result = lib.GetMessage(msg, null, 0, 0)) != 0) {  
            if (result == -1) {  
                System.err.println("error in get message");  
                break;  
            } else {  
                System.err.println("got message");  
                lib.TranslateMessage(msg);  
                lib.DispatchMessage(msg);  
            }  
        }  
        lib.UnhookWindowsHookEx(hhk);  
    }  
}
