package br.com.sistema.valor;

public class FreteDecorator extends AdicionalDecorator{


    private final double valorFrete;

    public FreteDecorator(ValorPedido interno, double valorFrete) {
        super(interno);
        if (valorFrete < 0) {
            throw new IllegalArgumentException("O frete não pode ser negativo.");
        }
        this.valorFrete = valorFrete;
    }

    @Override
    public double calcular() {
        return interno.calcular() + valorFrete;
    }

    @Override
    public String descricao() {
        return interno.descricao() + "\n   + Frete ................ " + String.format("R$ %.2f" + valorFrete);
    }
}
