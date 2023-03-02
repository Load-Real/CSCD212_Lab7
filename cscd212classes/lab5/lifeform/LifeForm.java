package cscd212classes.lab5.lifeform;

/**
 * The abstract LifeForm class represents a basic LifeForm.
 * @NOTE If a precondition is not met then the message will be "Bad Params in MethodName" <br>
 * @NOTE MethodName will be replaced with the actual method name, if constructor, replace with
 *       "ClassName Constructor"
 */
public abstract class LifeForm
{

   private String name;

   protected int currentLifePoints;

   protected final int MAX_LIFE_POINTS;

   /**
    * The constructor to create a LifeForm object which sets the name, currentLifePoints, and
    * MAX_LIFE_POINTS.
    * @param  name                     - this LifeForm's name
    * @param  currentLifePoints        - this LifeForm's current life points
    * @param  maxLifePoints            - this LifeForm's maximum life points
    * @throws IllegalArgumentException - If name is null or empty or if currentLifePoints is <= 0 or
    *                                  if maxLifePoints <= 0 or if currentLifePoints > maxLifePoints
    */
   public LifeForm(final String name, final int currentLifePoints, final int maxLifePoints)
   {
      if (name == null || name.isEmpty() || currentLifePoints <= 0 || maxLifePoints <= 0
            || currentLifePoints > maxLifePoints)
         throw new IllegalArgumentException("Bad Params in LifeForm Constructor");

      this.name = name;
      this.currentLifePoints = currentLifePoints;
      this.MAX_LIFE_POINTS = maxLifePoints;

   }

   /**
    * The constructor to create a LifeForm object which sets the name and currentLifePoints and sets
    * MAX_LIFE_POINTS to the default of 100.
    * @param  name                     - this LifeForm's name
    * @param  currentLifePoints        - this LifeForm's current life points
    * @throws IllegalArgumentException - If name is null or empty or if currentLifePoints is <= 0
    */
   public LifeForm(final String name, final int currentLifePoints)
   {
      this(name, currentLifePoints, 100);

   }

   /**
    * Returns this LifeForm's current life points.
    * @return int - this LifeForm's current life points
    */
   public int getCurrentLifePoints()
   {
      return this.currentLifePoints;
   }

   /**
    * Returns this LifeForm's name.
    * @return String - this LifeForm's name
    */
   public String getName()
   {
      return this.name;
   }

   /**
    * The takeHit method verifies the incoming damage is greater than 0 before reducing the current
    * life points by that amount. If the damage is greater than or equal to the life form's current
    * life points, then the life points will be reduced to 0.
    * @param  damage                   - int representing the damage being applied to this LifeForm
    * @throws IllegalArgumentException - if the incoming damage is <= 0
    * @NOTE                            it is not possible to have negative life points
    */
   public void takeHit(final int damage)
   {
      if (damage <= 0)
         throw new IllegalArgumentException("Bad Params in takeHit");

      if (damage >= this.currentLifePoints)
         this.currentLifePoints = 0;
      else
         this.currentLifePoints = this.currentLifePoints - damage;
   }

   /**
    * Returns a representation of the name and current life points as a String.
    * @return String in the format {name} + " has " + {currentLifePoints} + " life points"
    */
   @Override
   public String toString()
   {
      return this.name + " has " + this.currentLifePoints + " life points";
   }

}