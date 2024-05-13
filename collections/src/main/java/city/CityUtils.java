package city;

import java.util.*;

public class CityUtils {

    public static List<String> sortedCities(String... cities){
        var result = new ArrayList<String>();
        Collections.addAll(result, cities);
        Collections.sort(result);
        return result;
    }
}
