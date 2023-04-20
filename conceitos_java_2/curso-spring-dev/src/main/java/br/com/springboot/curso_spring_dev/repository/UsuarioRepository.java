package br.com.springboot.curso_spring_dev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.curso_spring_dev.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	// buscar por nomes - retirando espaços em branco - e filtrando caracteres maiúsculos e minúsculos
	@Query( value = " select u from Usuario u where upper(trim(u.nome)) like %?1% " )
	List<Usuario> findByName(String nome);

	
	
}
