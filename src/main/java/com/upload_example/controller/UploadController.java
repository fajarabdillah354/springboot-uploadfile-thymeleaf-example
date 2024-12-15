package com.upload_example.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {

    private static String uploaded_file = "D:\\myProgramming\\myJavaProgramming\\springboot-upload-example\\upload\\";

    @GetMapping("/")
    public String index(){
        return "index";
    }


    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes){

        if (file.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "please select file to upload");
            return "redirect:status";
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploaded_file + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message", "uploaded file successfully"+file.getOriginalFilename());
        }catch (IOException e){
            e.printStackTrace();
        }

        return "redirect:status";
    }


    @GetMapping("/status")
    public String uploadStatus(){
        return "status";
    }


}
