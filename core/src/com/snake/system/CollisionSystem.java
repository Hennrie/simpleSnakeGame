package com.snake.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import com.snake.common.EntityFactory;
import com.snake.common.GameManager;
import com.snake.component.BodyPartComponent;
import com.snake.component.BoundsComponent;
import com.snake.component.CoinComponent;
import com.snake.component.PositionComponent;
import com.snake.component.SnakeComponent;
import com.snake.config.GameConfig;
import com.snake.util.Mappers;

public class CollisionSystem extends IntervalSystem {

    public static final Family SNAKE_FAMILY = Family.all(SnakeComponent.class).get();

    public static final Family COIN_FAMILY = Family.all(CoinComponent.class).get();

    private final EntityFactory factory;

    public CollisionSystem(EntityFactory factory) {
        super(GameConfig.MOVE_TIME);
        this.factory = factory;

    }

    @Override
    protected void updateInterval() {
        Engine engine = getEngine();
        ImmutableArray<Entity> snakes = engine.getEntitiesFor(SNAKE_FAMILY);
        ImmutableArray<Entity> coins = engine.getEntitiesFor(COIN_FAMILY);

        // head <-> body parts
        for(Entity snakeEntity : snakes) {
            SnakeComponent snake = Mappers.SNAKE.get(snakeEntity);

            for(Entity bodyPartEntity : snake.bodyParts) {
                BodyPartComponent bodyPart = Mappers.BODY_PART.get(bodyPartEntity);

                if (bodyPart.justAdded) {
                    bodyPart.justAdded = false;
                    continue;
                }

                if(overlaps(snake.head, bodyPartEntity)) {
                    GameManager.INSTANCE.setGameOver();
                }
            }
        }

        // head <-> coin
        for(Entity snakeEntity : snakes) {
            for(Entity coinEntity : coins) {
                CoinComponent coin = Mappers.COIN.get(coinEntity);
                SnakeComponent snake = Mappers.SNAKE.get(snakeEntity);

                if(coin.available && overlaps(snake.head, coinEntity)) {
                    //mark coin as not available
                    coin.available = false;

                    // get head position and add body part
                    PositionComponent position = Mappers.POSITION.get(snake.head);
                    Entity bodyPart = factory.createBodyPart(position.x , position.y);
                    snake.bodyParts.insert(0, bodyPart);
                }
            }
        }
    }

    // overlaps function for 2 entities

    private static boolean overlaps(Entity first, Entity second) {
        BoundsComponent firstBounds = Mappers.BOUNDS.get(first);
        BoundsComponent secondBounds = Mappers.BOUNDS.get(second);

        return Intersector.overlaps(firstBounds.rectangle, secondBounds.rectangle);
    }
}
