/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author sanayapc
 */
public class JaxbCategoryItemAdapter extends XmlAdapter<String, String>{
    @Override
    public String unmarshal(String v) throws Exception {
            return v.replaceAll(",", "&#44;");
    }

    @Override
    public String marshal(String v) throws Exception {
            return v;
    }
}
