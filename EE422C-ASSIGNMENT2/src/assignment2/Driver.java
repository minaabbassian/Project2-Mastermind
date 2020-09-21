//Driver.java
/*
 * EE422C Project 2 (Mastermind) submission by
 * Mina Abbassian
 * mea2947
 * 16170
 * Fall 2020
 * Slip days used:
 */

package assignment2;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        // Use this for your testing.  We will not be calling this method.
    	 GameConfiguration config = new GameConfiguration();
         
         start(false, config, new SecretCodeGenerator() );
    }

    public static void start(Boolean isTesting, GameConfiguration config, SecretCodeGenerator generator) {
        // TODO: complete this method
		// We will call this method from our JUnit test cases.
    	
    	System.out.println("Welcome to Mastermind.");
    	System.out.println("Do you want to play a new game? (Y/N): ");
    	
    	Scanner scannerRef = new Scanner(System.in);
    	String playerStart = scannerRef.nextLine();
    	
    	//continues while the player wants to play the game 
    	while(!playerStart.equals("N") && playerStart.equals("Y")) {
    		Game gameRun = new Game(isTesting, config, generator, scannerRef);
    		gameRun.runGame(); //player plays the game here 
    		//does the player want to play again?
    		System.out.println("Do you want to play a new game? (Y/N): ");
    		playerStart = scannerRef.nextLine();
    	}
    	
    	//if the player does not want to play a new game
    	if(playerStart.equals("N")) 
    		return;
    }
}
