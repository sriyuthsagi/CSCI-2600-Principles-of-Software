/**
 * This is part of HW0: Environment Setup and Java Introduction.
 */
package hw0;

import java.awt.Color;

/**
 * This is a simple object that has a volume.
 */
// You may not make Ball implement the Comparable interface.
public class Ball {

    private double volume;   
    private Color color;

    /**
     * Constructor that creates a new ball object with the specified volume and color.
     * @param volume the volume of the new ball object
     * @param color the color of the new ball object
     */
    public Ball(double volume1, Color color1) {
        volume = volume1;
        color = color1;
    }
    
    /**
     * Constructor that creates a new ball object with the specified volume given by a string.
     * @param volume A string representing the volume of the new object.
     */
    public Ball(String volume1, Color color1) {
    	try {
    		volume = Double.parseDouble(volume1);
    	} catch (Exception e){
    		volume = 0;
    	}
    	
		color = color1;
    }    

    /**
     * Returns the volume of the ball.
     * @return the volume of the ball.
    */
    public double getVolume() {
    	if (volume > 0) {
    		return volume;
    	}
        return 20;
    }
    
    /**
     * Returns the color of the ball.
     * @return the color of the ball.
    */
    public Color getColor() {
    	if (color != null) {
    		return color;
    	}
        return Color.BLUE;
    }

}


