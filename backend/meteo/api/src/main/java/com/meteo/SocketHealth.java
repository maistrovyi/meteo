package com.meteo;

public final class SocketHealth {

    public final String message;
    public final SocketStatus status;

    public SocketHealth(String message, SocketStatus status) {
        this.message = message;
        this.status = status;
    }

    public enum  SocketStatus {

        SUCCESS, BAD_REQUEST

    }

}