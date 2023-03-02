package cscd212classes.lab5.lifeform;

import cscd212interfaces.lab5.recovery.*;

/**
 * Represents a Martian which is a child of Alien.
 */
public class Martian extends Alien
{
  
  /**
   * The constructor to create a Martian object which delegates the assignment of parameters and
   * setting of default maxLifePoints and RecoveryBehavior to the parent constructor.
   * 
   * @param name - this Martian's name
   * @param currentLifePoints - this Martian's current life points
   */
  public Martian(final String name, final int currentLifePoints)
  {
    super(name, currentLifePoints);
  }
  
  /**
   * The constructor to create a Martian object which delegates the assignment of parameters to the
   * parent constructor.
   * 
   * @param name - this Martian's name
   * @param currentLifePoints - this Martian's current life points
   * @param maxLifePoints - this Martian's maximum life points
   * @param recovery - this Martian's current RecoveryBehavior
   */
  public Martian(final String name, final int currentLifePoints, final int maxLifePoints,
      final RecoveryBehavior recovery)
  {
    super(name, currentLifePoints, maxLifePoints, recovery);
  }

  

}