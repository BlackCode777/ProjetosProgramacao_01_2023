package com.designAPIsRestFullSpringTddJunit3.libraryApi.controller;

import com.designAPIsRestFullSpringTddJunit3.libraryApi.api.exception.ApiErrors;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.api.exception.BusinessException;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.dto.BooKDTO;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity.Book;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

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

    @ExceptionHandler( MethodArgumentNotValidException.class )// Tratando os erros nas requisições com a classe MethodArgumentNotValidException.class
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    public ApiErrors handleValidationExceptions( MethodArgumentNotValidException ex ){
        BindingResult bindingResult = ex.getBindingResult(); // criando a instancia de BindingResult para pegar todos os erros da requisição
        return new ApiErrors( bindingResult );// criando os objetos que vão pegar os erros e vão mandar para a API
    }

    @ExceptionHandler( BusinessException.class )
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    public ApiErrors handleBusinessException( BusinessException ex ){
          return new ApiErrors( ex );
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

}
