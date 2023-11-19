package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
public class MainController {
    @Autowired
    private logIngestorService logIngestorService;
    @PostMapping("/submit")
    public ResponseEntity<String> logIngestor(@RequestBody requestDto requestDto){
        System.out.println("processing data");
        logIngestorService.data_insertion(requestDto);
      return ResponseEntity.ok("success");
    }
    @PostMapping("/search")
    public ResponseEntity<List<logEntity>> searchData(@RequestBody requestDto requestDto1){
        System.out.println(requestDto1.getLevel());
        return  ResponseEntity.ok(logIngestorService.search(requestDto1));
    }
}
