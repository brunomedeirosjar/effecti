package com.bids.effecti.service;

import com.bids.effecti.entity.SendEmail;
import com.bids.effecti.model.Channel;
import com.bids.effecti.model.EmailProperties;

import java.util.List;

public interface SendEmailService {
    String sendEmail(Channel channel) throws Exception;

    List<SendEmail> getAll();

    SendEmail findSendEmail(Long idSendEmail);

    String findReturn(Long idEmployer, Long guild) throws Exception;
}



