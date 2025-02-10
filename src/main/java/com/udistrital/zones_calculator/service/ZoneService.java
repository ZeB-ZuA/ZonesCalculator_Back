package com.udistrital.zones_calculator.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.udistrital.zones_calculator.entity.Antenna;
import com.udistrital.zones_calculator.entity.Person;

@Service
public class ZoneService {

 public Double getComplianceZone(double power, double gain) {
        double result = Math.sqrt((power * gain) / (4 * Math.PI * 0.01));
        return roundToTwoDecimals(result);
    }

    public Double getOccupationalZone(double power, double gain) {
        double result = Math.sqrt((power * gain) / (4 * Math.PI * 0.1));
        return roundToTwoDecimals(result);
    }

    public Double getHorizontalDistance(Double totalDistance, Antenna antenna, Person person) {
        if (Math.pow(totalDistance, 2) < Math.pow(person.getHeight() - antenna.getHeight(), 2)) {
            throw new IllegalArgumentException("La altura de la antena deve ser mayor a la altura de la persona");
        }
        double result = Math.sqrt(Math.pow(totalDistance, 2) - Math.pow(person.getHeight() - antenna.getHeight(), 2));
        return roundToTwoDecimals(result);
    }

    private Double roundToTwoDecimals(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
