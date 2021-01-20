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
public class Usuario {
    @ApiModelProperty(value = "Código de Identificação")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "Nome completo")
    @NotBlank(message = "O nome do usuário deve ser informado")
    private String nome;

    @ApiModelProperty(value = "Endereço")
    @NotBlank(message = "Informe o endereço do usuário.")
    private String endereco;

    @ApiModelProperty(value = "Telefone")
    private String telefone;

    @ApiModelProperty(value = "Email")
    @NotBlank(message = "Informe o campo de email")
    private String email;

    @ApiModelProperty(value = "senha")
    @NotBlank(message = "Digite a senha do usuário")
    private String senha;
}