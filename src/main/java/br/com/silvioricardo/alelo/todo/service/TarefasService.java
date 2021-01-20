package br.com.silvioricardo.alelo.todo.service;

import br.com.silvioricardo.alelo.todo.model.Tarefa;
import br.com.silvioricardo.alelo.todo.repository.TarefasRepository;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TarefasService {
    private final TarefasRepository tarefasRepository;

    public Tarefa save(Tarefa tarefa){
        return tarefasRepository.save(tarefa);
    }

    public Tarefa update(Tarefa tarefa){
        if(tarefa.getId()==null) {
            throw new ServiceException("Por favor, informe a Id da tarefa que será atualizada.");
        }
        return tarefasRepository.findById(tarefa.getId()).map(tarefaSalva -> {
            return tarefasRepository.save(tarefa);
        }).orElseThrow(()->new ServiceException("Tarefa com id "+ tarefa.getId() + " inexistente"));
    }

    public ResponseEntity<?> delete(Long id){
        return tarefasRepository.findById(id)
                .map(record -> {
                    tarefasRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    public Tarefa findById(Long id){
        return tarefasRepository.findById(id).orElseThrow(()->new ServiceException("Tarefa com id "+ id + " inexistente"));
    }

    public Tarefa findByUserId(Long usuarioId){
        return tarefasRepository.findByUsuarioId(usuarioId).orElseThrow(()->new ServiceException("Tarefa para o usuário id "+ usuarioId + " inexistentes"));
    }
}
