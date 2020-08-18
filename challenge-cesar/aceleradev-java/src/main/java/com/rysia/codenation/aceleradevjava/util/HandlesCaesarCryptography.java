package com.rysia.codenation.aceleradevjava.util;

import com.rysia.codenation.aceleradevjava.model.Quote;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.rysia.codenation.aceleradevjava.shared.Constants.AlphabetUnicode.*;
import static com.rysia.codenation.aceleradevjava.shared.Constants.ENCRYPTION;
import static com.rysia.codenation.aceleradevjava.shared.Constants.DECRYPTION;

public class HandlesCaesarCryptography {

    public static Quote encryptOrDecrypt(Quote quote) {
        Quote manipulatedQuote = quote;
        String message = "", action;
        char[] arrayMessage;
        int key =  manipulatedQuote.getNumeroCasas();

        if (manipulatedQuote.getCifrado() != null && !manipulatedQuote.getCifrado().isEmpty()) {
            arrayMessage = manipulatedQuote.getCifrado().toUpperCase().toCharArray();
            action = DECRYPTION;
        } else {
            arrayMessage = manipulatedQuote.getDecifrado().toUpperCase().toCharArray();
            action = ENCRYPTION;
        }
        for(char letter : arrayMessage){
            int index, decryption;
            if(letter>=FIRST_LETTER_UNICODE && letter<=LAST_LETTER_UNICODE){
                index = letter - FIRST_LETTER_UNICODE;
                decryption =  action.equals(DECRYPTION) ? (index - key) %26 : (index + key) %26;
                letter = decryption<0 ? (char)(decryption + LAST_LETTER_UNICODE + 1)
                        : (char)(decryption + FIRST_LETTER_UNICODE);
            }
            message +=letter;
        }
        if(action.equals(DECRYPTION))
            manipulatedQuote.setDecifrado(message.toLowerCase());
        else
            manipulatedQuote.setCifrado(message.toLowerCase());
        return manipulatedQuote;
    }

    public static String cryptographicHash(String originalEncrypted) throws NoSuchAlgorithmException {
        final MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        final byte[] messageDigestBytes = messageDigest.digest(originalEncrypted.getBytes(StandardCharsets.US_ASCII));
        return hexadecimalFormat(messageDigestBytes);
    }

    private static String hexadecimalFormat(byte[] messageDigestBytes) {
        String hexadecimalStringFormat = "";
        for(byte hash : messageDigestBytes){
            String hexadecimal = Integer.toHexString(0xff & hash);
            if(hexadecimal.length() == 1) hexadecimalStringFormat+='0';
            hexadecimalStringFormat+=hexadecimal;
        }
        return hexadecimalStringFormat;
    }
}
