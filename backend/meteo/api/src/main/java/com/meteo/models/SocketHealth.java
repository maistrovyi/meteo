package com.meteo.models;

public final class SocketHealth {

    @SuppressWarnings("WeakerAccess")
    public final String message;

    @SuppressWarnings("WeakerAccess")
    public final SocketStatus status;

    public SocketHealth(String message, SocketStatus status) {
        this.message = message;
        this.status = status;
    }

    public enum SocketStatus {

        OK, BAD_REQUEST

    }

}