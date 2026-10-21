package ua.edu.finance;
import java.io.IOException;
import java.io.Reader;
/** Незалежне від формату джерело текстових даних. */
public interface DataSource { Reader open() throws IOException; }
