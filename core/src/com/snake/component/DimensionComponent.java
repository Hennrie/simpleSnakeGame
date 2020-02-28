package com.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class DimensionComponent implements Component, Pool.Poolable {

    // == constants ==

    // == attributes ==
    public float width = 1f;
    public float height = 1f;
    // == constructors ==

    // == private methods ==

    // == public methods ==

    @Override
    public void reset() {
        width = 1f;
        height =1f;
    }
}
