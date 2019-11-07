package com.nkp.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nkp.config.utils.DataPackJSON;
import com.nkp.config.utils.NewDateTime;
import com.nkp.dao.EnrollMapper;
import com.nkp.dao.WorkMapper;
import com.nkp.pojo.Activity;
import com.nkp.pojo.UserInfo;
import com.nkp.pojo.WorkWithBLOBs;
import com.nkp.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.*;

@RestController
@RequestMapping("/work")
@CrossOrigin
public class WorkController {
    @Autowired
    private WorkMapper workMapper;

    @Autowired
    private EnrollMapper enrollMapper;
    @RequestMapping("/add")
    public DataPackJSON add(HttpServletRequest request, WorkWithBLOBs workWithBLOBs) throws ParseException {
        workWithBLOBs.setCreatetime(NewDateTime.getDateTime("yyyy-MM-dd :HH:mm:ss"));
        int res=workMapper.insertSelective(workWithBLOBs);
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
            res=+workMapper.deleteByPrimaryKey(Integer.valueOf(i));
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
    public DataPackJSON up(HttpServletRequest request, WorkWithBLOBs workWithBLOBs){
        int res=workMapper.updateByPrimaryKeySelective(workWithBLOBs);
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
        WorkWithBLOBs workWithBLOBs=workMapper.selectByPrimaryKey(id);
        DataPackJSON dataPackJSON=new DataPackJSON();
        Map map=new HashMap();
        HttpSession session = request.getSession();
        map.put("workWithBLOBs",workWithBLOBs);
        //map.put("session_user",(UserInfo) session.getAttribute("session_user"));
        dataPackJSON.setMap(map);
        dataPackJSON.setFlag(0);
        dataPackJSON.setMsg("SUCCESS");
        return dataPackJSON;
    }

    @RequestMapping("/pagingSel")
    public DataPackJSON pagingSel(HttpServletRequest request,int pageNum,int pageSize,Integer type ,Integer city,String name,Date date){
        DataPackJSON dataPackJSON=new DataPackJSON();
        List res=new ArrayList();
        PageHelper.startPage(pageNum,pageSize);
        List<WorkWithBLOBs> list=workMapper.selLike(type,city,name,date);
        PageInfo<WorkWithBLOBs> pageInfo = new PageInfo<>(list);
        List<WorkWithBLOBs> pageList = pageInfo.getList();
        dataPackJSON.setNumber((int)pageInfo.getTotal());
        Map all=new HashMap();
        all.put("pageList",pageList);
        dataPackJSON.setMap(all);
        dataPackJSON.setFlag(0);
        dataPackJSON.setMsg("SUCCESS");
        return dataPackJSON;

    }


}
