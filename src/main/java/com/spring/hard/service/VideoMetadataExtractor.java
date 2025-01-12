package com.spring.hard.service;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public class VideoMetadataExtractor {

    public Metadata extractMetadata(MultipartFile multipartFile) {
        Metadata metadata = new Metadata();
        try (InputStream inputStream = multipartFile.getInputStream()) {
            AutoDetectParser parser = new AutoDetectParser();
            BodyContentHandler handler = new BodyContentHandler();
            parser.parse(inputStream, handler, metadata, new ParseContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return metadata;
    }
}

