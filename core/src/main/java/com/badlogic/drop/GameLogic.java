package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameLogic {
    private final Array<Sprite> dropSprites = new Array<>();
    private final Sprite bucketSprite = new Sprite(Assets.bucketTexture);
    private final Viewport viewport;
    private float dropTimer = 0;
    private final Rectangle bucketRectangle;
    private final Rectangle dropRectangle;

    public GameLogic(Viewport viewport) {
        this.viewport = viewport;
        bucketSprite.setSize(1, 1);
        bucketRectangle = new Rectangle();
        dropRectangle = new Rectangle();
    }

    public void update() {
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        float bucketWidth = bucketSprite.getWidth();
        float bucketHeight = bucketSprite.getHeight();

        bucketSprite.setX(MathUtils.clamp(bucketSprite.getX(), 0, worldWidth - bucketWidth));

        float delta = Gdx.graphics.getDeltaTime();
        bucketRectangle.set(bucketSprite.getX(), bucketSprite.getY(), bucketWidth, bucketHeight);

        for (int i = dropSprites.size - 1; i >= 0; i--) {
            Sprite dropSprite = dropSprites.get(i);
            float dropWidth = dropSprite.getWidth();
            float dropHeight = dropSprite.getHeight();

            dropSprite.translateY(-2f * delta);
            dropRectangle.set(dropSprite.getX(), dropSprite.getY(), dropWidth, dropHeight);

            if (dropSprite.getY() < -dropHeight) dropSprites.removeIndex(i);
            else if (bucketRectangle.overlaps(dropRectangle)) {
                dropSprites.removeIndex(i);
                Assets.dropSound.play();
            }
        }

        dropTimer += delta;
        if (dropTimer > 1f) {
            dropTimer = 0;
            createDroplet();
        }
    }

    private void createDroplet() {
        Sprite dropSprite = new Sprite(Assets.dropTexture);
        dropSprite.setSize(1, 1);
        dropSprite.setX(MathUtils.random(0f, viewport.getWorldWidth() - dropSprite.getWidth()));
        dropSprite.setY(viewport.getWorldHeight());
        dropSprites.add(dropSprite);
    }

    public Sprite getBucketSprite() {
        return bucketSprite;
    }

    public Array<Sprite> getDropSprites() {
        return dropSprites;
    }
}
