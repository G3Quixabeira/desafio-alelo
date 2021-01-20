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

@RestController
@AllArgsConstructor
@Api(value = "CRUD para Lista de Tarefas")
@RequestMapping({"/tarefas"})
@CrossOrigin(origins = "*")
public class TarefasController {
    private final TarefasService tarefasService;
    private final TarefasRepository tarefasRepository;

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

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Tarefa cadastrada com sucesso"),
            @ApiResponse(code = 400, message = "Requisição mal formatada"),
            @ApiResponse(code = 500, message = "Erro ao processar a requisição"),
    })
    @PostMapping
    @ApiOperation(value = "Cadastra uma nova tarefa")
    public ResponseEntity<Tarefa>cadastrar(@Valid @RequestBody Tarefa tarefa){
        Tarefa novaTarefa = tarefasService.save(tarefa);
        return new ResponseEntity<>(novaTarefa, HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tarefa atualizada com sucesso"),
            @ApiResponse(code = 400, message = "Requisição mal formatada"),
            @ApiResponse(code = 500, message = "Erro ao processar a requisição"),
    })
    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza a tarefa")
    public ResponseEntity<Tarefa> atualizar(@Valid @RequestBody Tarefa tarefa){
        Tarefa tarefaAtualizada = tarefasService.update(tarefa);
        return new ResponseEntity<>(tarefaAtualizada, HttpStatus.CREATED);
    }

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
