package student;

import model.MapMaker;
import model.RailroadBaronsException;
import model.RailroadMap;

import java.io.InputStream;
import java.io.OutputStream;

public class MyMapMaker implements MapMaker {

    @Override
    public RailroadMap readMap(InputStream in)throws RailroadBaronsException {
     return null;
    }

    @Override
    public void writeMap(RailroadMap map, OutputStream out) {

    }
}
