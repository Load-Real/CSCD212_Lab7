package cscd212classes.lab5.lifeform;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import cscd212classes.lab5.recovery.RecoveryNone;
import cscd212interfaces.lab5.recovery.*;

/**
 * The abstract Alien class represents a basic Alien which is a child of LifeForm.
 * @NOTE If a precondition is not met then the message will be "Bad Params in MethodName"
 * @NOTE MethodName will be replaced with the actual method name, if constructor, replace with
 *       "ClassName Constructor"
 */
public abstract class Alien extends LifeForm implements PropertyChangeListener
{
   /**
    * The RecoveryBehavior object representing this Alien's current recovery behavior.
    */
   private RecoveryBehavior recovery;
   
   private PropertyChangeSupport pcsAlien;

   /**
    * The constructor to create an Alien object which delegates the assignment of name,
    * currentLifePoints, and maxLifePoints to the parent constructor and then sets this recovery to
    * the passed parameter.
    * @param  name                     - this Alien's name
    * @param  currentLifePoints        - this Alien's current life points
    * @param  maxLifePoints            - this Alien's maximum life points
    * @param  recovery                 - this Alien's current RecoveryBehavior
    * @throws IllegalArgumentException If recovery is null
    */
   public Alien(final String name, final int currentLifePoints, final int maxLifePoints,
         final RecoveryBehavior recovery)
   {
      super(name, currentLifePoints, maxLifePoints);

      if (recovery == null)
         throw new IllegalArgumentException("Bad Params in Alien Constructor");

      this.recovery = recovery;
      this.pcsAlien = new PropertyChangeSupport(this);
      if (this.pcsAlien == null) throw new IllegalArgumentException("Bad Params in Alien Constructor");
   }

   /**
    * The constructor to create an Alien object which delegates the assignment of name and
    * currentLifePoints to the parent constructor and then sets recovery to the default of
    * RecoveryNone.
    * @param name              - this Alien's name
    * @param currentLifePoints - this Alien's current life points
    */
   public Alien(final String name, final int currentLifePoints)
   {
      super(name, currentLifePoints);

      this.recovery = new RecoveryNone();
      
      //Add PropertyChangeSupport
      this.pcsAlien = new PropertyChangeSupport(this);
      //Check if parameter is null
      if (this.pcsAlien == null) throw new IllegalArgumentException("Bad Params in Alien Constructor");
   }

   /**
    * Sets the Alien's current life points.
    * @param  currentLifePoints        - this Alien's new life point value
    * @throws IllegalArgumentException - If currentLifePoints is <= 0 or greater than
    *                                  MAX_LIFE_POINTS
    */
   public void setCurrentLifePoints(final int currentLifePoints)
   {
      if (currentLifePoints <= 0 || currentLifePoints > super.MAX_LIFE_POINTS)
         throw new IllegalArgumentException("Bad Params in setCurrentLifePoints");
      
      super.currentLifePoints = currentLifePoints;
   }

   /**
    * Sets the Alien's current recovery.
    * @param  recovery                 - this Alien's new RecoveryBehavior
    * @throws IllegalArgumentException - If recovery is null
    */
   public void setRecoveryBehavior(final RecoveryBehavior recovery)
   {
      if (recovery == null)
         throw new IllegalArgumentException("Bad Params in setRecoveryBehavior");
      
      this.recovery = recovery;
   }

   /**
    * Returns the Alien's current recoveryBehavior.
    * @return RecoveryBehavior this Alien's current aggregated RecoveryBehavior object
    */
   public RecoveryBehavior getRecoveryBehavior()
   {
      return this.recovery;
   }

   /**
    * The recover method delegates the calculation of the recovery points to the aggregated recovery
    * object and returns a String representing the points recovered. Life points cannot recover a
    * LifeForm past their MAX_LIFE_POINTS. When this Alien recovers, a PropertyChangeEvent with the
    * property string "life points added" is fired to all its listeners.
    * @return String in the format {name} + " has had " + {points recovered} + " recovery points
    *         added their current life points"
    */
   public String recover()
   {
      int oldLifePoints = super.currentLifePoints;

      super.currentLifePoints += this.recovery.calculateRecovery(oldLifePoints);
      super.currentLifePoints = Math.min(MAX_LIFE_POINTS, this.currentLifePoints);
      
      this.pcsAlien.firePropertyChange("life points added", oldLifePoints, super.currentLifePoints);

      return super.getName() + " has had " + (super.currentLifePoints - oldLifePoints)
            + " recovery points added their current life points";
   }

   /**
    * Calls the parent's toString and appends " and has recovery mode of " + the Class
    * simple name of the aggregated recovery object.
    * @return String in the format {super toString} + " and has recovery mode of " + {the Class
    *         simple name of the aggregated recovery object.}
    */
   @Override
   public String toString()
   {
      return super.toString() + " and has recovery mode of "
            + this.recovery.getClass().getSimpleName();
   }
   
   //Subscription
   public void addPropertyChangeListener(PropertyChangeListener listener) {
	   pcsAlien.addPropertyChangeListener(listener);
   }
   
   //Unsubscription
   public void removePropertyChangeListener(PropertyChangeListener listener) {
	   pcsAlien.removePropertyChangeListener(listener);
   }
   
   public void propertyChange(PropertyChangeEvent evt) {
	   if (evt.getPropertyName().equalsIgnoreCase("row") || evt.getPropertyName().equalsIgnoreCase("col")) {
		   this.recover();
	   }
   }
}