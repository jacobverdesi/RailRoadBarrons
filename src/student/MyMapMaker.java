package student;

import model.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Scanner;

public class MyMapMaker implements MapMaker {

    @Override
    public RailroadMap readMap(InputStream in) throws RailroadBaronsException {
        RailroadMap railroadMap=null;
        List<Space> spaces;
        List<Station> stations;
        List<Track> tracks;
        List<Route> routes;
        Scanner scanner = new Scanner(in);
        Boolean isRoutes=false;
        while (scanner.hasNextLine()){
            String s = scanner.nextLine();
            if(s.equals("##ROUTES##")){
                isRoutes=true;
            }
            else if(!isRoutes){
                String[] split = s.split(" ");

            }
            else {

            }


        }

        return railroadMap;
    }

    @Override
    public void writeMap(RailroadMap map, OutputStream out) {

    }
}
