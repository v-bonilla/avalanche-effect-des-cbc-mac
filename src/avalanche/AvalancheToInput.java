package avalanche;

import cipher.DESCBCMAC;
import stats.Histogram;

import java.util.BitSet;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class AvalancheToInput {
	
	private DESCBCMAC descbcmac;
	private Histogram histogram;
	private byte[] key;

	// Inicializa atributos y lanza el efecto avalancha
	public AvalancheToInput() {
		descbcmac = new DESCBCMAC();
		// Clave aleatoria de 64 bits
		key = new byte[8];
		new Random().nextBytes(key);
		// Histograma. 33 elementos porque la distancia de Hamming
		// podra tomar 33 posibles valores (el bloque de salida tiene 32 bits)
		histogram = new Histogram(33);
		startAvalanche();
	}

	// Repite el efecto avalancha 1024 veces modificando la entrada en 1 bit
	public void startAvalanche() {
		for (int i = 0; i < 1000; i++){
			byte[] input1 = createRandomInput();
			byte[] input2 = flipRandomBit(input1);
			avalanche(input1, input2);
		}
	}

	public byte[] createRandomInput(){
		// Crea una entrada aleatoria de 128 bits porque
		// el algoritmo no especifica tamaño de bloque de entrada
		byte[] input = new byte[128];
		new Random().nextBytes(input);
		return input;
	}

	// Complementa un bit aleatorio en un array de bytes y devuelve el array de bytes
	// con el bit cambiado
	public byte[] flipRandomBit(byte[] bytes){
		BitSet bitSet = BitSet.valueOf(bytes);
		int randomBit = ThreadLocalRandom.current().nextInt(0, bitSet.size() - 1);
		bitSet.flip(randomBit);
		return bitSet.toByteArray();
	}



	// Añade al histograma los valores de efecto avalancha y probabilidad para
	// un caso de estudio
	public void avalanche(byte[] input1, byte[] input2) {
		byte[] output1 = descbcmac.encode(key, input1);
		byte[] output2 = descbcmac.encode(key, input2);
		int hD = getHammingDistance(output1, output2);
		histogram.addData(hD);
	}

	// Calcula la distancia de Hamming para un caso de estudio
	public int getHammingDistance(byte[] a, byte[] b){
		int res = 0;
		byte[] xnor = new byte[a.length];
		for (int i = 0; i < xnor.length; i++)
			 xnor[i] = (byte) ((a[i] & b[i]) | (~a[i] & ~b[i]));
		for (int j = 0; j < xnor.length; j++){
			for (int k = 0; k < 8; k++){
				if ((xnor[j] & (1 << k)) == 0)
					res++;
			}
		}
		return res;
	}

	// Metodos para mostrar por pantalla los valores del histograma
	// Valores del efecto avalancha
	public void printHistogramAE(){
		histogram.print();
	}

}
