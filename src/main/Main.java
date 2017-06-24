package main;

import cipher.DESCBCMAC;

public class Main {

	// Input size 1024 bits = 128 B
	// 32640 possible changes in input

	public static void main(String[] args) {
//		AvalancheToInput a = new AvalancheToInput();
//		// Muestra por pantalla los valores de la distribucion de probabilidad
//		System.out.println("Distribucion de probabilidad para 1024 casos:");
//		a.printHistogramPD();
//		DESCBCMAC d = new DESCBCMAC();
//		d.createRandomKey();
//		d.createRandomInput();
//		byte[] key = d.getInput();
//		d.flipRandomBitInInput();
//		byte[] key2 = d.getInput();
//		System.out.println("Key 1: ");
//		printBits(key);
//		System.out.println("Key 2: ");
//		printBits(key2);
//		System.out.println("Hamming: " + AvalancheToInput.getHammingDistance(key, key2));
	}

	public static void printBits(byte[] bytes){
		for (byte b : bytes){
			System.out.println(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
		}
	}

}
