package com.github.RafaelScarpelli.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.RafaelScarpelli.backend.model.Calculadora;
import com.github.RafaelScarpelli.backend.service.ServiceHello;

@RestController
public class ControllerHello {
    @Autowired
    private ServiceHello serviceHello;
    @GetMapping("/")
    public String hello(){
        return "olá spring";
    }
    @GetMapping("/somar")
    public Integer somar(@RequestParam("v1") Integer v1, @RequestParam("v2") Integer v2){
        return v1 + v2;
    }
    @PostMapping("/somar")
    public Calculadora somar(@RequestBody Calculadora calculadora){
        return serviceHello.somar(calculadora);
    }
}
