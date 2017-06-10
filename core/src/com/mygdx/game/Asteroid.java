package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

class Asteroid {
    private static final float SPEED_MAX = 500.0f;
    private static final float SPEED_MIN = 100.0f;
    private final Vector2 position;
    private float speed;
    private static Texture texture;
    private int hp;
    private float angle;

    Asteroid() {
        if (texture == null) {
            texture = new Texture("asteroid60.tga");
        }
        position = new Vector2((float) (Math.random() + 1) * Gdx.graphics.getWidth(), (float) Math.random() * (Gdx.graphics.getHeight() - texture.getHeight()));
        speed = SPEED_MIN + (float) Math.random() * SPEED_MAX;
        angle = (float) Math.random() * 360;
        hp = 3;
    }

    Rectangle getRectangle() {
        return new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
    }

    private void recreate() {
        position.x = (float) (Math.random() + 1) * Gdx.graphics.getWidth();
        position.y = (float) Math.random() * Gdx.graphics.getHeight() - texture.getHeight();
        speed = SPEED_MIN + (float) Math.random() * SPEED_MAX;
        angle = (float) Math.random() * 360;
        hp = 3;
    }

    void update(long dt) {
        position.x -= speed * dt / 1000;
        angle += speed * dt/ 2500;
        if (position.x < -texture.getWidth()) {
            recreate();
        }
    }

    void getDamage(int dmg) {
        hp -= dmg;
        if (hp <= 0) {
            recreate();
        }
    }

    void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y, texture.getWidth() / 2, texture.getHeight() / 2, texture.getWidth(), texture.getHeight(), 1f, 1f, angle, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }
}
