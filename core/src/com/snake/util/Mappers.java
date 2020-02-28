package com.snake.util;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.snake.component.BodyPartComponent;
import com.snake.component.BoundsComponent;
import com.snake.component.CoinComponent;
import com.snake.component.DimensionComponent;
import com.snake.component.DirectionComponent;
import com.snake.component.MovementComponent;
import com.snake.component.PositionComponent;
import com.snake.component.SnakeComponent;
import com.snake.component.TextureComponent;

public final class Mappers {

    // == constants ==
    public static final ComponentMapper<BoundsComponent> BOUNDS =
        ComponentMapper.getFor(BoundsComponent.class);

    public static final ComponentMapper<DimensionComponent> DIMENSION =
            ComponentMapper.getFor(DimensionComponent.class);
    public static final ComponentMapper<PositionComponent> POSITION =
            ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<SnakeComponent> SNAKE =
            ComponentMapper.getFor(SnakeComponent.class);
    public static final ComponentMapper<DirectionComponent> DIRECTION =
            ComponentMapper.getFor(DirectionComponent.class);
    public static final ComponentMapper<MovementComponent> MOVEMENT =
            ComponentMapper.getFor(MovementComponent.class);
    public static final ComponentMapper<CoinComponent> COIN =
            ComponentMapper.getFor(CoinComponent.class);
    public static final ComponentMapper<BodyPartComponent> BODY_PART =
            ComponentMapper.getFor(BodyPartComponent.class);
    public static final ComponentMapper<TextureComponent> TEXTURE =
            ComponentMapper.getFor(TextureComponent.class);

    // == attributes ==

    // == constructors ==
    private Mappers() {
    }
    // == private methods ==

    // == public methods ==
}
