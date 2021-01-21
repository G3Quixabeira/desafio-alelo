package br.com.silvioricardo.alelo.todo.controller;

import br.com.silvioricardo.alelo.todo.model.Lista;
import br.com.silvioricardo.alelo.todo.repository.ListasRepository;
import br.com.silvioricardo.alelo.todo.service.ListasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

/**
 * Controller com o CRUD para os endpoints das Listas
 *
 */
@RestController
@AllArgsConstructor
@Api(value = "CRUD para Lista de Listas")
@RequestMapping({"/listas"})
@CrossOrigin(origins = "*")
public class ListaController {
    private final ListasService listasService;
    private final ListasRepository listasRepository;

    /**
     * Endpoint que retorna a lista de Listas
     *
     * STATUS:
     *  200 Sucesso
     *  204 Sem conteúdo
     *  500 Erro ao processar a solicitação
     *
     * @return JSON listas
     */
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de listas"),
        @ApiResponse(code = 204, message = "Nenhuma lista cadastrada"),
        @ApiResponse(code = 500, message = "Erro ao processar a requisição"),
    })
    @ApiOperation(value = "Retorna todas as listas cadastrados", produces="application/json")
    @GetMapping
    public ResponseEntity<List<Lista>> findAll(){
        List<Lista> listaListas = listasRepository.findAll();

        if(listaListas.isEmpty())
            return noContent().build();

        return ok(listaListas);
    }

    /**
     * Endpoint que retorna o lista com base na id
     *
     * STATUS:
     *  200 Sucesso
     *  404 Não encontrado
     *  500 Erro ao processar a solicitação
     *
     * @return JSON listas
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista informado com base na Id"),
            @ApiResponse(code = 404, message = "Não foi encontrado lista com a Id informada"),
            @ApiResponse(code = 500, message = "Erro ao processar a requisição"),
    })
    @GetMapping("/{id}")
    @ApiOperation(value = "Procura lista com base na Id", produces="application/json")
    public ResponseEntity<Lista> findById(@PathVariable Long id){
        return ok(listasService.findById(id));
    }

    /**
     * Endpoint para inserção de uma nova lista
     *
     * STATUS:
     *  (201) Criado
     *  (400) Requisição mal formatada
     *  (409) Conflito, entidade já existe na base de dados
     *  (500) Erro interno no servidor
     *
     * @return Response STATUS
     */
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Lista cadastrada com sucesso"),
            @ApiResponse(code = 400, message = "Requisição mal formatada"),
            @ApiResponse(code = 409, message = "Conflito, entidade já existe na base de dados"),
            @ApiResponse(code = 500, message = "Erro ao processar a requisição"),
    })
    @PostMapping
    @ApiOperation(value = "Cadastra uma nova Lista")
    public ResponseEntity<Lista>cadastrar(@Valid @RequestBody Lista lista){
        Lista novaLista = listasService.save(lista);
        return new ResponseEntity<>(novaLista, HttpStatus.CREATED);
    }

    /**
     * Endpoint para atualização do lista com base na Id recebida
     *
     * STATUS:
     *  (200) Sucesso
     *  (204) Sem conteúdo para resposta
     *  (404) Registro não encontrado ou inválido
     *
     * @return Response STATUS
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista atualizada com sucesso"),
            @ApiResponse(code = 204, message = "Sem conteúdo para resposta"),
            @ApiResponse(code = 400, message = "Requisição mal formatada"),
            @ApiResponse(code = 500, message = "Erro ao processar a requisição"),
    })
    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza a lista")
    public ResponseEntity<Lista> atualizar(@Valid @RequestBody Lista lista, @PathVariable Long id){
        lista.setId(id);
        Lista listaAtualizada = listasService.update(lista);
        return new ResponseEntity<>(listaAtualizada, HttpStatus.CREATED);
    }

    /**
     * Endpoint para remoção de um registro
     *
     * STATUS:
     *  (200) Sucesso
     *  (404) Registro não encontrado ou inválido
     *
     * @param id Id da lista
     * @return Response STATUS
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista apagada com sucesso"),
            @ApiResponse(code = 404, message = "Não foi encontrado a lista para a Id informada"),
            @ApiResponse(code = 500, message = "Erro ao processar a requisição"),
    })
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta uma lista")
    public ResponseEntity<?>deletar(@PathVariable Long id){
        return listasService.delete(id);
    }
}
