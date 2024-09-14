package com.fill.flood;

public class Fila<T> {

	private int top = 0;

	private int base = 0;

	private int size = 0;

	private T[] data;

	@SuppressWarnings("unchecked")
	public Fila(int size) {
		setData((T[]) new Object[size]);
		this.setSize(size);
	}

	public void add(T data) {
		if (isFull()) {
			throw new IllegalAccessError("Fila cheia!");
		}
		getData()[getTop()] = data;
		setTop(getTop() + 1);
	}

	public T remove() {
		if (isEmpty()) {
			throw new IllegalAccessError("Fila já está vazia!");
		}
		T retorno = getData()[getBase()];
		getData()[getBase()] = null;
		setBase(getBase() + 1);
		return retorno;
	}

	public void clear() {
		for (int i = 0; i < getData().length; i++) {
			getData()[i] = null;
		}
		setTop(0);
		setBase(0);
	}

	public boolean isFull() {
		return this.data.length == this.top;
	}

	public boolean isEmpty() {
		return this.top == this.base;
	}

	public void exibirFila(T[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}
	}

//===================================================================
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
