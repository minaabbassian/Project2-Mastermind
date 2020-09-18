//Validity.java
/*
 * EE422C Project 2 (Mastermind) submission by
 * Mina Abbassian
 * mea2947
 * 16170
 * Fall 2020
 * Slip days used:
 */

//The Validity Class
//Checks the validity of the player's guess inputted on the keyboard to the console 
package assignment2;

public class Validity {
	
	GameConfiguration gameRules;
	
	/**
	 * Validity
	 * Constructor for the Validity class
	 * @param game GameConfiguration object that holds the rules for the setup of the game 
	 */
	public Validity(GameConfiguration game) {
		gameRules = game; 
	}
	
	
	/**
	 * checkValidity 
	 * Checks if the player's guess is of incorrect length, depending on the pegNmber field 
	 * 		of the GameConfiguration object
	 *  Additionally, checks if the player inputted any invalid characters, using the colors 
	 *  	field of the GameConfiguration object
	 * @param turn String holding the player's guess 
	 * @return true if the player's guess is valid
	 * 		   false if the player's guess is invalid  
	 */
	public Boolean checkValidity(String turn) {
		
		//check if the player inputted any invalid characters
		for(int i = 0; i < turn.length(); i++) {
			Boolean charCheck = false;
			for(String validChars: gameRules.colors)
				//will be false only if the player's guess includes an invalid character
				charCheck = charCheck || turn.substring(i, i+1).equals(validChars);
			if(!charCheck)
				return false;
		}
		
		//check if the player's guess is of incorrect length
		if(turn.length() != gameRules.pegNumber)
			return false;
		
		return true; 
	}

}
