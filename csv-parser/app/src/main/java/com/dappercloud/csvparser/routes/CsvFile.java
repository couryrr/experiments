package com.dappercloud.csvparser.routes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CsvFile
 */
@RestController
@RequestMapping("file")
public class CsvFile {
    @GetMapping("csv")
    public void CsvFileUpload() {
        System.out.println("Test!");
    }

}