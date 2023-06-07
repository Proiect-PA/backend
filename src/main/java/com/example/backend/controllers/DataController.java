package com.example.backend.controllers;

import com.example.backend.utils.CsvParser;
import com.opencsv.exceptions.CsvException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/data")
public class DataController {

    private final CsvParser csvParser;

    public DataController(CsvParser csvParser) {
        this.csvParser = csvParser;
    }

    @PostMapping(value="/csv/tracks")
    public ResponseEntity<Void> postTracks(@RequestBody String fileContent) {
        try {
            csvParser.parseTracks(fileContent);
        } catch (IOException | CsvException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
