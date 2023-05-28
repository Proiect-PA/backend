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
    public ResponseEntity<Void> postAlbums(@RequestBody String fileContent) throws IOException, CsvException {
        csvParser.parseTracks(fileContent);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
