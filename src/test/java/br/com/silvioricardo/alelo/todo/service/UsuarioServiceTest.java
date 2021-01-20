package br.com.silvioricardo.alelo.todo.service;

import br.com.silvioricardo.alelo.todo.model.Usuario;
import br.com.silvioricardo.alelo.todo.repository.UsuarioRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

public class UsuarioServiceTest {
    @Mock
    UsuarioRepository usuarioRepository;
    @InjectMocks
    UsuarioService usuarioService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() throws Exception {
        when(usuarioRepository.findByEmail(anyString())).thenReturn(null);

        Usuario result = usuarioService.save(new Usuario(Long.valueOf(1), "nome", "endereco", "telefone", "email", "senha"));
        Assert.assertEquals(new Usuario(Long.valueOf(1), "nome", "endereco", "telefone", "email", "senha"), result);
    }

    @Test
    public void testUpdate() throws Exception {
        when(usuarioRepository.findByEmail(anyString())).thenReturn(null);

        Usuario result = usuarioService.update(new Usuario(Long.valueOf(1), "nome", "endereco", "telefone", "email", "senha"));
        Assert.assertEquals(new Usuario(Long.valueOf(1), "nome", "endereco", "telefone", "email", "senha"), result);
    }

    @Test
    public void testDelete() throws Exception {
        ResponseEntity<?> result = usuarioService.delete(Long.valueOf(1));
        Assert.assertEquals(null, result);
    }

    @Test
    public void testFindById() throws Exception {
        Usuario result = usuarioService.findById(Long.valueOf(1));
        Assert.assertEquals(new Usuario(Long.valueOf(1), "nome", "endereco", "telefone", "email", "senha"), result);
    }
}
