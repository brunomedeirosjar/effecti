package com.bids.effecti.rest;

import com.bids.effecti.model.Channel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "AgrolandiaRest", url = "https://www.toptal.com/developers/feed2json")
public interface AgrolandiaRest {
    @GetMapping(value = "/convert?url=https://www.agrolandia.sc.gov.br/feed/assinar/licitacoes/tipo/feed")
    Channel search();
}