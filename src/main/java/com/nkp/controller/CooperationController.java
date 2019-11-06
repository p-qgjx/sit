package com.nkp.controller;

import com.nkp.config.utils.DataPackJSON;
import com.nkp.pojo.Cooperation;
import com.nkp.service.CooperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cooperation")
@CrossOrigin
public class CooperationController {
    @Autowired
    private CooperationService cooperationService;

    @RequestMapping("/add")
    public DataPackJSON add(HttpServletRequest request, Cooperation cooperation){
        return cooperationService.add(request,cooperation);
    }
    @RequestMapping("/del")
    public DataPackJSON del(HttpServletRequest request, String ids){
        return cooperationService.del(request,ids);
    }
    @RequestMapping("/up")
    public DataPackJSON up(HttpServletRequest request, Cooperation cooperation){
        return cooperationService.up(request,cooperation);
    }

    @RequestMapping("/selById")
    public DataPackJSON selById(HttpServletRequest request,int id){
        return cooperationService.selById(request,id);
    }

    @RequestMapping("/selAll")
    public DataPackJSON selAll(HttpServletRequest request){
        return cooperationService.selAll(request);
    }

    @RequestMapping("/pagingSel")
    public DataPackJSON pagingSel(HttpServletRequest request,int pageNum,int pageSize,Integer id){
        return cooperationService.pagingSel(request,pageNum,pageSize,id);
    }
}
