# 🔹 Desafio Técnico Júnior #1 – Cadastro e Consulta de Abastecimentos

## 🛠 Objetivo

Desenvolver uma aplicação simples em **Java** para cadastro e consulta de abastecimentos em um posto de combustível, com armazenamento em banco de dados e exibição dos dados via **Java Swing** ou **API REST**.

---

## 🚀 Como executar

**Pré-requisitos:** Java 17+ (o Maven vem pelo wrapper `mvnw` — não precisa instalar).

No PowerShell, na pasta do projeto:

```powershell
.\mvnw.cmd spring-boot:run
```

Se você já tiver Maven no PATH, também pode usar `mvn spring-boot:run`.

A API sobe em `http://localhost:8080`. Os dados ficam em `./data/posto` (H2 em arquivo) e **permanecem após reiniciar** a aplicação.

### Endpoints principais

| Recurso | Base URL |
|---------|----------|
| Tipos de combustível | `GET/POST /api/tipos-combustivel`, `GET/PUT/DELETE /api/tipos-combustivel/{id}` |
| Bombas | `GET/POST /api/bombas`, `GET/PUT/DELETE /api/bombas/{id}` |
| Abastecimentos | `GET/POST /api/abastecimentos`, `GET/PUT/DELETE /api/abastecimentos/{id}` |
| Consulta geral | `GET /api/consulta` |

**Exemplos cURL (todos os endpoints):** veja [docs/exemplos-curl.md](docs/exemplos-curl.md).

Fluxo rápido no PowerShell (com a API rodando):

```powershell
curl.exe -s -X POST http://localhost:8080/api/tipos-combustivel -H "Content-Type: application/json" -d '{"nome":"Gasolina Comum","precoPorLitro":5.89}'
curl.exe -s -X POST http://localhost:8080/api/bombas -H "Content-Type: application/json" -d '{"nome":"Bomba 01","tipoCombustivelId":1}'
curl.exe -s -X POST http://localhost:8080/api/abastecimentos -H "Content-Type: application/json" -d '{"bombaId":1,"dataAbastecimento":"2026-09-29","valorTotal":118.50,"litragem":20.000}'
curl.exe -s http://localhost:8080/api/consulta
```

### Estrutura do projeto

- `entity` — entidades JPA e relacionamentos
- `repository` — acesso a dados (Spring Data JPA)
- `service` — regras de negócio
- `controller` — API REST
- `dto` — contratos de entrada/saída da API

---

## 📌 Funcionalidades Implementadas

✅ Operaçoes basicas (Criar, Listar, Alterar, Deletar) de **Tipos de Combustível** 
- Nome - Texto
- Preço por litro

✅ Operaçoes basicas (Criar, Listar, Alterar, Deletar) de **Bombas de Combustível** (relacionadas a um tipo de combustível)
- Nome da bomba
- Combustivel que abastece

✅ Operaçoes basicas (Criar, Listar, Alterar, Deletar)  de **Abastecimentos** (com data, volume abastecido e valor total)
- Bomba que foi realizado o abastecimento
- Data do abastecimento
- Quantidade em valores
- Litragem
  
✅ **Consulta** de todos os dados cadastrados via **API REST** (`GET /api/consulta`)  
✅ Persistência em banco **H2 em arquivo** (dados mantidos após restart)  

---

## ✅ Requisitos Atendidos

- Projeto Java com **Maven** e pacotes organizados (`entity`, `repository`, `service`, `controller`)
- Relacionamentos entre entidades corretamente implementados
- **API HTTP REST** para cadastro e consulta (Spring Boot 3)
- Código comentado e organizado

---

## 🌟 Diferenciais Implementados

- API RESTful com `GET`, `POST`, `PUT` e `DELETE`
- Camada de repositório (JPA) + serviço + controllers
- Persistência em arquivo H2 após restart da aplicação
- Validação de entrada e tratamento de erros HTTP

---

## 👤 Autor

**Isaias Gonçalves** — isaias.legend@gmail.com

---

## 🧪 Como testei

1. `.\mvnw.cmd clean test` — contexto Spring e JPA sobem sem erro.
2. `.\mvnw.cmd spring-boot:run` — API em `http://localhost:8080`.
3. Fluxo manual com os comandos em [docs/exemplos-curl.md](docs/exemplos-curl.md) (criar combustível → bomba → abastecimento → `GET /api/consulta`).
4. Reinício da aplicação — dados permanecem em `./data/posto` (H2 em arquivo).

---

## 📬 Como entregar o desafio

A solução está implementada neste repositório. Para publicar no **seu fork** e enviar o link na vaga, siga [docs/ENTREGA.md](docs/ENTREGA.md) (fork → `git push` → link do GitHub).

Resumo exigido pelo desafio:

1. **Fork** de `merito-es/vaga-junior` na sua conta GitHub.
2. **Push** da branch `main` com os commits da solução.
3. Enviar o link do **repositório forkado**; projeto roda com `.\mvnw.cmd clean test` e `.\mvnw.cmd spring-boot:run`.

---
## 🔍 O que será avaliado

- Sua **comunicação**, especialmente ao surgir dúvidas ou obstáculos durante o desenvolvimento.
- **O processo de desenvolvimento** como um todo, e não apenas o resultado final.
- A clareza e organização dos **commits** realizados.
- Sua capacidade de **estruturar a solução em etapas**, mesmo que nem todos os requisitos sejam concluídos.

---

## 💡 Dicas para se sair bem

- Divida o desafio em **pequenas partes** e implemente **com calma**, focando em cada funcionalidade por vez.
- Use **commits claros e objetivos**, indicando exatamente o que foi alterado ou implementado.
- Em caso de dúvida, **comunique-se** — mostrar que você sabe buscar soluções é um ponto positivo.
- Mesmo que não finalize 100% dos requisitos, **a qualidade do seu processo será levada em conta**.

---
