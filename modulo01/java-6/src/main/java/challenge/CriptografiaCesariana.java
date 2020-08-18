package challenge;

public class CriptografiaCesariana implements Criptografia {
    @Override
    public String criptografar(String texto) {
        return getTextEncryptionOrDecryption(texto, true);
    }

    @Override
    public String descriptografar(String texto) {
        return getTextEncryptionOrDecryption(texto, false);
    }

    private String getTextEncryptionOrDecryption(String texto, boolean isEncryption) {
        int skipping = 3, code;
        char[] arrayText;
        String encryptedText = "";

        if (texto.equals(null))
            throw new NullPointerException("Texto está nulo!");
        if (texto.isEmpty())
            throw new IllegalArgumentException("Texto vazio!");

        arrayText = texto.toUpperCase().toCharArray();
        for (char letter : arrayText) {
            if (letter >= 'A' && letter <= 'Z') {
                code = letter - 'A';
                if (isEncryption) code = (code + skipping) % 26;
                else code = (code - skipping) % 26;
                letter = code < 0 ? (char) (code + 'Z' + 1) : (char) (code + 'A');
            }
            encryptedText += letter;
        }
        return encryptedText.toLowerCase();
    }
}
