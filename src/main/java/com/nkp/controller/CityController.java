package com.nkp.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nkp.config.utils.DataPackJSON;
import com.nkp.config.utils.NewDateTime;
import com.nkp.dao.CityMapper;
import com.nkp.dao.WorkMapper;
import com.nkp.pojo.Banner;
import com.nkp.pojo.City;
import com.nkp.pojo.WorkWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/city")
@CrossOrigin
public class CityController {

    @Autowired
    private CityMapper cityMapper;
    @RequestMapping("/add")
    public DataPackJSON add(HttpServletRequest request, City city){

        int res=cityMapper.insertSelective(city);
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
            res=+cityMapper.deleteByPrimaryKey(Integer.valueOf(i));
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
    public DataPackJSON up(HttpServletRequest request, City city){
        int res=cityMapper.updateByPrimaryKeySelective(city);
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
    @RequestMapping("/sel")
    public DataPackJSON up(HttpServletRequest request){
        DataPackJSON dataPackJSON=new DataPackJSON();
        Map map=new HashMap();

        List<City> list=cityMapper.sel();
        map.put("list",list);
        dataPackJSON.setMap(map);
        dataPackJSON.setFlag(0);
        dataPackJSON.setMsg("SUCCESS");
        return dataPackJSON;

    }

    @RequestMapping("/pagingSel")
    public DataPackJSON pagingSel(HttpServletRequest request, int pageNum, int pageSize,   String name){
        DataPackJSON dataPackJSON=new DataPackJSON();
        PageHelper.startPage(pageNum,pageSize);
        List<Banner> list=cityMapper.selLike(name);
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


}
