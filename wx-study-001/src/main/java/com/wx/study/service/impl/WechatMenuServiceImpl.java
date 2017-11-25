package com.wx.study.service.impl;


import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.wx.study.bean.Button;
import com.wx.study.bean.ComplexButton;
import com.wx.study.bean.Menu;
import com.wx.study.service.WechatMenuService;
import com.wx.study.utils.Constants;
import com.wx.study.utils.HttpUtils;

/**
 * Created by DT167 on 2017/6/2.
 */
public class WechatMenuServiceImpl implements WechatMenuService {

    private static Logger logger = LoggerFactory.getLogger(WechatMenuServiceImpl.class);

    
    public static void main(String[] args) {
    	WechatMenuServiceImpl we= new WechatMenuServiceImpl();
    	we.create_menu();
    }
    
    @Override
    public void create_menu()  {
        try{
            ComplexButton complexButton1 = new ComplexButton();
            complexButton1.setName("学习");
            Button button1 =new Button();
            button1.setName("微信学习1");
            button1.setKey("d1");
            button1.setType("view");
            button1.setUrl("http://www.baidu.com");
            Button button2 =new Button();
            button2.setName("微信学习2");
            button2.setKey("d2");
            button2.setType("view");
            button2.setUrl("http://www.baidu.com");
            Button button3 =new Button();
            button3.setName("微信学习3");
            button3.setKey("d3");
            button3.setType("view");
            button3.setUrl("http://www.baidu.com");
            complexButton1.setSub_button(new Button[]{button1, button2, button3});

            ComplexButton complexButton2 = new ComplexButton();
            complexButton2.setName("学习2");
            button1 =new Button();
            button1.setName("前端学习");
            button1.setKey("m1");
            button1.setType("view");
            button1.setUrl("http://www.baidu.com");
            complexButton2.setSub_button(new Button[]{button1});

            ComplexButton complexButton3 = new ComplexButton();
            complexButton3.setName("学习3");
            button1 =new Button();
            button1.setName("后端学习");
            button1.setKey("c1");
            button1.setType("click");
            complexButton3.setSub_button(new Button[]{button1});


            Menu menu = new Menu();
            menu.setButton(new ComplexButton[]{complexButton1, complexButton2, complexButton3});

            net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(menu);
            String body = jsonObject.toString();
            System.out.println(body);
            String accessToekn = HttpUtils.getAccessToken(Constants.appId, Constants.secret);
            PostMethod method = new PostMethod(Constants.wxCreateMenu+"?access_token="+accessToekn);
            method.setRequestEntity(new StringRequestEntity(body, "application/json", "UTF-8"));
            org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
            int statusCode = client.executeMethod(method);
            if (statusCode == HttpStatus.OK.value()) {
                System.out.println(method.getResponseBodyAsString());
            }
            System.out.println(statusCode);
        }catch(Exception e){
            logger.error(e.getMessage());
        }
    }
}
