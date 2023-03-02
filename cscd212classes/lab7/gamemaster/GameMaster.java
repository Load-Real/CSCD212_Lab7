package cscd212classes.lab7.gamemaster;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Scanner;

import cscd212classes.lab5.lifeform.*;
import cscd212classes.lab5.recovery.RecoveryFractional;
import cscd212classes.lab5.recovery.RecoveryLinear;
import cscd212classes.lab6.*;

import java.beans.*;

/**
 * The Game Master controls a single game.
 * It oversees the board and ALL of the LifeForms
 * 
 * We will move LifeForms around the board and then report that movement to the GameMaster
 */
public class GameMaster implements PropertyChangeListener
{
	/**
	 * Private Environment variable env
	 */
   private Environment env;
   
   /**
    * Private Human variable player
    */
   private Human player;
   
   /**
    * Private ArrayList of Aliens called aliens will be used for future improvements
    */
   private ArrayList<Alien> aliens; 
   
   /**
    * Private final int ROWS for the number of rows in the environment
    */
   private final int ROWS;
   
   /**
    * Private final int COLS for the number of columns in the environment
    */
   private final int COLS;
   
   /**
    * DVC that calls the other constructor passing 5 rows and 5 cols for a default game board
    */
   public GameMaster()
   {
	   this(5,5);	   
   }

   /**
    * Constructs a GameMaster. This constructor initializes this Environment to be of size 5 by 5,
    * initializes an ArrayList of Aliens, and adds to this list a Martian and a StarBellySneetch.
    * The Martian is added to (0,2) and the StarBellySneetch to (3,4). The constructor initializes
    * the Player to a Human which is added to (4,2). This GameMaster and all Aliens are then added
    * to the propertyChangeListener list of the player. This GameMaster is added to the listener
    * list of all the Aliens.
    * @param rows Representing the number of rows in the environment
    * @param cols Representing the number of cols in the environment
    * @throws IllegalArgumentException if rows or cols is less than 1
    */
   public GameMaster(final int rows, final int cols)
   {	   
	  if(rows < 1 || cols < 1)
		  throw new IllegalArgumentException("Bad Params rows/cols in constructor");
	  
	  this.ROWS = rows;
	  this.COLS = cols;
	  
      this.env = new Environment(this.ROWS, this.COLS);

      this.aliens = new ArrayList<Alien>();

      aliens.add(new Martian("Martin", 60, 300, new RecoveryLinear(10)));
      this.env.addLifeForm(0, 2, aliens.get(0));

      aliens.add(new StarBellySneetch("Starry", 30, 100, new RecoveryFractional(0.04, 0)));
      this.env.addLifeForm(3, 4, aliens.get(1));
      
      this.aliens.trimToSize();
      
      this.player = new Human("Hugh Mann", 80);
      this.env.addLifeForm(4, 2, this.player);
      
      //Adding the listeners to GameMaster
      this.player.addPropertyChangeListener(this);
      for (Alien alien : this.aliens) {
    	  alien.addPropertyChangeListener(this);
    	  this.player.addPropertyChangeListener(alien);
      }
   }

   /**
    * Starts the game and presents the player with a menu of choices. The choices are currently 1)
    * Move and 2) Exit. If the user chooses move, the move method is called. If they choose exit,
    * the Scanner and program are closed. The choices are continually presented until exit is
    * called.
    */
   public void playGame()
   {
      Scanner kb = new Scanner(System.in);
      boolean keepGoing = true;
      do
      {
    	  System.out.println("Player's current location: (" + this.player.getRow() + ", " + this.player.getCol() + ")");
    	  String choices = this.determineValidMoves();
    	  
    	  System.out.print("Move choices: " + choices + " (Q for Quit) --> ");
    	  
          String direction = this.getUserChoice(kb, choices);

          System.out.println();
          if((direction.trim().equalsIgnoreCase("Q")))
        	  keepGoing = false;
          
          else
          {
        	  this.movePlayer(direction);
        	  System.out.println();
          }
          
      }while (keepGoing);
      
      System.out.println("***** Game Over! *****");
   }

   /**
    * Helper method for move that determines the valid moves for a user and returns these moves as a
    * String.
    * @return String representing possible moves in the format "N S W E"
    */
   private String determineValidMoves()
   {
      int pRow = this.player.getRow();
      int pCol = this.player.getCol();
      String choices = "";

      if (pRow > 0 && this.env.getLifeForm(pRow - 1, pCol) == null)
         choices += "N ";
      if (pRow < this.ROWS - 1 && this.env.getLifeForm(pRow + 1, pCol) == null)
         choices += "S ";
      if (pCol > 0 && this.env.getLifeForm(pRow, pCol - 1) == null)
         choices += "W ";
      if (pCol < this.COLS - 1 && this.env.getLifeForm(pRow, pCol + 1) == null)
         choices += "E ";

      return choices.trim();
   }

   /**
    * Helper method for move that takes a String with valid choices and ensures the user enters a
    * value from that String.
    * @param  kb           Scanner to keyboard
    * @param  validChoices String containing valid choices.
    * @return              String representing one valid choice
    * @throws IllegalArgumentException if kb is null or validChoices is null or blank
    */
   private String getUserChoice(final Scanner kb, final String validChoices)
   {
	   if(kb == null || validChoices == null || validChoices.isBlank())
			  throw new IllegalArgumentException("Bad Params getUserChoice");
	   
      String answer = kb.nextLine().trim().toUpperCase();

      while (!validChoices.contains(answer)  && !answer.equals("Q"))
      {
         System.out.print("Move choices: " + validChoices + " (Q for Quit) --> ");
         answer = kb.nextLine().trim().toUpperCase();
      }
      return answer.toUpperCase();
   }

   /**
    * Helper method for move that removes the player from their current Cell and adds them to the
    * adjacent Cell determined by the given direction.
    * @param direction Letter representing cardinal direction to move.
    * @NOTE            Directions are N (row - 1) S (row + 1) W (col - 1) E (col +1)
    */
   private void movePlayer(final String direction)
   {
	   if(direction == null || direction.isBlank())
			  throw new IllegalArgumentException("Bad Params direction");
	   
      int pRow = this.player.getRow();
      int pCol = this.player.getCol();
      this.env.removeLifeForm(pRow, pCol);

      switch (direction.toUpperCase()) {
         case "N":
            this.player.setRow(--pRow);
            break;
         case "S":
            this.player.setRow(++pRow);
            break;
         case "W":
            this.player.setCol(--pCol);
            break;
         case "E":
            this.player.setCol(++pCol);
      }

      this.env.addLifeForm(pRow, pCol, this.player);
   }

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource().getClass().getSimpleName().equalsIgnoreCase("Human")) {	
			humanChanged(evt);
		}
		
		if (evt.getSource().getClass().getSimpleName().equalsIgnoreCase("Martian") || evt.getSource().getClass().getSimpleName().equalsIgnoreCase("StarBellySneetch")) {
			alienChanged(evt);
		}
	}
	
	/*
	 * You can only either move a row or a column at a time, not diagonal
	 * this.player.getCol or this.player.getRow
	 */
	public void humanChanged(PropertyChangeEvent evt) {
		String propertyName = evt.getPropertyName();
		if (propertyName.equalsIgnoreCase("row")) {
			//System.out.println(player.getName() + " has moved from (" + evt.getOldValue() + ", " + evt.getNewValue() + ")");
			System.out.println(player.getName() + " has moved from (" + evt.getOldValue() + "," + this.player.getCol() + ") to (" + evt.getNewValue() + "," + this.player.getCol() + ")");
		}
		
		if (propertyName.equalsIgnoreCase("col")) {
			System.out.println(player.getName() + " has moved from (" + evt.getOldValue() + "," + this.player.getRow() + ") to (" + evt.getNewValue() + "," + this.player.getRow() + ")");
		}
	}
	
	   public void alienChanged(PropertyChangeEvent evt) {
		   String propertyName = evt.getPropertyName();
		   if (propertyName.equalsIgnoreCase("life points added")) {
			   System.out.println(((Alien) evt.getSource()).getName() +/*.getName()*/ " has healed from " + evt.getOldValue() + " life points to " + evt.getNewValue() + " life points");
		   }
	   }

}// end class

/*Notes:
 * Human is watched by the GameMaster and Alien
 * 
 * Alien is watched by the GameMaster and Human
 * 
 * 7-9 Methods will need to be written
 */