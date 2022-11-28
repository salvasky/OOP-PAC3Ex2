package edu.uoc.pac3;

/**
 * This class is part of a course assignment, it represents theatres
 * This class is an associated class
 * @author Salvador Sanchis Beneseit
 * @version 1.0
 */

public class Theater {

    /**
     * Theater name
     */
    private String name;

    /**
     * Theater's audience capacity
     */
    private int capacity;

    /**
     * Theater's error message for wrong index
     */
    public static final String ERR_WRONG_INDEX = "[ERROR] Wrong index";

    /**
     * Theater's error message for wrong null parameter field
     */
    public static final String ERR_NULL_MOVIE = "[ERROR] the movie cannot be null";

    /**
     * Theater's error message for theater having reached maximum capacity
     */
    public static final String ERR_NO_MORE_MOVIES = "[ERROR] This theater cannot screen more movies";

    /**
     * Theater's error message for movie already shown in theater
     */
    public static final String ERR_MOVIE_EXISTS = "[ERROR] This movie is already screened in this theater";

    /**
     * Theater's error message for movie not in array
     */
    public static final String ERR_MOVIE_DOESNT_EXIST = "[ERROR] This movie does not exist in this theater";

    /***
     * Movie class initializer
     */
    private Movie[] movies;


    /**
     * constructor with arguments
     * @param name Theater name
     * @param capacity Theater's audience capacity
     * @param MAX_MOVIES Theater's maximum capacity of movies
     */
    public Theater(String name, int capacity, final int MAX_MOVIES){
        setName(name);
        setCapacity(capacity);
        movies = new Movie[MAX_MOVIES];

    }


    /**
     * Theater name setter
     * @param name Theater name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Capacity setter
     * @param capacity Theater's audience capacity
     */
    private void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Movie setter
     * @param index movie index position
     * @param movie movie object
     * @throws Exception if index is negative or larger than maximum movie capacity
     */
    private void setMovie(int index, Movie movie) throws Exception {
        if (index >= 0 && index <= movies.length) {
            movies[index] = movie;
        }else{
            throw new Exception(ERR_WRONG_INDEX);
        }
    }

    /**
     * theater name getter
     * @return name of theater
     */
    public String getName() {
        return name;
    }

    /**
     * theater's capacity getter
     * @return theater's audience capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Movie array etter
     * @return movie array
     */
    public Movie[] getMovies() {
        return movies;
    }

    /**
     * Movie getter
     * @param index index position
     * @return movie object
     * @throws Exception if index is negative or larger than maximum length
     */
    public Movie getMovie(int index) throws Exception{
        if (index < 0 || index >= movies.length) {
            throw new Exception(ERR_WRONG_INDEX);
        }else{
            return movies[index];
        }
    }

    /**
     * method to find movie index
     * @param movie object
     * @return movie index
     */
    private int findMovieIndex(Movie movie){

        int indexFound = -1;
        if (movie == null) {
            for (int i = 0; i < movies.length; i++)
                if (movies[i] == null) {
                    indexFound = i;
                    break;
                }
        }else {
            for (int i = 0; i < movies.length; i++)
                if (movies[i] == movie) {
                    indexFound = i;
                }
        }
        return indexFound;
    }

    /**
     * method to find if movie exists in theater
     * @param movie object
     * @return whether movie is in theather
     */
    public boolean movieExists(Movie movie){
        boolean inTheater = false;
        for (int i = 0; i < movies.length; i++)
            if (movie != null && movies[i] == movie) {
                inTheater = true;
                break;
            }
        return inTheater;
    }

    /**
     * method to find if theater can still show new movies
     * @return whether theater can show more movies
     */
    public boolean canScreenMoreMovies(){
        boolean room = false;
        for (int i = 0; i < movies.length; i++)
            if (movies[i] == null){
                room = true;
                break;
            }
        return room;
    }

    /**
     * method to add movie to theater
     * @param movie object
     * @throws Exception if object parameter is null,
     * if movie already exists in theater,
     * and if theatre cannot show more new movies
     * catches exceptions from setMovie and addTheater methods
     */
    public void addMovie(Movie movie) throws Exception {
        for (int i = 0; i < movies.length; i++)
            if (movie == null) {
                throw new Exception(ERR_NULL_MOVIE);
            } else if (movieExists(movie)) {
                throw new Exception(ERR_MOVIE_EXISTS);
            } else if (!canScreenMoreMovies()) {
                throw new Exception(ERR_NO_MORE_MOVIES);
            }else {
                if (movies[i] == null) {

                    try {
                        setMovie(i, movie);
                    } catch (Exception e){
                        break;
                    }

                    try {
                        movie.addTheater(this);
                    } catch (Exception e) {
                        break;
                    }
                    break;
                }
        }
    }


    /**
     * method to remove movie from theater
     * @param movie object
     * @throws Exception if movie is not in theater
     */
    public void removeMovie(Movie movie) throws Exception {
        if (!movieExists(movie)) {
            throw new Exception(ERR_MOVIE_DOESNT_EXIST);
        } else {
            for (int i = 0; i < movies.length; i++)
                if (movies[i] == movie) {
                   movies[i] = null;
                   movie.removeTheater(this);
                }
        }
    }


}
