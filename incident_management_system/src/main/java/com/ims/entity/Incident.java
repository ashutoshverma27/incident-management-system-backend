package com.ims.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Entity
public class Incident {
    public enum UserType {
        INDIVIDUAL("Individual"),
        ENTERPRISE("Enterprise"),
        GOVERNMENT("Government");
    
        private final String label;
    
        UserType(String label) {
            this.label = label;
        }
    
        public String getLabel() {
            return this.label;
        }
    }
    
    public enum Priority {
        LOW("Low"),
        MEDIUM("Medium"),
        HIGH("High");
    
        private final String label;
    
        Priority(String label) {
            this.label = label;
        }
    
        public String getLabel() {
            return this.label;
        }
    }

    public enum Status {
        OPEN("Open"),
        IN_PROGRESS("In Progress"),
        CLOSED("Closed");

        private final String label;

        Status(String label) {
            this.label = label;
        }

        public String getLabel() {
            return this.label;
        }
    }

    @Id
    private String incidentId;
    @Column(name = "userType")
    private String userType;
    @ManyToOne
    @JoinColumn(name = "reporterId")
    private User reporter;
    @Column(name = "incidentDetails")
    private String incidentDetails;
    @Column(name = "reportedOn")
    private LocalDateTime reportedOn;
    @Column(name = "updatedOn")
    private LocalDateTime updatedOn;
    @Column(name = "priority")
    private String priority;
    @Column(name = "status")
    private String status;

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getIncidentDetails() {
        return incidentDetails;
    }

    public void setIncidentDetails(String incidentDetails) {
        this.incidentDetails = incidentDetails;
    }

    public LocalDateTime getReportedOn() {
        return reportedOn;
    }

    public void setReportedOn(LocalDateTime reportedOn) {
        this.reportedOn = reportedOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @PrePersist
    private void generateIncidentId() {
        this.incidentId = "RMG" + generateRandomDigits(5) + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy"));
    }

    private String generateRandomDigits(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

}