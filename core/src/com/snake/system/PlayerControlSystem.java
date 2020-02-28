package com.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.snake.common.Direction;
import com.snake.component.DirectionComponent;
import com.snake.component.PlayerComponent;
import com.snake.util.Mappers;

public class PlayerControlSystem extends IteratingSystem {

    // == constants ==
    public static final Family FAMILY = Family.all(
            PlayerComponent.class,
            DirectionComponent.class
    ).get();

    // == constructors ==
    public PlayerControlSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean rightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean downPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        boolean upPressed = Gdx.input.isKeyPressed(Input.Keys.UP);

        DirectionComponent directionComponent = Mappers.DIRECTION.get(entity);

        if (leftPressed) {
            updateDirection(directionComponent, Direction.LEFT);
        } else if (rightPressed) {
            updateDirection(directionComponent, Direction.RIGHT);
        } else if (upPressed) {
            updateDirection(directionComponent, Direction.UP);
        } else if (downPressed) {
            updateDirection(directionComponent, Direction.DOWN);
        }
    }

    // == private methods ==
    private void updateDirection(DirectionComponent directionComponent, Direction newDirection ) {
        if(!directionComponent.isOpposite(newDirection)) {
            directionComponent.direction = newDirection;
        }
    }
}
