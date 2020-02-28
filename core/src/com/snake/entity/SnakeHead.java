package com.snake.entity;

import com.snake.config.GameConfig;

/**
 * Created by goran on 26/09/2016.
 */
@Deprecated
public class SnakeHead extends EntityBase {

    // == constructors ==
    public SnakeHead() {
        setSize(GameConfig.SNAKE_SIZE, GameConfig.SNAKE_SIZE);
    }

    // == public methods ==
    public void updateX(float amount) {
        x += amount;
        updateBounds();
    }

    public void updateY(float amount) {
        y += amount;
        updateBounds();
    }
}
