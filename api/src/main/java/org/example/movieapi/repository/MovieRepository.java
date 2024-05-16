package org.example.movieapi.repository;

import org.example.movieapi.data.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findByYear(int year);

    @Query("select m from Movie m where m.year between :year1 and :year2") // JPQL
    List<Movie> findByYearRange(int year1, int year2);
}
