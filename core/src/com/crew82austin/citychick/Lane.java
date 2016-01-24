package com.crew82austin.citychick;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.math.Vector2;
import com.crew82austin.citychick.path.ParametricPath;

public class Lane implements ParametricPath {
	protected ParametricPath		path;
	protected Stoplight         	stoplight;
	protected Set<CollidableObject> objects = new HashSet<>();

	public Lane(ParametricPath path, Stoplight stoplight) {
		this.path = path;
		this.stoplight = stoplight;
	}

	@Override
	public float calcX(float t) {
		return path.calcX(t);
	}

	@Override
	public float calcY(float t) {
		return path.calcY(t);
	}

	@Override
	public float calcRot(float t) {
		return path.calcRot(t);
	}
	
	public boolean testForCollisions(CollidableObject src, Vector2 start, Vector2 end) {
		if (stoplight.testCollision(start, end)) {
			return true;
		}
		for (CollidableObject test : objects) {
			if ((test != src) &&
				(test.testCollision(start, end))) {
				return true;
			}
		}
		return false;
	}

	public void add(CollidableObject car) {
		objects.add(car);
	}

	public void remove(CollidableObject car) {
		objects.remove(car);
	}
}
