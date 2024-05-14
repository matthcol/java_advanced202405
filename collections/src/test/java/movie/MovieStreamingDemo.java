package movie;

import csv.CsvUtils;
import io.FilePathResourceUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

class MovieStreamingDemo {

    static List<String> personLines;
    static List<String> movieLines;
    @BeforeAll
    static void readData(){
        personLines = CsvUtils.readFileWithHeader(
                FilePathResourceUtils.pathFromResource(MovieStreamingDemo.class, "/persons.tsv"),
                UnaryOperator.identity()
        );
        movieLines = CsvUtils.readFileWithHeader(
                FilePathResourceUtils.pathFromResource(MovieStreamingDemo.class, "/movies.tsv"),
                UnaryOperator.identity()
        );
    }

    @Test
    void demoPrintFirst10Persons(){
        personLines.stream()
                .limit(10)
                .forEach(System.out::println);
    }

    @Test
    void demoPrintFirst10Movies(){
        movieLines.stream()
                .limit(10)
                .forEach(line -> System.out.println(line)); // lambda equivalent to System.out::println
    }

    // from personLines: retrieve 20 first names into a list
    @Test
    void demoListFirst20Names(){
        //var names = personLines.stream()
        //        .limit(20)
        //        .map(line -> line.split("\t")[1])
        //        .toList();
        //System.out.println(names);
        var names =  personLines.stream()
                .limit(20)
                .peek(System.out::println)
                .map(line -> line.split("\t"))
                .peek(infos -> System.out.println(Arrays.toString(infos)))
                .map(infos -> infos[1])
                .peek(System.out::println)
                // .toList(); // Java 17
                .collect(Collectors.toList()); // Java 8
        System.out.println("Names: " + names);
    }

    // 1. adapt personLines into a list of named infos: { id: 1, name: "Fred Astaire", birthdate: 1899-05-10 (as date) }

    // 2. adapt personLines into a list of Person objects (id, name, birthdate as date)

    @Test
    void demoPersonMapAllInfos(){
        String rawData = "1\tFred Astaire\t1899-05-10";
        String[] infos = rawData.split("\t", -1);
        System.out.println(Arrays.toString(infos));
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("id", infos[0]);
        infoMap.put("name", infos[1]);
        infoMap.put("birthdate", infos[2]);
        System.out.println(infoMap);
        System.out.println(MessageFormat.format(
                "'{'id={0} , name={1}, birthdate={2}'}'",
                infoMap.get("id"),
                infoMap.get("name"),
                infoMap.get("birthdate")
        ));
    }

    @Test
    void demoPersonMapAllInfos2(){
        String rawData = "1\tFred Astaire\t1899-05-10";
        String[] infos = rawData.split("\t", -1);
        System.out.println(Arrays.toString(infos));
        Map<String, Object> infoMap = new HashMap<>();
        infoMap.put("id", Integer.parseInt(infos[0]));
        infoMap.put("name", infos[1]);
        infoMap.put("birthdate", LocalDate.parse(infos[2]));
        System.out.println(infoMap);
        System.out.println(MessageFormat.format(
                "'{'id={0} , name={1}, birthdate={2}'}'",
                infoMap.get("id"),
                infoMap.get("name"),
                infoMap.get("birthdate")
        ));
    }

}
