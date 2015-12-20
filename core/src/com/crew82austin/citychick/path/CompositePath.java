package com.crew82austin.citychick.path;

import java.util.Vector;

/**
 * A <b>ParametricPath</b> that is composed of multiple
 * <b>ParametricPath</b>s.
 * 
 * @author whitting
 * @since 2015-12-20
 */
public class CompositePath implements ParametricPath {

  protected Vector<ParametricPath>  m_PathSegments = new Vector<>();

  @Override
  public float calcX(float t) {
    float x = 0.0f;
    t = (t < 0.0f) ? 0.0f : ((t > 1.0) ? 1.0f : t);
    if (!m_PathSegments.isEmpty()) {
      t *= (float) (m_PathSegments.size());
      int index = (int) Math.floor((double) t);
      t -= (float) index;
      x = m_PathSegments.elementAt(index).calcX(t);
    }
    return x;
  }

  @Override
  public float calcY(float t) {
    float y = 0.0f;
    t = (t < 0.0f) ? 0.0f : ((t > 1.0) ? 1.0f : t);
    if (!m_PathSegments.isEmpty()) {
      t *= (float) (m_PathSegments.size());
      int index = (int) Math.floor((double) t);
      t -= (float) index;
      y = m_PathSegments.elementAt(index).calcX(t);
    }
    return y;
  }

  @Override
  public float calcRot(float t) {
    float rot = 0.0f;
    t = (t < 0.0f) ? 0.0f : ((t > 1.0) ? 1.0f : t);
    if (!m_PathSegments.isEmpty()) {
      t *= (float) (m_PathSegments.size());
      int index = (int) Math.floor((double) t);
      t -= (float) index;
      rot = m_PathSegments.elementAt(index).calcX(t);
    }
    return rot;
  }

  public void addPath(ParametricPath segment) {
    m_PathSegments.add(segment);
  }

}
