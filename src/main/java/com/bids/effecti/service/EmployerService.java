package com.bids.effecti.service;

import com.bids.effecti.dto.EmployerCreateOrUpdateDTO;
import com.bids.effecti.entity.Employer;

import java.util.List;

public interface EmployerService {
    List<Employer> getAll();

    Employer createOrUpdateEmployer(EmployerCreateOrUpdateDTO employerDTO);

    Employer findEmployer(Long idEmployer);

    Boolean delete(Long idEmployer);
}
