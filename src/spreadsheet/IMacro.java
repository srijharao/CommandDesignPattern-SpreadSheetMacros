package spreadsheet;

/**
 * The {@code IMacro} interface represents a macro that can be executed on a {@link SpreadSheet}
 * object. Any class that implements this interface must provide an implementation for the
 * {@link #execute(SpreadSheet)} method.
 */
public interface IMacro {

  /**
   * Executes the macro on the given {@link SpreadSheet} object.
   *
   * @param spreadSheet the {@link SpreadSheet} object on which to execute the macro
   */
  void execute(SpreadSheet spreadSheet);

}
