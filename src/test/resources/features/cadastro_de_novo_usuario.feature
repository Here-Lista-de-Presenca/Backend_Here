# language: pt

Funcionalidade: Cadastro de novo usuário
  Como visitante do sistema
  Quero realizar meu cadastro
  Para acessar as funcionalidades do sistema

  Cenário: Cadastro de usuário com sucesso
    Dado que informo um e-mail válido "estudante@unisul.br"
    E informo uma senha válida "senha123"
    Quando solicitar o cadastro do usuário
    Então o sistema deve cadastrar o usuário com sucesso


  Cenário: Cadastro de usuário com e-mail já cadastrado
    Dado que já existe um usuário cadastrado com o e-mail "estudante@unisul.br"
    Quando solicitar o cadastro utilizando o e-mail "estudante@unisul.br"
    Então o sistema deve impedir o cadastro
    E exibir a mensagem "E-mail já cadastrado"