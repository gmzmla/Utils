package com.util.maoxiandao;

import java.awt.Robot;

public class MapleStory2 implements Runnable{
    
    private boolean on_off=false;  
    
    public MapleStory2(boolean on_off){  
        this.on_off = on_off;  
    }  
    
    @Override
    public void run() {
        try {
            Robot robot = new Robot();
            while(on_off){
                Thread.sleep(1000);
                robot.keyPress(192);
                robot.keyRelease(192);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
}