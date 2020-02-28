package com.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.snake.component.DimensionComponent;
import com.snake.component.PositionComponent;
import com.snake.component.TextureComponent;
import com.snake.util.Mappers;

public class RenderSystem extends IteratingSystem {

    public static final Family FAMILY = Family.all(
            TextureComponent.class,
            PositionComponent.class,
            DimensionComponent.class
    ).get();

    private final SpriteBatch batch;
    private final Viewport viewport;

    public RenderSystem(SpriteBatch batch, Viewport viewport) {
        super(FAMILY);
        this.batch = batch;
        this.viewport = viewport;

    }

    @Override
    public void update(float deltaTime) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        // NOTE: calls processEntity for every entity in family inside fpr loop
        super.update(deltaTime);
        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.POSITION.get(entity);
        DimensionComponent dimension = Mappers.DIMENSION.get(entity);
        TextureComponent texture = Mappers.TEXTURE.get(entity);

        batch.draw(texture.region,
                position.x, position.y,
                dimension.width, dimension.height);
    }


}
