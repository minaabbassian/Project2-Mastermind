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
	
	String secretCode;
	int guessesLeft;
	Scanner refScanner;
	String recentTurn;
	GameConfiguration gameRules;
	Boolean isTest;
	History guessHis;
	PlayerGuesses guessValidity;
	
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
		
		//If and only if you are in testing mode, print the secret code
		if(isTest) {
			System.out.println("Secret code: " + secretCode);
			System.out.println(); //maybe change?????
		}
		
		String turn;
		Response reply;
		
		//While loop until the player has no guesses left
		while(guessesLeft != 0) {
			System.out.println("You have " + Integer.toString(guessesLeft) + " guess(es) left.");
			System.out.println("Enter guess: ");
			turn = refScanner.nextLine(); //get the player's inputted guess using the reference to the scanner
			
			//check whether the player wants their guess history printed
			if(turn.equals("HISTORY")) {
				guessHis.printGuessHistory();
				//System.out.println(); //added this to printGuessHistory() function ????
				continue;
			}
			
			//check whether the player's guess is invalid
			if(!guessValidity.guessValidity(turn)) {
				System.out.println("INVALID_GUESS");
				System.out.println();
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
			guessesLeft--;
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
		//number of black and white pegs in the feedback 
		int bPegs = 0;
		int wPegs = 0;
		boolean alreadyUtilized;
		ArrayList<Integer> bIndices = new ArrayList<Integer>();
		ArrayList<Integer> wIndices = new ArrayList<Integer>();

		
		
		//find the number of black pegs in the feedback response
		for(int i = 0; i < turn.length(); i++) {
			//player got the correct color in the correct place 
			//record the index position of the correct black peg
			if(turn.charAt(i) == secretCode.charAt(i)) {
				bPegs++;
				bIndices.add(i);
			}
		}
		
		//find the number of white pegs in the feedback response
		for(int i = 0; i < turn.length(); i++) {
			//flag to make sure that color has already been utilized before for another peg
			alreadyUtilized = false; 
			//compare the player's guess color to all the other colors in the secret code 
			for(int j = 0; j < turn.length(); j++) {
				if(turn.charAt(i) == secretCode.charAt(j)) {
					if(bIndices.contains(j) == false && alreadyUtilized == false && wIndices.contains(j) == false) {
						wPegs++;
						wIndices.add(j);
						alreadyUtilized = true; 
					}
				}
			}
		}
		
		return new Response(bPegs, wPegs);
	}
	

}
