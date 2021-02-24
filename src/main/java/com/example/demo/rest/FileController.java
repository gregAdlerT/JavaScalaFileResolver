package com.example.demo.rest;

import com.example.demo.service.FileResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.example.demo.api.ApiConstants.*;

/**
 * @author Greg Adler
 */
@RestController
public class FileController {
    @Autowired
    private FileResolver service;
    
    @GetMapping(value = START)
    public void startUpload(){
        service.uploadFile();
    }
    
    @GetMapping(value = PROCESS)
    public void processContent(){
        service.processFile();
    }
}
