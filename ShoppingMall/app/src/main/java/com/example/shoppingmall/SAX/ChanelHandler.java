package com.example.shoppingmall.SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

//数据处理器
public class ChanelHandler extends DefaultHandler {

    private List<Chanel> chanels;
    private Chanel chanel;
    private String tag;

    public List<Chanel> getChanels() {
        return chanels;
    }

    //开始解析文档
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        chanels = new ArrayList<>();
        System.out.println("开始解析文档");
    }

    //解析开始标志
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if("chanel".equals(qName)){
            chanel = new Chanel();
            chanel.setChanelid(attributes.getValue("chanelid"));
        }
        tag = qName;
    }

    //处理文本内容(包括空格)
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if(tag != null){
            if("image".equals(tag)){
                chanel.setImage(new String(ch,start,length));
            }else if("text".equals(tag)){
                chanel.setText(new String(ch,start,length));
            }
        }
    }

    //结束解析标签
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if("chanel".equals(qName)){
            chanels.add(chanel);
        }
        tag = null;
    }

    //结束解析文档
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("文档解析完毕");
    }
}
