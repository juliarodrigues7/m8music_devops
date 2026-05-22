
# M8Music API — DevOps Tools & Cloud Computing

API desenvolvida para o projeto **M&Music**, uma solução digital voltada para conectar músicos, estabelecimentos e público em uma plataforma interativa.

Nesta entrega, foi desenvolvido o módulo de **Músicos e Eventos**, com aplicação prática dos conceitos de DevOps, CI/CD, deploy automatizado e persistência de dados em nuvem.

---

## Integrantes

| Nome | RM | Função |
|---|---|---|
| Julia Rodrigues | RM 559781 | Project Analyst |
| Henrique Batista | RM 99742 | Back-End Developer |
| Felipe Gonçalves | RM 559175 | Front-End Developer & Designer |

---

## Descrição da Solução

O **M&Music** tem como objetivo facilitar a conexão entre músicos, estabelecimentos e ouvintes, promovendo maior visibilidade para artistas e melhor organização de eventos musicais.

Para esta entrega, foi selecionado o módulo de **gerenciamento de músicos e eventos**.

A API permite:

- Cadastrar músicos;
- Listar músicos;
- Buscar músico por ID;
- Atualizar músicos;
- Remover músicos;
- Cadastrar eventos;
- Listar eventos;
- Buscar evento por ID;
- Atualizar eventos;
- Remover eventos;
- Vincular eventos a músicos.

A solução foi publicada no **Azure App Service** e conectada ao **Azure SQL Database**, garantindo persistência de dados em nuvem.

---

## Tecnologias Utilizadas

- Java 21
- Spring Boot 4.0.6
- Maven
- Spring Data JPA
- Spring Validation
- Lombok
- SQL Server Driver
- GitHub
- Azure DevOps Pipelines
- Azure App Service
- Azure SQL Database
- Postman

---

## Arquitetura da Aplicação

A aplicação foi organizada em camadas:

```text
Controller
   ↓
Service
   ↓
Repository
   ↓
Azure SQL Database
```

| Camada     | Responsabilidade                                  |
| ---------- | ------------------------------------------------- |
| Controller | Recebe as requisições HTTP e retorna as respostas |
| Service    | Contém as regras de negócio                       |
| Repository | Realiza a comunicação com o banco de dados        |
| Model      | Representa as entidades da aplicação              |

---

## Modelo de Dados

A aplicação utiliza duas tabelas relacionadas:

```text
musicos 1 ---- N eventos
```

Ou seja:

* Um músico pode possuir vários eventos;
* Um evento pertence a um único músico.

### Tabela `musicos`

| Campo          | Tipo         | Descrição           |
| -------------- | ------------ | ------------------- |
| id             | BIGINT       | Identificador único |
| nome           | VARCHAR(100) | Nome do músico      |
| genero_musical | VARCHAR(100) | Gênero musical      |
| email          | VARCHAR(150) | E-mail de contato   |

### Tabela `eventos`

| Campo        | Tipo         | Descrição                             |
| ------------ | ------------ | ------------------------------------- |
| id           | BIGINT       | Identificador único                   |
| nome_evento  | VARCHAR(120) | Nome do evento                        |
| local_evento | VARCHAR(120) | Local do evento                       |
| data_evento  | DATE         | Data do evento                        |
| musico_id    | BIGINT       | Chave estrangeira da tabela `musicos` |

---

## Script SQL

```sql
CREATE TABLE musicos (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    genero_musical VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL
);

CREATE TABLE eventos (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    nome_evento VARCHAR(120) NOT NULL,
    local_evento VARCHAR(120) NOT NULL,
    data_evento DATE NOT NULL,
    musico_id BIGINT NOT NULL,
    CONSTRAINT fk_eventos_musicos
        FOREIGN KEY (musico_id)
        REFERENCES musicos(id)
);
```

---

## Configuração do Projeto

As credenciais do banco de dados não ficam expostas no código. A aplicação utiliza variáveis de ambiente.

Arquivo `application.properties`:

```properties
spring.application.name=m8music

server.port=8080

spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.SQLServerDialect
spring.jpa.open-in-view=false
```

### Variáveis necessárias

| Variável    | Descrição                   |
| ----------- | --------------------------- |
| DB_URL      | URL JDBC do banco Azure SQL |
| DB_USERNAME | Usuário do banco            |
| DB_PASSWORD | Senha do banco              |

Exemplo:

```text
DB_URL=jdbc:sqlserver://SEU_SERVIDOR.database.windows.net:1433;databaseName=m8musicdb;encrypt=true;trustServerCertificate=true;loginTimeout=60;
DB_USERNAME=SEU_USUARIO
DB_PASSWORD=SUA_SENHA
```

---

## Como Executar Localmente

### 1. Clonar o repositório

```bash
git clone https://github.com/juliarodrigues7/m8music_devops.git
```

### 2. Acessar a pasta do projeto

```bash
cd m8music_devops
```

### 3. Configurar as variáveis de ambiente

Configure no ambiente local:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
```

### 4. Executar a aplicação

```bash
mvn spring-boot:run
```

A aplicação ficará disponível em:

```text
http://localhost:8080
```

---

## API Publicada

A API está publicada no Azure App Service:

```text
https://m8music-api-julia-a9gsg2byccg5hfa0.brazilsouth-01.azurewebsites.net
```

---

# Endpoints

## Músicos

### Listar músicos

```http
GET /musicos
```

URL:

```text
https://m8music-api-julia-a9gsg2byccg5hfa0.brazilsouth-01.azurewebsites.net/musicos
```

---

### Buscar músico por ID

```http
GET /musicos/{id}
```

Exemplo:

```text
https://m8music-api-julia-a9gsg2byccg5hfa0.brazilsouth-01.azurewebsites.net/musicos/1
```

---

### Cadastrar músico

```http
POST /musicos
```

Body:

```json
{
  "nome": "Lucas Andrade",
  "generoMusical": "MPB",
  "email": "lucas@mmusic.com"
}
```

---

### Atualizar músico

```http
PUT /musicos/{id}
```

Body:

```json
{
  "nome": "Lucas Andrade Silva",
  "generoMusical": "Pop Rock",
  "email": "lucas.silva@mmusic.com"
}
```

---

### Remover músico

```http
DELETE /musicos/{id}
```

Resposta esperada:

```text
204 No Content
```

---

## Eventos

### Listar eventos

```http
GET /eventos
```

URL:

```text
https://m8music-api-julia-a9gsg2byccg5hfa0.brazilsouth-01.azurewebsites.net/eventos
```

---

### Buscar evento por ID

```http
GET /eventos/{id}
```

Exemplo:

```text
https://m8music-api-julia-a9gsg2byccg5hfa0.brazilsouth-01.azurewebsites.net/eventos/1
```

---

### Cadastrar evento

```http
POST /eventos
```

Body:

```json
{
  "nomeEvento": "Show Acústico M&Music",
  "localEvento": "Bar Central",
  "dataEvento": "2026-05-30",
  "musicoId": 1
}
```

Observação: o campo `musicoId` deve corresponder a um músico já cadastrado.

---

### Atualizar evento

```http
PUT /eventos/{id}
```

Body:

```json
{
  "nomeEvento": "Show M&Music Experience",
  "localEvento": "Casa de Shows Central",
  "dataEvento": "2026-06-10",
  "musicoId": 1
}
```

---

### Remover evento

```http
DELETE /eventos/{id}
```

Resposta esperada:

```text
204 No Content
```

---

## Testes no Postman

Ordem recomendada de testes:

1. `GET /musicos`
2. `POST /musicos`
3. `GET /musicos/{id}`
4. `PUT /musicos/{id}`
5. `GET /eventos`
6. `POST /eventos`
7. `GET /eventos/{id}`
8. `PUT /eventos/{id}`

Após os testes, os dados podem ser verificados no Azure SQL com:

```sql
SELECT * FROM musicos;
SELECT * FROM eventos;
```

---

# Pipeline CI/CD

A pipeline foi configurada no **Azure DevOps** por meio do arquivo:

```text
azure-pipelines.yml
```

A esteira possui duas etapas principais:

```text
CI - Integração Contínua
CD - Entrega Contínua
```

---

## Desenho da Pipeline

```text
GitHub
  ↓
Azure DevOps Pipeline
  ↓
CI - Build da aplicação
  ↓
Maven clean package
  ↓
Geração do artefato .jar
  ↓
Publicação do artefato
  ↓
CD - Deploy no Azure App Service
  ↓
API publicada em nuvem
  ↓
Azure SQL Database
```

---

## Etapa CI

A etapa de CI realiza:

1. Checkout do código-fonte no GitHub;
2. Configuração do Java 21;
3. Build da aplicação com Maven;
4. Geração do arquivo `.jar`;
5. Publicação do artefato.

Comando executado:

```bash
mvn clean package -DskipTests
```

---

## Etapa CD

A etapa de CD realiza:

1. Consumo do artefato gerado na etapa CI;
2. Deploy automático no Azure App Service;
3. Disponibilização da API em uma URL pública.

App Service utilizado:

```text
m8music-api-julia
```

---

## Benefícios da Pipeline

A pipeline automatiza o fluxo de entrega da aplicação:

```text
Commit no GitHub
→ Build automático
→ Geração do artefato
→ Deploy automático
→ API online no Azure
```

Com isso, o processo de publicação se torna mais rápido, padronizado e menos sujeito a erros manuais.

---

## Como Executar a Pipeline

No Azure DevOps:

```text
Pipelines
→ Selecionar a pipeline do projeto
→ Run pipeline
```

A pipeline também pode ser executada automaticamente a cada alteração enviada para a branch `main`, pois o YAML possui:

```yaml
trigger:
- main
```

---

## Evidência de Persistência em Nuvem

A persistência dos dados é realizada no **Azure SQL Database**.

Após cadastrar um músico e um evento pela API, os dados podem ser consultados diretamente no banco em nuvem:

```sql
SELECT * FROM musicos;
SELECT * FROM eventos;
```

Esse processo comprova que a API publicada no Azure App Service está conectada ao banco de dados em nuvem e persistindo as informações corretamente.

---

## Links da Entrega

GitHub:

```text
https://github.com/juliarodrigues7/m8music_devops
```

YouTube:

```text
INSERIR_LINK_DO_YOUTUBE
```

API publicada:

```text
https://m8music-api-julia-a9gsg2byccg5hfa0.brazilsouth-01.azurewebsites.net
```

---

## Observações Finais

Esta entrega demonstra a aplicação prática dos conceitos de DevOps por meio de uma esteira CI/CD no Azure DevOps.

O projeto contempla:

* Código versionado no GitHub;
* Pipeline CI/CD configurada;
* Build automatizado;
* Deploy automatizado;
* API publicada no Azure App Service;
* Banco de dados em nuvem;
* Duas tabelas relacionadas;
* Testes via Postman;
* Persistência dos dados no Azure SQL Database.

