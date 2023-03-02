package cscd212interfaces.lab5.recovery;

/**
 * The RecoveryBehavior interface.
 * 
 * @NOTE Modifier is public
 */
public interface RecoveryBehavior
{
   /**
    * The abstract method calculateRecovery.
    * 
    * @param currentLifePoints - the int used in the calculation of recovered life points
    * @return int Representing the total life points recovered
    * @NOTE: Modifier is public abstract
    */
   public abstract int calculateRecovery(final int currentLifePoints);
}