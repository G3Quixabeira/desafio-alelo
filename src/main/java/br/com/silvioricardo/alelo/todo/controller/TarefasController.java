package br.com.silvioricardo.alelo.todo.controller;

import br.com.silvioricardo.alelo.todo.model.Tarefa;
import br.com.silvioricardo.alelo.todo.repository.TarefasRepository;
import br.com.silvioricardo.alelo.todo.service.TarefasService;
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
 * Controller com o CRUD para os endpoints das Tarefas
 *
 */
@RestController
@AllArgsConstructor
@Api(value = "CRUD para Lista de Tarefas")
@RequestMapping({"/tarefas"})
@CrossOrigin(origins = "*")
public class TarefasController {
    private final TarefasService tarefasService;
    private final TarefasRepository tarefasRepository;

    /**
     * Endpoint que retorna a lista de tarefas
     *
     * STATUS:
     *  200 Sucesso
     *  204 Sem conteúdo
     *  500 Erro ao processar a solicitação
     *
     * @return JSON tarefas
     */
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de tarefas"),
        @ApiResponse(code = 204, message = "Nenhuma tarefa cadastrada"),
        @ApiResponse(code = 500, message = "Erro ao processar a requisição"),
    })
    @ApiOperation(value = "Retorna todas as tarefas cadastrados", produces="application/json")
    @GetMapping
    public ResponseEntity<List<Tarefa>> findAll(){
        List<Tarefa> listaTarefas = tarefasRepository.findAll();

        if(listaTarefas.isEmpty())
            return noContent().build();

        return ok(listaTarefas);
    }

    /**
     * Endpoint que retorna o tarefa com base na id
     *
     * STATUS:
     *  200 Sucesso
     *  404 Não encontrado
     *  500 Erro ao processar a solicitação
     *
     * @return JSON tarefas
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a tarefa informado com base na Id"),
            @ApiResponse(code = 404, message = "Não foi encontrado tarefa com a Id informada"),
            @ApiResponse(code = 500, message = "Erro ao processar a requisição"),
    })
    @GetMapping("/{id}")
    @ApiOperation(value = "Procura tarefa com base na Id", produces="application/json")
    public ResponseEntity<Tarefa> findById(@PathVariable Long id){
        return ok(tarefasService.findById(id));
    }

    /**
     * Endpoint para inserção de uma nova tarefa
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
            @ApiResponse(code = 201, message = "Tarefa cadastrada com sucesso"),
            @ApiResponse(code = 400, message = "Requisição mal formatada"),
            @ApiResponse(code = 409, message = "Conflito, entidade já existe na base de dados"),
            @ApiResponse(code = 500, message = "Erro ao processar a requisição"),
    })
    @PostMapping
    @ApiOperation(value = "Cadastra uma nova tarefa")
    public ResponseEntity<Tarefa>cadastrar(@Valid @RequestBody Tarefa tarefa){
        Tarefa novaTarefa = tarefasService.save(tarefa);
        return new ResponseEntity<>(novaTarefa, HttpStatus.CREATED);
    }

    /**
     * Endpoint para atualização do tarefa com base na Id recebida
     *
     * STATUS:
     *  (200) Sucesso
     *  (204) Sem conteúdo para resposta
     *  (404) Registro não encontrado ou inválido
     *
     * @return Response STATUS
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tarefa atualizada com sucesso"),
            @ApiResponse(code = 204, message = "Sem conteúdo para resposta"),
            @ApiResponse(code = 400, message = "Requisição mal formatada"),
            @ApiResponse(code = 500, message = "Erro ao processar a requisição"),
    })
    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza a tarefa")
    public ResponseEntity<Tarefa> atualizar(@Valid @RequestBody Tarefa tarefa, @PathVariable Long id){
        tarefa.setId(id);
        Tarefa tarefaAtualizada = tarefasService.update(tarefa);
        return new ResponseEntity<>(tarefaAtualizada, HttpStatus.CREATED);
    }

    /**
     * Endpoint para remoção de um registro
     *
     * STATUS:
     *  (200) Sucesso
     *  (404) Registro não encontrado ou inválido
     *
     * @param id Id da tarefa
     * @return Response STATUS
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tarefa apagada com sucesso"),
            @ApiResponse(code = 404, message = "Não foi encontrado a tarefa para a Id informada"),
            @ApiResponse(code = 500, message = "Erro ao processar a requisição"),
    })
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta uma tarefa")
    public ResponseEntity<?>deletar(@PathVariable Long id){
        return tarefasService.delete(id);
    }
}
