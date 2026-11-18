# Sistema de Pedido e Estoque

Este é um projeto desenvolvido em Java para gerenciamento de pedidos e controle de estoque, aplicando diversos Padrões de Projeto (Design Patterns)[cite: 1].

## Funcionalidades e Estrutura

O sistema está organizado nos seguintes pacotes dentro de `br.com.sistema`[cite: 1]:

* **Modelos (`model`)**: Representação de pedidos, itens de pedido e produtos (Alimentos e Eletrônicos)[cite: 1].
* **Estoque (`estoque`)**: Controle e gerenciamento centralizado de produtos em estoque[cite: 1].
* **Pagamentos (`pagamento`)**: Suporte para diferentes formas de pagamento, incluindo Boleto, Cartão e Pix[cite: 1].
* **Cálculo de Valores (`valor`)**: Gerenciamento do valor base do pedido com aplicação de descontos e frete[cite: 1].
* **Interface (`view`)**: Menu interativo via console para utilização do sistema[cite: 1].

## Padrões de Projeto Utilizados

* **Singleton**: Garantia de uma única instância global para o controle de estoque (`Estoque`)[cite: 1].
* **Factory**: Criação de diferentes tipos de produtos (`ProdutoFactory`, `AlimentoFactory`, `EletronicoFactory`)[cite: 1].
* **Facade**: Interface simplificada para integrar as operações de pedidos e estoque (`PedidoFacade`)[cite: 1].
* **Observer**: Monitoramento do estoque com alertas no console e registros de reposição (`EstoqueObserver`, `AlertaConsoleObserver`, `RegistroReposicaoObserver`)[cite: 1].
* **Decorator**: Adição dinâmica de valores como frete e descontos ao pedido (`AdicionalDecorator`, `DescontoDecorator`, `FreteDecorator`)[cite: 1].
* **Strategy**: Variação das formas de pagamento (`FormaPagamento`, `PagamentoBoleto`, `PagamentoCartao`, `PagamentoPix`)[cite: 1].

## Como Executar

1. Extraia o arquivo compactado e abra a pasta `SistemaPedidoEstoque-master` na sua IDE Java.[cite: 1].
2. Localize o arquivo `src/Main.java`[cite: 1].
3. Compile e execute a classe `Main` para iniciar o menu no console[cite: 1].
