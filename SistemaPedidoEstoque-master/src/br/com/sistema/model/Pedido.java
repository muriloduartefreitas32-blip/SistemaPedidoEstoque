package br.com.sistema.model;

import java.util.*;

public class Pedido {

    public enum Situacao{
        ABERTO,
        FINALIZADO
    }

    private static int proximoNumero = 1;
    private int numero;
    private List<ItemPedido> itens;
    private Situacao situacao;

    public Pedido(){
        this.numero = proximoNumero++;
        this.itens = new ArrayList<>();
        this.situacao = Situacao.ABERTO;
    }

    public int getNumero(){
        return numero;
    }

    public Situacao getSituacao(){
        return situacao;
    }

    public List<ItemPedido> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public void adicionarItem(Produto produto, int quantidade){
        if(situacao == Situacao.FINALIZADO){
            throw new IllegalStateException("Não é possivel alterar um pedido finalizado");
        }
        if(produto == null){
            throw new IllegalArgumentException("O produto é obrigatório");
        }
        if (quantidade <= 0){
            throw  new IllegalArgumentException("A quantidade deve ser maior que 0");
        }

        int quantidadeJaAdicionada = 0;

        for(ItemPedido item : itens){
            if(item.getProduto().getCodigo().equals(produto.getCodigo())){
                quantidadeJaAdicionada += item.getQuantidade();
            }
        }
        if(quantidade + quantidadeJaAdicionada > produto.getQuantidade()){
            throw new IllegalStateException("Estoque insuficiente");
        }
        itens.add(new ItemPedido(produto, quantidade));
    }

    public Map<String, Integer> quantidadePorCodigo() {
        Map<String, Integer> mapa = new LinkedHashMap<>();
        for (ItemPedido item : itens) {
            mapa.merge(item.getProduto().getCodigo(), item.getQuantidade(), Integer::sum);
        }
        return mapa;
    }

    public double calcularTotal(){
        double total = 0;

        for(ItemPedido item : itens){
            total += item.calcularSubtotal();
        }
        return total;
    }

    public void finalizar(){
        if(situacao == Situacao.FINALIZADO){
            throw new IllegalStateException("O pedido já foi finalizado");
        }
        if(itens.isEmpty()){
            throw new IllegalStateException("Não é possivel finalizar um pedido sem itens");
        }
        situacao = Situacao.FINALIZADO;
    }

}
