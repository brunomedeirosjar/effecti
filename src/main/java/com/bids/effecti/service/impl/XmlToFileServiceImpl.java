package com.bids.effecti.service.impl;

import com.bids.effecti.model.Channel;
import com.bids.effecti.rest.AgrolandiaRest;
import com.bids.effecti.service.XmlToFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XmlToFileServiceImpl implements XmlToFileService {

    @Autowired
    private AgrolandiaRest rest;

    @Override
    public Channel get() throws Exception {
        return rest.search();
    }
}
