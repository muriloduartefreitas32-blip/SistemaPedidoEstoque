package br.com.sistema.observer;

import br.com.sistema.model.Produto;

public class AlertaConsoleObserver implements EstoqueObserver{

    @Override
    public void estoqueBaixo(Produto produto) {
        System.out.println("!Alerta!\n" +
                "Estoque Baixo: " + produto.getNome() + "\n" +
                "tem " + produto.getQuantidade() + " item(s) em estoque\n" +
                "(minímo: " + produto.getEstoqueMinimo() + ")");
    }

}
