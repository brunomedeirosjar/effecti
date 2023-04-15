package com.bids.effecti.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement
public class Channel {
    private String version;
    private String title;
    private String home_page_url;
    private List<Item> items;
}
