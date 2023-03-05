package com.designAPIsRestFullSpringTddJunit3.libraryApi.controller;

import com.designAPIsRestFullSpringTddJunit3.libraryApi.dto.BooKDTO;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity.Book;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private BookService service;
    // public BookController( BookService service){
    //     this.service = service;
    //}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BooKDTO create(@RequestBody BooKDTO bookdto){ // Essa variável (bookdto) traz os dados do RequestBody - so que falta o ID populado - porisso da esso no teste
        // TODO - o mesmo bookdto que estou recebendo como parametro é o mesmo que estou retornando
        // TODO - precisa melhorar

        // Passa a instancia de Book para a instancia de BookService * 
        Book entity = Book.builder()
                .author(bookdto.getAuthor())
                .title(bookdto.getTitle())
                .isbn(bookdto.getIsbn())
                .build();
        // salva uma entidade no banco *
        entity = service.save(entity);

        // e retorna um DTO como boa prática *
        return BooKDTO.builder()
                .id(entity.getId())
                .author(entity.getAuthor())
                .title(entity.getTitle())
                .isbn(entity.getIsbn())
                .build();
    }

}
