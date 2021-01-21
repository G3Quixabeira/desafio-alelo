package br.com.silvioricardo.alelo.todo.service;

import br.com.silvioricardo.alelo.todo.model.Lista;
import br.com.silvioricardo.alelo.todo.repository.ListasRepository;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ListasService {
    private final ListasRepository listasRepository;

    public Lista save(Lista lista){
        return listasRepository.save(lista);
    }

    public Lista update(Lista lista){
        if(lista.getId()==null) {
            throw new ServiceException("Por favor, informe a Id da lista que será atualizada.");
        }
        return listasRepository.findById(lista.getId()).map(listaSalva -> {
            return listasRepository.save(lista);
        }).orElseThrow(()->new ServiceException("Lista com id "+ lista.getId() + " inexistente"));
    }

    public ResponseEntity<?> delete(Long id){
        return listasRepository.findById(id)
                .map(record -> {
                    listasRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    public Lista findById(Long id){
        return listasRepository.findById(id).orElseThrow(()->new ServiceException("Lista com id "+ id + " inexistente"));
    }

    public Lista findByUserId(Long usuarioId){
        return listasRepository.findByUsuarioId(usuarioId).orElseThrow(()->new ServiceException("Lista para o usuário id "+ usuarioId + " inexistentes"));
    }
}
