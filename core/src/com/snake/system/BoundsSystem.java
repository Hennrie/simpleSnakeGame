package com.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.snake.component.BoundsComponent;
import com.snake.component.DimensionComponent;
import com.snake.component.PositionComponent;
import com.snake.util.Mappers;

public class BoundsSystem extends IteratingSystem {

    //
    public static final Family FAMILY = Family.all(
            BoundsComponent.class,
            PositionComponent.class,
            DimensionComponent.class
    ).get();


    public BoundsSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.POSITION.get(entity);
        DimensionComponent dimension = Mappers.DIMENSION.get(entity);
        BoundsComponent bounds = Mappers.BOUNDS.get(entity);

        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width, dimension.height);
    }
}
