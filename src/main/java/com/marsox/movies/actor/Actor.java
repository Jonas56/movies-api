package com.marsox.movies.actor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "actors")
@NoArgsConstructor
@Getter
@Setter
public class Actor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    @NotEmpty(message = "Actor name cannot be empty")
    private String fullName;
    private String biography;
    private LocalDate bornDate;
    @Transient
    private Integer age;

    public Actor(String fullName, String biography, LocalDate bornDate) {
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
        return "Actor{" +
                ", fullName='" + fullName + '\'' +
                ", biography='" + biography + '\'' +
                ", bornDate=" + bornDate +
                ", age=" + age +
                '}';
    }
}
