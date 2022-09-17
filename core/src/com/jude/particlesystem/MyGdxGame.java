package com.jude.particlesystem;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor 
{
	SpriteBatch batch;
	Texture img;

	final static float SCALE = 32f;
	final static float INV_SCALE = 1.f/SCALE;

	//camera
	// this is our "target" resolution, note that the window can be any size, it is not bound to this one
	final static float VP_WIDTH = 1280 * INV_SCALE;
	final static float VP_HEIGHT = 720 * INV_SCALE;

	OrthographicCamera camera;
	ExtendViewport viewport;		
	ShapeRenderer shapes;

	//mouse stuff
	Vector3 tp;
	Boolean dragging;
	final boolean mouseDebug = false;

	//physics
	final Vector2 gravity = new Vector2(0f, -9.81f);
	World world;

	//test objects
	Particle particle;
	
	@Override
	public void create ()
	{
		//drawing
		batch = new SpriteBatch();

		//camera stuff
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(VP_WIDTH, VP_HEIGHT, camera);
		
		//mouse input
		shapes = new ShapeRenderer();
		shapes.setColor(Color.BLACK);
		tp = new Vector3();
		Gdx.input.setInputProcessor(this);

		//physics
		world = new World(gravity, true);

		//test objects
		particle = new Particle(100, 100);
	}

	@Override
	public void render ()
	{
		ScreenUtils.clear(1, 1, 1, 1);
		
		shapes.setProjectionMatrix(camera.combined);
		shapes.begin(ShapeRenderer.ShapeType.Filled);
		
		shapes.setColor(Color.BLUE);
		shapes.circle(pos.x, pos.y, 0.25f, 16);

		if(mouseDebug)shapes.circle(tp.x, tp.y, 0.1f, 500);
		shapes.end();
	}
	
	@Override
	public void dispose ()
	{
		batch.dispose();
		shapes.dispose();
	}

	@Override
	public boolean keyDown(int keycode)
	{
		return false;
	}

	@Override
	public boolean keyUp(int keycode) 
	{	
		return false;
	}

	@Override
	public boolean keyTyped(char character) 
	{	
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) 
	{
		if(button != Input.Buttons.LEFT || pointer > 0) return false;
		camera.unproject(tp.set(screenX, screenY, 0));
		dragging = true;
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{	
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{	
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) 
	{ 
		return false;
	}
}
