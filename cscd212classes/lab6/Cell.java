package cscd212classes.lab6;

import cscd212classes.lab5.lifeform.LifeForm;

/**
 * A Cell exists in an Environment and can hold a LifeForm.
 */
public class Cell
{
   /**
    * The LifeForm in this cell
    */
   private LifeForm entity;

   /**
    * Returns the LifeForm in this cell
    * @return - this Cell
    */
   public LifeForm getLifeForm()
   {
      return this.entity;
   }

   /**
    * Adds the LifeForm to this cell if it is empty. Returns true if the LifeForm was added and
    * false otherwise.
    * @param  entity the LifeForm to be added to this cell
    * @return        boolean representing if the LifeForm was successfully added
    */
   public boolean addLifeForm(final LifeForm entity)
   {
      if (entity == null)
         throw new IllegalArgumentException("Bad Params addLifeForm");

      if (this.entity != null)
         return false;

      this.entity = entity;
      return true;
   }

   /**
    * Removes and returns the LifeForm in this Cell if it exists. Returns null otherwise.
    * @return the LifeForm in this Cell if it exists and null otherwise
    */
   public LifeForm removeLifeForm()
   {
      LifeForm res = this.entity;
      this.entity = null;
      return res;
   }
}
