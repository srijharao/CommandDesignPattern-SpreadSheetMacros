import static org.junit.Assert.assertEquals;

import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import spreadsheet.AverageAssignment;
import spreadsheet.BulkAssign;
import spreadsheet.IMacro;
import spreadsheet.RangeAssignment;
import spreadsheet.SpreadSheetNew;
import spreadsheet.SpreadSheetProgram;

/**
 * Test class for SpreadSheetProgram class.
 */
public class SpreadSheetProgramTest {

  private SpreadSheetNew sheet;

  @Before
  public void setup() {
    sheet = new SpreadSheetProgram();
  }

  @Test
  public void testGetSet() {
    Random r = new Random(100);
    double[][] expectedSet = new double[100][100];
    for (int i = 0; i < 100; i = i + 1) {
      for (int j = 0; j < 100; j = j + 1) {
        double num = r.nextDouble();
        expectedSet[i][j] = num;
        assertTrue(sheet.isEmpty(i, j));
        assertEquals(0.0, sheet.get(i, j), 0.001);
        sheet.set(i, j, num);
        assertFalse(sheet.isEmpty(i, j));
      }
    }

    for (int i = 0; i < 100; i = i + 1) {
      for (int j = 0; j < 100; j = j + 1) {
        assertEquals(expectedSet[i][j], sheet.get(i, j), 0.01);
      }
    }
  }

  @Test
  public void testGetWidthHeight() {
    for (int i = 0; i < 100; i = i + 1) {
      for (int j = 0; j < 100; j = j + 1) {
        sheet.set(i, j, 0);
        assertEquals((i + 1), sheet.getHeight());
        if (i == 0) {
          assertEquals((j + 1), sheet.getWidth());
        } else {
          assertEquals(100, sheet.getWidth());
        }
      }
    }

    sheet.set(1000, 1000, 0);
    assertEquals(1001, sheet.getWidth());
    assertEquals(1001, sheet.getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetWithNegativeRow() {
    sheet.set(0, 0, 1);
    sheet.set(0, 1, 9);
    sheet.get(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetWithNegativeColumn() {
    sheet.set(0, 0, 1);
    sheet.set(0, 1, 9);
    sheet.get(0, -1);
  }

  @Test
  public void addBulkAssignMacroToSpreadSheet() {
    sheet = new SpreadSheetProgram();
    IMacro bulkAssign = new BulkAssign(3, 3, 12);
    sheet.addMacroToSpreadSheet(bulkAssign);
    assertEquals("SpreadSheetProgram{spreadSheet=SparseSpreadSheet{width=3, height=3 "
        + "sheet={CellPosition{row=1, column=0}=12.0, CellPosition{row=2, column=1}=12.0, "
        + "CellPosition{row=0, column=0}=12.0, CellPosition{row=1, column=1}=12.0, "
        + "CellPosition{row=2, column=2}=12.0, CellPosition{row=0, column=1}=12.0, "
        + "CellPosition{row=1, column=2}=12.0, CellPosition{row=0, column=2}=12.0, "
        + "CellPosition{row=2, column=0}=12.0}}}", sheet.toString());
  }

  @Test
  public void addAverageAssignmentAssignMacroToSpreadSheet() {
    sheet = new SpreadSheetProgram();
    IMacro bulkAssign = new BulkAssign(1, 1, 1);
    sheet.addMacroToSpreadSheet(bulkAssign);
    bulkAssign = new BulkAssign(2, 2, 2);
    sheet.addMacroToSpreadSheet(bulkAssign);
    bulkAssign = new BulkAssign(3, 3, 3);
    sheet.addMacroToSpreadSheet(bulkAssign);

    IMacro averageAssignment = new AverageAssignment(0, 1, 3, 3, 4, 5);
    sheet.addMacroToSpreadSheet(averageAssignment);
    assertEquals(3, sheet.get(4, 5), 0.01);
  }

  @Test
  public void addRangeAssignmentAssignMacroToSpreadSheet() {
    sheet = new SpreadSheetProgram();
    IMacro rangeAssignment = new RangeAssignment(0, 1, 3, 3, 4, 5);
    sheet.addMacroToSpreadSheet(rangeAssignment);
    assertEquals("SpreadSheetProgram{spreadSheet=SparseSpreadSheet{width=3, "
        + "height=3 sheet={CellPosition{row=2, column=1}=24.0, CellPosition{row=1, "
        + "column=1}=14.0, CellPosition{row=2, column=2}=29.0, CellPosition{row=0, "
        + "column=1}=4.0, CellPosition{row=1, column=2}=19.0, CellPosition{row=0, "
        + "column=2}=9.0}}}", sheet.toString());
  }
}