package cipher;


import org.bouncycastle.crypto.engines.DESEngine;
import org.bouncycastle.crypto.macs.CBCBlockCipherMac;
import org.bouncycastle.crypto.params.KeyParameter;

import java.util.BitSet;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DESCBCMAC {

    private byte[] key;
    private byte[] input;

    public DESCBCMAC() {}

    // Cifra input y devuelve el resultado como array de bytes
    public byte[] encode(){
        // El bloque de salida del algoritmo siempre es de 32 bits
        byte[]  output = new byte[4];
        KeyParameter keyParameter = new KeyParameter(key);
        DESEngine desEngine = new DESEngine();
        CBCBlockCipherMac cbcBlockCipherMac = new CBCBlockCipherMac(desEngine);
        cbcBlockCipherMac.init(keyParameter);
        cbcBlockCipherMac.update(input, 0, input.length);
        cbcBlockCipherMac.doFinal(output, 0);
        return output;
    }

    public void createRandomKey(){
        // Crea una clave aleatoria de 64 bits
        key = new byte[8];
        new Random().nextBytes(key);
    }

    public void createRandomInput(){
        // Crea una entrada aleatoria de 128 bits porque
        // el algoritmo no especifica tamaño de bloque de entrada
        input = new byte[128];
        new Random().nextBytes(input);
    }

    public byte[] flipRandomBit(byte[] bytes){
        BitSet bitSet = BitSet.valueOf(bytes);
        System.out.println("Bits before flip: " + bitSet.toString());
        int randomBit = ThreadLocalRandom.current().nextInt(0, bitSet.size() - 1);
        bitSet.flip(randomBit);
        System.out.println("Bits after flip: " + bitSet.toString());
        return bitSet.toByteArray();
    }

    public void flipRandomBitInKey(){
        setKey(flipRandomBit(key));
    }

    public void flipRandomBitInInput(){
        setInput(flipRandomBit(input));
    }

    public byte[] getKey() {
        return key;
    }

    public void setKey(byte[] key) {
        this.key = key;
    }

    public byte[] getInput() {
        return input;
    }

    public void setInput(byte[] input) {
        this.input = input;
    }

}
