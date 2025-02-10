package com.udistrital.zones_calculator.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.udistrital.zones_calculator.entity.Antenna;
import com.udistrital.zones_calculator.entity.Person;

@Service
public class ZoneService {

    public Double getComplianceZone(double power, double gain, double frequency) {
        double densityLimit = getDensityLimit(frequency, "COMPLIANCE");
        double result = Math.sqrt((power * gain) / (4 * Math.PI * densityLimit));
        return roundToTwoDecimals(result);
    }

    public Double getOccupationalZone(double power, double gain, double frequency) {
        double densityLimit = getDensityLimit(frequency, "OCCUPATIONAL");
        double result = Math.sqrt((power * gain) / (4 * Math.PI * densityLimit));
        return roundToTwoDecimals(result);
    }

    public Double getHorizontalDistance(Double totalDistance, Antenna antenna, Person person) {
        double heightDifference = Math.abs(person.getHeight() - antenna.getHeight());
    
        System.out.println("Total Distance (Z): " + totalDistance);
        System.out.println("Height Difference (h): " + heightDifference);
    
        if (totalDistance < heightDifference) {
            System.out.println("⚠️ Zona no toca el suelo, distancia horizontal = 0");
            return 0.0;  // La zona no alcanza el suelo
        }
    
        double result = Math.sqrt(Math.pow(totalDistance, 2) - Math.pow(heightDifference, 2));
        return roundToTwoDecimals(result);
    }
    
    

    private Double roundToTwoDecimals(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private double getDensityLimit(double frequency, String zoneType) {
        double density;
        if (frequency < 3) { // < 3 MHz
            density = zoneType.equals("COMPLIANCE") ? 100 : 500;
        } else if (frequency < 300) { // 3 MHz - 300 MHz
            density = zoneType.equals("COMPLIANCE") ? 10 : 50;
        } else { // > 300 MHz
            density = zoneType.equals("COMPLIANCE") ? 1 : 5;
        }
        System.out.println("⚙️ Frequency: " + frequency + " MHz → Density Limit for " + zoneType + ": " + density);
        return density;
    }
    
}
