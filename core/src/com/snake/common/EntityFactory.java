package com.snake.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.snake.assets.AssetDescriptors;
import com.snake.assets.RegionNames;
import com.snake.component.BodyPartComponent;
import com.snake.component.BoundsComponent;
import com.snake.component.CoinComponent;
import com.snake.component.DimensionComponent;
import com.snake.component.DirectionComponent;
import com.snake.component.MovementComponent;
import com.snake.component.PlayerComponent;
import com.snake.component.PositionComponent;
import com.snake.component.SnakeComponent;
import com.snake.component.TextureComponent;
import com.snake.component.WorldWrapComponent;
import com.snake.config.GameConfig;
import com.snake.util.Mappers;

import java.awt.TextComponent;

public class EntityFactory {

    // == constants ==

    // == attributes ==
    private final PooledEngine engine;
    private final AssetManager assetManager;
    private TextureAtlas gamePlaAtlas;


    // == constructors ==
    // parameters needed:
    // - engine for adding created entities to engine
    // - assetManager to deal with textures

    public EntityFactory(PooledEngine engine, AssetManager assetManager) {

        this.engine = engine;
        this.assetManager = assetManager;
        init();
    }

    // == private methods ==

    private void init(){
        gamePlaAtlas = assetManager.get(AssetDescriptors.GAME_PLAY);
    }

    // == public methods ==
    // create procedure:
    // - create component with engine
    // - define component's attached variables
    // - add components to entity
    // - add entity to engine
    // - return entity


    public Entity createSnake() {
        //snake
        SnakeComponent snake = engine.createComponent(SnakeComponent.class);
        snake.head = createSnakeHead();

        // entity
        Entity snakeEntity = engine.createEntity();
        snakeEntity.add(snake);

        // add to engine
        engine.addEntity(snakeEntity);
        return snakeEntity;

    }

    public Entity createSnakeHead() {


        // position
        PositionComponent position = engine.createComponent(PositionComponent.class);

        // dimension
        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.SNAKE_SIZE;
        dimension.height = GameConfig.SNAKE_SIZE;

        // bounds
        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width, dimension.height);

        // direction
        DirectionComponent direction = engine.createComponent(DirectionComponent.class);

        // movement
        MovementComponent movement = engine.createComponent(MovementComponent.class);

        // player
        PlayerComponent player = engine.createComponent(PlayerComponent.class);

        // world wrap
        WorldWrapComponent worldWrap = engine.createComponent(WorldWrapComponent.class);

        // texture
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = gamePlaAtlas.findRegion(RegionNames.HEAD);

        // entity
        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(direction);
        entity.add(movement);
        entity.add(player);
        entity.add(worldWrap);
        entity.add(texture);

        // add to engine
        engine.addEntity(entity);
        return entity;
    }

    public void createCoin() {
        // position
        PositionComponent position = engine.createComponent(PositionComponent.class);

        // dimension
        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.COIN_SIZE;
        dimension.height = GameConfig.COIN_SIZE;

        // bounds
        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.rectangle.setPosition(position.x , position.y);
        bounds.rectangle.setSize(dimension.width, dimension.height);

        // coin
        CoinComponent coin = engine.createComponent(CoinComponent.class);

        // texture
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = gamePlaAtlas.findRegion(RegionNames.COIN);

        // coinEntity
        Entity coinEntity = engine.createEntity();
        coinEntity.add(position);
        coinEntity.add(dimension);
        coinEntity.add(bounds);
        coinEntity.add(coin);
        coinEntity.add(texture);

        engine.addEntity(coinEntity);
    }

    public Entity createBodyPart(float x, float y) {
        // position
        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = x;
        position.y = y;

        // dimension
        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.SNAKE_SIZE;
        dimension.height = GameConfig.SNAKE_SIZE;

        // bounds
        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width, dimension.height);

        // body part
        BodyPartComponent bodyPart = engine.createComponent(BodyPartComponent.class);

        // texture
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = gamePlaAtlas.findRegion(RegionNames.BODY);

        // create entity
        Entity entity = engine.createEntity();
        entity.add(dimension);
        entity.add(position);
        entity.add(bounds);
        entity.add(bodyPart);
        entity.add(texture);

        engine.addEntity(entity);
        return entity;
    }
}
