package com.util.maoxiandao;

import java.awt.Robot;

public class MapleStory2 {
    
    public static void main(String[] args) throws Exception {
        Robot robot = new Robot();
        Thread.sleep(5000);
        robot.keyPress(99);
        robot.keyRelease(99);
    }
    
}
