package city;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityUtilsDemo {

    @Test
    void demoSortedCities(){
        var cities = CityUtils.sortedCities("Montauban", "Toulouse", "Bordeaux", "Pau");
        System.out.println(cities);
    }

}