package com.github.RafaelScarpelli.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.RafaelScarpelli.backend.model.Calculadora;
@Service
public class ServiceHello {

    public Calculadora somar(@RequestBody Calculadora calculadora){
        calculadora.setResultado(calculadora.getV1() + calculadora.getV2());
        return calculadora;
    }
}
