package com.company.utils;

import com.company.exception.ReadException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FromFileFiller implements Readable {

    private final String path;

    public FromFileFiller(String path) {
        this.path = path;
    }

    @Override
    public String toRead() throws ReadException {

        String text;
        try {
            text = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ReadException("Reading exception from path: " + path);
        }

        if (text.isEmpty()) {
            text = "";
        }
        return text;

    }
}
