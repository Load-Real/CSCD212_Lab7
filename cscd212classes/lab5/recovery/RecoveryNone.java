package cscd212classes.lab5.recovery;

import cscd212interfaces.lab5.recovery.*;

/**
 * The implementation of RecoveryBehavior that represents no recovery.
 */
public class RecoveryNone implements RecoveryBehavior
{

  /**
   * No recovery behavior means this implementation of calculateRecovery will always return 0.
   * 
   * @param currentLifePoints - the int used in the calculation of recovered life points
   * @return int - the total number of life points recovered
   */
  public int calculateRecovery(final int currentLifePoints)
  {
    return 0;
  }

}