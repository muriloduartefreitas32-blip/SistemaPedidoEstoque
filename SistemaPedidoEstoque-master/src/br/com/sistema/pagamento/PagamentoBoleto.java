package br.com.sistema.pagamento;

import br.com.sistema.util.Moeda;

import java.time.LocalDate;

public class PagamentoBoleto implements FormaPagamento{
    private static final double TAXA_BOLETO = 3.50;

    @Override
    public String getNome(){
        return ("Boleto (taxa de " + Moeda.formatar(TAXA_BOLETO) + ")");
    }
    @Override
    public double calcularValorFinal(double valor){
        return valor + TAXA_BOLETO;
    }
    @Override
    public String processar(double valorFinal){
        LocalDate vencimento = LocalDate.now().plusDays(3);
        return ("Boleto de " + Moeda.formatar(valorFinal) + "gerado, vencimento em " + vencimento + ".");
    }
}
