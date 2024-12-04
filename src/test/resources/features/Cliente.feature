#language: pt

Funcionalidade: Gerenciamento de clientes e vendas
  Como administrador do sistema
  Eu quero incluir novos clientes
  Para que eles possam utilizar nossos serviços

  Esquema do Cenário: Inclusão de cliente com sucesso preenchendo todos os campos obrigatórios e status Ativo
    Dado que o sistema está disponível
    E o usuário acessa a funcionalidade de Incluir Cliente
    Quando o usuário preenche os campos com Nome "<Nome>", CPF "<CPF>", Status "<Status>" e Saldo Disponivel <Saldo Disponivel>
    E confirma o cadastro
    Então o sistema deve exibir a mensagem "Cliente salvo com sucesso"

    Exemplos:
      | Nome            | CPF            | Status   | Saldo Disponivel |
      | Marcos da Silva | 123.456.354-00 | Ativo    | 125              |
      | Maria da Silva  | 123.456.354-01 | Ativo    | 300              |
      | Joao da Silva   | 123.456.354-02 | Ativo    | 500              |
      | Pedro da Silva  | 123.456.354-03 | Inativo  | 150              |

  Cenário: Inclusão de cliente com falha por campo obrigatório CPF vazio
    Dado que o sistema está disponível
    E o usuário acessa a funcionalidade de Incluir Cliente
    Quando o usuário tenta preencher os campos obrigatórios sem o campo CPF
      | Nome            | Status | Saldo Disponivel |
      | Marcos da Silva | Ativo  | 150           |
    E confirma o cadastro
    Então o sistema deve exibir a mensagem para informar o campo obrigatorio cpf "Campo Obrigatório"


  Cenário: Inclusão de cliente com falha por campo obrigatório Nome vazio
    Dado que o sistema está disponível
    E o usuário acessa a funcionalidade de Incluir Cliente
    Quando o usuário tenta preencher os campos obrigatórios sem o campo Nome
      | CPF            | Status   | Saldo Disponivel |
      | 123.456.354-00 | Inativo  | 150            |
    E confirma o cadastro
    Então o sistema deve exibir a mensagem para informar o campo obrigatorio nome "Campo Obrigatório"


  Cenário: Venda permitida quando o saldo do cliente é igual ao valor da compra
    Dado que o sistema está disponível
    Quando o cliente "Pedro da Silva" tenta realizar uma compra no valor de 125
    Então a venda deve ser realizada com sucesso
    E o sistema deve exibir a mensagem "Venda realizada com sucesso"

  Cenário: Venda permitida quando o saldo do cliente é superior ao valor da compra
    Dado que o sistema está disponível
    Quando o cliente "Maria da Silva" tenta realizar uma compra no valor de 150
    Então a venda deve ser realizada com sucesso
    E o sistema deve exibir a mensagem "Venda realizada com sucesso"

  Cenário: Venda permitida quando o cliente não utiliza todo o saldo disponível
    Dado que o sistema está disponível
    Quando o cliente "Joao da Silva" tenta realizar uma compra no valor de 499
    Então a venda deve ser realizada com sucesso
    E o sistema deve exibir a mensagem "Venda realizada com sucesso"


# ABRIR BUGS
#  O sistema está realizando uma venda quando o valor da compra é maior que o saldo do cliente
#  Não deixa cadastrar cliente com saldo superior a 999
#  Esta deixando cadastrar um cliente sem informar o Saldo Disponível que é um campo obrigatório
#  Esta deixando cadastrar o mesmo cliente mais de uma vez