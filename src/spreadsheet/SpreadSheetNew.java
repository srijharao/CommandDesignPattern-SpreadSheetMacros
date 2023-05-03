package spreadsheet;

/**
 * An interface that extends the SpreadSheet interface and adds a method for adding a macro to the
 * spreadsheet.
 */

public interface SpreadSheetNew extends SpreadSheet {

  /**
   * Adds the specified macro to the spreadsheet.
   *
   * @param macro the macro to add to the spreadsheet.
   */

  void addMacroToSpreadSheet(IMacro macro);

}
