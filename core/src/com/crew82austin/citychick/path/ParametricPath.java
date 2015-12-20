package com.crew82austin.citychick.path;

public interface ParametricPath {

  /**
   * Calculate the value for <b>X</b> given input parameter <b>t</b>.
   * @param t the parametric input, range 0.0 to 1.0 inclusive
   * @return the value <b>X</b>
   */
  public float calcX(float t);

  /**
   * Calculate the value for <b>Y</b> given input parameter <b>t</b>.
   * @param t the parametric input, range 0.0 to 1.0 inclusive
   * @return the value <b>Y</b>
   */
  public float calcY(float t);

  /**
   * Calculate the direction of the path, expressed as an angle in degrees,
   * given input parameter <b>t</b>.  A value of 0.0 degrees will point in
   * the positive X direction, and increasing values will be measured
   * counter-clockwise from the X axis.
   * @param t the parametric input, range 0.0 to 1.0 inclusive
   * @return the path's direction in degrees
   */
  public float calcRot(float t);

}
