//History.java
/*
 * EE422C Project 2 (Mastermind) submission by
 * Mina Abbassian
 * mea2947
 * 16170
 * Fall 2020
 * Slip days used:
 */

//The History class
//Uses an ArrayList of Strings to record the player's previous valid guesses
//	and their resulting pegs
package assignment2;

import java.util.ArrayList;

public class History {

	ArrayList<String> guessHistory;
	
	/**
	 * History
	 * Constructor for the History class
	 * Initializes an ArrayList of Strings to hold the player's previous valid guesses
	 */
	public History() {
		guessHistory = new ArrayList<String>();
	}
	
	
	/**
	 * addGuess
	 * adds the player's previous guess to the guessHistory ArrayList
	 * @param turn String representing the player's guess
	 */
	public void addGuess(String turn) {
		guessHistory.add(turn);
	}
	
	
	/**
	 * printHistory
	 * prints out all the player's previous guesses using a simple for-each loop that 
	 * 		fetches every element from the guessHistory ArrayList object one by one 
	 */
	public void printHistory() {
		for(String guess: guessHistory) {
			System.out.println(guess);
		}
	}
	
}
