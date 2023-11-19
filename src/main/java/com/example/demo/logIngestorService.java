package com.example.demo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class logIngestorService {
    @Autowired
    private logRepository logRepository;
    @PersistenceContext
    private EntityManager entityManager;
    public String data_insertion(requestDto requestDto){
        logEntity logEntity = new logEntity();
        logEntity.setMessage(requestDto.getMessage());
        logEntity.setCommit(requestDto.getCommit());
        logEntity.setResourceId(requestDto.getResourceId());
        logEntity.setSpanId(requestDto.getSpanId());
        logEntity.setMetadata(requestDto.getMetadata().getParentResourceId());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

        logEntity.setTimestamp(null);
        logEntity.setTraceId(requestDto.getTraceId());
        logEntity = logRepository.save(logEntity);
        System.out.println(logEntity.getId());
        return null;
    }

    public List<logEntity> search(requestDto requestDto){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(logEntity.class);
        Root root = cq.from(logEntity.class);
        Predicate predicate = cb.conjunction();
        if(requestDto.getMessage() != null){
            predicate = cb.and(predicate, cb.like(root.get("message"),requestDto.getMessage()));
        }
        if (requestDto.getMetadata() != null && requestDto.getMetadata().getParentResourceId() != null){
            predicate = cb.and(predicate,cb.equal(root.get("metadata"),requestDto.getMetadata().getParentResourceId()));
        }
        if(requestDto.getSpanId() != null){
            predicate = cb.and(predicate, cb.like(root.get("spanId"),requestDto.getSpanId()));
        }
        if(requestDto.getTimestamp() != null){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
            Timestamp timestamp = null;
        }
        if(requestDto.getCommit() != null){
            predicate = cb.and(predicate,cb.equal(root.get("commit"),requestDto.getCommit()));
        }
        cq.where(predicate);
        return entityManager.createQuery(cq).getResultList();
    }
}
