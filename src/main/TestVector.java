package main;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.util.encoders.Hex;


public class TestVector {
	// Test case 3 in "https://tools.ietf.org/html/rfc2144#page-15"
	// 40-bit key = 01 23 45 67 12
	//            = 01 23 45 67 12 00 00 00 00 00 00 00 00 00 00 00
	// plaintext  = 01 23 45 67 89 AB CD EF
	// ciphertext = 7A C8 16 D1 6E 9B 30 2E
	
//	public static void main(String[] args)  throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, DataLengthException, IllegalStateException, InvalidCipherTextException{
//
//		CAST cast128 = new CAST();
//		System.out.println("Test case 3 in \"https://tools.ietf.org/html/rfc2144#page-15\"\n"
//				+ "40-bit key = 01 23 45 67 12\n"
//				+ "plaintext  = 01 23 45 67 89 AB CD EF\n"
//				+ "ciphertext = 7A C8 16 D1 6E 9B 30 2E");
//		System.out.println("-------------------------------------------------------------");
//		System.out.println("Clave usada (Hex): 0123456712");
//		// Set key
//		cast128.setKey(Hex.decode("0123456712"));
//		System.out.println("Texto a cifrar (Hex): 0123456789ABCDEF");
//		System.out.println("Texto cifrado (Hex): 7AC816D16E9B302E");
//		System.out.println("Cifrando...");
//		// Encode
//		byte[] cipherdata = cast128.encode(Hex.decode("0123456789ABCDEF"));
//		System.out.println("Resultado: " + Hex.toHexString(cipherdata));
//	}

}
