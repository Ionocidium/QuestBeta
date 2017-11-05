package  model;

import java.io.IOException;



/**
 * Handles the encapsulation and representation of <code>Exercises</code> model.
 * 
 * @author Darren Garcia
 */

public class Exercises {

	private int exercise;
	private String message;
	private int area;
	private int cleared;
	
	/**
	 * Creates a blank representation for <code>User</code> model.
	 */
	public Exercises() {
		
	}

	/**
	 * Creates a representation that contains the information for <code>User</code> model.
	 * @param exercise Exercise
	 * @param message Exercise Message
	 * @param area Area
	 * @param cleared Clear Boolean
	 */
	public Exercises(int exercise, String message, int area, int cleared) {
		this.exercise = exercise;
		this.message = message;
		this.area = area;
		this.cleared = cleared;
	}

	public int getExercise() {
		return exercise;
	}

	public void setExercise(int exercise) {
		this.exercise = exercise;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}
	
	public int getCleared() {
		return cleared;
	}
	
	public void setCleared(int cleared) {
		this.cleared = cleared;
	}
}
