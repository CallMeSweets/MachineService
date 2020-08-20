package com.configuration.machine.models;

import com.configuration.machine.enums.MachineType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@ToString
@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Entity(name = "MACHINES")
public class Machine {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "LOCATION_ID")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private Owner owner;

    @Column(name = "MACHINE_TYPE_ID")
    private MachineType machineType;

    @JsonBackReference
    @OneToMany(mappedBy = "machine", cascade = CascadeType.ALL)
    private Set<MachineProducts> machineProducts;

}
