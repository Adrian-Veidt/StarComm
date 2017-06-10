package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private Background background;
    private Hero hero;
    private long pastTime;
    private Asteroid[] asteroids;
    static ArrayList<Bullet> bullets;
    private Texture textureBullet;
    private boolean isPaused;

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Background();
        hero = new Hero();
        pastTime = System.currentTimeMillis();
        asteroids = new Asteroid[20];
        for (int i = 0; i < asteroids.length; i++) {
            asteroids[i] = new Asteroid();
        }
        bullets = new ArrayList<>();
        textureBullet = new Texture("bullet20.tga");
        isPaused = false;
    }

    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.render(batch);
        hero.render(batch);
        for (Asteroid asteroid : asteroids) {
            asteroid.render(batch);
        }
        for (Bullet bullet : bullets) {
            bullet.render(batch, textureBullet);
        }
        batch.end();
    }

    private void update() {

        if (Gdx.input.isKeyPressed(Input.Keys.P)) {
            isPaused = !isPaused;
        }

        long currentTime = System.currentTimeMillis();
        long dt = currentTime - pastTime;
        if (!isPaused) {
            background.update(dt);
            hero.update(dt);
            for (Asteroid asteroid : asteroids) {
                if (hero.getRectangle().overlaps(asteroid.getRectangle())) {
                    hero.recreate();
                }
                asteroid.update(dt);
            }
            for (int i = bullets.size() - 1; i >= 0; i--) {
                bullets.get(i).update(dt);
                if (bullets.get(i).getPosition().x > Gdx.graphics.getWidth()) {
                    bullets.remove(i);
                    continue;
                }
                for (Asteroid asteroid : asteroids) {
                    if (asteroid.getRectangle().contains(bullets.get(i).getPosition())) {
                        asteroid.getDamage(1);
                        bullets.remove(i);
                        break;
                    }
                }
            }
        }
        pastTime = currentTime;
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
