package com.nkp.config.utils;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

public class Test {

    public static final String path="/nkp/imgs";

    /**
     *
     * @param file 上传的文件
     * @return
     */
    @ResponseBody
    @RequestMapping("/fileUpload")
    public String upload(@RequestParam("file") MultipartFile file){

        String fileName=file.getOriginalFilename();

        String warning="";
        String newFileName = FileUtils.upload(file, path, fileName);
        if(newFileName != null){

            warning="上传成功";

        }else{
            warning="上传失败";
        }
        System.out.println(warning);
        return warning;
    }

}
