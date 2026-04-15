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

```bash
src/main/java/com/exemplo/presenca
├── controller/
├── service/
├── repository/
└── model/
```


## ⚙️ Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Maven
- Banco de Dados (MySQL)
- REST API

## 🔄 Workflow de Versionamento

O projeto segue um fluxo de trabalho baseado em branches para garantir organização e colaboração em equipe.

- `main`: contém a versão estável do sistema
- `dev`: branch principal de desenvolvimento e integração
- `feature/*`: branches utilizadas para desenvolvimento de novas funcionalidades

O desenvolvimento é feito em branches de feature, que posteriormente são integradas à branch `dev` através de Pull Requests (PRs), com revisão por outros integrantes da equipe.

## 📝 Padrão de Commits

O projeto utiliza o padrão Conventional Commits para padronizar as mensagens de commit.

Exemplos:
- `feat:` nova funcionalidade
- `fix:` correção de bugs
- `docs:` alterações na documentação
- `test:` adição ou modificação de testes
- `ci:` alterações em integração contínua

## ⚙️ Integração Contínua (CI)

O projeto utiliza GitHub Actions para automatizar o processo de build.

Sempre que há um push ou pull request para a branch `dev`, o workflow de CI é executado automaticamente, realizando a compilação do projeto com Maven.

Essa automação ajuda a garantir a integridade do código e identificar possíveis falhas rapidamente.

O workflow é executado automaticamente através do GitHub Actions, sem necessidade de intervenção manual dos desenvolvedores.

## 🧹 Manutenção do Código

O projeto segue boas práticas de organização e manutenção, incluindo:

- Remoção de código comentado e não utilizado
- Exclusão de arquivos desnecessários
- Uso adequado do `.gitignore` para evitar versionamento de arquivos indevidos

## 📊 Modelo de Dados

### RegistroPresenca

| Campo         | Tipo            |
|--------------|----------------|
| id           | Long           |
| horaRegistro | LocalDateTime  |

## 🔗 Endpoints

### Registrar Presença

POST /presenca/registrar/{id}

#### Parâmetros
- `id` (Long): identificador do usuário

#### Body (JSON)

```json
{
  "latUser": -27.5935,
  "lonUser": -48.5528
}
```

#### Exemplo de resposta

```json
{
  "id": 1,
  "horaRegistro": "2026-03-16T19:55:12"
}
```

### Listar Presenças por Usuário

GET /presenca/usuario/{usuarioId}

#### Parâmetros
- `usuarioId` (Long): identificador do usuário

#### Resposta

Retorna a lista de registros de presença do usuário.

#### Exemplo de resposta

```json
[
  {
    "id": 1,
    "horaRegistro": "2026-03-16T19:55:12"
  }
]
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
