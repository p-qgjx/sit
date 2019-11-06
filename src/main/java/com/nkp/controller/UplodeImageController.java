package com.nkp.controller;

import com.nkp.config.utils.OSSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/uplode")
@CrossOrigin
public class UplodeImageController {
    @Autowired
    private OSSClientUtil ossClientUtil;

    @RequestMapping("/image")
    public String getUrl(MultipartFile file){
        String imageUrl = ossClientUtil.checkImage(file);
        return imageUrl.split("[?]")[0];

    }

}
