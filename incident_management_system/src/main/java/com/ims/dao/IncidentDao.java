package com.ims.dao;

import com.ims.entity.Incident;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class IncidentDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Incident save(Incident incident) {
        entityManager.persist(incident);
        return incident;
    }

    public Incident findById(String id) {
        return entityManager.find(Incident.class, id);
    }

    public List<Incident> findAll() {
        return entityManager.createQuery("SELECT i FROM Incident i", Incident.class).getResultList();
    }

    public Incident update(Incident incident) {
        return entityManager.merge(incident);
    }

    public void deleteById(String id) {
        Incident incident = findById(id);
        if (incident != null) {
            entityManager.remove(incident);
        }
    }
}