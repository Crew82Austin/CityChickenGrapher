package com.crew82austin.citychick;

import com.badlogic.gdx.math.Vector2;

public interface CollidableObject {

	/**
	 * Tests to see if the object intersects with a line segment that
	 * represents the motion of another object.
	 * @param start
	 * @param end
	 * @return true if the line segment intersects this object
	 */
	public boolean testCollision(Vector2 start, Vector2 end);
}
