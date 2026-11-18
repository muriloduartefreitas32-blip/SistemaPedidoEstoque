package br.com.sistema.factory;

import br.com.sistema.model.Produto;
import br.com.sistema.model.ProdutoEletronico;

public class EletronicoFactory implements ProdutoFactory {

    private final int mesesGarantia;

    public EletronicoFactory(int mesesGarantia){
        this.mesesGarantia = mesesGarantia;
    }

    @Override
    public Produto criarProduto(String codigo, String nome, int quantidade, int estoqueMinimo, double preco) {
        return new ProdutoEletronico(codigo, nome, quantidade, estoqueMinimo, preco, mesesGarantia);
    }
}
