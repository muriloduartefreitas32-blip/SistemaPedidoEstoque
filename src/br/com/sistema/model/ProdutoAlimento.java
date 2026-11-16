package br.com.sistema.model;

import java.time.LocalDate;

public class ProdutoAlimento extends Produto {
    private LocalDate validade;

    public ProdutoAlimento(String codigo, String nome, int quantidade, int estoqueMinimo, double preco, LocalDate validade){
        super(codigo, nome, quantidade, estoqueMinimo, preco);

        if(validade == null){
            throw new IllegalArgumentException("A validade é obrigatoria");
        }
        this.validade = validade;
    }
    public LocalDate getValidade(){
        return validade;
    }
    @Override
    public String getCategoria(){
        return "Alimento";
    }

}
