package com.crew82austin.citychick.path;

/**
 * A silly "test" path that causes the object to spin at a single point.
 * @author dwhittington
 *
 */
public class SpinningPath implements ParametricPath {
	
	protected float		x;
	protected float		y;
	protected float		rate;

	public SpinningPath(float x, float y, float rate) {
		this.x = x;
		this.y = y;
		this.rate = rate;
	}

	@Override
	public float calcX(float t) {
		return x;
	}

	@Override
	public float calcY(float t) {
		return y;
	}

	@Override
	public float calcRot(float t) {
		return (360.0f * rate * t);
	}

}
