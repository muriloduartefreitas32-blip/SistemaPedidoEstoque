package br.com.sistema.valor;

import br.com.sistema.model.Pedido;
import br.com.sistema.model.Produto;

public class ValorBase implements ValorPedido{

    private final Pedido pedido;

    public ValorBase(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public double calcular() {
        return pedido.calcularTotal();
    }

    @Override
    public String descricao() {
        return "Itens do pedido ... " + String.format("R$ %.2f", calcular());
    }
}
