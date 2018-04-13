package student;

import model.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

public class MyMapMaker implements MapMaker {
    @Override
    public RailroadMap readMap(InputStream in) {
        RailroadMap railroadMap=null;
        List<Space> spaces = new ArrayList<>();
        HashMap<Integer, Station> stations = new HashMap<>();
        List<Route> routes = new ArrayList<>();
        try {
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
                stations.put(Integer.parseInt(split[0]), station);
            } else {
                String[] split = s.split(" ");
                Station origin = new MyStation(null, 0, 0);
                Station dest = new MyStation(null, 0, 0);
                Route route;
                for (Integer integer : stations.keySet()) {
                    if (integer == Integer.parseInt(split[0])) {
                        origin = stations.get(integer);
                    } else if (integer == Integer.parseInt(split[1])) {
                        dest = stations.get(integer);
                    }
                }

                if (origin.getCol() == dest.getCol()) {
                    route = new MyRoute(origin, dest, Orientation.VERTICAL);
                } else {
                    route = new MyRoute(origin, dest, Orientation.HORIZONTAL);
                }
                if (!split[2].equals("UNCLAIMED")) {
                    if (split[2].equals("RED")) {
                        route.claim(Baron.RED);
                    } else if (split[2].equals("YELLOW")) {
                        route.claim(Baron.YELLOW);
                    } else if (split[2].equals("BLUE")) {
                        route.claim(Baron.BLUE);
                    } else if (split[2].equals("GREEN")) {
                        route.claim(Baron.GREEN);
                    }
                }
                routes.add(route);
            }
        }
        List<Station> stationList = new ArrayList<>();
        for (Integer integer : stations.keySet()) {
            stationList.add(stations.get(integer));
        }
            railroadMap = new MyRailRoadMap(routes, stationList, spaces);
            in.close();
        }
        catch (IOException io) {
            System.out.println(io);
        }
        return railroadMap;
    }

    @Override
    public void writeMap(RailroadMap map, OutputStream out) {
        PrintWriter printWriter = new PrintWriter(out);
        HashMap<Integer, Station> stations = new HashMap<>();
        Collection<Route> routes = map.getRoutes();
        int counter = 0;
        for (Route route : routes) {
            if (!stations.containsValue(route.getOrigin())) {
                stations.put(counter, route.getOrigin());
                counter++;
            }
           else if (!stations.containsValue(route.getDestination())) {
                stations.put(counter, route.getDestination());
                counter++;
            }
        }
        for (Integer integer : stations.keySet()) {
            Station station = stations.get(integer);
            printWriter.println(integer + " " + station.getRow() + " " + station.getCol() + " " + station.getName());
        }
        printWriter.println("##ROUTES##");
        for (Route route : routes) {
            int origin=0;
            int dest=0;
            for(Integer integer:stations.keySet()){
                if(stations.get(integer)==route.getOrigin()){
                    origin=integer;
                }
                else if(stations.get(integer)==route.getDestination()){
                    dest=integer;
                }
            }
            printWriter.println(origin+" "+dest+" "+route.getBaron());

        }
        printWriter.flush();
        printWriter.close();
    }
}
