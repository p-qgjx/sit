package com.nkp.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nkp.config.utils.DataPackJSON;
import com.nkp.config.utils.NewDateTime;
import com.nkp.dao.BannerMapper;
import com.nkp.dao.EnrollMapper;
import com.nkp.dao.WorkMapper;
import com.nkp.pojo.Banner;
import com.nkp.pojo.WorkWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.*;

@RestController
@RequestMapping("/banner")
@CrossOrigin
public class BannerController {
    @Autowired
    private BannerMapper bannerMapper;

    @RequestMapping("/add")
    public DataPackJSON add(HttpServletRequest request, Banner banner) throws ParseException {
        int res=bannerMapper.insertSelective(banner);
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

    @RequestMapping("/del")
    public DataPackJSON del(HttpServletRequest request, String ids){
        int res=0;
        String[] id=ids.split(",");
        for(String i:id){
            res=+bannerMapper.deleteByPrimaryKey(Integer.valueOf(i));
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

    @RequestMapping("/up")
    public DataPackJSON up(HttpServletRequest request, Banner banner){
        int res=bannerMapper.updateByPrimaryKeySelective(banner);
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

    @RequestMapping("/selById")
    public DataPackJSON selById(HttpServletRequest request,int id){
        Banner banner=bannerMapper.selectByPrimaryKey(id);
        DataPackJSON dataPackJSON=new DataPackJSON();
        Map map=new HashMap();
        HttpSession session = request.getSession();
        map.put("banner",banner);
        //map.put("session_user",(UserInfo) session.getAttribute("session_user"));
        dataPackJSON.setMap(map);
        dataPackJSON.setFlag(0);
        dataPackJSON.setMsg("SUCCESS");
        return dataPackJSON;
    }

    @RequestMapping("/pagingSel")
    public DataPackJSON pagingSel(HttpServletRequest request, int pageNum, int pageSize, Integer type , Integer city, String name, Date date){
        DataPackJSON dataPackJSON=new DataPackJSON();
        PageHelper.startPage(pageNum,pageSize);
        List<Banner> list=bannerMapper.sell();
        PageInfo<Banner> pageInfo = new PageInfo<>(list);
        List<Banner> pageList = pageInfo.getList();
        dataPackJSON.setNumber((int)pageInfo.getTotal());
        Map all=new HashMap();
        all.put("pageList",pageList);
        dataPackJSON.setMap(all);
        dataPackJSON.setFlag(0);
        dataPackJSON.setMsg("SUCCESS");
        return dataPackJSON;

    }

    @RequestMapping("/selAll")
    public DataPackJSON selAll(){
        DataPackJSON dataPackJSON=new DataPackJSON();
        List<Banner> list=bannerMapper.sell();
        Map all=new HashMap();
        all.put("list",list);
        dataPackJSON.setMap(all);
        dataPackJSON.setFlag(0);
        dataPackJSON.setMsg("SUCCESS");
        return dataPackJSON;

    }


}
