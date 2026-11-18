package br.com.sistema.estoque;

//Implementando Singleton

import br.com.sistema.model.Produto;
import br.com.sistema.observer.EstoqueObserver;

import java.util.*;

public class Estoque {

    private static Estoque instancia;

    public final Map<String, Produto> estoqueProdutos = new LinkedHashMap<>();
    public final List<EstoqueObserver> observers = new ArrayList<>();

    private Estoque(){

    }

    public static Estoque getInstance(){
        if (instancia == null){
            instancia = new Estoque();
        }
        return instancia;
    }

    public void cadastrar(Produto produto){

        if (produto == null){
            throw new IllegalArgumentException("O produto é obrigátorio");
        }
        if (estoqueProdutos.containsKey(produto.getCodigo())){
            throw new IllegalArgumentException("Já existe produto com código: " + produto.getCodigo());
        }

        estoqueProdutos.put(produto.getCodigo(), produto);
    }

    public Produto buscar(String codigo){
        Produto produto = (codigo == null) ? null : estoqueProdutos.get(codigo.trim());

        if (produto == null){
            throw new IllegalArgumentException("Produto não encontrado");
        }

        return produto;

    }

    public Collection<Produto> listar(){
        return List.copyOf(estoqueProdutos.values());
    }

    public void baixar(String codigo, int quantidade){
        Produto produto = buscar(codigo);
        produto.removerQuantidade(quantidade);
        if (produto.estaAbaixoDoMinimo()){
            notificar(produto);
        }
    }

    public void repor(String codigo, int quantidade) {
        buscar(codigo).adicionarQuantidade(quantidade);
    }

    public void adicionarObserver(EstoqueObserver observer) {
        observers.add(observer);
    }

    public void removerObserver(EstoqueObserver observer) {
        observers.remove(observer);
    }

    private void notificar(Produto produto) {
        for (EstoqueObserver observer : observers) {
            observer.estoqueBaixo(produto);
        }
    }

}
