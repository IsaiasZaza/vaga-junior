# Passo a passo para entregar o desafio

## 1. Fork no GitHub

**Automático (recomendado)** — na raiz do projeto, após autorizar o GitHub no navegador:

```powershell
.\scripts\publicar-fork.ps1
```

O script faz login (se precisar), cria o fork, configura `origin` e dá `git push`.

**Manual no site:** https://github.com/merito-es/vaga-junior → **Fork** → sua conta.  
URL do fork: `https://github.com/SEU_USUARIO/vaga-junior.git`

## 2. Apontar o Git para o seu fork

No PowerShell, na pasta do projeto (substitua `SEU_USUARIO`):

```powershell
git remote -v
```

- Se existir **`origin`** apontando para `merito-es/vaga-junior`, renomeie:  
  `git remote rename origin upstream`
- Se **`upstream`** já existir (erro *remote upstream already exists*), **pule** o rename — isso já está certo.

Depois adicione o seu fork (só se ainda não houver `origin`):

```powershell
git remote add origin https://github.com/SEU_USUARIO/vaga-junior.git
git remote -v
```

Você deve ver `origin` → seu fork e `upstream` → `merito-es/vaga-junior`.

## 3. Enviar os commits

```powershell
git push -u origin main
```

**Não** faça push para `upstream` (repositório da empresa), salvo instrução contrária.

## 4. Conferir antes de enviar o link

```powershell
.\mvnw.cmd clean test
.\mvnw.cmd spring-boot:run
```

Teste a API com [exemplos-curl.md](exemplos-curl.md).

## 5. O que enviar na vaga

- Link do **seu fork** no GitHub (branch `main` com os commits da solução).  
- Opcional: mencionar que a API sobe com `.\mvnw.cmd spring-boot:run` e que os dados persistem em `./data/posto`.

## Commits desta solução

Histórico organizado desde `chore: estrutura inicial Maven e Spring Boot` até `docs: README, exemplos cURL e instruções de execução`.
