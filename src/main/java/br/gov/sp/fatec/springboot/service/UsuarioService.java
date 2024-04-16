package br.gov.sp.fatec.springboot.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.springboot.entity.Autorizacao;
import br.gov.sp.fatec.springboot.entity.Usuario;
import br.gov.sp.fatec.springboot.repository.AutorizacaoRepository;
import br.gov.sp.fatec.springboot.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AutorizacaoRepository autorizacaoRepo;

    @PreAuthorize("hasRole('ADMIN')")
    public List<Usuario> buscarTodosUsuarios() {
        List<Usuario> usuarios = usuarioRepo.findAll();
        return usuarios;
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public Usuario cadastraUsuario(Usuario usuario) {
        if (usuario == null ||
                usuario.getNome() == null ||
                usuario.getNome().isBlank() ||
                usuario.getSenha().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados invalidos");

        }
        if (!usuario.getAutorizacoes().isEmpty()) {
            Set<Autorizacao> autorizacoes = new HashSet<Autorizacao>();
            for (Autorizacao autorizacao : usuario.getAutorizacoes()) {
                autorizacao = buscarAutorizacaoPorId(autorizacao.getId());
                autorizacoes.add(autorizacao);
            }
            usuario.setAutorizacoes(autorizacoes);
        }
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return usuarioRepo.save(usuario);

    }
    @PreAuthorize("isAuthenticad()")
    public Usuario buscarUsuarioPorId(Long id) {
        Optional<Usuario> usuarioOp = usuarioRepo.findById(id);

        if (usuarioOp.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado");
        }

        return usuarioOp.get();

    }

    public Autorizacao buscarAutorizacaoPorId(Long id) {
        Optional<Autorizacao> autorizacaoOp = autorizacaoRepo.findById(id);

        if (autorizacaoOp.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Autorização não encontrado");
        }

        return autorizacaoOp.get();

    }
}
