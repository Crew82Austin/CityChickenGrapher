package com.crew82austin.citychick;


public abstract class AbstractCollidableObject implements CollidableObject {

	public static boolean testLineIntersection(float p0_x, float p0_y,
			float p1_x, float p1_y, 
		    float p2_x, float p2_y,
		    float p3_x, float p3_y)
	{
	    float s1_x = p1_x - p0_x;
	    float s1_y = p1_y - p0_y;
	    float s2_x = p3_x - p2_x;
	    float s2_y = p3_y - p2_y;
	    float d = (-s2_x * s1_y + s1_x * s2_y);

	    if (d != 0.0) {
	    	float s = (-s1_y * (p0_x - p2_x) + s1_x * (p0_y - p2_y)) / d;
	    	float t = ( s2_x * (p0_y - p2_y) - s2_y * (p0_x - p2_x)) / d;

	    	return (s >= 0 && s <= 1 && t >= 0 && t <= 1);
	    }
	    return false;
	}

}
