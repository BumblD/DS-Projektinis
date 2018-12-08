package BusData;

import java.util.List;

public class Bus {

    private String routeName;
    private List<String> routeList;

    /**
     * Class constructor
     *
     * @param name bus route name
     * @param routes bus routes list
     */
    public Bus(String name, List<String> routes) {
        routeName = name;
        routeList = routes;
    }

    public String getRouteName() {
        return routeName;
    }

    public List<String> getRouteList() {
        return  routeList;
    }
}
