package br.com.sistema.valor;

public abstract class AdicionalDecorator implements ValorPedido{
    protected final ValorPedido interno;

    protected AdicionalDecorator(ValorPedido interno) {
        if (interno == null) {
            throw new IllegalArgumentException("O valor interno é obrigatório.");
        }
        this.interno = interno;
    }

}
