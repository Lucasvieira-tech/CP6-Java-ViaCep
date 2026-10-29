# CP6 - Java ViaCEP

## Descrição do projeto

Este projeto foi desenvolvido para o Checkpoint 6 da FIAP, utilizando Java e Spring Boot.

A aplicação consulta endereços por meio da API pública ViaCEP e permite cadastrar, listar e pesquisar endereços armazenados em um banco de dados Oracle.

## Tecnologias utilizadas

- Java
- Spring Boot
- Maven
- Oracle Database
- JDBC
- API pública ViaCEP
- IntelliJ IDEA
- Insomnia para testes dos endpoints

## API utilizada

**ViaCEP:** https://viacep.com.br/

A API ViaCEP permite consultar informações de endereços brasileiros a partir do CEP, retornando dados como logradouro, bairro, cidade e estado.

## Banco de dados

O projeto utiliza o banco de dados Oracle da FIAP, com uma tabela chamada `ENDERECO`.

A tabela armazena os seguintes campos:

- `ID`: identificador do endereço.
- `CEP`: Código de Endereçamento Postal.
- `LOGRADOURO`: rua ou avenida.
- `BAIRRO`: bairro do endereço.
- `LOCALIDADE`: cidade do endereço.
- `UF`: sigla do estado.
- `REGIAO`: região do Brasil.

Antes de executar o projeto, é necessário configurar as credenciais de acesso ao Oracle no arquivo `ConnectionFactory.java`.

## Endpoints

A aplicação é executada localmente na porta `8080`.

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/enderecos/{cep}` | Consulta um CEP na API ViaCEP |
| GET | `/enderecos` | Lista os endereços cadastrados no Oracle |
| POST | `/enderecos` | Cadastra um endereço no Oracle |
| GET | `/enderecos/id/{id}` | Busca um endereço pelo ID |
| GET | `/enderecos/buscar?campo=cep&valor=01001000` | Busca endereços por CEP, UF ou bairro |

## Exemplo de cadastro

Envie uma requisição `POST` para:

`http://localhost:8080/enderecos`

Com o seguinte JSON:

```json
{
  "cep": "01001000",
  "logradouro": "Praça da Sé",
  "bairro": "Sé",
  "cidade": "São Paulo",
  "uf": "SP",
  "regiao": "SUDESTE"
}
```

Se o cadastro for realizado com sucesso, a aplicação retornará o status HTTP `201 Created`.

## Exemplos de consulta de CEP

As consultas abaixo utilizam o endpoint `/enderecos/{cep}` para consultar dados por meio da API ViaCEP. Essas consultas não cadastram automaticamente os endereços no Oracle.

### 1. Praça da Sé — São Paulo (SP)

**Requisição:**

```http
GET http://localhost:8080/enderecos/01001000
```

**Exemplo de resposta JSON:**

```json
{
  "cep": "01001000",
  "logradouro": "Praça da Sé",
  "bairro": "Sé",
  "cidade": "São Paulo",
  "uf": "SP",
  "regiao": "SUDESTE"
}
```

### 2. Avenida Paulista — São Paulo (SP)

**Requisição:**

```http
GET http://localhost:8080/enderecos/01310100
```

**Exemplo de resposta JSON:**

```json
{
  "cep": "01310-100",
  "logradouro": "Avenida Paulista",
  "bairro": "Bela Vista",
  "cidade": "São Paulo",
  "uf": "SP",
  "regiao": "SUDESTE"
}
```

### 3. Copacabana — Rio de Janeiro (RJ)

**Requisição:**

```http
GET http://localhost:8080/enderecos/22070002
```

**Exemplo de resposta JSON:**

```json
{
  "cep": "22070-002",
  "logradouro": "Rua Aires Saldanha",
  "bairro": "Copacabana",
  "cidade": "Rio de Janeiro",
  "uf": "RJ",
  "regiao": "SUDESTE"
}
```

**Observação:** os JSONs são exemplos ilustrativos. Os valores retornados dependem da resposta real da API ViaCEP e do mapeamento implementado no projeto.

## Outros exemplos de requisições

### Listar endereços cadastrados

```http
GET http://localhost:8080/enderecos
```

Retorna uma lista de endereços armazenados no Oracle.

### Buscar endereço pelo ID

```http
GET http://localhost:8080/enderecos/id/2
```

Retorna o endereço correspondente ao ID informado, caso exista.

### Buscar endereço pelo CEP no Oracle

```http
GET http://localhost:8080/enderecos/buscar?campo=cep&valor=01001000
```

Retorna os endereços cadastrados que correspondem ao CEP informado.

### Buscar endereço pela UF

```http
GET http://localhost:8080/enderecos/buscar?campo=uf&valor=SP
```

Retorna os endereços cadastrados com a UF informada.

### Buscar endereço pelo bairro

```http
GET http://localhost:8080/enderecos/buscar?campo=bairro&valor=Copacabana
```

Retorna os endereços cadastrados que correspondem ao bairro informado.

### Consultar um CEP inexistente

```http
GET http://localhost:8080/enderecos/00000000
```

A aplicação retorna a mensagem `CEP não encontrado na ViaCEP.` quando o CEP não é encontrado.

## Como executar o projeto

1. Clone o repositório do GitHub.
2. Abra o projeto no IntelliJ IDEA.
3. Configure o acesso ao banco de dados Oracle da FIAP no arquivo `ConnectionFactory.java`.
4. Verifique se a tabela `ENDERECO` existe no banco de dados.
5. Execute a classe principal do Spring Boot.
6. Utilize o Insomnia ou o navegador para testar os endpoints.

**Importante:** configure suas credenciais localmente e não publique a senha do banco de dados no GitHub.

## Integrantes

- Lucas Marti Zapater Vieira — RM 571053
- Christian Versellezi Barbuto — RM 568677
