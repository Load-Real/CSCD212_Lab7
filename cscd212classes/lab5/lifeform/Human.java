package cscd212classes.lab5.lifeform;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Represents a basic Human which is a child of LifeForm.
 * @NOTE If a precondition is not met then the message will be "Bad Params in MethodName" <br>
 * @NOTE MethodName will be replaced with the actual method name, if constructor, replace with
 *       "ClassName Constructor"
 */

/* Notes:
 * Add PropertyChange stuff so that Human is watchable, focus on doing this for Human before you move on
 */
public class Human extends LifeForm //implements PropertyChangeListener
{
	private PropertyChangeSupport pcsHuman;
   /**
    * The int representing this Human's current armor points.
    */
   private int armorPoints;
   
   /**
    * The int representing the row of the environment that the player is currently in
    */
   private int row;
   
   /**
    * The int representing the column of the environment that the player is currently in
    */
   private int col;
   
   /**
    * The constructor to create an Alien object which delegates the assignment of name,
    * currentLifePoints, and maxLifePoints to the parent constructor and then sets armorPoints to
    * the passed parameter.
    * @param  name                     - this Human's name
    * @param  currentLifePoints        - this Human's current life points
    * @param  maxLifePoints            - this Human's maximum life points
    * @param  armorPoints              - this Human's current armor points
    * @throws IllegalArgumentException - if armorPoints is < 0
    */
   public Human(final String name, final int currentLifePoints, final int maxLifePoints,
         final int armorPoints)
   {
      super(name, currentLifePoints, maxLifePoints);

      if (armorPoints < 0)
         throw new IllegalArgumentException("Bad Params in Human Constructor");

      this.armorPoints = armorPoints;
      this.pcsHuman = new PropertyChangeSupport(this);
      if (this.pcsHuman == null) throw new IllegalArgumentException("Bad Params in Human Constructor");
   }

   /**
    * The constructor to create an Alien object which delegates the assignment of name and
    * currentLifePoints to the parent constructor and then sets armorPoints to the default of 100.
    * @param name              - this Human's name
    * @param currentLifePoints - this Human's current life points
    */
   public Human(final String name, final int currentLifePoints)
   {
      super(name, currentLifePoints);
      this.armorPoints = 100;
      this.row = 0;
      this.col = 0;
      this.pcsHuman = new PropertyChangeSupport(this);
      if (this.pcsHuman == null) throw new IllegalArgumentException("Bad Params in Human Constructor");
   }

   /**
    * Returns this Human's current armor points.
    * @return int - this Human's current armor points
    */
   public int getArmorPoints()
   {
      return this.armorPoints;
   }

   /**
    * Sets the Human's current armor points.
    * @param  armorPoints              Representing this Human's new armor point value
    * @throws IllegalArgumentException If armorPoints is < 0
    */
   public void setArmorPoints(final int armorPoints)
   {
      if (armorPoints < 0)
         throw new IllegalArgumentException("Bad Params in setArmorPoints");
      this.armorPoints = armorPoints;
   }

   /**
    * Calls the parent's toString and appends " and " + {armorPoints} + " armor points".
    * @return String in the format {super toString} + " and " + {armorPoints} + " armor points"
    */
   @Override
   public String toString()
   {
      return super.toString() + " and " + this.armorPoints + " armor points";
   }

   /**
    * When Humans with armorPoints take damage, the damage reduces the Human's armorPoints instead
    * of reducing life points. The damage will reduce the armor points until the armor points value
    * is 0. If a Human's armor points are reduced to 0, the application of the remaining damage is
    * delegated to the parent's takeHit method.
    * @param  damage                   - the int representing damage being applied to this Human
    * @throws IllegalArgumentException - if the incoming damage is <= 0
    * @NOTE                            it is not possible to have negative armor points
    */
   @Override
   public void takeHit(final int damage)
   {
      if (damage <= 0)
         throw new IllegalArgumentException("Bad Params in takeHit");

      if (damage <= this.armorPoints)
         this.armorPoints = this.armorPoints - damage;
      else
      {
         super.takeHit(damage - this.armorPoints);
         this.armorPoints = 0;
      }
   }

   /**
    * Gets the row of the environment that the player is currently in
    * @return int representing current row
    */
   public int getRow()
   {
      return this.row;
   }

   /**
    * Updates the player's current row to the given value.
    * @return int representing new row
    */
   public void setRow(final int row)
   {
      if(row < 0) 
         throw new IllegalArgumentException("Bad params setRow");
      int oldRow = this.row;
      this.row = row;
      this.pcsHuman.firePropertyChange("row", oldRow, row);
   }

   /**
    * Gets the column of the environment that the player is currently in
    * @return int representing current column
    */
   public int getCol()
   {
      return this.col;
   }

   /**
    * Updates the player's current column to the given value.
    * @return int representing new column
    */
   public void setCol(final int col)
   {
      if(col < 0) 
         throw new IllegalArgumentException("Bad params setCol");

      int oldCol = this.col;
      this.col = col;
      this.pcsHuman.firePropertyChange("col", oldCol, col);
   }
   
   //Subscription
   public void addPropertyChangeListener(PropertyChangeListener listener) {
	   pcsHuman.addPropertyChangeListener(listener);
   }
   
   //Unsubscription
   public void removePropertyChangeListener(PropertyChangeListener listener) {
	   pcsHuman.removePropertyChangeListener(listener);
   }
   /*
   //Fire a change for col
   public void changeCol(final int col) {
	   int oldCol = this.col;
	   this.col = col;
	   this.pcsHuman.firePropertyChange("col", oldCol, col);
   }
   
   //Fire a change for row
   public void changeRow(final int row) {
	   int oldRow = this.col;
	   this.row = row;
	   this.pcsHuman.firePropertyChange("row", oldRow, row);
   }
   */
}// end class