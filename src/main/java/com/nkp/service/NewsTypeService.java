package com.nkp.service;

import com.nkp.config.utils.DataPackJSON;
import com.nkp.config.utils.NewDateTime;
import com.nkp.dao.NewsMapper;
import com.nkp.dao.NewsTypeMapper;
import com.nkp.pojo.NewsType;
import com.nkp.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsTypeService {
    @Autowired
    private NewsTypeMapper newsTypeMapper;
    @Autowired
    private NewsMapper newsMapper;


    public DataPackJSON add(HttpServletRequest request, NewsType newsType) throws ParseException {
        DataPackJSON dataPackJSON=new DataPackJSON();
        if("".equals(newsType.getName())){
            dataPackJSON.setFlag(1);
            dataPackJSON.setMsg("请输入内容");
            return dataPackJSON;
        }
        String str=newsTypeMapper.selByName(newsType.getName());
        if(str!=null){
            dataPackJSON.setFlag(1);
            dataPackJSON.setMsg("内容重复");
            return dataPackJSON;
        }
        int max=newsTypeMapper.newsTypeMax()==null?0:newsTypeMapper.newsTypeMax();
        newsType.setTypeid(max+1);
        newsType.setCreattime(NewDateTime.getDateTime("yyyy-MM-dd :hh:mm:ss"));
        int res=newsTypeMapper.insertSelective(newsType);

        if(res==1){
            dataPackJSON.setFlag(0);
            dataPackJSON.setMsg("SUCCESS");
            return dataPackJSON;
        }
        dataPackJSON.setFlag(1);
        dataPackJSON.setMsg("ERROR");
        return dataPackJSON;
    }
    public DataPackJSON del(HttpServletRequest request, int id){
        DataPackJSON dataPackJSON=new DataPackJSON();
        List list=newsMapper.selByTypeId(newsTypeMapper.selectByPrimaryKey(id).getTypeid());
        if(list!=null){
            dataPackJSON.setFlag(1);
            dataPackJSON.setMsg("此栏目下还有新闻");
            return dataPackJSON;

        }
        int res=newsTypeMapper.deleteByPrimaryKey(id);

        if(res==1){
            dataPackJSON.setFlag(0);
            dataPackJSON.setMsg("SUCCESS");
            return dataPackJSON;
        }
        dataPackJSON.setFlag(1);
        dataPackJSON.setMsg("ERROR");
        return dataPackJSON;
    }

    public DataPackJSON up(HttpServletRequest request, NewsType newsType){
        int res=newsTypeMapper.updateByPrimaryKeySelective(newsType);
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

    public DataPackJSON selById(HttpServletRequest request,int id){
        NewsType newsType=newsTypeMapper.selectByPrimaryKey(id);
        DataPackJSON dataPackJSON=new DataPackJSON();
        Map map=new HashMap();
        HttpSession session = request.getSession();
        map.put("newsType",newsType);
        map.put("session_user",(UserInfo) session.getAttribute("session_user"));
        dataPackJSON.setMap(map);
        dataPackJSON.setFlag(0);
        dataPackJSON.setMsg("SUCCESS");
        return dataPackJSON;
    }
    public DataPackJSON selAll(HttpServletRequest request){
        List list=newsTypeMapper.selAll();
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

}
