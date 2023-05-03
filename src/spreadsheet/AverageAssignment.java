package spreadsheet;

/**
 * CLass for a macro that assigns the average of specified cells to a destination cell in a
 * spreadsheet.
 */
public class AverageAssignment implements IMacro {

  private final int sX;
  private final int sY;
  private final int eX;
  private final int eY;
  private final int destinationX;
  private final int destinationY;

  /**
   * Constructs an AverageAssignment object with the given source and destination cell indices.
   *
   * @param sX           the starting row index of the source cells.
   * @param sY           the starting column index of the source cells.
   * @param eX           the ending row index of the source cells.
   * @param eY           the ending column index of the source cells.
   * @param destinationX the row index of the destination cell.
   * @param destinationY the column index of the destination cell.
   * @throws IllegalArgumentException if any of the input indices are negative, or if eX or eY is
   *                                  less than sX or sY, respectively.
   */

  public AverageAssignment(int sX, int sY, int eX, int eY, int destinationX, int destinationY) {
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
    if (destinationX < 0 || destinationY < 0) {
      throw new IllegalArgumentException("Destination row or column can't be negative");
    }
    this.sX = sX;
    this.sY = sY;
    this.eX = eX;
    this.eY = eY;
    this.destinationX = destinationX;
    this.destinationY = destinationY;
  }

  @Override
  public void execute(SpreadSheet spreadSheet) {
    double average = 0.0;
    int ct = 0;
    for (int r = sX; r < eX; r++) {
      for (int c = sY; c < eY; c++) {
        average += spreadSheet.get(r, c);
        ct++;
      }
    }
    spreadSheet.set(destinationX, destinationY, average / ct);
  }

  @Override
  public String toString() {
    return "AverageAssignment{" +
        "sX=" + sX +
        ", sY=" + sY +
        ", eX=" + eX +
        ", eY=" + eY +
        ", destinationX=" + destinationX +
        ", destinationY=" + destinationY +
        '}';
  }
}
