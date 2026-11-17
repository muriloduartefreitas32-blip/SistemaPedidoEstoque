package br.com.sistema.model;

public class ProdutoEletronico extends Produto {
    private int mesesGarantia;

    public ProdutoEletronico(String codigo, String nome, int quantidade, int estoqueMinimo, double preco, int mesesGarantia) {
        super(codigo, nome, quantidade, estoqueMinimo, preco);

        if (mesesGarantia < 0) {
            throw new IllegalArgumentException(
                    "Os meses de garantia não podem ser negativos."
            );
        }
        this.mesesGarantia = mesesGarantia;
    }

    public int getMesesGarantia() {
        return mesesGarantia;
    }

    @Override
    public String getCategoria(){
        return "Eletrônico";
    }

    @Override
    public String exibirDetalhes(){
        return "Meses de Garantia do produto: " + mesesGarantia;
    }
}
