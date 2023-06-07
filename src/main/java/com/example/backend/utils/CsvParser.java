package com.example.backend.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;


@Service
public class CsvParser {

    private final DataSaving dataSaving;

    public CsvParser(DataSaving dataSaving) {
        this.dataSaving = dataSaving;
    }

    public void parseTracks(String fileContent) throws IOException, CsvException {

        File file = new File("./data/csv_tracks.csv");
        try (OutputStream outStream = new FileOutputStream(file)) {
            outStream.write(fileContent.getBytes());
        }

        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            List<String[]> res = reader.readAll();

            int part = res.size() / 4;
            List<String[]> res1 = res.subList(0, part);
            List<String[]> res2 = res.subList(part, 2 * part);
            List<String[]> res3 = res.subList(2 * part, 3 * part);
            List<String[]> res4 = res.subList(3 * part, res.size());

            Thread th1 = new Thread(() -> dataSaving.saveTracks(res1));
            Thread th2 = new Thread(() -> dataSaving.saveTracks(res2));
            Thread th3 = new Thread(() -> dataSaving.saveTracks(res3));
            Thread th4 = new Thread(() -> dataSaving.saveTracks(res4));

            th1.start();
            th2.start();
            th3.start();
            th4.start();

        }
    }


}
