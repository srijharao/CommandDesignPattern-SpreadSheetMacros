package spreadsheet;

/**
 * A class that represents a macro that assigns a range of values to a rectangular block of cells in
 * a spreadsheet, starting from a specified value and incrementing by a specified amount for each
 * subsequent cell.
 */
public class RangeAssignment implements IMacro {

  private final int sX;
  private final int sY;
  private final int eX;
  private final int eY;
  private final int increment;
  private int value;

  /**
   * Constructs a new RangeAssignment object with the specified start and end coordinates, value,
   * and increment.
   *
   * @param sX        the starting x-coordinate of the block (must be non-negative).
   * @param sY        the starting y-coordinate of the block (must be non-negative).
   * @param eX        the ending x-coordinate of the block (must be non-negative and greater than or
   *                  equal to sX).
   * @param eY        the ending y-coordinate of the block (must be non-negative and greater than or
   *                  equal to sY).
   * @param value     the starting value of the range.
   * @param increment the amount by which to increment the value for each subsequent cell.
   * @throws IllegalArgumentException if sX or sY is negative, or if eX or eY is negative or less
   *                                  than sX or sY.
   */
  public RangeAssignment(int sX, int sY, int eX, int eY, int value, int increment) {
    if (sX < 0 || sY < 0) {
      throw new IllegalArgumentException("Source start row or column can't be negative");
    }
    if (eX < 0 || eY < 0) {
      throw new IllegalArgumentException("Source end row or column can't be negative");
    }
    if (eX < sX || eY < sY) {
      throw new IllegalArgumentException(
          "Starting cell can't have its x or y less than that of ending cell");
    }
    this.sX = sX;
    this.sY = sY;
    this.eX = eX;
    this.eY = eY;
    this.value = value;
    this.increment = increment;
  }

  @Override
  public void execute(SpreadSheet spreadSheet) {
    for (int r = sX; r < eX; r++) {
      for (int c = sY; c < eY; c++) {
        spreadSheet.set(r, c, value);
        value += increment;
      }
    }
  }

  @Override
  public String toString() {
    return "RangeAssignment{" +
        "sX=" + sX +
        ", sY=" + sY +
        ", eX=" + eX +
        ", eY=" + eY +
        ", value=" + value +
        ", increment=" + increment +
        '}';
  }
}
