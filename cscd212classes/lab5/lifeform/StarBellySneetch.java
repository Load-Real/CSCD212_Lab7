package cscd212classes.lab5.lifeform;

import java.beans.PropertyChangeEvent;

import cscd212interfaces.lab5.recovery.RecoveryBehavior;

/**
 * Represents a StarBellySneetch which is a child of Alien.
 */
public class StarBellySneetch extends Alien
{
  /**
   * The constructor to create a StarBellySneetch object which delegates the assignment of parameters to the
   * parent constructor.
   * 
   * @param name - this StarBellySneetch's name
   * @param currentLifePoints - this StarBellySneetch's current life points
   * @param maxLifePoints - this StarBellySneetch's maximum life points
   * @param recovery - this StarBellySneetch's current RecoveryBehavior
   */
  public StarBellySneetch(final String name, final int currentLifePoints, final int maxLifePoints,
      final RecoveryBehavior recovery)
  {
    super(name, currentLifePoints, maxLifePoints, recovery);
  }

  /**
   * The constructor to create a StarBellySneetch object which delegates the assignment of parameters and
   * setting of default maxLifePoints and RecoveryBehavior to the parent constructor.
   * 
   * @param name - this Martian's name
   * @param currentLifePoints - this Martian's current life points
   */
  public StarBellySneetch(final String name, final int currentLifePoints)
  {
    super(name, currentLifePoints);
  }
}
