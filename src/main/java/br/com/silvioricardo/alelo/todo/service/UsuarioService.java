package br.com.silvioricardo.alelo.todo.service;

import br.com.silvioricardo.alelo.todo.model.Usuario;
import br.com.silvioricardo.alelo.todo.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public Usuario save(Usuario usuario){
        verifyEmail(usuario.getEmail());
        verifyPassword(usuario.getSenha());

        return usuarioRepository.save(usuario);
    }

    public Usuario update(Usuario usuario){
        if(usuario.getId()==null) {
            throw new ServiceException("Por favor, informe a Id do usuário que será atualizado.");
        }
        return usuarioRepository.findById(usuario.getId()).map(usuarioSalvo -> {
            if(!usuario.getEmail().equals(usuarioSalvo.getEmail())) verifyEmail(usuario.getEmail());

            return usuarioRepository.save(usuario);
        }).orElseThrow(()->new ServiceException("Usuário com id "+ usuario.getId() + " inexistente"));
    }

    public ResponseEntity<?> delete(Long id){
        return usuarioRepository.findById(id)
                .map(record -> {
                    usuarioRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    public Usuario findById(Long id){
        return usuarioRepository.findById(id).orElseThrow(()->new ServiceException("Usuário com id "+ id + " inexistente"));
    }

    private void verifyPassword(String senha){
        if(senha.length() < 6) {
            throw new ServiceException("A senha de conter pelo menos 6 caracteres.");
        }
    }

    private void verifyEmail(String email){
        if(usuarioRepository.findByEmail(email).isPresent()){
            throw new ServiceException("Email já cadastrado");
        }
    }
}
