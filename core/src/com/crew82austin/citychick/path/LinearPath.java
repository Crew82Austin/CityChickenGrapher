package com.crew82austin.citychick.path;

public class LinearPath implements ParametricPath {

  protected float     m_X1;
  protected float     m_Y1;
  protected float     m_DeltaX;
  protected float     m_DeltaY;
  protected float     m_Rot = 0.0f;

  public LinearPath(float x1, float y1, float x2, float y2) {
    m_X1 = x1;
    m_Y1 = y1;
    m_DeltaX = (x2 - x1);
    m_DeltaY = (y2 - y1);
    m_Rot = (float) (Math.atan2((double) m_DeltaY, (double) m_DeltaX) * 180.0 / Math.PI);
  }

  @Override
  public float calcX(float t) {
    return (m_DeltaX * t) + m_X1;
  }

  @Override
  public float calcY(float t) {
    return (m_DeltaY * t) + m_Y1;
  }

  @Override
  public float calcRot(float t) {
    return m_Rot;
  }

}
