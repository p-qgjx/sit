package com.nkp.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nkp.config.utils.DataPackJSON;
import com.nkp.config.utils.NewDateTime;
import com.nkp.dao.ShrioMapper;
import com.nkp.dao.UserInfoMapper;
import com.nkp.pojo.Shrio;
import com.nkp.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.*;

@Service(value = "userService")
public class UserService {

    @Autowired
    private UserInfoMapper userDao;

    @Autowired
    private ShrioMapper shrioMapper;




    public UserInfo check(String userName, String userPW) {
        return  userDao.check(userName,userPW);
    }

    public DataPackJSON findAllUser(HttpServletRequest request) {
        Map map=new HashMap();
        HttpSession session = request.getSession();
        List list=userDao.findAllUser();
        DataPackJSON dataPackJSON=new DataPackJSON();

        dataPackJSON.setFlag(0);
        dataPackJSON.setMsg("SUCCESS");
        map.put("lists",list);
        map.put("session_user",(UserInfo) session.getAttribute("session_user"));
        dataPackJSON.setMap(map);
        dataPackJSON.setNumber(list.size());
        return dataPackJSON;

    }

    public boolean addUser(UserInfo userInfo) {
        try {
            userInfo.setCreatetime(NewDateTime.getDateTime("yyyy-MM-dd :HH:mm:ss"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int res=userDao.insertSelective(userInfo);
        if(res==1){
            return true;
        }
        return false;
    }

    public boolean upUser(UserInfo userInfo) {

        int res=userDao.updateByPrimaryKeySelective(userInfo);
        if(res==1){
            return true;
        }
        return false;
    }

    public boolean del(int id) {
        int res=userDao.deleteByPrimaryKey(id);
        if(res==1){
            return true;
        }
        return false;
    }

    public DataPackJSON selById(HttpServletRequest request,int id) {
        DataPackJSON dataPackJSON=new DataPackJSON();
        UserInfo userInfo=userDao.selectByPrimaryKey(id);
        List<Shrio> big=shrioMapper.selByuid(id);
        List<Map> subChecked=new ArrayList<>();
        List<Map> jsonParam=new ArrayList<>();

        for(int i=0;i<big.size();i++){

            String lable=(i+1)%4==1?"增加":(i+1)%4==2?"删除":(i+1)%4==3?"编辑":"查看";
            Map map=new HashMap<String,Object>();
            map.put("lable",lable);
            map.put("isChecked",big.get(i).getCid()==0?false:true);
            map.put("num",i+1);
            subChecked.add(map);
        }

        for(int j=0;j<5;j++){
            Map map1=new HashMap<String,Object>();
            map1.put("title",j==0?"文章":j==1?"作者":j==2?"活动":j==3?"产品":"系统");
            List<Map> temp=subChecked.subList(4*j,4*j+4);

            map1.put("subChecked",temp);
            boolean isAllElection=true;
            for(Map sc:temp){
                if(!(boolean)sc.get("isChecked")){
                    isAllElection=false;
                    break;

                }
            }
            map1.put("isAllElection",isAllElection);
            jsonParam.add(map1);

        }
        Map ress=new HashMap();
        ress.put("userInfo",userInfo);
        ress.put("jsonParam",jsonParam);
        dataPackJSON.setFlag(0);
        dataPackJSON.setMsg("SUCCESS");
        dataPackJSON.setMap(ress);
        return dataPackJSON;

    }

    public DataPackJSON oneId(int id){
        DataPackJSON dataPackJSON=new DataPackJSON();
        Map map=new HashMap();
        UserInfo userInfo=userDao.selectByPrimaryKey(id);
        map.put("user",userInfo);
        dataPackJSON.setMap(map);
        dataPackJSON.setFlag(0);
        dataPackJSON.setMsg("SUCCESS");
        return dataPackJSON;
    }

    public DataPackJSON pagingSel(HttpServletRequest request, int pageNum, int pageSize, Integer id, String condition, Integer type, Date date) {
        DataPackJSON dataPackJSON=new DataPackJSON();
        Map map=new HashMap();
        HttpSession session = request.getSession();

        PageHelper.startPage(pageNum,pageSize);

        List<UserInfo> list=userDao.selLike(condition,type,date);

        //得到分页的结果对象
        PageInfo<UserInfo> pageInfo = new PageInfo<>(list);
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
