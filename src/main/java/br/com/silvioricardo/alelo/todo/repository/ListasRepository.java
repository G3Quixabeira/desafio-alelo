package br.com.silvioricardo.alelo.todo.repository;

import br.com.silvioricardo.alelo.todo.model.Lista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ListasRepository extends JpaRepository<Lista,Long> {
    Lista findById(long id);

    @Query("select listas from Lista listas where listas.usuarioId=:usuarioId")
    Optional<Lista>findByUsuarioId(Long usuarioId);
}
