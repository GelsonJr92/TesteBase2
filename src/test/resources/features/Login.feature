#language: pt

Funcionalidade: Login de usuario

  @loginComCredenciaisValidas
  Cenário: Login com credenciais válidas
    Dado que o usuário está na página de login
    Quando o usuário insere credenciais válidas
    Então o usuário deve ser redirecionado para o painel principal

  @loginComCredenciaisInválidas
  Cenário: Login com credenciais inválidas
    Dado que o usuário está na página de login
    Quando o usuário insere credenciais inválidas
    Então o usuário deve receber uma mensagem de erro


