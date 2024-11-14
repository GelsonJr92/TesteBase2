#language: pt

Funcionalidade: Cadastro de Tarefa

  @cadastroDeTarefaComSucessoPrivada
  Cenario: Cadastro de tarefa com sucesso marcando a visibilidade como privada
    Dado que o usuário está na página de cadastro de tarefas
    Quando o usuário insere os dados da tarefa
    E informa a visibilidade como privada
    E clica no botão de cadastro
    Então a tarefa deve ser cadastrada com sucesso

  @cadastroDeTarefaComSucessoPublica
  Cenario: Cadastro de tarefa com sucesso marcando a visibilidade como privada
    Dado que o usuário está na página de cadastro de tarefas
    Quando o usuário insere os dados da tarefa
    E informa a visibilidade como publica
    E clica no botão de cadastro
    Então a tarefa deve ser cadastrada com sucesso

    @cadastroDeTarefaSemPreencherTodosOsCamposObrigatorios
    Cenario: Cadastro de tarefa sem preencher todos os campos obrigatórios
    Dado que o usuário está na página de cadastro de tarefas
    Quando o usuário não preenche todos os campos obrigatórios
    E clica no botão de cadastro
    Então devo receber uma mensagem de erro no cadastro da tarefa

