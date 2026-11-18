package br.com.sistema.pagamento;

import br.com.sistema.util.Moeda;

public class PagamentoPix implements FormaPagamento{
    private static final double DESCONTO = 0.05;

    @Override
    public String getNome(){
        return ("Pix (5% de desconto)");
    }
    @Override
    public double calcularValorFinal(double valor){
        return valor * (1 - DESCONTO);
    }
    @Override
    public String processar(double valorFinal){
        return ("Pix de" + Moeda.formatar(valorFinal) + "confirmado");
    }
}
