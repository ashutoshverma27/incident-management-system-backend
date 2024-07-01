package com.ims.controller;

import com.ims.entity.Incident;
import com.ims.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/incidents")
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    @PostMapping("/create")
    public ResponseEntity<Incident> createIncident(@RequestBody Incident incident) {
        Incident newIncident = incidentService.saveIncident(incident);
        return ResponseEntity.status(HttpStatus.OK).body(newIncident);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getIncidentById(@PathVariable String id) {
        Incident incident = incidentService.findIncidentById(id);
        return incident != null ? ResponseEntity.status(HttpStatus.OK).body(incident) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Incident not found.");
    }

    @GetMapping
    public ResponseEntity<List<Incident>> getAllIncidents() {
        List<Incident> incidents = incidentService.findAllIncidents();
        return ResponseEntity.status(HttpStatus.OK).body(incidents);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateIncident(@PathVariable String id, @RequestBody Incident incidentBody) {
        Incident incident = incidentService.findIncidentById(id);

        if (incident == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Incident not found.");
        }

        if (incident.getStatus().equals("Closed")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incident is closed. Cannot update.");
        }

        if (incidentBody.getIncidentDetails() == null && incidentBody.getPriority() == null && incidentBody.getStatus() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is nothing to update. Please provide incident details, priority or status.");
        }
        
        if (incidentBody.getIncidentDetails() != null) {
            incident.setIncidentDetails(incidentBody.getIncidentDetails());
        }
        if (incidentBody.getPriority() != null) {
            incident.setPriority(incidentBody.getPriority());
        }
        if(incidentBody.getStatus() != null) {
            incident.setStatus(incidentBody.getStatus());
        }

        incident.setUpdatedOn(LocalDateTime.now());
        Incident updatedIncident = incidentService.updateIncident(incident);

        return ResponseEntity.status(HttpStatus.OK).body(updatedIncident);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteIncident(@PathVariable String id) {
        Incident incident = incidentService.findIncidentById(id);
        if (incident == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Incident not found.");
        }

        incidentService.deleteIncidentById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Incident deleted successfully.");
    }
}