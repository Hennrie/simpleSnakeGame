package com.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class CoinComponent implements Component, Pool.Poolable {

    public boolean available;

    @Override
    public void reset() {
        available = false;
    }
}
