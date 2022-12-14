package com.jude.particlesystem;

import java.util.ArrayList;

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
	Boolean holding;
	final boolean mouseDebug = false;

	//physics
	final Vector2 gravity = new Vector2(0f, -100f);
	World world;

	//actual objects
	ArrayList<Bunch> bunches;

	//fps stuff
	FPSCounter fpscount;
	final boolean showFPS = false;

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
		holding = false;
		tp = new Vector3();
		Gdx.input.setInputProcessor(this);

		//physics
		world = new World(gravity, true);

		//actual objects
		bunches = new ArrayList<Bunch>();

		//fps counter
		fpscount = new FPSCounter(0f, 0f);
	}

	@Override
	public void render ()
	{
		//graphical stuff
		ScreenUtils.clear(1, 1, 1, 1);

		shapes.setProjectionMatrix(camera.combined);
		shapes.begin(ShapeRenderer.ShapeType.Filled);

		renderBunches();

		if(mouseDebug) 
		{
			shapes.circle(tp.x, tp.y, 0.1f, 500);
			System.out.println("(" + tp.x + ", " + tp.y + ")");
		}
		shapes.end();

		//display fps
		if(showFPS) fpscount.render(batch);

		//logic
		if(Gdx.graphics.getDeltaTime() < 10 && holding)
		{
			bunches.add(new Bunch(tp.x, tp.y ,world));
		}

		//clean up
		for(Bunch b: bunches)
		{
			b.clean();
		}

		world.step(Gdx.graphics.getDeltaTime(), 6, 2);
	}
	
	public void renderBunches()
	{
		for(int i = 0; i < bunches.size(); i++)
		{
			for(int ii = 0; ii < bunches.get(i).getArr().size(); ii++)
			{
				Particle particle = bunches.get(i).getArr().get(ii);
				
				shapes.setColor(Color.BLUE);
				shapes.circle(particle.getPos().x, particle.getPos().y, 0.25f, 16);
			}
		}
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
		holding = true;
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{	
		holding = false;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{	
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && Gdx.graphics.getDeltaTime() < 10)
		{
			camera.unproject(tp.set(screenX, screenY, 0));
			dragging = true;
			bunches.add(new Bunch(tp.x, tp.y ,world));
			return true;
		}
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

	@Override
	public void resize(int width, int height)
	{
		viewport.update(width, height);
	}
}
