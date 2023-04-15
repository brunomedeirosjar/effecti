package com.bids.effecti.util;

import com.bids.effecti.dto.EmployerDTO;
import com.bids.effecti.dto.SendEmailDTO;
import com.bids.effecti.entity.Employer;
import com.bids.effecti.entity.SendEmail;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Converter {

    public EmployerDTO converterEmployerEntityToDto(Employer employer) {
        return new EmployerDTO(employer.getId(), employer.getCommercialName(), employer.getCpnj(), employer.getEmail(), employer.getSendEmail() == null ? new ArrayList<>() : converterSendEmailListEntityToListDto(employer.getSendEmail()));
    }

    public SendEmailDTO converterSendEmailEntityToDto(SendEmail sendEmail) {
        return new SendEmailDTO(sendEmail.getId(), sendEmail.getIsSending(), sendEmail.getIsReturn(), sendEmail.getGuid(), sendEmail.getEmployer().getId(), sendEmail.getUrl());
    }

    public List<SendEmailDTO> converterSendEmailListEntityToListDto(List<SendEmail> sendEmail) {
        return new ArrayList<>(sendEmail.stream().map(this::converterSendEmailEntityToDto).collect(Collectors.toList()));
    }

    public List<EmployerDTO> converterEmployerListEntityToListDto(List<Employer> employers) {
        return new ArrayList<>(employers.stream().map(this::converterEmployerEntityToDto).collect(Collectors.toList()));
    }

}
