### Implantação
O projeto está configurado para build totalmente automatizada via Maven

A base de dados é H2 e com persistência local na pasta '.data/Banco.db' já inicializada.

Build via jar
>mvn package

Build para deploy em servidor de aplicação
>mvn compile war:war

### Payload Json para Testes
O Web Service utiliza o padrão REST de retorno para o processamento das requisições enviadas:

    200 - Sucesso
    201 - Registro criado, incluído com sucesso.
    204 - Sem conteúdo para retornar
    400 - Dados enviados em formato incorreto
    404 - Registro não encontrado para a operação
    409 - Conflito, já existe registro.
    500 - Erro genérico no servidor, verificar aplicação

**Usuário** /usuarios [GET/POST/PUT/DELETE]
>[GET] Retorna lista de usuários

>[POST] Adiciona um usuário
```json
{
  "email": "email@email.com",
  "endereco": "ENDEREÇO DO CARINHA",
  "nome": "Carinha da Silva",
  "senha": "hahahhaha",
  "telefone": "9899999233"
}
```

>[PUT] Atualiza um usuário /{id}
```json
{
  "email": "email@email.com",
  "endereco": "Novo endereço",
  "senha": "novasenha"
}
```

>[DELETE] Apaga um usuário com base na id /{id}

**Tarefas** /tarefas [GET/POST/PUT/DELETE]
>[GET] Retorna lista de tarefas

>[POST] Adiciona uma tarefa
```json
{
  "descricao": "Tomar água",
  "usuario": {
    "id": 1
  }
}
```

>[PUT] Atualiza uma tarefa com base na id /{id}
```json
{
  "dataConcluida": "2021-01-20T20:15:45.093Z",
  "dataPrevista": "2021-01-20T20:16:45.093Z",
  "id": 1,
  "status": 1,
  "usuario": {
    "id": 1
  }
}
```

>[DELETE] Apaga uma tarefa com base na id /{id}