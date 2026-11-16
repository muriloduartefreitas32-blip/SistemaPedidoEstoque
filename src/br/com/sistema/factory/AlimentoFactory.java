package br.com.sistema.factory;

import br.com.sistema.model.Produto;

public interface AlimentoFactory {
    Produto criarProduto(String codigo, String nome, int quantidade, int estoqueMinimo,double preco);
}
