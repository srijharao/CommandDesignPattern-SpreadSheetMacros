import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;
import spreadsheet.IMacro;
import spreadsheet.RangeAssignment;
import spreadsheet.SparseSpreadSheet;
import spreadsheet.SpreadSheet;

/**
 * Tests for RangeAssignment class.
 */
public class RangeAssignmentTest {

  @Test
  public void testValidRangeAssignment() {
    SpreadSheet sheet = new SparseSpreadSheet();
    IMacro rangeAssignment = new RangeAssignment(1, 1, 4, 4, 5, 2);
    rangeAssignment.execute(sheet);

    assertEquals(5.0, sheet.get(1, 1), 0.001);
    assertEquals(7.0, sheet.get(1, 2), 0.001);
    assertEquals(9.0, sheet.get(1, 3), 0.001);
    assertEquals(11.0, sheet.get(2, 1), 0.001);
    assertEquals(13.0, sheet.get(2, 2), 0.001);
    assertEquals(15.0, sheet.get(2, 3), 0.001);
  }

  @Test
  public void testNegativeStartRow() {
    assertThrows(IllegalArgumentException.class,
        () -> new RangeAssignment(-1, 0, 4, 4, 5, 2));
  }

  @Test
  public void testNegativeStartColumn() {
    assertThrows(IllegalArgumentException.class,
        () -> new RangeAssignment(0, -1, 4, 4, 5, 2));
  }

  @Test
  public void testNegativeEndRow() {
    assertThrows(IllegalArgumentException.class,
        () -> new RangeAssignment(0, 0, -1, 4, 5, 2));
  }

  @Test
  public void testNegativeEndColumn() {
    assertThrows(IllegalArgumentException.class,
        () -> new RangeAssignment(0, 0, 4, -1, 5, 2));
  }

  @Test
  public void testEndRowLessThanStartRow() {
    assertThrows(IllegalArgumentException.class,
        () -> new RangeAssignment(4, 4, 1, 1, 5, 2));
  }

  @Test
  public void testEndColumnLessThanStartColumn() {
    assertThrows(IllegalArgumentException.class,
        () -> new RangeAssignment(1, 4, 4, 1, 5, 2));
  }
}