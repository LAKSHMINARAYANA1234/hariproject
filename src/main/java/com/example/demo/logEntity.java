package com.example.demo;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class logEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String message;
    private String resourceId;
    private Timestamp timestamp;
    private String traceId;
    private String spanId;
    private String commit;
    private String metadata;
}
