package BusData;

import java.util.List;

public class Bus {

    private String routeName;
    private List<String> routeList;

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
