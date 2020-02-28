package com.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class PositionComponent implements Component, Pool.Poolable {

    // == constants ==

    // == attributes ==
    public float x;
    public float y;

    // == constructors ==

    // == private methods ==

    // == public methods ==

    @Override
    public void reset() {
        x = 0;
        y = 0;
    }
}
