package student;

import model.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

public class MyMapMaker implements MapMaker {
    @Override
    public RailroadMap readMap(InputStream in) {
        RailroadMap railroadMap;
        List<Space> spaces = new ArrayList<>();
        HashMap<Integer, Station> stations = new HashMap<>();
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
                stations.put(Integer.parseInt(split[0]), station);
            } else {
                String[] split = s.split(" ");
                Station origin = new MyStation(null, 0, 0);
                Station dest = new MyStation(null, 0, 0);
                Route route = new MyRoute(origin, dest, Orientation.HORIZONTAL);
                for (Integer integer : stations.keySet()) {
                    if (integer == Integer.parseInt(split[0])) {
                        origin = stations.get(integer);
                    } else if (integer == Integer.parseInt(split[1])) {
                        dest = stations.get(integer);
                    }
                }
                System.out.println("INITILIZED----------------");
                System.out.println(origin);
                System.out.println(dest);
                if (origin.getCol() == dest.getCol()) {
                    System.out.println(origin.getCol() +","+dest.getCol());
                    route = new MyRoute(origin, dest, Orientation.VERTICAL);
                } else {
                    route = new MyRoute(origin, dest, Orientation.HORIZONTAL);
                }
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

                System.out.println("ROUTE----------------");
                System.out.println(route);
                routes.add(route);

            }

        }
        List<Station> stationList = new ArrayList<>();
        for (Integer integer : stations.keySet()) {
            stationList.add(stations.get(integer));
        }

        railroadMap = new MyRailRoadMap(routes, stationList, spaces);
        return railroadMap;
    }

    @Override
    public void writeMap(RailroadMap map, OutputStream out) {
        PrintWriter printWriter = new PrintWriter(out);
        HashMap<Integer, Station> stations = new HashMap<>();
        Collection<Route> routes = map.getRoutes();
        int counter = 0;
        for (Route route : routes) {
            if (stations.containsKey(route.getOrigin())) {
                stations.put(counter, route.getOrigin());
                counter++;
            }
        }
        for (Integer integer : stations.keySet()) {
            Station station = stations.get(integer);
            printWriter.write(integer + " " + station.getRow() + " " + station.getCol() + " " + station.getName());
        }
        printWriter.write("##ROUTES##");
        for (Route route : routes) {

            //printWriter.write();
        }

    }
}
