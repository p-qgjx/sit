package com.nkp.controller;

import com.nkp.config.utils.DataPackJSON;
import com.nkp.pojo.NewsType;
import com.nkp.service.NewsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@RestController
@RequestMapping("/newsType")
@CrossOrigin
public class NewsTypeController {
    @Autowired
    private NewsTypeService newsTypeService;

    @RequestMapping("/add")
    public DataPackJSON add(HttpServletRequest request, NewsType newsType) throws ParseException {
        return newsTypeService.add(request,newsType);
    }
    @RequestMapping("/del")
    public DataPackJSON del(HttpServletRequest request, int id){
        return newsTypeService.del(request,id);
    }
    @RequestMapping("/up")
    public DataPackJSON up(HttpServletRequest request, NewsType newsType){
        return newsTypeService.up(request,newsType);
    }

    @RequestMapping("/selById")
    public DataPackJSON selById(HttpServletRequest request,int id){
        return newsTypeService.selById(request,id);
    }

    @RequestMapping("/selAll")
    public DataPackJSON selAll(HttpServletRequest request){
        return newsTypeService.selAll(request);
    }
}
