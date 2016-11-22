package com.util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
/**
 * 通过java代码模拟访问网站
 * 
 * 以下例子：访问页面是否存在订单，如果存在播放音乐文件
 * @author gmzmla
 */

public class httpAccess{
   
    public static void main(String[] args) {
    	WebClient myClient = new WebClient(BrowserVersion.FIREFOX_24);	//模仿火狐访问
    	//设置禁止css和js执行，加快页面载入速度
    	myClient.getOptions().setCssEnabled(false);
    	myClient.getOptions().setJavaScriptEnabled(false);    	
    	try {
    		fangf(myClient);
		} catch (FailingHttpStatusCodeException | IOException e1) {
			e1.printStackTrace();
		}finally {
			myClient.closeAllWindows();
		}
    }
    
    public static void fangf(WebClient myClient) throws FailingHttpStatusCodeException, MalformedURLException, IOException{
    	while (true) {
	    	HtmlPage mypage=myClient.getPage("http://www.yc5151.com/Views/Web/takeorder.aspx?page=1&Workflow=101");
			String code=mypage.asXml();
			Document doc = Jsoup.parse(code);
			Elements links = doc.getElementsByClass("to_list_detail");
			if("".equals(links.html())){
				System.out.println("================未检测到订单================ "+new Date());
			}else{
				for (Element link : links) {
					Elements linksA =link.getElementsByClass("to_rl_buy");
					for (Element linkB : linksA) {
						System.out.println("检测到订单，页面地址==================== http://www.yc5151.com"+linkB.attr("href"));
					}
				}
				try {
					AudioClip acp=Applet.newAudioClip(new URL("file:///D:/123.wav"));
					acp.loop();
					return ;
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
    	}
    }
}
