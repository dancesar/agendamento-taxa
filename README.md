# 💼 Agendamento de Transferências com Cálculo de Taxas (Backend)

Este é o backend da aplicação de **agendamento de transferências bancárias com cálculo automático de taxas**, desenvolvido em **Java 11** com **Spring Boot** seguindo a **Arquitetura Hexagonal** (Ports and Adapters).

## 🚀 Tecnologias utilizadas

- Java 11
- Spring Boot 2.7.18
- Spring Web
- Spring Data JPA
- H2 Database (em memória)
- MariaDB (produção)
- Arquitetura Hexagonal
- Testes com JUnit 5 e AssertJ

## 🧱 Estrutura do Projeto
agendamento-taxa
├── application/
│ └── services/ → Casos de uso (Agendar, Listar, etc.)
├── domain/
│ ├── entities/ → Entidades de negócio (Transferencia)
│ ├── exceptions/ → Regras de erro do domínio
│ ├── ports/ → Interfaces (ports) de entrada/saída
│ └── strategy/ → Estratégia de cálculo de taxas
├── infrastructure/
│ ├── adapters/ → REST Controllers, Repositórios
│ ├── config/ → Configurações (beans, CORS, Swagger, etc.)
│ └── repository/ → Implementação do port de persistência
├── AgendamentoTaxaApplication → Classe principal
└── resources/
├── application.yml → Configurações do projeto


## ✅ Funcionalidades

- Agendamento de transferências futuras
- Cálculo automático de taxa conforme:
    - Valor da transferência
    - Número de dias até a execução
- Persistência em banco relacional (MariaDB ou H2)
- API RESTful documentada
- Cobertura de testes com JUnit

## 📡 Endpoints da API

| Método | URL                           | Descrição                        |
|--------|-------------------------------|----------------------------------|
| POST   | `/transferencias`            | Agendar nova transferência       |
| GET    | `/transferencias`            | Listar todas as transferências   |

### Exemplo de JSON de envio:
```json
{
  "contaOrigem": "1234",
  "contaDestino": "5678",
  "valor": 1000.00,
  "dataAgendamento": "2025-05-20",
  "dataTransferencia": "2025-06-01"
}

http://localhost:8080