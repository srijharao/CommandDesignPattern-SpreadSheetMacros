package spreadsheet;

import java.util.Scanner;

/**
 * Better controller by implementing the command pattern for SpreadSheetController.
 */
public class SpreadSheetControllerNew extends SpreadSheetController {

  /**
   * Create a controller to work with the specified sheet (model), readable (to take inputs) and
   * appendable (to transmit output).
   *
   * @param sheet      the sheet to work with (the model)
   * @param readable   the Readable object for inputs
   * @param appendable the Appendable objects to transmit any output
   */
  public SpreadSheetControllerNew(SpreadSheet sheet, Readable readable, Appendable appendable) {
    super(sheet, readable, appendable);
  }

  @Override
  protected void processCommand(String userInstruction, Scanner sc, SpreadSheet sheet) {
    if (userInstruction.equalsIgnoreCase("bulk-assign-value")) {
      try {
        int row = getRowNum(sc.next()); //get in the row string
        int col = sc.nextInt(); //get in the column number, starting with 1
        double value = sc.nextDouble();
        writeMessage("Executing Bulk Assign for cells of range (" + row + "," + col);
        IMacro bulkAssign = new BulkAssign(row, col, value);
        bulkAssign.execute(sheet);
      } catch (IllegalArgumentException e) {
        writeMessage("Error: " + e.getMessage() + System.lineSeparator());
      }
    } else if (userInstruction.equalsIgnoreCase("average")) {
      try {
        int sX = getRowNum(sc.next()); //get in the row string
        int sY = sc.nextInt(); //get in the column number, starting with 1
        int eX = getRowNum(sc.next());
        int eY = sc.nextInt();
        int destinationX = getRowNum(sc.next());
        int destinationY = sc.nextInt();
        writeMessage("Computing Average for cells of range (" + sX + "," + sY + ") and ("
            + eX + "," + eY + ") and assigning the value to (" + destinationX
            + "," + destinationY + ")");
        IMacro averageAssignment = new AverageAssignment(sX, sY, eX, eY, destinationX,
            destinationY);
        averageAssignment.execute(sheet);
      } catch (IllegalArgumentException e) {
        writeMessage("Error: " + e.getMessage() + System.lineSeparator());
      }
    } else if (userInstruction.equalsIgnoreCase("range-assign")) {
      try {
        int sX = getRowNum(sc.next()); //get in the row string
        int sY = sc.nextInt(); //get in the column number, starting with 1
        int eX = getRowNum(sc.next());
        int eY = sc.nextInt();
        int value = sc.nextInt();
        int increment = sc.nextInt();
        writeMessage("Assigning values for cells of range (" + sX + "," + sY + ") and ("
            + eX + "," + eY + ") in the increments of " + increment
            + " starting with " + value);
        IMacro rangeAssignment = new RangeAssignment(sX, sY, eX, eY, value, increment);
        rangeAssignment.execute(sheet);
      } catch (IllegalArgumentException e) {
        writeMessage("Error: " + e.getMessage() + System.lineSeparator());
      }
    } else {
      super.processCommand(userInstruction, sc, sheet);
    }
  }

  @Override
  protected void printMenu() throws IllegalStateException {
    writeMessage("Supported user instructions are: " + System.lineSeparator());
    writeMessage("assign-value row-num col-num value (set a cell to a value)"
        + System.lineSeparator());
    writeMessage("bulk-assign-value row-num col-num value (set a cell to a value)"
        + System.lineSeparator());
    writeMessage("print-value row-num col-num (print the value at a given cell)"
        + System.lineSeparator());
    writeMessage("menu (Print supported instruction list)" + System.lineSeparator());
    writeMessage("q or quit (quit the program) " + System.lineSeparator());
  }

  @Override
  protected void welcomeMessage() throws IllegalStateException {
    writeMessage("Welcome to the spreadsheet program!" + System.lineSeparator());
    printMenu();
  }
}
