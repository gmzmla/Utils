package com.util;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
/**
 * 截取图片，像素对比，控制鼠标
 * 以下例子：魔兽世界钓鱼操作
 * @author gmzmla
 */
public class WOWFishing {
	public static void main(String[] args) {
		try {
			Robot robot = new Robot();
			imageThread(robot);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static void imageThread(Robot robot){
	    //扫描高度
		int height=400;
		//扫描宽度
		int width=700;
		// 左上角的 X 坐标
		int x=611;
		// 左上角的 Y 坐标
		int y=360;
		BufferedImage image=null;
		Long data=System.currentTimeMillis();
			while(true){
				System.gc();
				image =robot.createScreenCapture(new Rectangle(x,y, width, height));	//截图
				b:for(int i=1;i<image.getWidth();i++){
					for(int j=1;j<image.getHeight();j++){
//						System.out.println("---搜索："+(x+i)+","+(y+j));
						int rgb=image.getRGB(i,j);
						System.out.println((System.currentTimeMillis()-data)/1000);
						//超过20秒没钓到鱼就重新释放技能
						if((System.currentTimeMillis()-data)/1000 >20){
							hideMove(robot);
							data=System.currentTimeMillis();
							System.out.println("---超时1");
							break b;
						}
						//判断是否是鱼漂颜色
						if((rgb & 0xff0000) >> 16 >=100&&(rgb & 0xff0000) >> 16 <=159  &&(rgb & 0xff00) >> 8 >=24 &&(rgb & 0xff00) >> 8 <=60
	                            &&(rgb & 0xff) <30 && (rgb & 0xff) >10){
//							System.out.println("-------------------------------------找到鱼漂---鱼漂位置:"+(x+i)+","+(y+j)+"鱼漂颜色："+((rgb & 0xff0000) >> 16) +","+((rgb & 0xff00) >> 8)+","+(rgb & 0xff));
							robot.mouseMove((x+i+15), (y+j));
							while(true){
								boolean bool=true;
								BufferedImage image2 =robot.createScreenCapture(new Rectangle((x+i-5), (y+j-5),20,10));	//截图鱼漂位置平方
								a:for(int i1=1;i1<image2.getWidth();i1++){
									for(int j1=1;j1<image2.getHeight();j1++){
										System.out.println((System.currentTimeMillis()-data)/1000);
										//超过20秒没钓到鱼就重新释放技能
										if((System.currentTimeMillis()-data)/1000 >20){
											hideMove(robot);
											System.out.println("---超时1");
											break b;
										}
										int rgb1=image2.getRGB(i1, j1);
										if((rgb1 & 0xff0000) >> 16 >=100&&(rgb1 & 0xff0000) >> 16 <=159  &&(rgb1 & 0xff00) >> 8 >=24 &&(rgb1 & 0xff00) >> 8 <=60
					                            &&(rgb1 & 0xff) <30 && (rgb1 & 0xff) >10){
											bool=false;
											break a;
										}
									}
								}
								if(bool){	//收杆操作
									
									// 鼠标移动到X,Y
									robot.mouseMove(x+i+20, y+j);
									// 右击鼠标
									robot.mousePress(InputEvent.BUTTON3_MASK);
									// 释放右击
									robot.mouseRelease(InputEvent.BUTTON3_MASK);
//									System.out.println("-------------------------------------移动鼠标到" + (x*1+i*1) + "," + (y*1+j*1));
									try {
										Thread.sleep(1000); // 暂停1秒
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									hideMove(robot);	//重新释放钓鱼技能
									try {
										Thread.sleep((int)(1000+Math.random()*(3000-1000))); // 暂停1秒
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									break b;
								}
								System.out.println((System.currentTimeMillis()-data)/1000);
								//超过20秒没钓到鱼就重新释放技能
								if((System.currentTimeMillis()-data)/1000 >15){
									hideMove(robot);
									System.out.println("---超时2");
									break b;
								}

							}
						}
					}
					//超过20秒没钓到鱼就重新释放技能
					if((System.currentTimeMillis()-data)/1000 >20){
						hideMove(robot);
						System.out.println("---超时3");
						break b;
					}
				}
			}
	}

	/**
	 * 重新释放钓鱼技能
	 * @param robot
	 */
	public static void hideMove(Robot robot){
		//技能坐标
		int jnX=1801;
		int jnY=398;
		// 鼠标移动到X,Y
		robot.mouseMove(jnX,jnY);
		//左击鼠标
		robot.mousePress(InputEvent.BUTTON1_MASK);
		//释放左击 
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
//		System.out.println("移动鼠标到" + jnX + "," + jnY);
	}
}
