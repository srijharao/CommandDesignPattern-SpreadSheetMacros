package spreadsheet;

/**
 * A class that represents a macro that assigns a single value to a rectangular block of cells in a
 * spreadsheet.
 */
public class BulkAssign implements IMacro {

  private final int row;
  private final int col;
  private final double val;

  /**
   * Constructs a new BulkAssign object with the specified row, column, and value.
   *
   * @param row the number of rows in the block (must be non-negative).
   * @param col the number of columns in the block (must be non-negative).
   * @param val the value to assign to the block.
   * @throws IllegalArgumentException if row or col is negative.
   */

  public BulkAssign(int row, int col, double val) {
    if (row < 0 || col < 0) {
      throw new IllegalArgumentException("Rows or Columns can't be negative");
    }
    this.row = row;
    this.col = col;
    this.val = val;
  }

  /**
   * Executes the macro on the given spreadsheet, setting the block of cells to the assigned value.
   *
   * @param spreadSheet the spreadsheet on which to execute the macro.
   */
  @Override
  public void execute(SpreadSheet spreadSheet) {
    for (int r = 0; r < row; r++) {
      for (int c = 0; c < col; c++) {
        spreadSheet.set(r, c, val);
      }
    }
  }

  /**
   * Returns a string representation of the BulkAssign object, including its row, column, and
   * value.
   *
   * @return a string representation of the BulkAssign object.
   */

  @Override
  public String toString() {
    return "BulkAssign{" +
        "row=" + row +
        ", col=" + col +
        ", val=" + val +
        '}';
  }
}
