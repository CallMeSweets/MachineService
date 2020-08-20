package com.configuration.machine.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity(name = "LOCATIONS")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STREET")
    private String street;

    @Column(name = "STREET_NUMBER")
    private Integer streetNumber;

    @Column(name = "DESCRIPTION")
    private String description;

    @JsonBackReference
    @OneToMany(mappedBy = "location")
    private Set<Machine> machines;

}
