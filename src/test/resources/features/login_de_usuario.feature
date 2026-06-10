# language: pt
@ignore
Funcionalidade: Login de usuário
  Como usuário cadastrado
  Quero realizar meu login
  Para acessar o sistema

  Cenário: Login realizado com sucesso
    Dado que existe um usuário cadastrado com o e-mail "estudante@unisul.br" e senha "senha123"
    Quando informar o e-mail "estudante@unisul.br" e a senha "senha123"
    Então o sistema deve permitir o acesso

  Cenário: Login com credenciais inválidas
    Dado que existe um usuário cadastrado com o e-mail "estudante@unisul.br" e senha "senha123"
    Quando informar o e-mail "estudante@unisul.br" e a senha "senhaErrada"
    Então o sistema deve impedir o acesso
    E exibir a mensagem "E-mail ou senha inválidos"
