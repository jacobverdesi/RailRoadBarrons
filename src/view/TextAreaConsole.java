package view;

import javafx.scene.control.TextArea;

/**
 * An implementation of the {@linkplain Console} that writes all output to a
 * {@linkplain TextArea text area}.
 */
public class TextAreaConsole extends TextArea implements Console {
    /**
     * Used to add a line separator to the end of output as it is added.
     */
    private static final String NL = System.getProperty("line.separator");

    /**
     * Creates a new {@linkplain Console console} that appends lines of text
     * to a scrolling {@linkplain TextArea text area}.
     */
    TextAreaConsole() {
        setWrapText(true);
        setEditable(false);
        setMaxHeight(Double.MAX_VALUE);
        setMaxWidth(Double.MAX_VALUE);
    }

    @Override
    public void write(String output) {
        appendText(output + NL);
    }

    @Override
    public void clear() {
        super.clear();
    }
}
