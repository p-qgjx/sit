package com.nkp.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nkp.config.utils.DataPackJSON;
import com.nkp.dao.CooperationMapper;
import com.nkp.pojo.Cooperation;
import com.nkp.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CooperationService {

    @Autowired
    private CooperationMapper cooperationMapper;

    public DataPackJSON add(HttpServletRequest request, Cooperation cooperation) {
        int res=cooperationMapper.insertSelective(cooperation);
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

    public DataPackJSON del(HttpServletRequest request, String ids) {
        int res=0;
        String[] id=ids.split(",");
        for(String i:id){
            res=+cooperationMapper.deleteByPrimaryKey(Integer.valueOf(i));
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

    public DataPackJSON up(HttpServletRequest request, Cooperation cooperation) {
        int res=cooperationMapper.updateByPrimaryKeySelective(cooperation);
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

    public DataPackJSON selById(HttpServletRequest request, int id) {
        Cooperation cooperation=cooperationMapper.selectByPrimaryKey(id);
        DataPackJSON dataPackJSON=new DataPackJSON();
        Map map=new HashMap();
        HttpSession session = request.getSession();
        map.put("cooperation",cooperation);
        map.put("session_user",(UserInfo) session.getAttribute("session_user"));
        dataPackJSON.setMap(map);
        dataPackJSON.setFlag(0);
        dataPackJSON.setMsg("SUCCESS");
        return dataPackJSON;
    }

    public DataPackJSON selAll(HttpServletRequest request) {
        List list=cooperationMapper.selAll();
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

    public DataPackJSON pagingSel(HttpServletRequest request, int pageNum, int pageSize, Integer id) {
        DataPackJSON dataPackJSON=new DataPackJSON();
        Map map=new HashMap();
        HttpSession session = request.getSession();

        PageHelper.startPage(pageNum,pageSize);

        List list=cooperationMapper.selAll();

        //得到分页的结果对象
        PageInfo<Cooperation> pageInfo = new PageInfo<>(list);
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
}
