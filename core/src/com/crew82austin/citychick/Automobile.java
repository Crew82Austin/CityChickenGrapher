package com.crew82austin.citychick;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Represents a car in the game.
 * 
 * @author dwhittington
 *
 */
public class Automobile extends AbstractCollidableObject {
	
	protected static final float		MAX_RATE = 0.25f;
	protected static final float        DELTA_RATE = 0.05f;
	protected static final float        LOOKAHEAD = 0.10f;

	protected SpriteSet         spriteSet;
	protected Lane              lane;
	
	// rate represents the rate of "progress" per second.
	// A "rate" of .25 means that the car will reach 100% completion in
	// 4 seconds.
	protected float             rate;
	// progress represents the fraction of path completion, from 0.0 to 1.0
	protected float             progress;
	protected Vector2           position;
	protected Vector2           target;
	protected float             angle;
	
	public Automobile(SpriteSet spriteSet, Lane lane) {
		this.spriteSet = spriteSet;
		this.lane = lane;
		this.rate = MAX_RATE;
		this.progress = 0;
		this.lane.add(this);
		position = new Vector2(lane.calcX(progress), lane.calcY(progress));
		target = new Vector2(position);
		update(0.0f);
	}

	public void draw(SpriteBatch batch) {
		spriteSet.draw(batch, position.x, position.y, 0, angle, 1.0f);
	}

	public void drawDebug(ShapeRenderer renderer) {
		renderer.line(position, target);
	}
	
	public void update(float delta) {
		// First, update our rate
		target.set(lane.calcX(progress + LOOKAHEAD), lane.calcY(progress + LOOKAHEAD));
		if (lane.testForCollisions(this, position, target)) {
			if (rate >= 0.0f) {
				rate -= DELTA_RATE;
				if (rate < 0.0f) {
					rate = 0.0f;
				}
			}
		} else {
			if (rate < MAX_RATE) {
				rate += DELTA_RATE;
			}
			if (rate > MAX_RATE) {
				rate = MAX_RATE;
			}
		}
		// Update our position using our new rate
		progress += (delta * rate);
		position.set(lane.calcX(progress), lane.calcY(progress));
		angle = lane.calcRot(progress) - 90.0f;
	}

	public boolean isDone() {
		if (progress > 1.0f) {
			lane.remove(this);
			return true;
		}
		return false;
	}

	@Override
	public boolean testCollision(Vector2 start, Vector2 end) {
		Vector2 carCenter = new Vector2(position);
		carCenter.sub(start);
		Vector2 closest = new Vector2(end);
		closest.sub(start);
		float scale = closest.dot(carCenter);
		if (scale > 1.0f) {
			scale = 1.0f;
		}
		if (scale < 0.0f) {
			scale = 0;
		}
		closest.scl(scale);
		return closest.dst2(carCenter) <= 2048.0;
	}
}
