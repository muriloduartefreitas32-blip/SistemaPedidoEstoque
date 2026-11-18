package br.com.sistema.pagamento;

public interface FormaPagamento {
    String getNome();

    double calcularValorFinal(double valor);

    String processar(double valorFinal);
}
