package com.crew82austin.citychick;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpriteSet {

  protected Texture img;
  protected int rows = 0;
  protected int columns = 0;
  protected int spritesTotal = 0;
  protected int tileWidth = 0;
  protected int tileHeight = 0;
  protected float tileWidthCenter = 0.0f;
  protected float tileHeightCenter = 0.0f;

  public SpriteSet(String resource, int width, int height) {
    img = new Texture(resource);
    tileWidth = width;
    tileHeight = height;
    tileWidthCenter = ((float) tileWidth) / 2.0f;
    tileHeightCenter = ((float) tileHeight) / 2.0f;
    rows = img.getHeight() / tileHeight;
    columns = img.getWidth() / tileWidth;
    spritesTotal = (img.getHeight() / tileHeight) * tileWidth;
  }

  public void draw(SpriteBatch batch, int x, int y, int frame, boolean reversedX, boolean reversedY, float scale) {
    int imgX = (frame % columns) * tileWidth;
    int imgY = (frame / columns) * tileHeight;
    batch.draw(img,                            // Texture texture
        x,                 y,                  // float x,       float y,
        tileWidth * scale, tileHeight * scale, // float width,   float height,
        imgX,              imgY,               // int srcX,      int srcY,
        tileWidth,         tileHeight,         // int srcWidth,  int srcHeight,
        reversedX,         reversedY);         // boolean flipX, boolean flipY
  }

  public void draw(SpriteBatch batch, float x, float y, int frame, float rot, float scale) {
    int imgX = (frame % columns) * tileWidth;
    int imgY = (frame / columns) * tileHeight;
    batch.draw(img,                                // Texture texture,
        x - tileWidthCenter, y - tileHeightCenter, // float x,       float y,
        tileWidthCenter,     tileHeightCenter,     // float originX, float originY,
        tileWidth * scale,   tileHeight * scale,   // float width,   float height,
        scale,               scale,                // float scaleX,  float scaleY,
        rot,                                       // float rotation,
        imgX,                imgY,                 // int srcX,      int srcY,
        tileWidth,           tileHeight,           // int srcWidth,  int srcHeight,
        false,               false);               // boolean flipX, boolean flipY
  }

  public final int maxSprites() {
    return rows * columns;
  }
}
