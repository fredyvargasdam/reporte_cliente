/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad;

import java.io.FileInputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Esta clase se encargará de la encriptación de datos.
 * @author Fredy
 */
public class Seguridad {

    private static PublicKey publicKey;
    private static  Cipher rsa;
    private static final char[] HEXADECIMAL_ARRAY = "0123456789ABCDEF".toCharArray();
    private static Logger LOGGER = Logger.getLogger(Seguridad.class.getName());

    /**
     * Cargar clave pública
     *
     * @param fileName
     * @return clave publica
     * @throws Exception
     */
   
        private static PublicKey loadPublicKey(String fileName) throws Exception {
        FileInputStream fis = new FileInputStream(fileName);
        int numBtyes = fis.available();
        byte[] bytes = new byte[numBtyes];
        fis.read(bytes);
        fis.close();

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        KeySpec keySpec = new X509EncodedKeySpec(bytes);
        PublicKey keyFromBytes = keyFactory.generatePublic(keySpec);
        return keyFromBytes;
    }

    // Convierte Array de Bytes en hexadecimal
    private static String bytesToHexadecimal(byte[] bytes) {
        char[] caracter = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            caracter[j * 2] = HEXADECIMAL_ARRAY[v >>> 4];
            caracter[j * 2 + 1] = HEXADECIMAL_ARRAY[v & 0x0F];
        }
        return new String(caracter);
    }

    /**
     * Contraseña encriptada con clave pública y convertida a hexadecimal
     *
     * @param contrasenia
     * @return contrasenia cifrada
     */
    public static String encriptarContrasenia(String contrasenia) {
        String pass = "";
        try {
            rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            publicKey = loadPublicKey("publickey.dat");
            rsa.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encriptado = rsa.doFinal(contrasenia.getBytes());
            pass = bytesToHexadecimal(encriptado);
            
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException ex) {
            LOGGER.severe("Error al encriptar con clave pública");
        } catch (Exception ex) {
            Logger.getLogger(Seguridad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pass;
    }

}
