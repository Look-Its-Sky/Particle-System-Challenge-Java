package com.jude.particlesystem;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Entity
{
    protected float w, h;
    protected Body body;
    protected Color color;

    public Entity(float x, float y, World world)
    {
        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        body = world.createBody(bodyDef);
    }

    public Body getBody()
    {
        return body;
    }

    public Vector2 getPos()
    {
        return body.getPosition();
    }
}
