package br.com.silvioricardo.alelo.todo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Lista {
    @ApiModelProperty(value = "Código de Identificação")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "Id do Usuário")
    @NotBlank(message = "Informe o id do usuário")
    @OneToOne
    private Usuario usuario;

    @ApiModelProperty(value = "Nome da lista")
    @NotBlank(message = "O nome da lista não deve ser vazia")
    private String nome;
}