package com.bids.effecti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerDTO {
    private Long id;
    private String commercialName;
    private String cpnj;
    private String email;
    private List<SendEmailDTO> sendEmailDTOS;
}
