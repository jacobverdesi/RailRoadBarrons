package student;

import model.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;
/**
 * Interface for a class that can load and save {@linkplain RailroadMap maps}.
 * Maps files are in the format:
 * <ol>
 *     <li>&lt;station number&gt; &lt;row&gt; &lt;col&gt; &lt;station
 *     name&gt;</li>
 *     <li>##ROUTES##</li>
 *     <li>&lt;origin station number&gt; &lt;destination station number&gt;
 *     &lt;owner&gt;</li>
 * </ol>
 * @Author Jacob Verdesi
 * @Author Andrew Yankowsky
 */
public class MyMapMaker implements MapMaker {
    /**
     * Loads a {@linkplain RailroadMap map} using the data in the given
     * {@linkplain InputStream input stream}.
     *
     * @param in The {@link InputStream} used to read the {@link RailroadMap
     * map} data.
     * @return The {@link RailroadMap map} read from the given
     * {@link InputStream}.
     *
     * @throws RailroadBaronsException If there are any problems reading the
     * data from the {@link InputStream}.
     */
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
            String[] split = s.split(" ",4);
            if (s.equals("##ROUTES##")) {
                isRoutes = true;
            } else if (!isRoutes) {
                Station station = new MyStation(split[3], Integer.parseInt(split[1]), Integer.parseInt(split[2]));
                stations.put(Integer.parseInt(split[0]), station);
            } else {
                Station origin = stations.get(Integer.parseInt(split[0]));
                Station dest =  stations.get(Integer.parseInt(split[1]));
                Route route;
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
                    if (split[2].equals("RED")){
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
    /**
     * Writes the specified {@linkplain RailroadMap map} in the Railroad
     * Barons map file format to the given {@linkplain OutputStream output
     * stream}. The written map should include an accurate record of any
     * routes that have been claimed, and by which {@linkplain Baron}.
     *
     * @param map The {@link RailroadMap map} to write out to the
     * {@link OutputStream}.
     * @param out The {@link OutputStream} to which the
     * {@link RailroadMap map} data should be written.
     *
     * @throws RailroadBaronsException If there are any problems writing the
     * data to the {@link OutputStream}.
     */

    @Override
    public void writeMap(RailroadMap map, OutputStream out) {
        PrintWriter printWriter = new PrintWriter(out);
        HashMap<Integer, Station> stations = new HashMap<>();
        Collection<Route> routes = map.getRoutes();
        int counter = 0;
        for (Route route : routes) {
            if (!stations.containsValue(route.getOrigin())) {
                stations.put(counter, route.getOrigin());
            }
           else if (!stations.containsValue(route.getDestination())) {
                stations.put(counter, route.getDestination());
            }
            counter++;
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
                if(stations.get(integer).equals(route.getOrigin())){
                    origin=integer;
                }
                else if(stations.get(integer).equals(route.getDestination())){
                    dest=integer;
                }
            }
            printWriter.println(origin+" "+dest+" "+route.getBaron());
        }
        printWriter.flush();
        printWriter.close();
    }
}
