package com.designAPIsRestFullSpringTddJunit3.libraryApi.controller;

import com.designAPIsRestFullSpringTddJunit3.libraryApi.api.exception.ApiErrors;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.api.exception.BusinessException;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.dto.BooKDTO;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity.Book;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    //Criar rota de Post
    //Chama anotation @PostMapping
    //Criar o pacote DTO junto com a sua classe BookDto - linha 16 -> com\designAPIsRestFullSpringTddJunit3\libraryApi\dto
    //Chama Annotation @ResponseStatus - linha 20 ->> cria a resposta 201
    //Cria uma Classe BookModel ou classe DTO
    //Instancia a classe BookDTO
    //Acessa os methodos setId(),setAuthor(),setTitle(),setIsbn()
    //Depois de fazer passar os requitos ,  precisamos fazer o retorno dos dados em Json - refatora o teste
    // Chama o metodo @RequestBody BooKDTO bookdto para pegar os valores que vem na requisisção
    // do corpo body e jogar os valores dentro da variavel bookdto - linha 25
    // Instancia a Interface o service *

    private BookService service;

    private ModelMapper modelMapper;

    @Autowired
     public BookController( BookService service, ModelMapper mapper){
         this.service = service;
         this.modelMapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BooKDTO create(@RequestBody @Valid BooKDTO bookdto){ // Essa variável (bookdto) traz os dados do RequestBody - so que falta o ID populado - porisso da esso no teste
        // Passa a instancia de Book para a instancia de BookService *
        Book entity = modelMapper.map(bookdto, Book.class);
        entity = service.save(entity);
        // e retorna um DTO como boa prática *
        return modelMapper.map( entity, BooKDTO.class);
    }

    @GetMapping("{id}")
    public BooKDTO get( @PathVariable Long id){
        return service.getById( id ).map( book -> modelMapper.map(book, BooKDTO.class  ) )
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    public BooKDTO update( @PathVariable Long id, BooKDTO dto ){

        // Ele pega o livro encontrado
        return service.getById( id ).map( book -> {
            //Insere ou atualiza um novo livro
            book.setAuthor( dto.getAuthor() );
            book.setTitle( dto.getTitle() );
            //Salva um novo livro no DTO
            book = service.update( book );
            return modelMapper.map( book, BooKDTO.class );

        } ).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @DeleteMapping("{id}")
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void delete( @PathVariable Long id ){
        Book book = service.getById(  id  ).get();
        service.delete(book);
    }

    @GetMapping
    public Page<BooKDTO> find(BooKDTO dto, Pageable pageRequest){
        Book filter = modelMapper.map( dto, Book.class );
        Page<Book> result = service.find( filter, pageRequest );
        List<BooKDTO> list = result.getContent()
                .stream()
                .map( entity -> modelMapper.map( entity, BooKDTO.class ) )
                .collect( Collectors.toList() ); // retorna uma list de string de dto

        return new PageImpl<BooKDTO>( list, pageRequest, result.getTotalElements() );

    }

}
