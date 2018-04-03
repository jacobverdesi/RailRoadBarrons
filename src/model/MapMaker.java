package model;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Interface for a class that can load and save {@linkplain RailroadMap maps}.
 * Maps files are in the format:
 * <ol>
 *     <li>&lt;station number&gt; &lt;row&gt; &lt;col&gt; &lt;station
 *     name&gt;</li>
 *     <li>##ROUTES##</li>
 *     <li>&lt;origin station number&gt; &lt;destination station number&gt;
 *     &lt;owner&gt;</li>
 * </ol>
 */
public interface MapMaker {
    /**
     * Loads a {@linkplain RailroadMap map} using the data in the given
     * {@linkplain InputStream input stream}.
     *
     * @param in The {@link InputStream} used to read the {@link RailroadMap
     * map} data.
     * @return The {@link RailroadMap map} read from the given
     * {@link InputStream}.
     *
     * @throws RailroadBaronsException If there are any problems reading the
     * data from the {@link InputStream}.
     */
    RailroadMap readMap(InputStream in) throws RailroadBaronsException;

    /**
     * Writes the specified {@linkplain RailroadMap map} in the Railroad
     * Barons map file format to the given {@linkplain OutputStream output
     * stream}. The written map should include an accurate record of any
     * routes that have been claimed, and by which {@linkplain Baron}.
     *
     * @param map The {@link RailroadMap map} to write out to the
     * {@link OutputStream}.
     * @param out The {@link OutputStream} to which the
     * {@link RailroadMap map} data should be written.
     *
     * @throws RailroadBaronsException If there are any problems writing the
     * data to the {@link OutputStream}.
     */
    void writeMap(RailroadMap map, OutputStream out)
            throws RailroadBaronsException;
}
