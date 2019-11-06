package com.nkp.controller;

import com.nkp.config.utils.DataPackJSON;
import com.nkp.pojo.Product;
import com.nkp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/add")
    public DataPackJSON add(HttpServletRequest request, Product product){
        return productService.add(request,product);
    }
    @RequestMapping("/del")
    public DataPackJSON del(HttpServletRequest request, String ids){
        return productService.del(request,ids);
    }
    @RequestMapping("/up")
    public DataPackJSON up(HttpServletRequest request, Product product){
        return productService.up(request,product);
    }

    @RequestMapping("/selById")
    public DataPackJSON selById(HttpServletRequest request,int id){
        return productService.selById(request,id);
    }

    @RequestMapping("/selAll")
    public DataPackJSON selAll(HttpServletRequest request){
        return productService.selAll(request);
    }

    @RequestMapping("/pagingSel")
    public DataPackJSON pagingSel(HttpServletRequest request,int pageNum,int pageSize,Integer id,String condition, Integer type, Date date){
        return productService.pagingSel(request,pageNum,pageSize,id,id,condition,type,date);
    }

}
