package com.bids.effecti.service.impl;

import com.bids.effecti.dto.EmployerCreateOrUpdateDTO;
import com.bids.effecti.entity.Employer;
import com.bids.effecti.repository.EmployerRepository;
import com.bids.effecti.repository.SendEmailRepository;
import com.bids.effecti.service.EmployerService;
import com.bids.effecti.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerServiceImpl implements EmployerService {

    @Autowired
    private EmployerRepository repository;
    @Autowired
    private Converter converter;
    @Autowired
    private SendEmailRepository sendEmailRepository;

    @Override
    public List<Employer> getAll() {
        return repository.findAll();
    }

    @Override
    public Employer createOrUpdateEmployer(EmployerCreateOrUpdateDTO employerDTO) {
        Employer employer = repository.findByCpnj(employerDTO.getCpnj());
        if (employer == null) {
            employer = repository.save(new Employer(null, employerDTO.getCommercialName(), employerDTO.getCpnj(), employerDTO.getEmail(), null));
        } else {
            employer.setEmail(employerDTO.getEmail());
            employer.setCommercialName(employerDTO.getCommercialName());
            employer = repository.save(employer);
        }
        return employer;
    }

    @Override
    public Employer findEmployer(Long idEmployer) {
        return repository.findById(idEmployer).get();
    }

    @Override
    public Boolean delete(Long idEmployer) {
        repository.delete(repository.findById(idEmployer).get());
        return true;
    }


}

