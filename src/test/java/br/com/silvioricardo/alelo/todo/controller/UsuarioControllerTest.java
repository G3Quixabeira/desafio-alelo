package br.com.silvioricardo.alelo.todo.controller;

import br.com.silvioricardo.alelo.todo.model.Usuario;
import br.com.silvioricardo.alelo.todo.repository.UsuarioRepository;
import br.com.silvioricardo.alelo.todo.service.UsuarioService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.*;

public class UsuarioControllerTest {
    @Mock
    UsuarioService usuarioService;
    @Mock
    UsuarioRepository usuarioRepository;
    @InjectMocks
    UsuarioController usuarioController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() throws Exception {
        ResponseEntity<List<Usuario>> result = usuarioController.findAll();
        Assert.assertEquals(204, result.getStatusCodeValue());
    }

    @Test
    public void testFindById() throws Exception {
        when(usuarioService.findById(anyLong())).thenReturn(new Usuario(Long.valueOf(1), "nome", "endereco", "telefone", "email", "senha"));

        ResponseEntity<Usuario> result = usuarioController.findById(Long.valueOf(1));
        Assert.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testCadastrar() throws Exception {
        when(usuarioService.save(any())).thenReturn(new Usuario(Long.valueOf(1), "nome", "endereco", "telefone", "email", "senha"));

        ResponseEntity<Usuario> result = usuarioController.cadastrar(new Usuario(Long.valueOf(1), "nome", "endereco", "telefone", "email", "senha"));
        Assert.assertEquals(201, result.getStatusCodeValue());
    }

    @Test
    public void testAtualizar() throws Exception {
        when(usuarioService.update(any())).thenReturn(new Usuario(Long.valueOf(1), "nome", "endereco", "telefone", "email", "senha"));

        ResponseEntity<Usuario> result = usuarioController.atualizar(new Usuario(Long.valueOf(1), "nome", "endereco", "telefone", "email", "senha"), Long.valueOf(1));
        Assert.assertEquals(201, result.getStatusCodeValue());
    }

    @Test
    public void testDeletar() throws Exception {
        when(usuarioService.delete(anyLong())).thenReturn(null);

        ResponseEntity<?> result = usuarioController.deletar(Long.valueOf(1));
        Assert.assertEquals(null, result);
    }
}