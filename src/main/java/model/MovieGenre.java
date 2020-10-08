package model;

public class MovieGenre extends Model{

    private Integer genreId;
    private Integer movieId;

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }
}
