package com.snake.entity;

import com.snake.config.GameConfig;

/**
 * Created by goran on 26/09/2016.
 */
@Deprecated
public class Coin extends EntityBase {

    private boolean available;

    public Coin() {
        setSize(GameConfig.COIN_SIZE, GameConfig.COIN_SIZE);
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
