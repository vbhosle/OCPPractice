package ocp.inaction.model;

import java.util.Random;

public class Apple {

	private String color = "red";
	private int weight;
	
	public Apple() {
		Random random = new Random();
		this.weight = 100 + random.nextInt(100);
	}
	
	public Apple(int weight) {
		this.weight = weight;
	}

	public Apple(String color, int weight) {
		this.color = color;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Apple [color=" + color + ", weight=" + weight + "]";
	}

	public String getColor() {
		return color;
	}

	public int getWeight() {
		return weight;
	}
	
	
}
