package com.example.demo;

import lombok.Data;

import java.io.Serializable;

@Data
public class requestMetaDataDto implements Serializable {
    private String parentResourceId;
}
