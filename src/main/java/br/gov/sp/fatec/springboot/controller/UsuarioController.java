package br.gov.sp.fatec.springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.springboot.entity.Usuario;
import br.gov.sp.fatec.springboot.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping(value = "/usuario")
@CrossOrigin

public class UsuarioController {
    
    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<Usuario> buscarTodosUsuarios() {
        return service.buscarTodosUsuarios();
    }

    @PostMapping
    public Usuario cadastrarNovoUsuario (@RequestBody Usuario usuario) {
        return service.cadastraUsuario(usuario);
    }

    @GetMapping(value = "/{id}")
    public Usuario buscarUsuarioPorId (Long id) {
        return service.buscarUsuarioPorId(id);
    }
    
}
