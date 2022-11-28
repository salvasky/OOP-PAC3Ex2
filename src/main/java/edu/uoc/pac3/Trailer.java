package edu.uoc.pac3;

import java.time.LocalDate;

/**
 * This class is part of a course assignment, it represents movie trailers
 * This class is a component class
 * @author Salvador Sanchis Beneseit
 * @version 1.0
 */
public class Trailer {

    /**
     * Trailer's url
     */
    private String url;

    /**
     * Trailer's duration in seconds, e.g. 150
     */
    private int duration;

    /**
     * Trailer's date of release
     */
    private LocalDate releaseDate;

    /**
     * Trailer's maximum duration in seconds
     */
    public static final int MAX_DURATION = 180;

    /**
     * Trailer's error message for exceeding maximum duration
     */
    public static final String ERR_DURATION = "[ERROR] The duration of the trailer cannot be 0, negative or greater than '+MAX_DURATION+'";

    /**
     * Trailer's error message for wrong date entry
     */
    public static final String ERR_RELEASE = "[ERROR] The release of the trailer cannot be null or a date that is later than today";



    /**
     * constructor with arguments
     * @param url Trailer's url
     * @param duration Trailer's duration
     * @param releaseDate Trailer's release date
     * throws setDuration and setReleaseDate's exceptions
     */
    public Trailer(String url, int duration, LocalDate releaseDate) throws Exception {
        setUrl(url);
        setDuration(duration);
        setReleaseDate(releaseDate);
    }

    /**
     * url setter
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * duration setter
     * @param duration Trailer's duration
     * @throws Exception if duration is negative or exceeds maximum duration
     */
    public void setDuration(int duration) throws Exception {
        if (duration <= 0 || duration > MAX_DURATION){
            throw new Exception(ERR_DURATION);
        }
        this.duration = duration;
    }

    /**
     * release date setter
     * @param releaseDate Trailer's release date
     * @throws Exception if release date is null or later than current date
     */
    public void setReleaseDate(LocalDate releaseDate) throws Exception{
        if (releaseDate == null || releaseDate.isAfter(LocalDate.now())) {
            throw new Exception(ERR_RELEASE);
        }
        this.releaseDate = releaseDate;
    }

    /**
     * url getter
     * @return Trailer's url
     */
    public String getUrl() {
        return url;
    }

    /**
     * duration getter
     * @return Trailer's duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * releaseDate getter
     * @return Trailer's date of release
     */
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    /**
     * formattedDuration getter
     * @return Trailer's duration in a specific string format
     */
    public String getFormattedDuration(){
        long secs = getDuration();
        assert secs > 0;
        long MM = (secs % 3600)/60;
        long SS = secs % 60;
        return String.format("%02d:%02d", MM, SS);
    }

}
