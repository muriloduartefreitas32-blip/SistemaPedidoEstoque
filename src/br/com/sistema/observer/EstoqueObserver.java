package br.com.sistema.observer;

import br.com.sistema.model.Produto;

public interface EstoqueObserver {
    void estoqueBaixo(Produto produto);
}
