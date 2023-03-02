package cscd212classes.lab6;

import cscd212classes.lab5.lifeform.Human;
import cscd212classes.lab5.lifeform.LifeForm;

/**
 * An Environment is composed of a 2D array of Cells which represents a GameBoard.
 * @author jdone
 */
public class Environment
{
   /**
    * The 2D array of Cells that holds the LifeForms in this Environment
    */
   private Cell[][] theCells;

   /**
    * Constructs an Environment. This constructor initializes the 2d array of Cells and then
    * initializes a new Cell at each index in the array.
    * @param  row                      the height of this 2D array
    * @param  col                      the length of the 2d array
    * @throws IllegalArgumentException - if the row or col is <= 0
    */
   public Environment(final int row, final int col)
   {
      if (row <= 0 || col <= 0)
         throw new IllegalArgumentException("Bad Params Environment Constructor");
      this.theCells = new Cell[row][col];

      for (int i = 0; i < row; i++)
      {
         for (int j = 0; j < col; j++)
         {
            this.theCells[i][j] = new Cell();
         }
      }

   }

   /**
    * Returns a reference to the LifeForm at the given coordinates if it exists and null otherwise.
    * @param  row                      the row to get the LifeForm from
    * @param  col                      the column to get the LifeForm from
    * @return                          the LifeForm at [row][col] if it exists or null otherwise
    * @throws IllegalArgumentException if row < 0 or greater than the length of the outer array
    * @throws IllegalArgumentException if col < 0 or greater than the length of the inner array
    */
   public LifeForm getLifeForm(final int row, final int col)
   {
      if (row < 0 || col < 0 || row >= this.theCells.length || col >= this.theCells[0].length)
         throw new IllegalArgumentException("Bad Params getLifeForm");
      return this.theCells[row][col].getLifeForm();
   }

   /**
    * Adds the LifeForm to the given coordinates if it is empty. Returns true if the LifeForm was
    * added and false if it was not.
    * @param  row                      the row to add the LifeForm from
    * @param  col                      the column to add the LifeForm from
    * @return                          boolean representing whether the LifeForm was successfully added
    * @throws IllegalArgumentException if row < 0 or greater than the length of the outer array
    * @throws IllegalArgumentException if col < 0 or greater than the length of the inner array
    */
   public boolean addLifeForm(final int row, final int col, final LifeForm entity)
   {
      if (row < 0 || col < 0 || row >= this.theCells.length || col >= this.theCells[0].length)
         throw new IllegalArgumentException("Bad Params addLifeForm");

      if (entity.getClass().getSimpleName().equals("Human"))
      {
         Human human = (Human) entity;
         human.setRow(row);
         human.setCol(col);
      }
      return this.theCells[row][col].addLifeForm(entity);
   }

   /**
    * Returns and removes the LifeForm at the given coordinates if it exists. Returns null if it does not exist.
    * @param  row                      the row to remove the LifeForm from
    * @param  col                      the column to remove the LifeForm from
    * @return                          the LifeForm at [row][col] if it exists or null otherwise
    * @throws IllegalArgumentException if row < 0 or greater than the length of the outer array
    * @throws IllegalArgumentException if col < 0 or greater than the length of the inner array
    */
   public LifeForm removeLifeForm(final int row, final int col)
   {
      if (row < 0 || col < 0 || row >= this.theCells.length || col >= this.theCells[0].length)
         throw new IllegalArgumentException("Bad Params removeLifeForm");

      LifeForm result = this.theCells[row][col].getLifeForm();
      this.theCells[row][col].removeLifeForm();

      return result;
   }

}
