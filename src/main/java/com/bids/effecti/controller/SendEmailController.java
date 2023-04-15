package com.bids.effecti.controller;

import com.bids.effecti.dto.SendEmailDTO;
import com.bids.effecti.rest.AgrolandiaRest;
import com.bids.effecti.service.SendEmailService;
import com.bids.effecti.util.Converter;
import com.bids.effecti.util.ResponseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/sendEmail")
public class SendEmailController {

    @Autowired
    private SendEmailService service;
    @Autowired
    private Converter converter;

    @Autowired
    private AgrolandiaRest rest;

    @PostMapping(value = "/createEmail")
    public ResponseEntity<ResponseApi<String>> createEmail() {
        ResponseApi<String> response = new ResponseApi<>();
        try {
            response.setData(service.sendEmail(rest.search()));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setErrors(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<ResponseApi<SendEmailDTO>> findById(@PathVariable Long id) {
        ResponseApi<SendEmailDTO> response = new ResponseApi<>();
        try {
            response.setData(converter.converterSendEmailEntityToDto(service.findSendEmail(id)));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setErrors(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<ResponseApi<List<SendEmailDTO>>> findAll() {
        ResponseApi<List<SendEmailDTO>> response = new ResponseApi<>();
        try {
            response.setData(converter.converterSendEmailListEntityToListDto(service.getAll()));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setErrors(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @RequestMapping(value = "/return/{idEmployer}/{guild}")
    @ApiIgnore
    public RedirectView update(@PathVariable Long idEmployer, @PathVariable Long guild) {
        try {
            return new RedirectView(service.findReturn(idEmployer, guild));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
