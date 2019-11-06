package com.nkp.controller;

import com.nkp.config.utils.DataPackJSON;
import com.nkp.pojo.Author;
import com.nkp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/author")
@CrossOrigin
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @RequestMapping(value = "/add")
    public DataPackJSON add(HttpServletRequest request, Author author){
        return authorService.add(request,author);
    }
    @RequestMapping("/del")
    public DataPackJSON del(HttpServletRequest request, String ids){
        return authorService.del(request,ids);
    }
    @RequestMapping("/up")
    public DataPackJSON up(HttpServletRequest request, Author author){
        return authorService.up(request,author);
    }

    @RequestMapping("/selById")
    public DataPackJSON selById(HttpServletRequest request,int id,int pageNum,int pageSize){
        return authorService.selById(request,id,pageNum,pageSize);
    }

    @RequestMapping("/selAll")
    public DataPackJSON selAll(HttpServletRequest request){
        return authorService.selAll(request);
    }

    @RequestMapping("/pagingSel")
    public DataPackJSON pagingSel(HttpServletRequest request,int pageNum,int pageSize,Integer id,String condition, Integer type, Date date){
        return authorService.pagingSel(request,pageNum,pageSize,id,condition,type,date);
    }

    @RequestMapping("/selById1")
    public DataPackJSON selById1(HttpServletRequest request,int id){
        return authorService.selById1(request,id);
    }
}
