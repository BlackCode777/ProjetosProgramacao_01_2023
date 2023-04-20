package br.com.springboot.curso_spring_dev.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_spring_dev.model.Usuario;
import br.com.springboot.curso_spring_dev.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {

	@Autowired 
	private UsuarioRepository usuarioRepository;	
	    
    @GetMapping( value = "listaTodos" )
    @ResponseBody//Retorna os dados para o corpo da resposta
    public ResponseEntity<List<Usuario>>  getUsuario() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);//retorna a lista em json
	}
    
    @PostMapping( value = "salvar" )
    @ResponseBody
    public ResponseEntity<Usuario> salvar( @RequestBody Usuario usuario ){
    	Usuario user = usuarioRepository.save(usuario);
    	return new ResponseEntity<Usuario>( user, HttpStatus.CREATED );
    }
    
    @DeleteMapping( value = "delete" )
    @ResponseBody
    public ResponseEntity<String> deletar( @RequestParam Long iduser ){
    	usuarioRepository.deleteById(iduser);
    	return new ResponseEntity<String>( "Usuario deletado com sucesso !  ", HttpStatus.OK );
    }
    
    @GetMapping( value = "buscarUserId" )
    @ResponseBody
    public ResponseEntity<Usuario> buscarUserId( @RequestParam( name = "iduser" ) Long iduser ){
    	Usuario user = usuarioRepository.findById(iduser).get();
    	return new ResponseEntity<Usuario>( user, HttpStatus.OK );
    }
    
    @PutMapping( value = "atualizar" )
    @ResponseBody
    public ResponseEntity<?> atualizar( @RequestBody Usuario usuario ){
    	
    	if( usuario.getId() == null ) {
    		return new ResponseEntity<String>( " Id do usuário não foi informado. ", HttpStatus.BAD_REQUEST );
    	}    
    	
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	return new ResponseEntity<Usuario>( user, HttpStatus.OK );
    }
    
    //consultarPorNome
    @GetMapping( value = "buscarPorNome" )
    @ResponseBody // método trim() para retirar o espaço das buscas
    public ResponseEntity<List<Usuario>> buscarPorNome( @RequestBody @RequestParam( name = "name" ) String name ){
    	List<Usuario> nomeUser = usuarioRepository.findByName( name.trim().toUpperCase() ); 
    	return new ResponseEntity<List<Usuario>>( nomeUser, HttpStatus.OK );
    }
    
    
}



















