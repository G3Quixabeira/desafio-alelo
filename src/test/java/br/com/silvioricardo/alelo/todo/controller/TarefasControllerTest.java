package br.com.silvioricardo.alelo.todo.controller;

import br.com.silvioricardo.alelo.todo.model.Tarefa;
import br.com.silvioricardo.alelo.todo.model.Usuario;
import br.com.silvioricardo.alelo.todo.repository.TarefasRepository;
import br.com.silvioricardo.alelo.todo.service.TarefasService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.mockito.Mockito.*;

public class TarefasControllerTest {
    @Mock
    TarefasService tarefasService;
    @Mock
    TarefasRepository tarefasRepository;
    @InjectMocks
    TarefasController tarefasController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() throws Exception {
        ResponseEntity<List<Tarefa>> result = tarefasController.findAll();
        Assert.assertEquals(204, result.getStatusCodeValue());
    }

    @Test
    public void testFindById() throws Exception {
        when(tarefasService.findById(anyLong())).thenReturn(new Tarefa(Long.valueOf(1), new Usuario(Long.valueOf(1), "nome", "endereco", "telefone", "email", "senha"), "descricao", new GregorianCalendar(2021, Calendar.JANUARY, 20, 17, 25).getTime(), new GregorianCalendar(2021, Calendar.JANUARY, 20, 17, 25).getTime(), Integer.valueOf(0)));

        ResponseEntity<Tarefa> result = tarefasController.findById(Long.valueOf(1));
        Assert.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testCadastrar() throws Exception {
        when(tarefasService.save(any())).thenReturn(new Tarefa(Long.valueOf(1), new Usuario(Long.valueOf(1), "nome", "endereco", "telefone", "email", "senha"), "descricao", new GregorianCalendar(2021, Calendar.JANUARY, 20, 17, 25).getTime(), new GregorianCalendar(2021, Calendar.JANUARY, 20, 17, 25).getTime(), Integer.valueOf(0)));

        ResponseEntity<Tarefa> result = tarefasController.cadastrar(new Tarefa(Long.valueOf(1), new Usuario(Long.valueOf(1), "nome", "endereco", "telefone", "email", "senha"), "descricao", new GregorianCalendar(2021, Calendar.JANUARY, 20, 17, 25).getTime(), new GregorianCalendar(2021, Calendar.JANUARY, 20, 17, 25).getTime(), Integer.valueOf(0)));
        Assert.assertEquals(201, result.getStatusCodeValue());
    }

    @Test
    public void testAtualizar() throws Exception {
        when(tarefasService.update(any())).thenReturn(new Tarefa(Long.valueOf(1), new Usuario(Long.valueOf(1), "nome", "endereco", "telefone", "email", "senha"), "descricao", new GregorianCalendar(2021, Calendar.JANUARY, 20, 17, 25).getTime(), new GregorianCalendar(2021, Calendar.JANUARY, 20, 17, 25).getTime(), Integer.valueOf(0)));

        ResponseEntity<Tarefa> result = tarefasController.atualizar(new Tarefa(Long.valueOf(1), new Usuario(Long.valueOf(1), "nome", "endereco", "telefone", "email", "senha"), "descricao", new GregorianCalendar(2021, Calendar.JANUARY, 20, 17, 25).getTime(), new GregorianCalendar(2021, Calendar.JANUARY, 20, 17, 25).getTime(), Integer.valueOf(0)));
        Assert.assertEquals(201, result.getStatusCodeValue());
    }

    @Test
    public void testDeletar() throws Exception {
        when(tarefasService.delete(anyLong())).thenReturn(null);

        ResponseEntity<?> result = tarefasController.deletar(Long.valueOf(1));
        Assert.assertEquals(null, result);
    }
}