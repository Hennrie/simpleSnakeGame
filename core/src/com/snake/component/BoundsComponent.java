package com.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;



public class BoundsComponent implements Component, Pool.Poolable {


    // == constants ==

    // == attributes ==
    public final Rectangle rectangle = new Rectangle(0, 0, 1, 1);
    // == constructors ==

    // == private methods ==

    // == public methods ==

    @Override
    public void reset() {
        rectangle.setPosition(0, 0);
        rectangle.setSize(1, 1);
    }
}
