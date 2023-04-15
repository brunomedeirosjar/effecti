package com.bids.effecti.controller;

import com.bids.effecti.model.Channel;
import com.bids.effecti.service.XmlToFileService;
import com.bids.effecti.util.ResponseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    private XmlToFileService xmlToFileService;


    @GetMapping(value = "/conversorXML")
    public ResponseEntity<ResponseApi<Channel>> conversorChannel() {
        ResponseApi<Channel> response = new ResponseApi<>();
        try {
            response.setData(xmlToFileService.get());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setErrors(Collections.singletonList("Ops! Algo deu errado, tente novamente mais tarde ou procure o suporte técnico."));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


}
