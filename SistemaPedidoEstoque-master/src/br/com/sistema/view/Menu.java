package br.com.sistema.view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import br.com.sistema.facade.PedidoFacade;
import br.com.sistema.factory.AlimentoFactory;
import br.com.sistema.factory.EletronicoFactory;
import br.com.sistema.factory.ProdutoFactory;
import br.com.sistema.model.Pedido;
import br.com.sistema.model.Produto;
import br.com.sistema.pagamento.FormaPagamento;
import br.com.sistema.pagamento.PagamentoBoleto;
import br.com.sistema.pagamento.PagamentoCartao;
import br.com.sistema.pagamento.PagamentoPix;
import br.com.sistema.util.Moeda;
import br.com.sistema.valor.DescontoDecorator;
import br.com.sistema.valor.ValorPedido;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final PedidoFacade facade;
    private Pedido pedidoAtual;

    public Menu(PedidoFacade facade) {
        if (facade == null) {
            throw new IllegalArgumentException("A Facade é obrigatória.");
        }
        this.facade = facade;
    }

    public void iniciar() {
        int opcao;

        do {
            exibirOpcoes();
            opcao = lerInteiro("Escolha uma opção: ");

            try {
                switch (opcao) {
                    case 1:
                        cadastrarProduto();
                        break;
                    case 2:
                        listarEstoque();
                        break;
                    case 3:
                        consultarEstoque();
                        break;
                    case 4:
                        reporEstoque();
                        break;
                    case 5:
                        retirarEstoque();
                        break;
                    case 6:
                        criarPedido();
                        break;
                    case 7:
                        adicionarItem();
                        break;
                    case 8:
                        exibirTotalPedido();
                        break;
                    case 9:
                        finalizarPedido();
                        break;
                    case 0:
                        System.out.println("Sistema encerrado.");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private void exibirOpcoes() {
        System.out.println("\n=== GERENCIAMENTO DE PEDIDOS E ESTOQUE ===");
        System.out.println("1. Cadastrar produto");
        System.out.println("2. Listar estoque");
        System.out.println("3. Consultar produto");
        System.out.println("4. Repor estoque");
        System.out.println("5. Retirar do estoque");
        System.out.println("6. Criar pedido");
        System.out.println("7. Adicionar item ao pedido");
        System.out.println("8. Exibir total do pedido");
        System.out.println("9. Finalizar pedido");
        System.out.println("0. Sair");
    }

    private void cadastrarProduto() {
        int tipo = lerInteiro("Produto: 1-Eletrônico, 2-Alimento: ");

        String codigo = lerTexto("Código: ");
        String nome = lerTexto("Nome: ");
        int quantidade = lerInteiro("Quantidade inicial: ");
        int estoqueMinimo = lerInteiro("Estoque mínimo: ");
        double preco = lerDouble("Preço: ");

        ProdutoFactory fabrica;

        if (tipo == 1) {
            int garantia = lerInteiro("Meses de garantia: ");
            fabrica = new EletronicoFactory(garantia);
        } else if (tipo == 2) {
            LocalDate validade = lerData("Validade (AAAA-MM-DD): ");
            fabrica = new AlimentoFactory(validade);
        } else {
            throw new IllegalArgumentException("Tipo de produto inválido.");
        }

        Produto produto = facade.cadastrarProduto(
                fabrica, codigo, nome, quantidade, estoqueMinimo, preco
        );
        System.out.println("Produto cadastrado: " + produto);
    }

    private void listarEstoque() {
        String relatorio = facade.relatorioEstoque();

        if (relatorio.isEmpty()) {
            System.out.println("O estoque está vazio.");
        } else {
            System.out.println(relatorio);
        }
    }

    private void consultarEstoque() {
        String codigo = lerTexto("Código do produto: ");
        System.out.println(facade.consultarProduto(codigo));
    }

    private void reporEstoque() {
        String codigo = lerTexto("Código do produto: ");
        int quantidade = lerInteiro("Quantidade a repor: ");
        facade.reporEstoque(codigo, quantidade);
        System.out.println("Estoque atualizado.");
    }

    private void retirarEstoque() {
        String codigo = lerTexto("Código do produto: ");
        int quantidade = lerInteiro("Quantidade a retirar: ");
        facade.retirarEstoque(codigo, quantidade);
        System.out.println("Retirada realizada.");
    }

    private void criarPedido() {
        if (pedidoAtual != null
                && pedidoAtual.getSituacao() == Pedido.Situacao.ABERTO) {
            throw new IllegalStateException(
                    "Finalize o pedido atual antes de criar outro."
            );
        }

        pedidoAtual = facade.novoPedido();
        System.out.println("Pedido #" + pedidoAtual.getNumero() + " criado.");
    }

    private void adicionarItem() {
        exigirPedidoAberto();

        String codigo = lerTexto("Código do produto: ");
        int quantidade = lerInteiro("Quantidade: ");

        facade.adicionarItem(pedidoAtual, codigo, quantidade);
        System.out.println("Item adicionado ao pedido.");
    }

    private void exibirTotalPedido() {
        exigirPedidoAberto();

        ValorPedido valor = facade.valorBase(pedidoAtual);
        System.out.println("Total: " + Moeda.formatar(valor.calcular()));
    }

    private void finalizarPedido() {
        exigirPedidoAberto();

        double percentual = lerDouble("Desconto (0 se não houver): ");
        ValorPedido valor = facade.valorBase(pedidoAtual);

        if (percentual > 0) {
            valor = new DescontoDecorator(valor, percentual);
        }

        FormaPagamento forma = escolherPagamento();
        String resumo = facade.finalizarPedido(pedidoAtual, valor, forma);

        System.out.println("\n" + resumo);
        pedidoAtual = null;
    }

    private FormaPagamento escolherPagamento() {
        System.out.println("Pagamento: 1-Pix, 2-Cartão, 3-Boleto");
        int opcao = lerInteiro("Escolha: ");

        switch (opcao) {
            case 1:
                return new PagamentoPix();
            case 2:
                int parcelas = lerInteiro("Número de parcelas (1 a 12): ");
                return new PagamentoCartao(parcelas);
            case 3:
                return new PagamentoBoleto();
            default:
                throw new IllegalArgumentException(
                        "Forma de pagamento inválida."
                );
        }
    }

    private void exigirPedidoAberto() {
        if (pedidoAtual == null
                || pedidoAtual.getSituacao() != Pedido.Situacao.ABERTO) {
            throw new IllegalStateException(
                    "Crie um pedido antes de usar essa opção."
            );
        }
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    private double lerDouble(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                String entrada = scanner.nextLine().trim().replace(",", ".");
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }

    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    private LocalDate lerData(String mensagem) {
        System.out.print(mensagem);
        try {
            return LocalDate.parse(scanner.nextLine().trim());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Data inválida. Use o formato AAAA-MM-DD."
            );
        }
    }
}