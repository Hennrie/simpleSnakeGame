package com.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.snake.component.MovementComponent;
import com.snake.component.PositionComponent;
import com.snake.component.SnakeComponent;
import com.snake.config.GameConfig;
import com.snake.util.Mappers;

public class SnakeMovementSystem extends IntervalIteratingSystem {

    // == constants ==
    public static final Family FAMILY = Family.all(
            SnakeComponent.class
    ).get();

    // == attributes ==
    private float beforeUpdateHeadX;
    private float beforeUpdateHeadY;

    // == constructors ==
    public SnakeMovementSystem() {
        super(FAMILY, GameConfig.MOVE_TIME);
    }

    @Override
    protected void processEntity(Entity entity) {
        SnakeComponent snake = Mappers.SNAKE.get(entity);

        moveHead(snake.head);
        moveBodyParts(snake);
    }

    // == private methods ==
    private void moveHead(Entity head) {
        MovementComponent movement = Mappers.MOVEMENT.get(head);
        PositionComponent position = Mappers.POSITION.get(head);

        beforeUpdateHeadX = position.x;
        beforeUpdateHeadY = position.y;

        position.x += movement.xSpeed;
        position.y += movement.ySpeed;

    }

    private void moveBodyParts(SnakeComponent snake) {
        if(snake.hasBodyParts()) {
            Entity tail = snake.bodyParts.removeIndex(0);
            PositionComponent position = Mappers.POSITION.get(tail);
            position.x = beforeUpdateHeadX;
            position.y = beforeUpdateHeadY;
            snake.bodyParts.add(tail);
        }
    }
}
