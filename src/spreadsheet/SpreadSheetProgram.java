package spreadsheet;

/**
 * The driver of this application.
 */
public class SpreadSheetProgram implements SpreadSheetNew {

  private SpreadSheet spreadSheet;

  public SpreadSheetProgram() {
    spreadSheet = new SparseSpreadSheet();
  }

  @Override
  public double get(int row, int col) throws IllegalArgumentException {
    return spreadSheet.get(row, col);
  }

  @Override
  public void set(int row, int col, double value) throws IllegalArgumentException {
    spreadSheet.set(row, col, value);
  }

  @Override
  public boolean isEmpty(int row, int col) throws IllegalArgumentException {
    return spreadSheet.isEmpty(row, col);
  }

  @Override
  public int getWidth() {
    return spreadSheet.getWidth();
  }

  @Override
  public int getHeight() {
    return spreadSheet.getHeight();
  }

  @Override
  public void addMacroToSpreadSheet(IMacro macro) {
    macro.execute(spreadSheet);
  }

  @Override
  public String toString() {
    return "SpreadSheetProgram{" +
        "spreadSheet=" + spreadSheet +
        '}';
  }

}
