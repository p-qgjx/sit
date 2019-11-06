package com.nkp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PageController {

    @RequestMapping("/login")//登录页
    public String login(){
        return "admin/login";
    }

    @RequestMapping("/index")//首页
    public String index(){
        return "admin/index";
    }


    @RequestMapping("/userList")//用户信息列表
    public String accounts(){
        return "admin/accounts";
    }


    @RequestMapping("/signout")//退出
    public String signOut(HttpServletRequest request){
        request.getSession().invalidate();
        return "admin/login";
    }

    @RequestMapping("/activityList")//活动列表
    public String activityList(){
        return "admin/activityList";
    }

    @RequestMapping("/addActivity")//活动添加
    public String addActivity(){
        return "admin/add-activity";
    }

    @RequestMapping("/editActivity")//活动编辑
    public String editActivity(){
        return "admin/edit-activity";
    }



    @RequestMapping("/newsList")//文章列表
    public String products(){
        return "admin/products";
    }

    @RequestMapping("/addNews")//添加文章
    public String addNews(){
        return "admin/add-product";
    }

    @RequestMapping("/editNews")//编辑文章
    public String editProduct(){
        return "admin/edit-product";
    }



    @RequestMapping("/authorList")//作者列表
    public String authorList(){
        return "admin/authorList";
    }

    @RequestMapping("/editAuthor")//编辑作者
    public String editAuthor(){
        return "admin/edit-author";
    }

    @RequestMapping("/addAuthor")//添加作者
    public String addAuthor(){
        return "admin/add-author";
    }



    @RequestMapping("/addProduct")//添加产品
    public String productAdd(){
        return "admin/productAdd";
    }

    @RequestMapping("/editProduct")//编辑产品
    public String productEdit(){
        return "admin/productEdit";
    }


    @RequestMapping("/productList")//产品列表
    public String productsList(){
        return "admin/productsList";
    }


    @RequestMapping("/cooperationList")//合作方列表
    public String cooperationList(){
        return "admin/cooperationList";
    }


    @RequestMapping("/editCooperation")//编辑合作方页面
    public String editCooperation(){
        return "admin/edit";
    }

    @RequestMapping("/addCooperation")//新增合作方页面
    public String addCooperation(){
        return "admin/add-cooperation";
    }

    //手机端页面
    @RequestMapping("/activityPhone")//活动列表
    public String activityListP(){
        return "page/activity-list";
    }

    @RequestMapping("/activityDetails")//活动详情
    public String activityDetails(){
        return "page/activity-details";
    }



    @RequestMapping("/newsListPhone")//新闻列表
    public String newsListPhone(){
        return "page/news-list";
    }

    @RequestMapping("/articleDetails")//文章详情
    public String articleDetails(){
        return "page/article-details";

    }

    @RequestMapping("/authorListPhone")//作者列表
    public String authorListPhone(){
        return "page/author-list";

    }

    @RequestMapping("/authorDetails")//作者详情
    public String authorDetails(){
        return "page/author-details";

    }



    @RequestMapping("/surplusticketlist")//盈票列表
    public String surplusticketlist(){
        return "page/surplusticket-list";

    }

    @RequestMapping("/surplusticketdetails")//盈票详情
    public String surplusticketdetails(){
        return "page/surplusticket-details";

    }

    @RequestMapping("/search")//搜
    public String search(){
        return "page/search";

    }
    @RequestMapping("/result")//结果
    public String result(){
        return "page/result";

    }
    @RequestMapping("/addUser")//增加用户
    public String addUser(){
        return "admin/add-userList";
    }

    @RequestMapping("/editUser")//编辑用户
    public String editUser(){
        return "admin/edit-userList";
    }

    @RequestMapping("/footer")//编辑用户
    public String footer(){
        return "admin/footer";
    }









}
