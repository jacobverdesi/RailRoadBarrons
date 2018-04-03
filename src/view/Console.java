package view;

/**
 * The interface for a console that can be used to provide textual output to
 * the user. This is used by the controller and model classes to update the
 * user interface without introducing a direct dependency on the view.
 */
public interface Console {
    /**
     * Writes the specified output to the console.
     *
     * @param output The output to write to the console.
     */
    public void write(String output);

    /**
     * Clears the console, removing any previously written output.
     */
    void clear();
}
