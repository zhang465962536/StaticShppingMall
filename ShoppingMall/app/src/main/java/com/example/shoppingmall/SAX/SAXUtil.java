package com.example.shoppingmall.SAX;

import android.content.Context;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

//基于事件驱动，占用内存小，灵活性差，顺序读取，速度快（适用于在性能要求较高的设备上使用）
//在Android开发常用
public class SAXUtil {
    public static List<Chanel> parseSaxXml(Context context){

        List<Chanel> list  = null;
        //解析器工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //生成解析器
        try {
            SAXParser sax = factory.newSAXParser();

            InputStream is = context.getAssets().open("chanel.xml");

            ChanelHandler dh = new ChanelHandler();

            sax.parse(is,dh);  //解析

            list = dh.getChanels();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
