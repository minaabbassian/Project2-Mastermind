//Game.java
/*
 * EE422C Project 2 (Mastermind) submission by
 * Mina Abbassian
 * mea2947
 * 16170
 * Fall 2020
 * Slip days used:
 */

package assignment2;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	
	Boolean isTest;
	PlayerGuesses guessValidity;
	String secretCode;
	int guessesLeft;
	History guessHis;
	Scanner refScanner;
	GameConfiguration gameRules;
	String recentTurn;
	
	
	/**
	 * Game
	 * Constructor for the Game class
	 */
	public Game(Boolean test, GameConfiguration rules, SecretCodeGenerator genCode, Scanner scan) {
		isTest = test;
		gameRules = rules;
		secretCode = genCode.getNewSecretCode();
		guessValidity = new PlayerGuesses(rules);
		guessesLeft = rules.guessNumber;
		refScanner = scan;
		guessHis = new History();	
	}
	
	
	/**
	 * runGame
	 * Carries out the actual Mastermind Game 
	 */
	public void runGame() {
		
		Response reply;
		String turn;
		
		//If and only if you are in testing mode, print the secret code
		if(isTest) {
			System.out.println("Secret code: " + secretCode);
			System.out.println(); //maybe change?????
		} else {
			System.out.println();
		}
		
		//While loop until the player has no guesses left
		while(guessesLeft != 0) {
			System.out.println("You have " + Integer.toString(guessesLeft) + " guess(es) left.");
			System.out.println("Enter guess: ");
			turn = refScanner.nextLine(); //get the player's inputted guess using the reference to the scanner
			
			//check whether the player's guess is invalid
			if(!guessValidity.guessValidity(turn)) {
				System.out.println("INVALID_GUESS"); 
				System.out.println();
				continue;
			}
			
			//check whether the player wants their guess history printed
			if(turn.equals("HISTORY")) {
				guessHis.printGuessHistory();
				continue;
			}
		
			//formulate a reply to the player's given guess
			reply = formulateReply(turn);
			//create feedback response to be outputted to the console
			recentTurn = turn + " -> " + Integer.toString(reply.b) + "b_" + Integer.toString(reply.w) + "w";
			//add the most recent player guess to the history
			guessHis.addGuessHistory(recentTurn);
			//print out the feedback
			System.out.println(recentTurn);
			
			//check whether the player wins
			if(reply.b == gameRules.pegNumber) {
				System.out.println("You win!");
				System.out.println();
				return;
			}
			
			//decrement the guesses remaining 
			guessesLeft = guessesLeft - 1;
			if(guessesLeft == 0) //ADDED THIS STATEMENT SO THAT DOESN'T PRINT AN EXTRA LINE
				break;
			System.out.println();
		}
		
		//if there are no guesses left, the player loses
		System.out.println("You lose! The pattern was " + secretCode);
		System.out.println();
		
	}
	
	
	/**
	 * formulateReply
	 * @param turn String representing the player's inputted guess
	 * @return a Response object representing the feedback response
	 * 		   in the feedback, the number of black pegs (if any) are given first, and then
	 *  	   the number of white ones, if any
	 */
	private Response formulateReply(String turn) {
		
		//holds the number of black and white pegs in feedback response
		int bPegs = 0;
	    int wPegs = 0;
	    
	    //references to if any black or white pegs are used in a location
		boolean[] blackRef = new boolean[gameRules.pegNumber];
		boolean[] whiteRef = new boolean[gameRules.pegNumber];
		   
		//check if there are any black pegs in the feedback response
		for(int i = 0; i < turn.length(); i++) {
			//if a character in the player's guess is in the exact same position as in the secret code
			if(turn.charAt(i) == secretCode.charAt(i)) {
				bPegs++;
				whiteRef[i] = true; //no more white pegs for this location
				blackRef[i] = true; //no more black pegs for this location
		    }
		}

		//check if there are any white pegs in the feedback response
		for(int i = 0; i < turn.length(); i++) {
			boolean alreadyDone = false; 
			//compares a character to every other character in the secret code 
			for(int j = 0; j < turn.length(); j++) {
				if(turn.charAt(i) == secretCode.charAt(j) && !whiteRef[j] && !alreadyDone) {
					wPegs++;
					alreadyDone = true;
					whiteRef[j] = true;
		        }
			}
		}
		return new Response(bPegs, wPegs);
	
	}
	

}
