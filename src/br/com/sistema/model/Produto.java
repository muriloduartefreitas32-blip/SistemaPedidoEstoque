package br.com.sistema.model;

public abstract class Produto {

    private String codigo;
    private String nome;
    private double preco;
    private int quantidade;
    private int estoqueMinimo;


    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public Produto(String codigo, String nome, int quantidade, int estoqueMinimo, double preco) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("O código é obrigatório.");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }
        if (preco < 0 || Double.isNaN(preco) || Double.isInfinite(preco)) {
            throw new IllegalArgumentException("O preço deve ser válido e não negativo.");
        }
        if (quantidade < 0 || estoqueMinimo < 0) {
            throw new IllegalArgumentException("As quantidades não podem ser negativas.");
        }
        this.codigo = codigo.trim();
        this.nome = nome.trim();
        this.preco = preco;
        this.estoqueMinimo = estoqueMinimo;
        this.quantidade = quantidade;
    }
      public abstract String getCategoria();
}
