package br.com.silvioricardo.alelo.todo.service;

import br.com.silvioricardo.alelo.todo.model.Tarefa;
import br.com.silvioricardo.alelo.todo.model.Usuario;
import br.com.silvioricardo.alelo.todo.repository.TarefasRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.*;

public class TarefasServiceTest {
    @Mock
    TarefasRepository tarefasRepository;
    @InjectMocks
    TarefasService tarefasService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() throws Exception {
        Tarefa result = tarefasService.save(new Tarefa(Long.valueOf(1), new Usuario(Long.valueOf(1), "nome", "endereco", "telefone", "email", "senha"), "descricao", new GregorianCalendar(2021, Calendar.JANUARY, 20, 17, 26).getTime(), new GregorianCalendar(2021, Calendar.JANUARY, 20, 17, 26).getTime(), Integer.valueOf(0)));
        Assert.assertEquals(new Tarefa(Long.valueOf(1), new Usuario(Long.valueOf(1), "nome", "endereco", "telefone", "email", "senha"), "descricao", new GregorianCalendar(2021, Calendar.JANUARY, 20, 17, 26).getTime(), new GregorianCalendar(2021, Calendar.JANUARY, 20, 17, 26).getTime(), Integer.valueOf(0)), result);
    }

    @Test
    public void testUpdate() throws Exception {
        Tarefa result = tarefasService.update(new Tarefa(Long.valueOf(1), new Usuario(Long.valueOf(1), "nome", "endereco", "telefone", "email", "senha"), "descricao", new GregorianCalendar(2021, Calendar.JANUARY, 20, 17, 26).getTime(), new GregorianCalendar(2021, Calendar.JANUARY, 20, 17, 26).getTime(), Integer.valueOf(0)));
        Assert.assertEquals(new Tarefa(Long.valueOf(1), new Usuario(Long.valueOf(1), "nome", "endereco", "telefone", "email", "senha"), "descricao", new GregorianCalendar(2021, Calendar.JANUARY, 20, 17, 26).getTime(), new GregorianCalendar(2021, Calendar.JANUARY, 20, 17, 26).getTime(), Integer.valueOf(0)), result);
    }

    @Test
    public void testDelete() throws Exception {
        ResponseEntity<?> result = tarefasService.delete(Long.valueOf(1));
        Assert.assertEquals(null, result);
    }

    @Test
    public void testFindById() throws Exception {
        Tarefa result = tarefasService.findById(Long.valueOf(1));
        Assert.assertEquals(new Tarefa(Long.valueOf(1), new Usuario(Long.valueOf(1), "nome", "endereco", "telefone", "email", "senha"), "descricao", new GregorianCalendar(2021, Calendar.JANUARY, 20, 17, 26).getTime(), new GregorianCalendar(2021, Calendar.JANUARY, 20, 17, 26).getTime(), Integer.valueOf(0)), result);
    }

    @Test
    public void testFindByUserId() throws Exception {
        when(tarefasRepository.findByUsuarioId(anyLong())).thenReturn(null);

        Tarefa result = tarefasService.findByUserId(Long.valueOf(1));
        Assert.assertEquals(new Tarefa(Long.valueOf(1), new Usuario(Long.valueOf(1), "nome", "endereco", "telefone", "email", "senha"), "descricao", new GregorianCalendar(2021, Calendar.JANUARY, 20, 17, 26).getTime(), new GregorianCalendar(2021, Calendar.JANUARY, 20, 17, 26).getTime(), Integer.valueOf(0)), result);
    }
}