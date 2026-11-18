package br.com.sistema.observer;

import br.com.sistema.model.Produto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegistroReposicaoObserver implements EstoqueObserver{

    private final List<String> sugestoes = new ArrayList<>();

    @Override
    public void estoqueBaixo(Produto produto) {
        int sugerida = Math.max(1, produto.getEstoqueMinimo() * 3 - produto.getQuantidade());
        sugestoes.add("Repor " + sugerida + " un. de " + produto.getNome() + " (" + produto.getCodigo() + ")");
    }

    public List<String> getSugestoes(){
        return List.copyOf(sugestoes);
    }
}
