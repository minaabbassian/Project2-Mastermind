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

import java.util.Scanner;

public class Game {
	
	String secretCode;
	int guessesRemaining;
	Scanner refScanner;
	String lastGuess;
	GameConfiguration gameRules;
	Boolean isTesting;
	History guessHistory;
	Validity guessValidity;
	
	/**
	 * Game
	 * Constructor for the Game class
	 */
	public Game(Boolean testing, GameConfiguration rules, SecretCodeGenerator codeGenerator, Scanner scan) {
		secretCode = codeGenerator.getNewSecretCode();
		guessesRemaining = rules.guessNumber;
		guessValidity = new Validity(rules);
		guessHistory = new History();
		gameRules = rules;
		refScanner = scan;
		isTesting = testing;
	}
	
	
	public void runGame() {
		
	}
	

}
