package br.gov.sp.fatec.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springboot.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
