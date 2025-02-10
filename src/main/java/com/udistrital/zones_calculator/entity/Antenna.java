package com.udistrital.zones_calculator.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Antenna {
    private double power; // in watts
    private double height; // in meters
    private double gain; // in linear scale

}
