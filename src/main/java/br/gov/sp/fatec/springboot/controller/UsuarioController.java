package br.gov.sp.fatec.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.springboot.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
@CrossOrigin

public class UsuarioController {
    
    @Autowired
    private UsuarioService service;

    

}
