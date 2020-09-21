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
		secretCode = genCode.getInstance().getNewSecretCode();
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
		} else {
			System.out.println();
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
		//copy of the secret code and the player's guess
		String[] copyCode = new String[GameConfiguration.pegNumber];
		String[] copyTurn = new String[GameConfiguration.pegNumber];
		
		//number of black and white pegs in the feedback 
		int wPegs = 0;
		int bPegs = 0;

		 //create the copies of the two arrays 
        for(int i = 0; i < GameConfiguration.pegNumber; i++) {
        	copyTurn[i] = turn.substring(i,i+1);
            copyCode[i] = secretCode.substring(i,i+1);
        }

        //check to see how many black pegs occur in the feedback response
        for(int i = 0; i < GameConfiguration.pegNumber; i++){
            if(copyCode[i].equals(copyTurn[i])){
            	bPegs++; //increment the number of black begs
                copyCode[i]=""; //remove this location from the copied arrays
                copyTurn[i]="";
            }
        }

        //check to see how many white pegs occur in the feedback response
        for(int i = 0; i < GameConfiguration.pegNumber; i++) {
            if(copyCode[i].equals(""))
                continue;
            //loop through the indices that have not already been used above 
            for(int j = 0; j < GameConfiguration.pegNumber; j++) {
                if(copyTurn[j].equals("")){
                    continue;
                }
                //if two different indices that have not yet been used are the same
                //then that is a white peg
                else if(copyCode[i].equals(copyTurn[j])){
                	wPegs++;
                    copyCode[j]="";
                    copyTurn[i]="";
                }
            }
        }
        
        return new Response(bPegs, wPegs);
	}
	

}
