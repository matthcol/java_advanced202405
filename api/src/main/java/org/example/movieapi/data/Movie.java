package org.example.movieapi.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

// lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// Jackson
@JsonInclude(JsonInclude.Include.NON_NULL)
// JPA
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min=1, max=300)
    // @Pattern(regexp = "[\\W].*")
    @Column(nullable = false, length=300)
    private String title;

    @Min(1888)
    private int year;

    private Integer duration;
}
