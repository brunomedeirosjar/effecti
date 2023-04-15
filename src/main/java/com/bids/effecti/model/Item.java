package com.bids.effecti.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement
public class Item {
    private String guid;
    private String url;
    private String title;
    private String content_html;
    private String date_published;
}
