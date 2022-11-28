package edu.uoc.pac3;

import java.time.LocalDate;
import java.util.UUID;

/**
 * This class is part of a course assignment, it represents movies
 * This class is a component class
 * @author Salvador Sanchis Beneseit
 * @version 1.0
 */
public class Movie {

    /**
     * Movie's id
     */
    private UUID id;

    /**
     * Movie's title
     */
    private String title;

    /**
     * Movie's summary
     */
    private String summary;

    /**
     * Movie's duration
     */
    private int duration;

    /**
     * Movie's date of release
     */
    private LocalDate releaseDate;

    /**
     * Movie's language (original version or dubbed)
     */
    private boolean ov;

    /**
     * Movie's maximum summary length
     */
    private static final int MAX_SUMMARY_LENGTH = 450;

    /**
     * Movie's duration error message for movie length
     */
    public static final String ERR_DURATION = "[ERROR] The duration of the movie cannot be 0 or negative";

    /**
     * Movie's error message for maximum summary length
     */
    public static final String ERR_SUMMARY_LENGTH = "[ERROR] Summary's length cannot be greater than '+MAX_SUMMARY_LENGTH+' characters";

    /**
     * Movie's error message for wrong index
     */
    public static final String ERR_WRONG_INDEX = "[ERROR] Wrong index";

    /**
     * Movie's error message for theatre already in movie array
     */
    public static final String ERR_THEATER_EXISTS = "[ERROR] Movie already exists in this theaters";

    /**
     * Movie array maximum length
     */
    public static final int MAX_THEATERS = 5;


    /**
     * Movie's trailer information
     */
    private Trailer trailer;

    /**
     * Theater class initializer
     */
    private final Theater[] theaters;

    {
        setId();
    }

    /**
     * id setter
     * sets a random id number
     */
    private void setId() {
        id = UUID.randomUUID();
    }


    /**
     * constructor with arguments
     * @param title Movie's title
     * @param summary Movie's summary
     * @param duration Movie's duration
     * @param releaseDate Movie's release date
     * @param ov Movie's language
     * catches exceptions from setSummary and setDuration methods
     */
    public Movie(String title, String summary, int duration, LocalDate releaseDate, boolean ov) {
        setTitle(title);
        try {
            setSummary(summary);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try{
            setDuration(duration);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        setReleaseDate(releaseDate);
        setOv(ov);
        trailer = null;
        theaters = new Theater[MAX_THEATERS];
    }

    /**
     * constructor with arguments
     * @param title Movie's url
     * @param summary Movie's summary
     * @param duration Movie's duration
     * @param releaseDate Movie's release date
     * @param ov Movie's language
     * @param url Trailer's url
     * @param trailerDuration Trailer's duration
     * @param trailerReleaseDate Trailer's date of release
     * catches exceptions from setSummary and setDuration methods
     */
    public Movie(String title, String summary, int duration, LocalDate releaseDate, boolean ov, String url, int trailerDuration, LocalDate trailerReleaseDate) {
        setTitle(title);
        try {
            setSummary(summary);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            setDuration(duration);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        setReleaseDate(releaseDate);
        setOv(ov);

        setTrailer(url, trailerDuration, trailerReleaseDate);

        theaters = new Theater[MAX_THEATERS];

    }


    /**
     * title setter
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * summary setter
     * @param summary movie's summary
     * @throws Exception if summary exceeds maximum duration
     */
    public void setSummary(String summary) throws Exception {
        if (summary.length() > MAX_SUMMARY_LENGTH) {
            throw new Exception(ERR_SUMMARY_LENGTH);
        }
        this.summary = summary;
    }

    /**
     * duration setter
     * @param duration Trailer's duration
     * @throws Exception if duration is zero or negative
     */
    public void setDuration(int duration) throws Exception {
        if (duration <= 0){
            throw new Exception(ERR_DURATION);
        }
        this.duration = duration;
    }

    /**
     * releaseDate setter
     * @param releaseDate movie's date of release
     */
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * ov setter
     * @param ov movie's language
     */
    public void setOv(boolean ov) {
        this.ov = ov;
    }

    /**
     * trailer setter
     * @param url Trailer's url
     * @param duration Trailer's duration
     * @param releaseDate Trailer's date of release
     * catches Exception if duration is negative or exceeds maximum duration
     */
    public void setTrailer(String url, int duration, LocalDate releaseDate ) {
        try{
        trailer = new Trailer(url, duration, releaseDate);
        } catch (Exception e)  {
            System.out.println(e.getMessage());
            trailer = null;
        }
    }

    /**
     * Theater setter
     * @param index theater's index position
     * @param theater theater object
     * @throws Exception if index is negative or bigger than maximum length
     */
    private void setTheater(int index, Theater theater) throws Exception {
        if (index >= 0 && index < theaters.length) {
            theaters[index] = theater;
        }else{
            throw new Exception(ERR_WRONG_INDEX);
        }
    }


    /**
     * id getter
     * @return Movie's id
     */
    public UUID getId() {
        return id;
    }

    /**
     * title getter
     * @return Movie's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * summary getter
     * @return Movie's summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * duration getter
     * @return Movie's duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * releaseDate getter
     * @return Movie's date of release
     */
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    /**
     * ov getter
     * @return Movie's ov (original language)
     */
    public boolean isOv() {
        return ov;
    }

    /**
     * Trailer getter
     * @return Movie's trailer information
     */
    public Trailer getTrailer() {
        return trailer;
    }


    /**
     * Theater array getter
     * @return array with theatres
     */
    private Theater[] getTheaters(){
        if (theaters == null){
            return null;}
        return new Theater[0];
    }

    /**
     * Theater getter
     * @param index theater index position
     * @return Theatre with specified index
     * @throws Exception if index is out of bounds
     */
    public Theater getTheater(int index) throws Exception {
        if (index < 0 || index >= theaters.length) {
            throw new Exception(ERR_WRONG_INDEX);
        }else{
            return theaters[index];
        }
    }


    /**
     * Theater boolean getter
     * @param theater theater object
     * @return true if theatre shows the movie
     */
    public boolean isInTheTheater(Theater theater) {
        boolean inTheater = false;
        for (Theater value : theaters)
            if (theater != null && value == theater) {
                inTheater = true;
                break;
            }
        return inTheater;
    }


    /**
     * Theater index getter
     * @param theater theater object
     * @return Theater with specified index
     */
    private int findTheaterIndex(Theater theater){
        int indexFound = -1;
        for (int i = 0; i < theaters.length; i++)
            if (theaters[i] == theater) {
                indexFound = i;
            }
        return indexFound;
    }


    /**
     * method to add theater
     * @param theater theater object
     * @throws Exception if theater exists in movie array
     * catches exceptions from setTheater and addMovie methods
     */
    public void addTheater(Theater theater) throws Exception {
        for (int i = 0; i < theaters.length; i++)
            if (isInTheTheater(theater)) {
                throw new Exception(ERR_THEATER_EXISTS);
            } else {
            if (theaters[i] == null) {
                    try {
                        setTheater(i, theater);
                    } catch (Exception e) {
                        break;
                    }
                    try {
                        theater.addMovie(this);
                    } catch (Exception e) {
                        break;
                    }
                    break;
                }
        }
    }

    /**
     * method to remove theater
     * @param theater theater object
     * propagates exceptions from movieExists and removeMovie methods
     */
    public void removeTheater(Theater theater) throws Exception {
        for (int i = 0; i < theaters.length; i++)
            if (theaters[i] == theater) {
                theaters[i] = null;
                if (theater.movieExists(this)){
                theater.removeMovie(this);}
            }
    }

    /**
     * method to remove all theatres
     */
    public void notInTheaters() {
        for (int i = 0; i < theaters.length; i++) {
            try {
                getTheater(i).removeMovie(this);
            } catch (Exception e) {
                break;
            }
            theaters[i] = null;
        }

    }
}


