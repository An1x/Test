package engine.util;

import java.io.Serializable;

public class Vector2f implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public float x, y;

	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2f() {
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * The dot product of two vectors is calculated as v1.x * v2.x + v1.y * v2.y
	 * @return vector1 dot vector2.
	 */
	public static float dot(Vector2f vector1, Vector2f vector2) {
		return vector1.x * vector2.x + vector1.y * vector2.y;
	}
	
	/**
	 * Return the length of this vector (Read Only).
	 * @return the length of this vector (Read Only).
	 */
	public static float magnitude(Vector2f vector) {
		return (float) Math.sqrt((double) (vector.x * vector.x + vector.y * vector.y));
	}
	
	/**
	 * Returns the angle between vector1 and vector2, in radians. 
	 * @return the angle between vector1 and vector2, in radians. 
	 */
	public static float angle(Vector2f vector1, Vector2f vector2) {
		float scalar = dot(vector1, vector2);
		float mag1 = magnitude(vector1);
		float mag2 = magnitude(vector2);
		return (float) Math.acos((double) (scalar / (mag1 * mag2)));
	}
	
	/**
	 * Used for image rotation.
	 * Returns the rotation between vector1 and vector2, in radians. 
	 * @return the angle between vector1 and vector2, in radians. 
	 */
	public static float rotation(Vector2f vector1, Vector2f vector2) {
		int dx = (int) (vector1.x - vector2.x);
        int dy = (int) (vector1.y - vector2.y);
        return  (float) (Math.atan2(dy, dx) - 1.570796327);
	}
	
	/**
	 * Subtract a vector from another vector and place the result in a destination vector.
	 * @return another vector.
	 */
	public static Vector2f substract(Vector2f vector1, Vector2f vector2) {
		return new Vector2f(vector1.x - vector2.x, vector1.y - vector2.y);
	}
	
	/**
	 * Returns the distance between vector1 and vector2.
	 * @return the distance between vector1 and vector2.
	 */
	public static float distance(Vector2f vector1, Vector2f vector2) {
		return magnitude(substract(vector1, vector2));
	}
	
	/**
	 * Gradually changes a vector towards a desired goal over time, with deviation.
	 * A good deviation is [1.22f].
	 * @param target 
	 * @param smoothTime 
	 * @param deviation until the position is equal use [0] for the exact position.
	 */
	public static void smoothDamp(Vector2f current, Vector2f target, float smoothTime, float deviation) {
		if(!equalPosition(current, target, deviation)) {
			float diffX = current.x - target.x;
			float diffY = current.y - target.y;
			float distance = distance(current, target);
			current.x += ((-1 / distance) * diffX) * smoothTime;
			current.y += ((-1 / distance) * diffY) * smoothTime;
		}
	}
	
	/**
	 * Returns true if vectors position is equal, use [0] for the exact position 
	 * @param deviation until they are equal. 
	 * @return true if vectors position is equal with the deviation.
	 */
	public static boolean equalPosition(Vector2f current, Vector2f target, float deviation) {
		return (current.x >= target.x -deviation && current.x <= target.x +deviation && current.y >= target.y -deviation && current.y <= target.y +deviation);
	}
	
	
	
	
	/**
	 * Returns true if vectors position is equal, use [0] for the exact position 
	 * @param deviation until they are equal. 
	 * @return true if vectors position is equal with the deviation.
	 */
	public boolean equalPosition(Vector2f target, float deviation) {
		return (this.x >= target.x - deviation && this.x <= target.x + deviation && this.y >= target.y - deviation && this.y <= target.y + deviation);
	}
	
	/**
	 * Gradually changes a vector towards a desired goal over time, with deviation.
	 * A good deviation is [1.22f].
	 * @param target 
	 * @param smoothTime 
	 * @param deviation until the position is equal, use [0] for the exact position.
	 */
	public void smoothDamp(Vector2f target, float smoothTime, float deviation) {
		if(!equalPosition(target, deviation)) {
			float diffX = this.x - target.x;
			float diffY = this.y - target.y;
			float distance = distance(this, target);
			move(((-1 / distance) * diffX) * smoothTime, ((-1 / distance) * diffY) * smoothTime);
		}
	}

	/**
	 * Return the length of this vector (Read Only).
	 * @return the length of this vector (Read Only).
	 */
	public float magnitude() {
		return (float) Math.sqrt((double) (x * x + y * y));
	}
	
	/**
	 * Return this vector with a magnitude of 1 (Read Only).
	 * @return this vector with a magnitude of 1 (Read Only).
	 */
	public Vector2f normalized() {
		float nX = (1.0f / magnitude()) * x;
		float nY = (1.0f / magnitude()) * y;
		return new Vector2f(nX, nY);
	}
	
	/**
	 * Makes this vector have a magnitude of 1.
	 */
	public void normalize() {
		this.x = (1.0f / magnitude()) * x;
		this.y = (1.0f / magnitude()) * y;
	}
	
	/**
	 * Scale this vector
	 */
	public void scale(float scale) {
		x *= scale;
		y *= scale;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	/**
	 * Set x and y components of an existing Vector2f.
	 * @param vector2f is the existing Vector2f.
	 */
	public void set(Vector2f vector2f) {
		x = vector2f.x;
		y = vector2f.y;
	}
	
	/**
	 * Set the X and Y values
	 */
	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Set X
	 */
	public void setX(float x) {
		this.x = x;
	}
	
	/**
	 * Set Y
	 */
	public void setY(float y) {
		this.y = y;
	}
	
	/**
	 * @param velX how quick is the moving in the X direction.
	 */
	public void moveX(float velX) {
		this.x += velX;
	}
	
	/**
	 * @param velY how quick is the moving in the Y direction.
	 */
	public void moveY(float velY) {
		this.y += velY;
	}
	
	/**
	 * @param velX how quick is the moving in the X direction.
	 * @param velY how quick is the moving in the Y direction.
	 */
	public void move(float velX, float velY) {
		this.x += velX;
		this.y += velY;
	}
	
	/**
	 * @return a nicely formatted string for this vector.
	 */
	public String toString() {
		return "[" + x + ", " + y + "]";
	}
	
}
