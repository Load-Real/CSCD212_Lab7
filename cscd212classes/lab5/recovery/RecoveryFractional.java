package cscd212classes.lab5.recovery;

import cscd212interfaces.lab5.recovery.*;

/**
 * The implementation of RecoveryBehavior that represents recovery based on percentage of total life
 * points. If the calculated recovery is less than the BASE_RECOVERY amount, then the BASE_RECOVERY
 * is recovered instead.
 */
public class RecoveryFractional implements RecoveryBehavior
{
  /**
   * The int constant which represents the minimum life points recovered per calculation.
   */
  private final int BASE_RECOVERY;
  /**
   * The double which represents the percentage of total life points to be returned when calculating
   * recovery.
   */
  private double recoveryPercent;

  /**
   * The constructor to create a RecoveryFractional object which sets this recoveryPercent and
   * BASE_RECOVERY to the passed parameters.
   * 
   * @param recoveryPercent - the double which represents the percentage of total life points to be
   *        returned when calculating recovery.
   * @param baseRecovery - the int which represents the minimum life points recovered per
   *        calculation
   * @NOTE recovery percentages are passed in as doubles, such as 0.33 for 33%
   */
  public RecoveryFractional(final double recoveryPercent, final int baseRecovery)
  {
    if (recoveryPercent <= 0)
      throw new IllegalArgumentException("Bad Params in RecoveryFractional Constructor");

    this.recoveryPercent = recoveryPercent;
    this.BASE_RECOVERY = baseRecovery;
  }

  /**
   * The constructor to create a RecoveryFractional object which sets this recoveryPercent to the
   * passed parameter and then sets the BASE_RECOVERY to a default of 10.
   *
   * @param recoveryPercent - the double which represents the percentage of total life points to be
   *        returned when calculating recovery.
   * @NOTE recovery percentages are passed in as doubles, such as 0.33 for 33%
   */
  public RecoveryFractional(final double recoveryPercent)
  {
    if (recoveryPercent <= 0)
      throw new IllegalArgumentException("Bad Params in RecoveryFractional Constructor");

    this.recoveryPercent = recoveryPercent;
    this.BASE_RECOVERY = 10;
  }

  /**
   * Recovery amount is calculated by multiplying currentLifePoints by this Object's recoveryPercent
   * and casting the result to an int. If the result is less than this Object's BASE_RECOVERY,
   * BASE_RECOVERY is returned. Otherwise, the calculated amount is returned.
   * 
   * @param currentLifePoints - the int used in the calculation of recovered life points
   * @return int - the total number of life points recovered
   */
  public int calculateRecovery(final int currentLifePoints)
  {
    if ((int) (this.recoveryPercent * currentLifePoints) < this.BASE_RECOVERY)
      return this.BASE_RECOVERY;

    return (int) (this.recoveryPercent * currentLifePoints);
  }

}