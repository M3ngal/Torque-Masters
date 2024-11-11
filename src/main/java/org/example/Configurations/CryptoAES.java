package org.example.Configurations;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;
import java.util.Base64;

public class CryptoAES {
    private byte[] textoCifrado;
    private byte[] textoDecifrado;
    private SecretKeySpec chaveSimetrica; // Mantém a chave em memória

    public CryptoAES() throws NoSuchAlgorithmException {
        // Gera uma chave simétrica de 128 bits ao criar a instância da classe
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(128);
        SecretKey sk = kg.generateKey();
        chaveSimetrica = new SecretKeySpec(sk.getEncoded(), "AES");
        textoCifrado = null;
        textoDecifrado = null;
    }

    // Método para cifrar texto em formato String
    public String geraCifra(String texto) throws Exception {
        Cipher aescf = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivspec = new IvParameterSpec(new byte[16]); // Vetor de inicialização (16 bytes para AES)
        aescf.init(Cipher.ENCRYPT_MODE, chaveSimetrica, ivspec);
        textoCifrado = aescf.doFinal(texto.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(textoCifrado); // Retorna o texto cifrado como string Base64
    }

    // Método para decifrar texto em formato String
    public String geraDecifra(String textoCifradoBase64) throws Exception {
        byte[] textoCifradoBytes = Base64.getDecoder().decode(textoCifradoBase64);
        Cipher aescf = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivspec = new IvParameterSpec(new byte[16]); // Vetor de inicialização (16 bytes para AES)
        aescf.init(Cipher.DECRYPT_MODE, chaveSimetrica, ivspec);
        textoDecifrado = aescf.doFinal(textoCifradoBytes);
        return new String(textoDecifrado, "UTF-8"); // Retorna o texto decifrado como string
    }

    // Métodos de acesso para obter os bytes cifrados e decifrados
    public byte[] getTextoCifrado() {
        return textoCifrado;
    }

    public byte[] getTextoDecifrado() {
        return textoDecifrado;
    }
}
