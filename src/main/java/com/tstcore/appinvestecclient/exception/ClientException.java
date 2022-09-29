package com.tstcore.appinvestecclient.exception;

import com.tstcore.appinvestecclient.entities.Client;

public class ClientException extends  RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ClientException() {
        super();
    }

    public ClientException(String message) {
        super(message);
    }

    public ClientException(String message, long id) {
        super(message + id);
    }
    public ClientException(String message, String mobileNumber) { super(message + mobileNumber); }

    public ClientException(String message, Client client) {
        super(client.getClass().getName() + message);
    }
}
