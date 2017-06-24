package main;

import avalanche.AvalancheToInput;

public class Main {

	public static void main(String[] args) {
		AvalancheToInput a = new AvalancheToInput(100000);
		// Muestra por pantalla los valores de la distribucion de probabilidad
		System.out.println("Histograma:");
		a.printHistogram();
	}

	public static void printBits(byte[] bytes){
		for (byte b : bytes){
			System.out.println(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
		}
	}

}
