package com.lolz404.rsa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@RestController
@RequestMapping("rsa")
public class Endpoints {
    @GetMapping("keys")
    public void createKeys(){
        try {
            var generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            var pair = generator.generateKeyPair();
            var privateKey = pair.getPrivate();
            var publicKey = pair.getPublic();

            System.out.println(String.format("Private key is: %s", privateKey.getEncoded().toString()));
            System.out.println(String.format("Public key is: %s", publicKey.getEncoded().toString()));

            var message = "This is a test message";

            var cipher = Cipher.getInstance("RSA");

            cipher.init(Cipher.ENCRYPT_MODE, privateKey);

            var messageBytes = message.getBytes(StandardCharsets.UTF_8);
            var encryptedMessageBytes = cipher.doFinal(messageBytes);

            var encodedMessage = Base64.getEncoder().encodeToString(encryptedMessageBytes);

            System.out.println(encodedMessage);

            var decodedMessage = Base64.getDecoder().decode(encodedMessage);

            cipher.init(Cipher.DECRYPT_MODE, publicKey);

            var decryptedMessageBytes = cipher.doFinal(decodedMessage);
            var decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);

            System.out.println(decryptedMessage);



        } catch (NoSuchAlgorithmException | NoSuchPaddingException e){
            System.err.println(e.getMessage());
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }


}
