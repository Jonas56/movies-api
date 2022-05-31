package com.marsox.movies.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "directors")
@NoArgsConstructor
@Getter
@Setter
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    @NotEmpty(message = "Director name must not be empty!!")
    private String fullName;
    private String image;
    private String biography;
    private LocalDate bornDate;
    @Transient
    private Integer age;


    public Director(String fullName, String biography, LocalDate bornDate) {
        this.fullName = fullName;
        this.biography = biography;
        this.bornDate = bornDate;
    }

    public Integer getAge() {
        if(this.bornDate != null){
            return Period.between(this.bornDate, LocalDate.now()).getYears();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Director{" +
                "fullName='" + fullName + '\'' +
                ", image='" + image + '\'' +
                ", biography='" + biography + '\'' +
                ", bornDate=" + bornDate +
                ", age=" + age +
                '}';
    }
}
