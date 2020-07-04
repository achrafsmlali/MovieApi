package com.decathlon.moviesAPI.model;

import com.decathlon.moviesAPI.payload.MovieRankResponse;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@SqlResultSetMapping(
        name = "movieRankMap",
        classes = {
                @ConstructorResult(
                        targetClass = MovieRankResponse.class,
                        columns = {
                                @ColumnResult(name = "movie_id", type = Integer.class),
                                @ColumnResult(name = "total_comments", type = Integer.class),
                                @ColumnResult(name = "rank", type = Integer.class)
                        }
                )
        }
)
@NamedNativeQuery(name = "getMovieRanking",
        query = "SELECT m.id as movie_id, count(c.movie_id) as total_comments, DENSE_RANK() OVER(ORDER BY count(c.movie_id) DESC) rank  " +
        "FROM Comment c RIGHT JOIN (" +
                "SELECT * FROM Movie mo " +
                "WHERE  (mo.release_date IS NOT NULL AND mo.release_date BETWEEN  ?1 AND ?2) OR (mo.release_date IS NULL AND mo.year BETWEEN YEAR(?1) and YEAR(?2))) m " +
                "ON c.movie_id = m.id " +
        "GROUP BY m.id " + "ORDER BY rank",
        resultSetMapping="movieRankMap" )

public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Movie movie;

    @NotBlank
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
