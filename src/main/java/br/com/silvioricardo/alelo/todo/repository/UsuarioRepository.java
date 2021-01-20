package br.com.silvioricardo.alelo.todo.repository;

import br.com.silvioricardo.alelo.todo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Usuario findById(long id);

    @Query("select usuario from Usuario usuario where usuario.email=:email")
    Optional<Usuario>findByEmail(String email);
}
