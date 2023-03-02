package cscd212classes.lab5.recovery;

import cscd212interfaces.lab5.recovery.*;

/**
 * The implementation of RecoveryBehavior that represents recovery by a set and independent amount.
 */
public class RecoveryLinear implements RecoveryBehavior
{
  /**
   * The int representing life points to be added when recovering.
   */
  private int recoveryStep;

  /**
   * The constructor to create a RecoveryLinear object which sets this recoveryStep to the passed
   * parameter.
   *
   * @param recoveryStep - the int which represents the set number of life points recovered per
   *        calculation
   * @throws IllegalArgumentException If recoveryStep is <= 0
   */
  public RecoveryLinear(final int recoveryStep)
  {
    if (recoveryStep <= 0)
      throw new IllegalArgumentException("Bad Params in RecoveryLinear Constructor");
    this.recoveryStep = recoveryStep;
  }

  /**
   * Recovery amount is always the recoveryStep, therefore calculateRecovery simply returns this
   * recoveryStep without any calculation.
   * 
   * @param currentLifePoints - the int used in the calculation of recovered life points
   * @return int - the total number of life points recovered
   */
  public int calculateRecovery(final int currentLifePoints)
  {
    return this.recoveryStep;
  }

}