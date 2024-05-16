package org.example.movieapi.controller;

import jakarta.validation.Valid;
import org.example.movieapi.data.Movie;
import org.example.movieapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/movie")
public class MovieController {

    @Autowired // DI = Dependency Injection
    private MovieRepository movieRepository;

    @GetMapping
    // @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Movie> allMovies(){
        return movieRepository.findAll();
    }

    @GetMapping("{id}")
    public Movie movieById(@PathVariable("id") int id){
        return movieRepository.findById(id)
                .orElseThrow(() -> new ErrorResponseException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("byYear")
    public List<Movie> moviesByYear(@RequestParam("y") int year){
        return movieRepository.findByYear(year);
    }

    @GetMapping("byYearRange")
    public List<Movie> moviesByYearRange(@RequestParam("y1") int year1, @RequestParam("y2") int year2){
        return movieRepository.findByYearRange(year1, year2);
    }

    @GetMapping("/fail")
    public String fail(){
        throw new ArithmeticException();
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Movie addMovie(@RequestBody @Valid Movie movie) {
        movieRepository.save(movie);
        movieRepository.flush();
        return movie;
    }
}
