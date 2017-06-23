package avalanche;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import cipher.DESCBCMAC;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import stats.Histogram;

public class Avalanche {
	
	private DESCBCMAC function;
	private Histogram histogram;
	private int posInArray;
	private int valueByte;
	
	// Inicializa atributos y lanza el efecto avalancha
	public Avalanche() throws NoSuchAlgorithmException, InvalidKeyException, DataLengthException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, IllegalStateException, InvalidCipherTextException{
		function = new DESCBCMAC();
		// Clave aleatoria de 40 bits
		byte[] key = new byte[5];
		new Random().nextBytes(key);
		function.setKey(key);
		histogram = new Histogram();
		posInArray = 127;
		valueByte = 0;
		startAvalanche();
	}
	
	// Repite el efecto avalancha 1024 veces modificando la entrada en 1 bit
	public void startAvalanche() throws NoSuchAlgorithmException, InvalidKeyException, DataLengthException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, IllegalStateException, InvalidCipherTextException{
		byte[] input1 = new byte[128];
		byte[] input2 = new byte[input1.length];
		for (int i = 0; i < 1024; i++){
			input2 = modifyByteArray(input1);
			avalanche(input1, input2);
			input1 = input2;
		}
	}
	
	// Devuelve el array parametro modificado (suma 1 al byte[posInArray])
	public byte[] modifyByteArray(byte[] oldArray){
		byte[] newArray = oldArray.clone();
		if (posInArray >= 0){
			newArray[posInArray]++;
			valueByte++;
			if (valueByte == 255){
				posInArray--;
				valueByte = 0;
			}
		}
		else
			throw new Error("PosInArray = 0");
		return newArray;
	}
	
	// A�ade al histograma los valores de efecto avalancha y probabilidad para 
	// un caso de estudio
	public void avalanche(byte[] input1, byte[] input2) throws NoSuchAlgorithmException, InvalidKeyException, DataLengthException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, IllegalStateException, InvalidCipherTextException{
		byte[] output1 = function.encode();
		byte[] output2 = function.encode();
		int hD = getHammingDistance(output1, output2);
		histogram.addDataAE(getAvalancheEffect(hD, (output1.length * 8)));
		histogram.addDataPD(getProbability(hD,(output1.length * 8)));
	}
	
	// Calcula el efecto avalancha a partir de la distancia de Hamming
	// de un caso de estudio
	private BigDecimal getAvalancheEffect(int hammingDistance, int nBits) {
		BigDecimal b1 = new BigDecimal(hammingDistance);
		BigDecimal b2 = new BigDecimal(nBits);
		BigDecimal res = b1.divide(b2, 4, RoundingMode.HALF_UP);
		return res;
	}
	
	// Calcula la probabilidad de un caso de estudio
	private BigDecimal getProbability(int hammingDistance, int nBits) {
		BigDecimal b1 = new BigDecimal(hammingDistance);
		BigDecimal b2 = new BigDecimal(nBits);
		BigDecimal b3 = new BigDecimal(1024);
		BigDecimal res = b2.subtract(b1);
		res = res.divide(b3, 6, RoundingMode.HALF_UP);
		return res;
	}

	// Calcula la distancia de Hamming para un caso de estudio
	public int getHammingDistance(byte[] a, byte[]b){
		int res = 0;
		byte[] and = new byte[a.length];
		for (int i = 0; i < and.length; i++)
			 and[i] = (byte) ((a[i] & b[i]) | (~a[i] & ~b[i]));
		for (int j = 0; j < and.length; j++){
			for (int k = 0; k < 8; k++){
				if ((and[j] & (1 << k)) != 1)
					res++;
			}
		}
		return res;
	}
	
	// Metodos para mostrar por pantalla los valores del histograma
	// Valores del efecto avalancha
	public void printHistogramAE(){
		histogram.printAE();
	}
	
	// Valores dela distribucion de probabilidad
	public void printHistogramPD(){
		histogram.printPD();
	}

}
