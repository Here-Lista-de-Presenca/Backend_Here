# 📚 Sistema de Registro de Presença - Backend

API REST desenvolvida em Java com Spring Boot para gerenciamento de presença de alunos, com regras de horário automatizadas.

## 📌 Sobre o Projeto

O backend é responsável por toda a lógica de negócio do sistema de presença, incluindo validações de horário e persistência dos dados.

## ⏰ Regras de Negócio

- Aula inicia às **19:00**
- Presença liberada às **19:50**
- Presença encerrada às **21:00**

✔ O registro só é permitido entre **19:50 e 21:00**

## 🏗️ Arquitetura

O projeto segue arquitetura em camadas:

Controller → Service → Repository → Model


## 📂 Estrutura do Projeto

src/main/java/com/exemplo/presenca

controller/
service/
repository/
model/


## ⚙️ Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Maven
- Banco de Dados (MySQL)
- REST API

## 📊 Modelo de Dados

### RegistroPresenca

| Campo         | Tipo            |
|--------------|----------------|
| id           | Long           |
| horaRegistro | LocalDateTime  |

## 🔗 Endpoints

## Registrar Presença

POST /presenca/registrar

## Exemplo de resposta

```json
{
  "id": 1,
  "horaRegistro": "2026-03-16T19:55:12"
}
```

## 🧠 Regras Implementadas

- Bloqueio antes das 19:50
- Bloqueio após 21:00
- Registro automático do horário atual
- Persistência no banco de dados


## 👥 Equipe

- Adhan Yudi Oda Fukumoto
- Beatriz Gorges Guesser
- Mauricio Batista Gabriel
- Muriel Demonti de Souza


## 🎯 Objetivo
Automatizar e otimizar o controle de presença em sala de aula.



