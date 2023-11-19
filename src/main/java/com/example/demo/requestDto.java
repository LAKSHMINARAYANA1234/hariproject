package com.example.demo;

import lombok.Data;

import java.io.Serializable;

@Data
public class requestDto implements Serializable,Cloneable {
    private String level;
    private String message;
    private String resourceId;
    private String timestamp;
    private String traceId;
    private String spanId;
    private String commit;
    private requestMetaDataDto metadata;
}
