# Compliance & Quality Assurance — M8Music API

---

## Integrantes

| Nome | RM | Função |
|---|---|---|
| Julia Rodrigues | RM 559781 | Project Analyst |
| Henrique Batista | RM 99742 | Back-End Developer |
| Felipe Gonçalves | RM 559175 | Front-End Developer & Designer |

---
## Projeto

O M8Music é uma solução digital voltada para conectar músicos, estabelecimentos e público em uma plataforma interativa.

Para esta entrega, foi utilizado o módulo de **Músicos e Eventos**, desenvolvido como uma API Java Spring Boot publicada no Azure App Service e conectada ao Azure SQL Database.

A API permite realizar operações CRUD para músicos e eventos, além de manter o relacionamento entre as tabelas `musicos` e `eventos`.

---

## Parte A — Plano de Testes Manuais

Os testes manuais foram criados no **Azure Boards**, dentro da organização e do projeto utilizados pela equipe.

Os casos de teste foram documentados como **Test Cases**, contemplando:

- Testes planejados;
- Dados de entrada;
- Dados de saída esperados;
- Procedimento de execução.

### Funcionalidades cobertas

| Código | Caso de teste |
|---|---|
| CT01 | Cadastrar músico com dados válidos |
| CT02 | Listar músicos cadastrados |
| CT03 | Buscar músico por ID |
| CT04 | Atualizar músico existente |
| CT05 | Cadastrar evento vinculado a músico |
| CT06 | Listar eventos cadastrados |
| CT07 | Buscar evento por ID |
| CT08 | Atualizar evento existente |

### Link do Azure Boards

https://dev.azure.com/RM559781/MMusic

> Observação: para acesso aos testes manuais, o professor precisa estar como membro da organização/projeto no Azure DevOps.

---

## Parte B — Testes Automatizados

Como o sistema é composto por uma API sem interface gráfica, foi utilizado o **Postman** para automação dos testes.

Foram criados 4 casos de testes automatizados para validar os principais fluxos da API:

| Código | Teste automatizado | Endpoint |
|---|---|---|
| TA01 | Cadastrar músico | POST /musicos |
| TA02 | Buscar músico por ID | GET /musicos/{id} |
| TA03 | Cadastrar evento | POST /eventos |
| TA04 | Buscar evento por ID | GET /eventos/{id} |

---

## Validações automatizadas

Os testes automatizados validam:

- Status HTTP da resposta;
- Retorno em formato JSON;
- Existência de ID gerado;
- Dados retornados pela API;
- Busca por ID;
- Cadastro de evento vinculado a músico;
- Uso de variáveis de collection no Postman.

Resultado da execução no Postman:

```text
Passed: 24
Failed: 0
Errors: 0
Skipped: 0
Variáveis utilizadas no Postman
Variável	Descrição
baseUrl	URL base da API publicada
musicoId	ID do músico criado durante a execução
eventoId	ID do evento criado durante a execução

URL base utilizada:

https://m8music-api-julia-a9gsg2byccg5hfa0.brazilsouth-01.azurewebsites.net
Como executar os testes automatizados
Abrir o Postman;
Importar a collection m8music-quality-tests.postman_collection.json;
Verificar se a variável baseUrl está configurada;
Executar a collection pelo Collection Runner;
Validar se todos os testes passam com sucesso.

Ordem de execução:

1. TA01 - Cadastrar músico
2. TA02 - Buscar músico por ID
3. TA03 - Cadastrar evento
4. TA04 - Buscar evento por ID
Arquivo da collection

A collection exportada do Postman está disponível em:

postman/m8music-quality-tests.postman_collection.json
API utilizada
https://m8music-api-julia-a9gsg2byccg5hfa0.brazilsouth-01.azurewebsites.net

Endpoints principais:

/musicos
/eventos
Branch de entrega

A entrega de Compliance & Quality Assurance foi disponibilizada na branch:

develop
Observação

Os testes foram criados controlados e valores esperados previamente definidos, conforme solicitado na atividade.