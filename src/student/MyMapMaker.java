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
        HashMap<Integer, Station> stations = new HashMap<>();
        List<Route> routes = new ArrayList<>();
        try {
        Scanner scanner = new Scanner(in);
        Boolean isRoutes = false;
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            if (s.equals("##ROUTES##")) {
                isRoutes = true;
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
                    if(origin.getRow()<dest.getRow()) {
                        route = new MyRoute(origin, dest, Orientation.VERTICAL);
                    }
                    else {
                        route = new MyRoute(dest,origin, Orientation.VERTICAL);
                    }
                } else {
                    if(origin.getCol()<dest.getCol()) {
                    route = new MyRoute(origin, dest, Orientation.HORIZONTAL);
                }
                else {
                    route = new MyRoute(dest,origin, Orientation.HORIZONTAL);
                }
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
            railroadMap = new MyRailRoadMap(routes);
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
