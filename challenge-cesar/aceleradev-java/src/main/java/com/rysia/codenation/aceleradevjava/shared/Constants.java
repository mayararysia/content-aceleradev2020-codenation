package com.rysia.codenation.aceleradevjava.shared;

public interface Constants {
    String ENCRYPTION = "encryption";
    String DECRYPTION = "decryption";
    interface API {
        String TOKEN = "?token=320eda94e19c1a217bd31abbaef124c78f4fc019";
        String URL = "https://api.codenation.dev/v1/challenge/dev-ps/";
        String URL_GET_DATA = URL.concat("generate-data").concat(TOKEN);
        String URL_SUBMIT = URL.concat("submit-solution").concat(TOKEN);
    }

    interface AlphabetUnicode {
        int FIRST_LETTER_UNICODE = 65;
        int LAST_LETTER_UNICODE = 90;
    }
}

