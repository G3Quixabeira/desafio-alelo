package br.com.silvioricardo.alelo.todo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tarefa {
    @ApiModelProperty(value = "Código de Identificação")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "Id do Usuário")
    @NotBlank(message = "Informe o id do usuário")
    @OneToOne
    private Usuario usuario;

    @ApiModelProperty(value = "Descrição")
    @NotBlank(message = "O nome do usuário deve ser informado")
    private String descricao;

    @ApiModelProperty(value = "Data Prevista para conclusão")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPrevista;

    @ApiModelProperty(value = "Data de conclusão")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataConcluida;

    @ApiModelProperty(value = "Status")
    private Integer status;
}