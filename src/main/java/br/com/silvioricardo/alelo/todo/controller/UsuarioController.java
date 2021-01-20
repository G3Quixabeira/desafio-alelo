package br.com.silvioricardo.alelo.todo.controller;

import br.com.silvioricardo.alelo.todo.model.Usuario;
import br.com.silvioricardo.alelo.todo.repository.UsuarioRepository;
import br.com.silvioricardo.alelo.todo.service.UsuarioService;
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

import static org.springframework.http.ResponseEntity.*;

@RestController
@AllArgsConstructor
@Api(value = "CRUD para Usuarios")
@RequestMapping({"/usuarios"})
@CrossOrigin(origins = "*")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de usuários"),
        @ApiResponse(code = 204, message = "Nenhum usuário cadastrado"),
        @ApiResponse(code = 500, message = "Erro ao processar a requisição"),
    })
    @ApiOperation(value = "Retorna os usuários cadastrados", produces="application/json")
    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(){
        List<Usuario> listaUsuarios = usuarioRepository.findAll();

        if(listaUsuarios.isEmpty())
            return noContent().build();

        return ok(listaUsuarios);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o usuário informado com base na Id"),
            @ApiResponse(code = 404, message = "Não foi encontrado usuário com a Id informada"),
            @ApiResponse(code = 500, message = "Erro ao processar a requisição"),
    })
    @GetMapping("/{id}")
    @ApiOperation(value = "Procura usuário com base na Id", produces="application/json")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        return ok(usuarioService.findById(id));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuário cadastrado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição mal formatada"),
            @ApiResponse(code = 500, message = "Erro ao processar a requisição"),
    })
    @PostMapping
    @ApiOperation(value = "Cadastra um novo usuário")
    public ResponseEntity<Usuario>cadastrar(@Valid @RequestBody Usuario usuario){
        Usuario novoUsuario = usuarioService.save(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição mal formatada"),
            @ApiResponse(code = 500, message = "Erro ao processar a requisição"),
    })
    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza dados do usuário")
    public ResponseEntity<Usuario> atualizar(@Valid @RequestBody Usuario usuario, @PathVariable Long id){
        if(usuario == null) {
            return new ResponseEntity<>(usuario, HttpStatus.BAD_REQUEST);
        }

        usuario.setId(id);
        Usuario usuarioAtualizado = usuarioService.update(usuario);
        return new ResponseEntity<>(usuarioAtualizado, HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário apagado com sucesso"),
            @ApiResponse(code = 404, message = "Não foi encontrado usuário para a Id informada"),
            @ApiResponse(code = 500, message = "Erro ao processar a requisição"),
    })
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta um usuário")
    public ResponseEntity<?>deletar(@PathVariable Long id){
        return usuarioService.delete(id);
    }
}
