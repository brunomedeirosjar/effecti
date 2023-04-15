package com.bids.effecti.controller;


import com.bids.effecti.dto.EmployerCreateOrUpdateDTO;
import com.bids.effecti.dto.EmployerDTO;
import com.bids.effecti.service.EmployerService;
import com.bids.effecti.util.Converter;
import com.bids.effecti.util.ResponseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/employer")
public class EmployerController {

    @Autowired
    private EmployerService service;

    @Autowired
    private Converter converter;

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseApi<EmployerDTO>> create(@Valid @RequestBody EmployerCreateOrUpdateDTO employer) {
        ResponseApi<EmployerDTO> response = new ResponseApi<>();
        try {
            response.setData(converter.converterEmployerEntityToDto(service.createOrUpdateEmployer(employer)));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setErrors(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ResponseApi<EmployerDTO>> update(@Valid @RequestBody EmployerCreateOrUpdateDTO employer) {
        ResponseApi<EmployerDTO> response = new ResponseApi<>();
        try {
            response.setData(converter.converterEmployerEntityToDto(service.createOrUpdateEmployer(employer)));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setErrors(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<ResponseApi<EmployerDTO>> findById(@PathVariable Long id) {
        ResponseApi<EmployerDTO> response = new ResponseApi<>();
        try {
            response.setData(converter.converterEmployerEntityToDto(service.findEmployer(id)));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setErrors(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<ResponseApi<List<EmployerDTO>>> findAll() {
        ResponseApi<List<EmployerDTO>> response = new ResponseApi<>();
        try {
            response.setData(converter.converterEmployerListEntityToListDto(service.getAll()));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setErrors(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping(value = "/deleteById/{id}")
    public ResponseEntity<ResponseApi<Boolean>> deleteEmployer(@PathVariable Long id) {
        ResponseApi<Boolean> response = new ResponseApi<>();
        try {
            response.setData(service.delete(id));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setErrors(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


}
