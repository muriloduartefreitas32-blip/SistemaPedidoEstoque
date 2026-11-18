package br.com.sistema.facade;

import java.util.Map;

import br.com.sistema.estoque.Estoque;
import br.com.sistema.factory.ProdutoFactory;
import br.com.sistema.model.ItemPedido;
import br.com.sistema.model.Pedido;
import br.com.sistema.model.Produto;
import br.com.sistema.pagamento.FormaPagamento;
import br.com.sistema.util.Moeda;
import br.com.sistema.valor.ValorBase;
import br.com.sistema.valor.ValorPedido;

public class PedidoFacade {
    private final Estoque estoque = Estoque.getInstance();

    public Produto cadastrarProduto(
            ProdutoFactory fabrica, String codigo, String nome,
            int quantidade, int estoqueMinimo, double preco) {
        Produto produto = fabrica.criarProduto(
                codigo, nome, quantidade, estoqueMinimo, preco
        );
        estoque.cadastrar(produto);
        return produto;
    }

    public Pedido novoPedido() {
        return new Pedido();
    }

    public void adicionarItem(Pedido pedido, String codigoProduto, int quantidade) {
        pedido.adicionarItem(estoque.buscar(codigoProduto), quantidade);
    }

    public ValorPedido valorBase(Pedido pedido) {
        return new ValorBase(pedido);
    }

    public String finalizarPedido(
            Pedido pedido, ValorPedido valor, FormaPagamento forma) {
        if (pedido.getSituacao() == Pedido.Situacao.FINALIZADO) {
            throw new IllegalStateException("O pedido já foi finalizado.");
        }
        if (pedido.getItens().isEmpty()) {
            throw new IllegalStateException(
                    "Não é possível finalizar um pedido sem itens."
            );
        }
        if (forma == null) {
            throw new IllegalArgumentException(
                    "A forma de pagamento é obrigatória."
            );
        }

        for (Map.Entry<String, Integer> item
                : pedido.quantidadePorCodigo().entrySet()) {
            Produto produto = estoque.buscar(item.getKey());
            if (produto.getQuantidade() < item.getValue()) {
                throw new IllegalStateException(
                        "Estoque insuficiente para \"" + produto.getNome() + "\"."
                );
            }
        }

        double valorFinal = forma.calcularValorFinal(valor.calcular());

        for (Map.Entry<String, Integer> item
                : pedido.quantidadePorCodigo().entrySet()) {
            estoque.baixar(item.getKey(), item.getValue());
        }

        pedido.finalizar();
        return montarResumo(pedido, valor, forma, valorFinal);
    }

    public String relatorioEstoque() {
        StringBuilder texto = new StringBuilder();

        for (Produto produto : estoque.listar()) {
            texto.append("   ").append(produto).append("\n");
        }

        return texto.toString();
    }

    private String montarResumo(
            Pedido pedido, ValorPedido valor,
            FormaPagamento forma, double valorFinal) {
        StringBuilder texto = new StringBuilder();

        texto.append("PEDIDO #").append(pedido.getNumero())
                .append(" - ").append(pedido.getSituacao()).append("\n");

        for (ItemPedido item : pedido.getItens()) {
            texto.append(String.format(
                    "   %dx %-18s %s%n",
                    item.getQuantidade(),
                    item.getProduto().getNome(),
                    Moeda.formatar(item.calcularSubtotal())
            ));
        }

        texto.append(valor.descricao()).append("\n");
        texto.append("   Pagamento: ").append(forma.getNome()).append("\n");
        texto.append("   TOTAL A PAGAR: ")
                .append(Moeda.formatar(valorFinal)).append("\n");
        texto.append("   ").append(forma.processar(valorFinal));

        return texto.toString();
    }
}