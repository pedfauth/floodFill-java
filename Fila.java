package com.fill.flood;

public class Fila<T> {

	private int top = 0;
	private int base = 0;
	private int count = 0;
	private T[] data;

	@SuppressWarnings("unchecked")
	public Fila(int size) {
		setData((T[]) new Object[size]);
	}

	public void add(T data) {
		if (isFull()) {
			throw new IllegalAccessError("Fila cheia!");
		}
		getData()[top] = data;
		top = (top + 1) % getData().length;
		count++;
	}

	public T remove() {
		if (isEmpty()) {
			throw new IllegalAccessError("Fila já está vazia!");
		}
		T retorno = getData()[base];
		getData()[base] = null;
		base = (base + 1) % getData().length;
		count--;
		return retorno;
	}

	public boolean isFull() {
		return count == getData().length;
	}

	public boolean isEmpty() {
		return count == 0;
	}

	public void clear() {
		for (int i = 0; i < getData().length; i++) {
			getData()[i] = null;
		}
		top = 0;
		base = 0;
		count = 0;
	}

	public int getCount() {
		return count;
	}

	public T[] getData() {
		return data;
	}

	public void setData(T[] data) {
		this.data = data;
	}
}
