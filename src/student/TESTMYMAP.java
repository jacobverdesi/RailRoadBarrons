package student;

import model.RailroadMap;
import model.Route;
import model.Space;

import java.io.*;
import java.util.Collection;

public class TESTMYMAP {
    public static void main(String[] args) throws IOException{
        File initialFile = new File("maps/simple.rbmap");
        InputStream targetStream = new FileInputStream(initialFile);
        MyMapMaker myMapMaker=new MyMapMaker();
        RailroadMap map= myMapMaker.readMap(targetStream);
        OutputStream outputStream=System.out;
        myMapMaker.writeMap(map,outputStream);
    }
}
