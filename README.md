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

## Design Técnico

A solução segue estes princípios e padrões:

### SOLID Principles
- **Single Responsibility Principle**: Cada classe tem um propósito único e bem definido:
  - `PasswordValidationService`: Interface que define o contrato para validação de senha 
  - `PasswordValidationServiceImpl`: Implementação da interface com a lógica de validação
  - `PasswordValidationController`: Lida com as requisições e respostas HTTP
  - DTOs: Responsáveis pela transferência de dados entre as camadas
- **Open/Closed Principle**: A lógica de validação pode ser estendida sem modificar o código existente
- **Liskov Substitution Principle**: Interfaces pequenas e focadas (DTOs) para transferência de dados
- **Interface Segregation Principle**: O contêiner de DI do Spring gerencia as dependências

### Código Limpo
- Nomes significativos para classes, métodos e variáveis 
- Métodos pequenos e focados com responsabilidades únicas
- Separação clara de responsabilidades
- Cobertura abrangente de testes
- Código autodocumentado com comentários mínimos necessários

### Design da API
- Endpoints RESTful seguindo as melhores práticas
- DTOs claros para requisição e resposta
- HTTP POST para operações de validação
- Formato JSON para troca de dados
- Endpoints versionados (/api/v1/...)

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

## Testes

O projeto inclui testes unitários e testes de integração:

- Testes unitários para o serviço de validação  
- Testes de integração para o controlador REST

Para rodar os testes:
```bash
mvn test
```

## Decisões de Design e Suposições

1. **Design da API**: Escolhi POST em vez de GET para validar senhas porque:
   - Evita expor dados sensíveis nos parâmetros da URL
   - Garante o tratamento adequado de caracteres especiais
   - Segue as melhores práticas de segurança

2. **Abordagem de Validação**: Optei por usar Java Streams e verificações de caracteres em vez de regex porque:
   - Isso torna o código mais legível
   - Facilita a manutenção futura
   - As regras de validação ficam mais explícitas

3. **Tratamento de Erros**: Decidi usar uma resposta booleana simples em vez de mensagens de erro detalhadas porque:
   - Isso mantém a API simples e focada 
   - Atende exatamente aos requisitos especificados
