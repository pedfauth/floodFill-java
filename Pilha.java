package com.fill.flood;

public class Pilha<T> {

    private int top = 0; // Índice do topo da pilha
    private T[] data; // Array para armazenar os elementos da pilha
    private int size; // Tamanho da pilha

    @SuppressWarnings("unchecked")
    public Pilha(int size) {
        // Construtor que inicializa a pilha com um tamanho específico
        this.data = (T[]) new Object[size];
        this.size = size;
    }

    public void push(T data) {
        // Adiciona um elemento ao topo da pilha
        if (isFull()) {
            throw new IllegalAccessError("Pilha cheia!");
        }
        this.data[this.top] = data;
        this.top++;
    }

    public T pop() {
        // Remove e retorna o elemento do topo da pilha
        if (isEmpty()) {
            throw new IllegalAccessError("Pilha já está vazia!");
        }
        T retorno = this.data[this.top - 1];
        this.data[this.top - 1] = null;
        this.top--;
        return retorno;
    }

    public void clear() {
        // Limpa todos os elementos da pilha
        for (int i = 0; i < this.data.length; i++) {
            this.data[i] = null;
        }
        this.top = 0;
    }

    public boolean isFull() {
        // Verifica se a pilha está cheia
        return this.top == this.size;
    }

    public boolean isEmpty() {
        // Verifica se a pilha está vazia
        return this.top == 0;
    }

    // Getters e setters para os atributos da pilha
    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public T[] getData() {
        return data;
    }

    public void setData(T[] data) {
        this.data = data;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}