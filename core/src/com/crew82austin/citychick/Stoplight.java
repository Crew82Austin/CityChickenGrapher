package com.crew82austin.citychick;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class Stoplight {

	public static final long	YELLOW_DURATION = 3000;		// 3 seconds
	
	public enum LightState {
		GREEN,
		YELLOW,
		RED
	}

	protected LightState    m_eState = LightState.RED;
	protected Vector2       m_vecLowerLeft;
	protected float         m_nWidth;
	protected float         m_nHeight;
	protected long          m_nExpires = 0L;

	public Stoplight(float x1, float y1, float width, float height) {
		m_vecLowerLeft = new Vector2(x1, y1);
		m_nWidth  = width;
		m_nHeight = height;
	}

	public void draw(ShapeRenderer renderer) {
		renderer.begin(ShapeType.Filled);
		switch (m_eState) {
		case GREEN:
			renderer.setColor(Color.GREEN);
			break;
		case YELLOW:
			renderer.setColor(Color.YELLOW);
			if (System.currentTimeMillis() >= m_nExpires) {
				m_eState = LightState.RED;
			}
			break;
		case RED:
			renderer.setColor(Color.RED);
			break;
		}
		renderer.rect(m_vecLowerLeft.x, m_vecLowerLeft.y, m_nWidth, m_nHeight);
		renderer.end();
	}

	// Toggles the stoplight into it's next "state"
	public void toggle() {
		switch (m_eState) {
		case GREEN:
			m_eState = LightState.YELLOW;
			m_nExpires = System.currentTimeMillis() + YELLOW_DURATION;
			break;
		case YELLOW:
			// We're already changing, so don't do anything.
			// The light will turn RED when it's ready.
			break;
		case RED:
			m_eState = LightState.GREEN;
			break;
		}
	}

	// Returns the current state of the stoplight
	public LightState getLightState() {
		return (m_eState);
	}

	public boolean testPoint(float x, float y) {
		return ((x >= m_vecLowerLeft.x) && (x < m_vecLowerLeft.x + m_nWidth) &&
				(y >= m_vecLowerLeft.y) && (y < m_vecLowerLeft.y + m_nHeight));
	}
}
