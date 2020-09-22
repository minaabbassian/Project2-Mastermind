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
        
    }

    public static void start(Boolean isTesting, GameConfiguration config, SecretCodeGenerator generator) {
        // TODO: complete this method
		// We will call this method from our JUnit test cases.
    	
    	//Print beginning messages 
    	System.out.println("Welcome to Mastermind.");
    	System.out.println("Do you want to play a new game? (Y/N): ");
    	
    	//scan the player's answer, either Y or N
    	Scanner scannerRef = new Scanner(System.in);
    	String playerStart = scannerRef.nextLine();
    	
    	//continues while the player wants to play the game 
    	while(playerStart.equals("Y") && !playerStart.equals("N")) {
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
