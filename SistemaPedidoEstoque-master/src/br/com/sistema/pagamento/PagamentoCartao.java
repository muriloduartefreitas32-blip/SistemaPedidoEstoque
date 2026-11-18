package br.com.sistema.pagamento;

import br.com.sistema.util.Moeda;

public class PagamentoCartao implements  FormaPagamento{
    private static final int PARCELAS_SEM_JUROS = 3;
    private static final double JUROS_POR_PARCELA_EXTRA = 0.02;

    private final int parcelas;

    public PagamentoCartao(int parcelas){
        if (parcelas < 1 || parcelas > 12) {
            throw new IllegalArgumentException(
                    "O número de parcelas deve estar entre 1 e 12.");
        }
        this.parcelas = parcelas;
    }
    @Override
    public String getNome(){
        return ("Cartão de crédito em " + parcelas + "x");
    }
    @Override
    public double calcularValorFinal(double valor){
        if(parcelas <= PARCELAS_SEM_JUROS){
            return valor;
        }
        int parcelasComjuros = parcelas - PARCELAS_SEM_JUROS;
        return valor * (1 + JUROS_POR_PARCELA_EXTRA * parcelasComjuros);
    }
    @Override
    public String processar(double valorFinal){
        double valorParcela = valorFinal / parcelas;
        return ("Cartão aprovado: " + parcelas + "x de " + Moeda.formatar(valorParcela) + ".");
    }

}
