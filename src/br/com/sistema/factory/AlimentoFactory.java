package br.com.sistema.factory;

import br.com.sistema.model.Produto;
import br.com.sistema.model.ProdutoAlimento;
import br.com.sistema.model.ProdutoEletronico;

import java.time.LocalDate;

public class AlimentoFactory implements ProdutoFactory{

    private LocalDate validade;

    @Override
    public Produto criarProduto(String codigo, String nome, int quantidade, int estoqueMinimo, double preco) {
        return new ProdutoAlimento(codigo, nome, quantidade, estoqueMinimo, preco, validade);
    }

    public AlimentoFactory(LocalDate validade){
        this.validade = validade;
    }


}
