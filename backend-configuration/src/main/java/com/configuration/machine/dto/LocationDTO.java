package com.configuration.machine.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LocationDTO {

    private String street;
    private String city;
    private String description;
    private Long id;
    private Long ownerId;
    private Integer streetNumber;

}
