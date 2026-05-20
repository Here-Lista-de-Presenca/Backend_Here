# 📚 Sistema de Registro de Presença - Backend

API REST desenvolvida em Java com Spring Boot para gerenciamento de presença de alunos, com regras automatizadas de horário e localização.

## 📌 Sobre o Projeto

Este backend é responsável por toda a lógica de negócio de um sistema de registro de presença, permitindo:

- Cadastro de usuários
- Autenticação (login)
- Registro de presença com validações de horário e localização
- Consulta de presenças por usuário

O sistema aplica regras para garantir que a presença só seja registrada dentro de um intervalo de tempo permitido e em um raio geográfico válido.

## 🧠 Regras de Negócio

O sistema aplica regras automáticas para controle de presença:

- ⏰ Bloqueio antes das **19:50** 
- ⏰ Bloqueio após **21:00** 
- 📍 Registro permitido apenas dentro do raio de 100 metros
- 🕒 Registro automático com o horário atual do sistema

## ⚙️ Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- H2 Database
- JUnit 5
- TestRestTemplate
- Maven

## 📂 Estrutura do Projeto

```bash
src/main/java/com/unisul/presenca
├── controller/
├── service/
├── repository/
└── model/
```

## 🔗 Endpoints

### 👤 Usuários

- `POST /users` → Cadastro de usuário
- `POST /users/login` → Login de usuário

### 📍 Presença

- `POST /presenca/registrar/{userId}` → Registra presença
- `GET /presenca/usuario/{userId}` → Lista presenças por usuário

## 🧪 Testes Automatizados

O projeto conta com testes de integração utilizando JUnit 5 e Spring Boot Test, simulando requisições HTTP reais na API com TestRestTemplate.

## 👤 Testes de Usuário
 
 ✔️ Cadastro de usuário

- Cadastro com sucesso usando email válido
- Erro ao utilizar email inválido

✔️ Login de usuário

- Login realizado com sucesso
- Erro quando:
  - Email não cadastrado
  - Senha inválida
  - Email inválido

## 📍 Testes de Registro de Presença

### ✔️ Cenário de sucesso
- Registro permitido quando:
  - Usuário existe
  - Está dentro do horário permitido
  - Está dentro da localização válida

### ❌ Regras de validação
- Não permite registro antes do horário liberado
- Não permite registro após o horário encerrado
- Não permite registro fora do raio de 100 metros

## 👥 Equipe

- Adhan Yudi Oda Fukumoto
- Beatriz Gorges Guesser
- Mauricio Batista Gabriel
- Muriel Demonti de Souza

## 🎯 Objetivo

Automatizar e otimizar o controle de presença em sala de aula por meio de uma API com regras de validação de horário e localização.
