package cipher;


import org.bouncycastle.crypto.engines.DESEngine;
import org.bouncycastle.crypto.macs.CBCBlockCipherMac;
import org.bouncycastle.crypto.params.KeyParameter;

import java.util.Random;

public class DESCBCMAC {

    private byte[] key;
    private byte[] input;

    public DESCBCMAC() {}

    // Recibe un array de bytes, lo cifra y devuelve el resultado como array de bytes
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
