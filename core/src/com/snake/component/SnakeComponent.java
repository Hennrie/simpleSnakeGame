package com.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Pool;

public class SnakeComponent implements Component, Pool.Poolable {

    // == constants ==
    private static final Logger log = new Logger(SnakeComponent.class.getName(), Logger.DEBUG);

    // == attributes ==
    public Entity head;
    public final Array<Entity> bodyParts = new Array<>();

    // == public methods ==
    public boolean hasBodyParts() {
        return bodyParts.size > 0;
    }

    @Override
    public void reset() {
        log.debug("resetting snake component");
        head = null;
        bodyParts.clear();
        log.debug("reset done");

    }

}
