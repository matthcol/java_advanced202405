package csv;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CsvPerson {
    public static Map<String,Object> lineToInfoMap(String line){
        String[] infos = line.split("\t", -1);
        Map<String, Object> infoMap = new HashMap<>();
        infoMap.put("id", Integer.parseInt(infos[0]));
        infoMap.put("name", infos[1]);
        if (!infos[2].isEmpty()) infoMap.put("birthdate", LocalDate.parse(infos[2]));
        return infoMap;
    }
}
