package com.configuration.machine.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OwnerDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String emial;

    private String phoneNumber;

}
