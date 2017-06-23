package main;

import cipher.DESCBCMAC;
import org.bouncycastle.util.encoders.Hex;

public class Main {

	// Input size 1024 bits = 128 B
	// 32640 possible changes in input

	public static void main(String[] args) {
//		Avalanche a = new Avalanche();
//		// Muestra por pantalla los valores de la distribucion de probabilidad
//		System.out.println("Distribucion de probabilidad para 1024 casos:");
//		a.printHistogramPD();
		DESCBCMAC d = new DESCBCMAC();
		d.createRandomKey(); d.createRandomInput();
		byte[] o = d.encode();
		System.out.println(o.length);
		System.out.println(Hex.encode(o));
	}

}
