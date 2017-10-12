package com.meteo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


public class GameState implements Serializable {

    @Setter
    @Getter
    private String gameUUID;

    public GameState(String gameUUID) {
        this.gameUUID = gameUUID;
    }

}