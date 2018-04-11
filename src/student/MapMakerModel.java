package student;

import model.MapMaker;
import model.RailroadBaronsException;
import model.RailroadMap;

import java.io.InputStream;
import java.io.OutputStream;

public class MapMakerModel implements MapMaker {
    public MapMakerModel(){

    }

    @Override
    public RailroadMap readMap(InputStream in) {
        return null;
    }

    @Override
    public void writeMap(RailroadMap map, OutputStream out) {

    }
}
