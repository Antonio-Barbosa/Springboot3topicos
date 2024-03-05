package br.gov.sp.fatec.springboot.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.springboot.entity.Anotacao;
import br.gov.sp.fatec.springboot.entity.Usuario;
import br.gov.sp.fatec.springboot.repository.AnotacaoRepository;

@Service
public class AnotacaoService {

    @Autowired
    private AnotacaoRepository anotacaoRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Anotacao nova(Anotacao anotacao) {
        if (anotacao == null ||
                anotacao.getTexto() == null ||
                anotacao.getTexto().isBlank() ||
                anotacao.getUsuario() == null ||
                anotacao.getUsuario().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados invalidos");
        }

        if (anotacao.getDataHora() == null) {
            anotacao.setDataHora(LocalDateTime.now());
        }
        Usuario usuario = usuarioService.buscarUsuarioPorId(anotacao.getId());
        anotacao.setUsuario(usuario);
        return anotacaoRepository.save(anotacao);

    }

}
