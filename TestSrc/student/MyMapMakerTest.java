package student;

import model.RailroadMap;
import model.Route;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class MyMapMakerTest {
    @BeforeClass
    public static void init(){
        //initilize fields
    }
    @Test
    public void testReadmap()throws IOException{

        File initialFile = new File("maps/20x25Asia.rbmap");
        InputStream targetStream = new FileInputStream(initialFile);
        MyMapMaker myMapMaker=new MyMapMaker();
        RailroadMap map= myMapMaker.readMap(targetStream);
        Collection<Route> routes=map.getRoutes();
        System.out.println(map.getRoutes());
        for(Route route:routes ){
            System.out.println(route);
        }
        //System.out.println(map);
    }

}