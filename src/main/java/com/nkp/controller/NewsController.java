package com.nkp.controller;

import com.nkp.config.utils.DataPackJSON;
import com.nkp.pojo.News;
import com.nkp.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;

@RestController
@RequestMapping("/news")
@CrossOrigin
public class NewsController {
    @Autowired
    private NewsService newsService;

    @RequestMapping("/add")
    public DataPackJSON add(HttpServletRequest request, News news) throws ParseException {
        return newsService.add(request,news);
    }
    @RequestMapping("/del")
    public DataPackJSON del(HttpServletRequest request, String ids){
        return newsService.del(request,ids);
    }
    @RequestMapping("/up")
    public DataPackJSON up(HttpServletRequest request,  Integer newsid,Integer newstype,String title,Integer author,String toppicture,String keyword,
    String remarks,String content) throws ParseException {
        News news=new News();
        news.setNewsid(newsid);
        news.setNewstype(newstype);
        news.setTitle(title);
        news.setAuthor(author);
        news.setToppicture(toppicture);
        news.setKeyword(keyword);
        news.setRemarks(remarks);
        news.setContent(content);
        return newsService.up(request,news);
    }

    @RequestMapping("/selById")
    public DataPackJSON selById(HttpServletRequest request,int id){
        return newsService.selById(request,id);
    }

    @RequestMapping("/selAll")
    public DataPackJSON selAll(HttpServletRequest request,Integer id){
        return newsService.selAll(request,id);
    }

    @RequestMapping("/pagingSel")
    public DataPackJSON pagingSel(HttpServletRequest request, int pageNum, int pageSize, Integer id, String condition, Integer type, Date date){
        return newsService.pagingSel(request,pageNum,pageSize,id,condition,type,date);
    }
    //得到作者与新闻分类的下拉
    @RequestMapping("/getAT")
    public DataPackJSON getAT(HttpServletRequest request){
        return newsService.getTypeAndAuthor(request);
    }

    @RequestMapping("/hide")
    public DataPackJSON hide(HttpServletRequest request,String ids){
        return newsService.hide(request,ids);
    }

    @RequestMapping("/hide1")
    public DataPackJSON hide1(HttpServletRequest request,Integer id,Integer flag){
        return newsService.hide1(request,id,flag);
    }
    //手机端查新闻详情，含作者名称
    @RequestMapping("/details")
    public DataPackJSON details(HttpServletRequest request,int id){
        return newsService.details(request,id);
    }
    //最热新闻
    @RequestMapping("/number")
    public DataPackJSON number(HttpServletRequest request){
        return newsService.number(request);
    }

    //手机端查新闻列表
    @RequestMapping("/byType")
    public DataPackJSON byType(HttpServletRequest request,int pageNum,int pageSize,Integer newsType){
        return newsService.byType(request,pageNum,pageSize,newsType);
    }

    //模糊查询新闻
    @RequestMapping("/like")
    public DataPackJSON like(HttpServletRequest request,int pageNum,int pageSize,String str){
        return newsService.like(request,pageNum,pageSize,str);
    }

    //热搜词
    @RequestMapping("/hotSearch")
    public DataPackJSON hotSearch(HttpServletRequest request){
        return newsService.hotSearch(request);
    }

    @RequestMapping("/roof")
    public DataPackJSON roof(HttpServletRequest request,Integer id){
        return newsService.roof(request,id);
    }

}
