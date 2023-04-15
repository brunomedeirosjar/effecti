package com.bids.effecti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendEmailDTO {
    private Long id;
    private Boolean isSet;
    private Boolean isReturn;
    private Long guid;
    private Long employer;
    private String url;
}
