package br.com.silvioricardo.alelo.todo.repository;

import br.com.silvioricardo.alelo.todo.model.Tarefa;
import br.com.silvioricardo.alelo.todo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TarefasRepository extends JpaRepository<Tarefa,Long> {
    Tarefa findById(long id);

    @Query("select tarefas from Tarefa tarefas where tarefas.usuario.id=:usuarioId")
    Optional<Tarefa>findByUsuarioId(Long usuarioId);
}