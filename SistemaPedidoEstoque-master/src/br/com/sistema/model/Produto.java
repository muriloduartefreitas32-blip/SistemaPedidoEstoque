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

    public void adicionarQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade de itens adicionado deve ser maior que zero");
        }
        this.quantidade += quantidade;
    }

    public void removerQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade de itens removidos deve ser maior que zero");
        }
        if (quantidade > this.quantidade) {
            throw new IllegalStateException("Estoque insuficiente.");

        }
        this.quantidade -= quantidade;
    }


    public abstract String getCategoria();

    public abstract String exibirDetalhes();

    public boolean estaAbaixoDoMinimo() {
        return quantidade <= estoqueMinimo;
    }

    @Override
    public String toString() {
        return String.format("[%s] %-18s %-11s qtd: %3d (mín. %d) | %s | %s",
                codigo, nome, getCategoria(), quantidade, estoqueMinimo,
                String.format("R$ %.2f", preco), exibirDetalhes());
    }
}
