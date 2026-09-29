# Exemplos cURL — API Abastecimentos

Base: `http://localhost:8080`

No **PowerShell**, use `curl.exe` (o alias `curl` chama outro comando).  
No **CMD**, use `\` no fim da linha ou o caractere `^` como no README.

Ordem sugerida: tipos de combustível → bombas → abastecimentos (substitua `{id}` pelos IDs retornados).

---

## Consulta geral

```bash
curl.exe -s http://localhost:8080/api/consulta
```

---

## Tipos de combustível — `/api/tipos-combustivel`

### Listar

```bash
curl.exe -s http://localhost:8080/api/tipos-combustivel
```

### Buscar por ID

```bash
curl.exe -s http://localhost:8080/api/tipos-combustivel/1
```

### Criar

```bash
curl.exe -s -X POST http://localhost:8080/api/tipos-combustivel ^
  -H "Content-Type: application/json" ^
  -d "{\"nome\":\"Gasolina Comum\",\"precoPorLitro\":5.89}"
```

```bash
curl.exe -s -X POST http://localhost:8080/api/tipos-combustivel ^
  -H "Content-Type: application/json" ^
  -d "{\"nome\":\"Etanol\",\"precoPorLitro\":3.79}"
```

### Atualizar

```bash
curl.exe -s -X PUT http://localhost:8080/api/tipos-combustivel/1 ^
  -H "Content-Type: application/json" ^
  -d "{\"nome\":\"Gasolina Comum\",\"precoPorLitro\":6.10}"
```

### Excluir

```bash
curl.exe -s -X DELETE http://localhost:8080/api/tipos-combustivel/2 -w "\nHTTP %{http_code}\n"
```

(Resposta esperada: `204 No Content`. Não exclua tipo com bombas vinculadas.)

---

## Bombas — `/api/bombas`

### Listar

```bash
curl.exe -s http://localhost:8080/api/bombas
```

### Buscar por ID

```bash
curl.exe -s http://localhost:8080/api/bombas/1
```

### Criar

`tipoCombustivelId` deve existir (ex.: `1`).

```bash
curl.exe -s -X POST http://localhost:8080/api/bombas ^
  -H "Content-Type: application/json" ^
  -d "{\"nome\":\"Bomba 01\",\"tipoCombustivelId\":1}"
```

```bash
curl.exe -s -X POST http://localhost:8080/api/bombas ^
  -H "Content-Type: application/json" ^
  -d "{\"nome\":\"Bomba 02\",\"tipoCombustivelId\":1}"
```

### Atualizar

```bash
curl.exe -s -X PUT http://localhost:8080/api/bombas/1 ^
  -H "Content-Type: application/json" ^
  -d "{\"nome\":\"Bomba 01 - Faixa A\",\"tipoCombustivelId\":1}"
```

### Excluir

```bash
curl.exe -s -X DELETE http://localhost:8080/api/bombas/2 -w "\nHTTP %{http_code}\n"
```

(Não exclua bomba com abastecimentos vinculados.)

---

## Abastecimentos — `/api/abastecimentos`

### Listar

```bash
curl.exe -s http://localhost:8080/api/abastecimentos
```

### Buscar por ID

```bash
curl.exe -s http://localhost:8080/api/abastecimentos/1
```

### Criar

`bombaId` deve existir. Data no formato `AAAA-MM-DD`.

```bash
curl.exe -s -X POST http://localhost:8080/api/abastecimentos ^
  -H "Content-Type: application/json" ^
  -d "{\"bombaId\":1,\"dataAbastecimento\":\"2026-09-29\",\"valorTotal\":118.50,\"litragem\":20.000}"
```

```bash
curl.exe -s -X POST http://localhost:8080/api/abastecimentos ^
  -H "Content-Type: application/json" ^
  -d "{\"bombaId\":1,\"dataAbastecimento\":\"2026-09-28\",\"valorTotal\":59.25,\"litragem\":10.500}"
```

### Atualizar

```bash
curl.exe -s -X PUT http://localhost:8080/api/abastecimentos/1 ^
  -H "Content-Type: application/json" ^
  -d "{\"bombaId\":1,\"dataAbastecimento\":\"2026-09-29\",\"valorTotal\":120.00,\"litragem\":20.000}"
```

### Excluir

```bash
curl.exe -s -X DELETE http://localhost:8080/api/abastecimentos/1 -w "\nHTTP %{http_code}\n"
```

---

## Fluxo completo (copiar e colar no CMD)

```bash
curl.exe -s -X POST http://localhost:8080/api/tipos-combustivel -H "Content-Type: application/json" -d "{\"nome\":\"Gasolina Comum\",\"precoPorLitro\":5.89}"
curl.exe -s -X POST http://localhost:8080/api/bombas -H "Content-Type: application/json" -d "{\"nome\":\"Bomba 01\",\"tipoCombustivelId\":1}"
curl.exe -s -X POST http://localhost:8080/api/abastecimentos -H "Content-Type: application/json" -d "{\"bombaId\":1,\"dataAbastecimento\":\"2026-09-29\",\"valorTotal\":118.50,\"litragem\":20.000}"
curl.exe -s http://localhost:8080/api/consulta
```

---

## Linux / macOS (bash)

Troque `^` por `\` no fim da linha ou use uma linha só:

```bash
curl -s -X POST http://localhost:8080/api/tipos-combustivel \
  -H "Content-Type: application/json" \
  -d '{"nome":"Gasolina Comum","precoPorLitro":5.89}'
```
