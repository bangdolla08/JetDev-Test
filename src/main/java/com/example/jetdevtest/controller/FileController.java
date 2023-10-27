package com.example.jetdevtest.controller;

import com.example.jetdevtest.exception.NotAcceptableMediaException;
import com.example.jetdevtest.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    private static final List<String> contentTypes = Arrays.asList("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
            "text/csv",
            "application/vnd.ms-excel");

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> custom(@RequestParam("file") MultipartFile file) {
        String fileContentType = file.getContentType();
        if (!contentTypes.contains(fileContentType)||file.isEmpty()) {
            throw new NotAcceptableMediaException("wrong file in please enter the correct file");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
