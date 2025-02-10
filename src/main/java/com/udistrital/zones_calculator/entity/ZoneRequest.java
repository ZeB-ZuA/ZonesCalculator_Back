package com.udistrital.zones_calculator.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ZoneRequest {
    private Antenna antenna;
    private Person person;
    
}
