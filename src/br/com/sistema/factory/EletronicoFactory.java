package br.com.sistema.factory;

import br.com.sistema.model.Produto;

public interface EletronicoFactory {
    Produto criarProduto(String codigo, String nome, int quantidade, int estoqueMinimo,double preco);

}
