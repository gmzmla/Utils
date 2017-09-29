package com.util.maoxiandao;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AIOperation implements Runnable{
    
    private Date date=new Date();
    
    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    private boolean on_off=false;  
    
    public AIOperation(boolean on_off){  
        this.on_off = on_off;  
    }  
    
    @Override
    public void run() {
        try {
            Robot robot = new Robot();
            int s=3;    //秒
            System.out.println(df.format(date));
            while(on_off){
                if(new Date().getTime()-date.getTime() >s*1000){
                   operationAI(robot);
                   date=new Date();
                }
                Thread.sleep(100);
                robot.keyPress(192);
                robot.keyRelease(192);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
    /**
     * 切换频道操作
     * @param robot
     * @throws Exception
     */
    private void operationAI(Robot robot) throws Exception{
        int a=5; //秒，飞行时间
        int b=10;   //秒，向右移动时间
        int c=10;   //秒,向上移动时间
        
        int x=392;    //X轴
        int y=579;    //Y轴
        int x1=392;   //选择频道
        int y1=579;   //选择频道
        
        /* *****************飞行 脱离战斗*******************/
        //按键6 code=54
        robot.keyPress(54);
        robot.keyRelease(54);
        Thread.sleep(a*1000);
        
        /* *****************移动鼠标切换频道*******************/
        // 鼠标移动到X,Y 打开频道选择
        robot.mouseMove(x,y);
        //左击鼠标
        robot.mousePress(InputEvent.BUTTON1_MASK);
        //释放左击 
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        Thread.sleep(2000);
        //选择频道
        robot.mouseMove(x1,y1);
        //左击鼠标
        robot.mousePress(InputEvent.BUTTON1_MASK);
        //释放左击 
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        
        /* ******************等待切换频道 ******************/
        Thread.sleep(5*1000);
        /* ******************飞行******************/
        //按键6 code=54
        robot.keyPress(54);
        robot.keyRelease(54);
        
        /* *****************飞行移动*******************/
        //按键→ code=39
        robot.keyPress(39);
        Thread.sleep(b*1000);
        robot.keyRelease(39);
        //按键↑ code=38
        robot.keyPress(38);
        Thread.sleep(c*1000);
        robot.keyRelease(38);
        
        /* ******************取消飞行******************/
        robot.keyPress(54);
        robot.keyRelease(54);
    }
}