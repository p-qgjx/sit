package com.nkp.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nkp.config.utils.DataPackJSON;
import com.nkp.config.utils.NewDateTime;
import com.nkp.dao.AuthorMapper;
import com.nkp.dao.NewsMapper;
import com.nkp.pojo.Author;
import com.nkp.pojo.News;
import com.nkp.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthorService {
    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private NewsMapper newsMapper;

    public DataPackJSON add(HttpServletRequest request, Author author){
        try {
            author.setCreatetime(NewDateTime.getDateTime("yyyy-MM-dd :HH:mm:ss"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int res=authorMapper.insertSelective(author);
        DataPackJSON dataPackJSON=new DataPackJSON();
        if(res==1){
            dataPackJSON.setFlag(0);
            dataPackJSON.setMsg("SUCCESS");
            return dataPackJSON;
        }
        dataPackJSON.setFlag(1);
        dataPackJSON.setMsg("ERROR");
        return dataPackJSON;
    }
    public DataPackJSON del(HttpServletRequest request, String ids){
        int res=0;
        String[] id=ids.split(",");
        for(String i:id){
            res=+authorMapper.deleteByPrimaryKey(Integer.valueOf(i));
        }

        DataPackJSON dataPackJSON=new DataPackJSON();
        if(res!=0){
            dataPackJSON.setFlag(0);
            dataPackJSON.setMsg("SUCCESS");
            return dataPackJSON;
        }
        dataPackJSON.setFlag(1);
        dataPackJSON.setMsg("ERROR");
        return dataPackJSON;
    }

    public DataPackJSON up(HttpServletRequest request, Author author){
        int res=authorMapper.updateByPrimaryKeySelective(author);
        DataPackJSON dataPackJSON=new DataPackJSON();
        if(res==1){
            dataPackJSON.setFlag(0);
            dataPackJSON.setMsg("SUCCESS");
            return dataPackJSON;
        }
        dataPackJSON.setFlag(1);
        dataPackJSON.setMsg("ERROR");
        return dataPackJSON;
    }

    public DataPackJSON selById(HttpServletRequest request,int id,int pageNum,int pageSize){
        Author author=authorMapper.selectByPrimaryKey1(id);

        PageHelper.startPage(pageNum,pageSize);
        List<News> list=newsMapper.getNews(author.getId());
        PageInfo<News> pageInfo = new PageInfo<>(list);
        List pageList = pageInfo.getList();
        DataPackJSON dataPackJSON=new DataPackJSON();

        dataPackJSON.setNumber((int)pageInfo.getTotal());

        Map map=new HashMap();
        map.put("author",author);
        map.put("pageList",pageList);
        dataPackJSON.setMap(map);
        dataPackJSON.setFlag(0);
        dataPackJSON.setMsg("SUCCESS");
        return dataPackJSON;
    }
    public DataPackJSON selAll(HttpServletRequest request){
        List list=authorMapper.selAll();
        DataPackJSON dataPackJSON=new DataPackJSON();
        Map map=new HashMap();
        HttpSession session = request.getSession();
        map.put("list",list);
        map.put("session_user",(UserInfo) session.getAttribute("session_user"));
        dataPackJSON.setMap(map);
        dataPackJSON.setFlag(0);
        dataPackJSON.setMsg("SUCCESS");
        dataPackJSON.setNumber(list.size());
        return dataPackJSON;
    }

    public DataPackJSON pagingSel(HttpServletRequest request, int pageNum, int pageSize, Integer id, String condition, Integer type, Date date) {
        DataPackJSON dataPackJSON=new DataPackJSON();
        Map map=new HashMap();
        HttpSession session = request.getSession();

        PageHelper.startPage(pageNum,pageSize);

        List<Author> list=authorMapper.selLike(condition,type,date);
        for(Author author:list){
            Integer i=newsMapper.selByAuthor(author.getId());
            i=(i==null?0:i);
            author.setRemarks(i.toString());
        }

        //得到分页的结果对象
        PageInfo<Author> pageInfo = new PageInfo<>(list);
        //得到分页中的person条目对象(分页后的list)
        List pageList = pageInfo.getList();

        dataPackJSON.setNumber((int)pageInfo.getTotal());
        dataPackJSON.setFlag(0);
        dataPackJSON.setMsg("SUCCESS");

        map.put("pageList",pageList);
        map.put("session_user",(UserInfo) session.getAttribute("session_user"));
        dataPackJSON.setMap(map);
        return dataPackJSON;

    }

    public DataPackJSON selById1(HttpServletRequest request, int id) {
        Author author=authorMapper.selectByPrimaryKey1(id);
        DataPackJSON dataPackJSON=new DataPackJSON();
        Map map=new HashMap();
        HttpSession session = request.getSession();
        map.put("activity",author);
        map.put("session_user",(UserInfo) session.getAttribute("session_user"));
        dataPackJSON.setMap(map);
        dataPackJSON.setFlag(0);
        dataPackJSON.setMsg("SUCCESS");
        return dataPackJSON;
    }
}
