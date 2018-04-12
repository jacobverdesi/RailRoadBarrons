package student;

import model.RailroadMap;
import model.Route;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public class TESTMYMAP {
    public static void main(String[] args) throws IOException{
        File initialFile = new File("maps/20x25Asia.rbmap");
        InputStream targetStream = new FileInputStream(initialFile);
        MyMapMaker myMapMaker=new MyMapMaker();
        RailroadMap map= myMapMaker.readMap(targetStream);
        Collection<Route> routes=map.getRoutes();
        for(Route route:routes){
            System.out.println(route);
        }
        System.out.println(map.getRoutes());
        System.out.println(map);
    }
}
