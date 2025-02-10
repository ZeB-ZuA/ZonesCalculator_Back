package com.udistrital.zones_calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udistrital.zones_calculator.entity.Antenna;
import com.udistrital.zones_calculator.entity.Person;
import com.udistrital.zones_calculator.entity.ZoneRequest;
import com.udistrital.zones_calculator.entity.ZoneSummary;
import com.udistrital.zones_calculator.service.ZoneService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/zones")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;

    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

@PostMapping("/calculate")
public ResponseEntity<?> getZones(@RequestBody ZoneRequest zoneRequest) {
    Antenna antenna = zoneRequest.getAntenna();
    Person person = zoneRequest.getPerson();
    System.out.println("calculating zones");
    double complianceZone = zoneService.getComplianceZone(antenna.getPower(), antenna.getGain());
    double occupationalZone = zoneService.getOccupationalZone(antenna.getPower(), antenna.getGain());
    double horizontalDistanceCompliance = zoneService.getHorizontalDistance(complianceZone, antenna, person);
    double horizontalDistanceOccupational = zoneService.getHorizontalDistance(occupationalZone, antenna, person);

    ZoneSummary zoneSummary = new ZoneSummary(complianceZone, occupationalZone, horizontalDistanceCompliance, horizontalDistanceOccupational);

    return ResponseEntity.ok(zoneSummary);
}




    
}
