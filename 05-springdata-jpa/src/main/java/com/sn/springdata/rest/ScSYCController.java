package com.sn.springdata.rest;

import com.sn.springdata.pojo.AttrInfo;
import com.sn.springdata.pojo.BaseInfo;
import com.sn.springdata.pojo.FileInfo;
import com.sn.springdata.pojo.Result;
import com.sn.springdata.service.ScSYCService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class ScSYCController {
    @Autowired
    private ScSYCService scSYCService;
    @PostMapping("/baseInfo/all")
    public Result saveBaseInfo(@RequestParam(value = "baseInfoXml",required = false) String baseInfoXml, @RequestParam(value = "attrXml",required = false) String attrXml, @RequestParam(value = "formXml",required = false) String formXml, @RequestParam(value = "apasPostXml",required = false) String apasPostXml){
        if(baseInfoXml==null || attrXml==null){
            return new Result().paramNull();
        }
        // 解析baseInfoXml申报基本信息
        BaseInfo baseInfo = AnalysisBaseInfoXml(baseInfoXml);
        scSYCService.saveBaseInfo(baseInfo);
        // 解析attrXml申报基本信息
        AnalysisAttrInfo(attrXml);
        return new Result().ok();
    }
    /**
     * 解析申报基本信息
     */
    public BaseInfo AnalysisBaseInfoXml(String baseInfoXml){
        try {
            baseInfoXml = baseInfoXml.replaceAll("\t|\n", "");
            Document doc = DocumentHelper.parseText(baseInfoXml);
            // 根节点 RECORD
            Element roots = doc.getRootElement();
            // 子节点
            Iterator elements=roots.elementIterator();
            BaseInfo baseInfoXmlRecord = new BaseInfo();
            while (elements.hasNext()){
                Element child= (Element) elements.next();
                List subElemets=child.elements();
                if(subElemets.size()!=0){
                    for(int i=0;i<subElemets.size();i++){
                        Element subChild= (Element) subElemets.get(i);
                        setObject(subChild, baseInfoXmlRecord);
                    }
                }else{
                    setObject(child, baseInfoXmlRecord);
                }
            }
            return baseInfoXmlRecord;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析申报材料信息
     */
    public List<AttrInfo> AnalysisAttrInfo(String attrInfoXml){
        try {
            String xmlStr = attrInfoXml.replaceAll("\t|\n", "");
            System.out.println(xmlStr);
            Document doc = DocumentHelper.parseText(xmlStr);
            // 根节点 RECORDS
            Element roots = doc.getRootElement();
            // 子节点
            Iterator elements=roots.elementIterator();

            List<AttrInfo> attrXmlRecordList = new ArrayList<>();
            while (elements.hasNext()){
                Element child= (Element) elements.next();
                // System.out.println("节点名称1 = [" + child.getName() + "]"+"节点内容："+child.getText());
                AttrInfo attrXmlRecord = new AttrInfo();
                List<Element>  subElemets=child.elements();
                if(subElemets.size()!=0){
                    List<FileInfo> fileInfoList = new ArrayList<>();
                    FileInfo fileInfo = new FileInfo();
                    for(Element subChild : subElemets){
                        List<Element> thrChilds = subChild.elements();
                        if(subChild.getName().equals("UNID")){
                            fileInfo.setUNID(subChild.getText());
                        }
                        if(thrChilds.size()!= 0){
                            for(Element thrChild : thrChilds){
                                // System.out.println("节点名称3 = [" + thrChild.getName() + "]"+"节点内容："+thrChild.getText());
                                List<Element> fourChilds = thrChild.elements();
                                for(Element fourChild : fourChilds){
                                    // System.out.println("节点名称4 = [" + fourChild.getName() + "]"+"节点内容："+fourChild.getText());
                                    setObject(fourChild,fileInfo);
                                }
                                fileInfoList.add(fileInfo);
                                scSYCService.saveFileInfo(fileInfoList);
                            }
                        }else{
                            setObject(subChild, attrXmlRecord);
                        }
                    }

                }
                attrXmlRecordList.add(attrXmlRecord);
                scSYCService.saveAttrInfo(attrXmlRecordList);
            }
            return attrXmlRecordList;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 反射
     * @param child
     * @param object
     */
    public void setObject(Element child, Object  object ) {
        String all = child.getName().toUpperCase();
        String wayName = "set" + all;
        Class clazz = object.getClass();
        try {
            Method setMethod = clazz.getDeclaredMethod(wayName, String.class);
            setMethod.invoke(object, child.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
