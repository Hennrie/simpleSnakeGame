package com.snake.entity;

import com.snake.config.GameConfig;

/**
 * Created by goran on 26/09/2016.
 */
@Deprecated
public class BodyPart extends EntityBase {

    private boolean justAdded = true;

    public BodyPart() {
        setSize(GameConfig.SNAKE_SIZE, GameConfig.SNAKE_SIZE);
    }

    public boolean isJustAdded() {
        return justAdded;
    }

    public void setJustAdded(boolean justAdded) {
        this.justAdded = justAdded;
    }
}
