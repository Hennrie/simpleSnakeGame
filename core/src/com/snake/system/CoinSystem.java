package com.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.snake.component.CoinComponent;
import com.snake.component.PositionComponent;
import com.snake.config.GameConfig;
import com.snake.util.Mappers;

public class CoinSystem extends IteratingSystem {

    public static final Family FAMILY = Family.all(
            PositionComponent.class,
            CoinComponent.class
    ).get();

    public CoinSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CoinComponent coin = Mappers.COIN.get(entity);
        PositionComponent position = Mappers.POSITION.get(entity);

        if (coin.available) {
            return;
        }

        // coin not available, reposition and make coin available
        float coinX = MathUtils.random((int)(GameConfig.WORLD_WIDTH - GameConfig.COIN_SIZE));
        float coinY = MathUtils.random((int)(GameConfig.MAX_Y - GameConfig.COIN_SIZE));

        position.x = coinX;
        position.y = coinY;
        coin.available = true;
    }
}
