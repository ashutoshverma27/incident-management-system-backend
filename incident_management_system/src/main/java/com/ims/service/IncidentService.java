package com.ims.service;

import com.ims.dao.IncidentDao;
import com.ims.entity.Incident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class IncidentService {

    @Autowired
    private IncidentDao incidentDao;

    @Transactional
    public Incident saveIncident(Incident incident) {
        return incidentDao.save(incident);
    }

    @Transactional(readOnly = true)
    public Incident findIncidentById(String id) {
        return incidentDao.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Incident> findAllIncidents() {
        return incidentDao.findAll();
    }

    @Transactional
    public Incident updateIncident(Incident incident) {
        return incidentDao.update(incident);
    }

    @Transactional
    public void deleteIncidentById(String id) {
        incidentDao.deleteById(id);
    }
}