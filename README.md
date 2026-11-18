# Sistema de Pedido e Estoque

Este é um projeto desenvolvido em Java para gerenciamento de pedidos e controle de estoque, aplicando diversos Padrões de Projeto (Design Patterns).

## Funcionalidades e Estrutura

O sistema está organizado nos seguintes pacotes dentro de `br.com.sistema`:

* **Modelos (`model`)**: Representação de pedidos, itens de pedido e produtos (Alimentos e Eletrônicos).
* **Estoque (`estoque`)**: Controle e gerenciamento centralizado de produtos em estoque.
* **Pagamentos (`pagamento`)**: Suporte para diferentes formas de pagamento, incluindo Boleto, Cartão e Pix.
* **Cálculo de Valores (`valor`)**: Gerenciamento do valor base do pedido com aplicação de descontos e frete.
* **Interface (`view`)**: Menu interativo via console para utilização do sistema.

## Padrões de Projeto Utilizados

* **Singleton**: Garantia de uma única instância global para o controle de estoque (`Estoque`).
* **Factory**: Criação de diferentes tipos de produtos (`ProdutoFactory`, `AlimentoFactory`, `EletronicoFactory`).
* **Facade**: Interface simplificada para integrar as operações de pedidos e estoque (`PedidoFacade`).
* **Observer**: Monitoramento do estoque com alertas no console e registros de reposição (`EstoqueObserver`, `AlertaConsoleObserver`, `RegistroReposicaoObserver`).
* **Decorator**: Adição dinâmica de valores como frete e descontos ao pedido (`AdicionalDecorator`, `DescontoDecorator`, `FreteDecorator`).
* **Strategy**: Variação das formas de pagamento (`FormaPagamento`, `PagamentoBoleto`, `PagamentoCartao`, `PagamentoPix`).

## Como Executar

1. Extraia o arquivo compactado e abra a pasta `SistemaPedidoEstoque-master` na sua IDE Java..
2. Localize o arquivo `src/Main.java`.
3. Compile e execute a classe `Main` para iniciar o menu no console.
