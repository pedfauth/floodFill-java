package com.fill.flood;

public class Pilha<T> {

	private int top = 0;
	private T[] data;
	private int size;

	@SuppressWarnings("unchecked")
	public Pilha(int size) {
		this.data = (T[]) new Object[size];
		this.size = size;
	}

	public void push(T data) {
		if (isFull()) {
			throw new IllegalAccessError("Pilha cheia!");
		}
		this.data[this.top] = data;
		this.top++;
	}

	public T pop() {
		if (isEmpty()) {
			throw new IllegalAccessError("Pilha já está vazia!");
		}
		T retorno = this.data[this.top - 1];
		this.data[this.top - 1] = null;
		this.top--;
		return retorno;
	}

	public void clear() {
		for (int i = 0; i < this.data.length; i++) {
			this.data[i] = null;
		}
		this.top = 0;
	}

	public boolean isFull() {
		return this.top == this.size;
	}

	public boolean isEmpty() {
		return this.top == 0;
	}

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
