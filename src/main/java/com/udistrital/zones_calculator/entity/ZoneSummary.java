package com.udistrital.zones_calculator.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ZoneSummary {
    private double complianceZoneRadius;
    private double occupationalZoneRadius;
    private double horizontalComplianceZoneRadius;
    private double horizontalOccupationalZoneRadius;
}