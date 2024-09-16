package com.fill.flood;

public class Fila<T> {

    private int top = 0; // Índice do topo da fila
    private int base = 0; // Índice da base da fila
    private int size = 0; // Tamanho da fila
    private T[] data; // Array para armazenar os elementos da fila

    @SuppressWarnings("unchecked")
    public Fila(int size) {
        // Construtor que inicializa a fila com um tamanho específico
        setData((T[]) new Object[size]);
        this.setSize(size);
    }

    public void add(T data) {
        // Adiciona um elemento à fila
        if (isFull()) {
            throw new IllegalAccessError("Fila cheia!");
        }
        getData()[getTop()] = data;
        setTop(getTop() + 1);
    }

    public T remove() {
        // Remove e retorna o elemento da base da fila
        if (isEmpty()) {
            throw new IllegalAccessError("Fila já está vazia!");
        }
        T retorno = getData()[getBase()];
        getData()[getBase()] = null;
        setBase(getBase() + 1);
        return retorno;
    }

    public void clear() {
        // Limpa todos os elementos da fila
        for (int i = 0; i < getData().length; i++) {
            getData()[i] = null;
        }
        setTop(0);
        setBase(0);
    }

    public boolean isFull() {
        // Verifica se a fila está cheia
        return this.data.length == this.top;
    }

    public boolean isEmpty() {
        // Verifica se a fila está vazia
        return this.top == this.base;
    }

    public void exibirFila(T[] data) {
        // Exibe todos os elementos da fila
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
    }

    // Getters e setters para os atributos da fila
    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public T[] getData() {
        return data;
    }

    public void setData(T[] data) {
        this.data = data;
    }
}