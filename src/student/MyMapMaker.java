package student;

import model.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class MyMapMaker implements MapMaker {

    @Override
    public RailroadMap readMap(InputStream in) throws RailroadBaronsException {
        RailroadMap railroadMap = null;
        List<Space> spaces = new ArrayList<>();
        HashMap<Integer,Station> stations = new HashMap<>();
        List<Track> tracks;
        List<Route> routes = new ArrayList<>();
        Scanner scanner = new Scanner(in);
        Boolean isRoutes = false;
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            if (s.equals("##ROUTES##")) {
                isRoutes = true;
                int maxCol = 0;
                int maxRow = 0;
                for (Station station : stations.values()) {
                    if (station.getRow() > maxRow) {
                        maxRow = station.getRow();
                    } else if (station.getCol() > maxCol) {
                        maxCol = station.getCol();
                    }
                }
                for (int i = 0; i < maxCol; i++) {
                    for (int j = 0; j < maxCol; j++) {
                        MySpace space = new MySpace(i, j);
                        spaces.add(space);
                    }
                }
            } else if (!isRoutes) {
                String[] split = s.split(" ");
                Station station = new MyStation(split[3], Integer.parseInt(split[1]), Integer.parseInt(split[2]));
                stations.put(Integer.parseInt(split[0]),station);
            } else {
                String[] split = s.split(" ");
                Station origin = null, dest = null;
                Orientation orientation = null;
                for (Integer integer : stations.keySet()) {
                    if(integer== Integer.parseInt(split[0])) {
                        origin = stations.get(integer);
                    }else if(integer== Integer.parseInt(split[0])) {
                        dest = stations.get(integer);
                    }
                }
                if (origin.getCol() == dest.getCol()) {
                    orientation = Orientation.VERTICAL;
                } else if (origin.getRow() == dest.getRow()) {
                    orientation = Orientation.HORIZONTAL;
                }

                MyRoute route = new MyRoute(origin, dest, orientation);
                if (!split[2].equals("UNCLAIMED")) {
                    if (split[2].equals("Red")) {
                        route.claim(Baron.RED);
                    } else if (split[2].equals("Yellow")) {
                        route.claim(Baron.YELLOW);
                    } else if (split[2].equals("Blue")) {
                        route.claim(Baron.BLUE);
                    } else if (split[2].equals("Green")) {
                        route.claim(Baron.GREEN);
                    }
                }
                routes.add(route);
            }
        }
        List<Station> stationList=new ArrayList<>();
        for (Integer integer : stations.keySet()) {
            stationList.add(stations.get(integer));
        }
        railroadMap = new MyMap(routes, stationList, spaces);
        return railroadMap;
    }

    @Override
    public void writeMap(RailroadMap map, OutputStream out) {

    }
}
