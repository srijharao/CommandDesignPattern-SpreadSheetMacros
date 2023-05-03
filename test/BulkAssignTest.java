import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import spreadsheet.BulkAssign;
import spreadsheet.SparseSpreadSheet;
import spreadsheet.SpreadSheet;

/**
 * Tests for BulkAssignment class.
 */
public class BulkAssignTest {

  private SpreadSheet sheet;


  @Before
  public void setUp() {
    sheet = new SparseSpreadSheet();
  }


  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptions() {
    new BulkAssign(-1, 1, 10);
  }

  @Test
  public void testBulkSetValidInputs() {
    BulkAssign bulkAssignMacro = new BulkAssign(10, 1, 10);
    bulkAssignMacro.execute(sheet);
    assertEquals(1, sheet.getWidth());
    assertEquals(10, sheet.getHeight());
  }
}