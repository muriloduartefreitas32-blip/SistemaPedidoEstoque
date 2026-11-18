package br.com.sistema.valor;

import br.com.sistema.util.Moeda;

public class DescontoDecorator extends AdicionalDecorator {
    private final double percentual;

    public DescontoDecorator(ValorPedido interno, double percentual) {
        super(interno);

        if (percentual < 0 || percentual > 100) {
            throw new IllegalArgumentException(
                    "O desconto deve estar entre 0% e 100%."
            );
        }

        this.percentual = percentual;
    }

    @Override
    public double calcular() {
        return interno.calcular() * (1 - percentual / 100);
    }

    @Override
    public String descricao() {
        double valorDesconto = interno.calcular() * percentual / 100;

        return interno.descricao()
                + "\n   - Desconto (" + percentual + "%) ... "
                + Moeda.formatar(valorDesconto);
    }
}
