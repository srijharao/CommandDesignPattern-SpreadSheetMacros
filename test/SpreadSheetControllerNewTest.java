import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Before;
import org.junit.Test;
import spreadsheet.SparseSpreadSheet;
import spreadsheet.SpreadSheet;
import spreadsheet.SpreadSheetController;
import spreadsheet.SpreadSheetControllerNew;

/**
 * Tests for Better SpreadSheetController with command pattern.
 */
public class SpreadSheetControllerNewTest {

  private StringWriter writer;
  private SpreadSheetControllerNew controller;

  @Before
  public void setUp() {
    SpreadSheet sheet = new SparseSpreadSheet();
    writer = new StringWriter();
    controller = new SpreadSheetControllerNew(sheet, new StringReader(""), writer);
  }

  @Test
  public void testNewControllerCreation() {
    SpreadSheetController sheetController = new SpreadSheetControllerNew(new SparseSpreadSheet(),
        new InputStreamReader(System.in), new OutputStreamWriter(System.out));
    assertNotNull(sheetController);
  }

  @Test
  public void testMenuCommand() throws IOException {
    SpreadSheet sheet = new SparseSpreadSheet();
    String input = "menu\nquit\n";
    controller = new SpreadSheetControllerNew(sheet, new StringReader(input), writer);
    controller.control();
    String expectedOutput = "Welcome to the spreadsheet program!\n"
        + "Supported user instructions are: \n"
        + "assign-value row-num col-num value (set a cell to a value)\n"
        + "bulk-assign-value row-num col-num value (set a cell to a value)\n"
        + "print-value row-num col-num (print the value at a given cell)\n"
        + "menu (Print supported instruction list)\n"
        + "q or quit (quit the program) \n"
        + "Type instruction: Welcome to the spreadsheet program!\n"
        + "Supported user instructions are: \n"
        + "assign-value row-num col-num value (set a cell to a value)\n"
        + "bulk-assign-value row-num col-num value (set a cell to a value)\n"
        + "print-value row-num col-num (print the value at a given cell)\n"
        + "menu (Print supported instruction list)\n"
        + "q or quit (quit the program) \n"
        + "Type instruction: Thank you for using this program!";
    assertEquals(expectedOutput, writer.toString());
  }

  @Test
  public void testQuitCommand() throws IOException {
    SpreadSheet sheet = new SparseSpreadSheet();
    String input = "quit\n";
    controller = new SpreadSheetControllerNew(sheet, new StringReader(input), writer);
    controller.control();
    String expectedOutput = "Welcome to the spreadsheet program!\n"
        + "Supported user instructions are: \n"
        + "assign-value row-num col-num value (set a cell to a value)\n"
        + "bulk-assign-value row-num col-num value (set a cell to a value)\n"
        + "print-value row-num col-num (print the value at a given cell)\n"
        + "menu (Print supported instruction list)\n"
        + "q or quit (quit the program) \n"
        + "Type instruction: Thank you for using this program!";
    assertEquals(expectedOutput, writer.toString());
  }

  @Test
  public void testBulk() throws IOException {
    SpreadSheet sheet = new SparseSpreadSheet();
    String input = "bulk-assign-value A 1 5\n";
    controller = new SpreadSheetControllerNew(sheet, new StringReader(input), writer);
    controller.control();
    String expectedOutput = "Welcome to the spreadsheet program!\n"
        + "Supported user instructions are: \n"
        + "assign-value row-num col-num value (set a cell to a value)\n"
        + "bulk-assign-value row-num col-num value (set a cell to a value)\n"
        + "print-value row-num col-num (print the value at a given cell)\n"
        + "menu (Print supported instruction list)\n"
        + "q or quit (quit the program) \n"
        + "Type instruction: Executing Bulk Assign "
        + "for cells of range (0,1Thank you for using this program!";
    assertEquals(expectedOutput, writer.toString());
  }

  @Test
  public void testAverageFail() throws IOException {
    SpreadSheet sheet = new SparseSpreadSheet();
    String input = "average A 1 B 10 0 0\n";
    controller = new SpreadSheetControllerNew(sheet, new StringReader(input), writer);
    controller.control();
    String expectedOutput = "Welcome to the spreadsheet program!\n"
        + "Supported user instructions are: \n"
        + "assign-value row-num col-num value (set a cell to a value)\n"
        + "bulk-assign-value row-num col-num value (set a cell to a value)\n"
        + "print-value row-num col-num (print the value at a given cell)\n"
        + "menu (Print supported instruction list)\n"
        + "q or quit (quit the program) \n"
        + "Type instruction: Error: Invalid row\n"
        + "Type instruction: Undefined instruction: 0\n"
        + "Thank you for using this program!";
    assertEquals(expectedOutput, writer.toString());
  }

  @Test
  public void testAverage() throws IOException {
    SpreadSheet sheet = new SparseSpreadSheet();
    String input = "average A 1 B 10 C 2\n";
    controller = new SpreadSheetControllerNew(sheet, new StringReader(input), writer);
    controller.control();
    String expectedOutput = "Welcome to the spreadsheet program!\n"
        + "Supported user instructions are: \n"
        + "assign-value row-num col-num value (set a cell to a value)\n"
        + "bulk-assign-value row-num col-num value (set a cell to a value)\n"
        + "print-value row-num col-num (print the value at a given cell)\n"
        + "menu (Print supported instruction list)\n"
        + "q or quit (quit the program) \n"
        + "Type instruction: Computing Average for cells of range (0,1) "
        + "and (1,10) and assigning the value to (2,2)Thank you for using this program!";
    assertEquals(expectedOutput, writer.toString());
  }

  @Test
  public void testRange() throws IOException {
    SpreadSheet sheet = new SparseSpreadSheet();
    String input = "range-assign A 1 B 10 2 2\n";
    controller = new SpreadSheetControllerNew(sheet, new StringReader(input), writer);
    controller.control();
    String expectedOutput = "Welcome to the spreadsheet program!\n"
        + "Supported user instructions are: \n"
        + "assign-value row-num col-num value (set a cell to a value)\n"
        + "bulk-assign-value row-num col-num value (set a cell to a value)\n"
        + "print-value row-num col-num (print the value at a given cell)\n"
        + "menu (Print supported instruction list)\n"
        + "q or quit (quit the program) \n"
        + "Type instruction: Assigning values for cells of range (0,1) and "
        + "(1,10) in the increments of 2 starting with 2Thank you for using this program!";
    assertEquals(expectedOutput, writer.toString());
  }

}