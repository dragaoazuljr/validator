# API de Validação de Senhas

Este projeto implementa uma API RESTful para validação de senhas de acordo com regras específicas.

## Regras de Validação de Senhas

Uma senha é considerada válida quando atende a todos os seguintes critérios:
- Nove ou mais caracteres
- Pelo menos 1 dígito
- Pelo menos 1 letra minúscula  
- Pelo menos 1 letra maiúscula
- Pelo menos 1 caractere especial (do conjunto: !@#$%^&*()-+)
- Nenhum caractere repetido
- Nenhum caractere de espaço em branco

## Como Executar  

1. Clone o repositório
2. Compile e execute a aplicação:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
   O servidor será iniciado na porta **8080**  

## Uso da API  

### Endpoint  
```
POST /api/v1/password/validate
```

### Corpo da Requisição  
```json
{
    "password": "password"
}
```

### Resposta  
```json
{
    "valid": true/false
}
```

### Exemplo  
```bash
curl -X POST http://localhost:8080/api/v1/password/validate \
     -H "Content-Type: application/json" \
     -d '{"password": "AbTp9!fok"}'
```
