package com.nkp.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nkp.config.utils.DataPackJSON;
import com.nkp.config.utils.NewDateTime;
import com.nkp.dao.ActivityMapper;
import com.nkp.pojo.Activity;
import com.nkp.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.*;

@Service
public class ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    public DataPackJSON add(HttpServletRequest request, Activity activity) throws ParseException {
        activity.setCreatetime(NewDateTime.getDateTime("yyyy-MM-dd :HH:mm:ss"));
        activity.setAnumber(0);
        int res=activityMapper.insertSelective(activity);
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
            res=+activityMapper.deleteByPrimaryKey(Integer.valueOf(i));
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

    public DataPackJSON up(HttpServletRequest request, Activity activity){
        try {
            activity.setCreatetime(NewDateTime.getDateTime("yyyy-MM-dd :HH:mm:ss"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int res=activityMapper.updateByPrimaryKeySelective(activity);
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
        Activity activity=activityMapper.selectByPrimaryKey(id);
        DataPackJSON dataPackJSON=new DataPackJSON();
        Map map=new HashMap();
        HttpSession session = request.getSession();
        map.put("activity",activity);
        map.put("session_user",(UserInfo) session.getAttribute("session_user"));
        dataPackJSON.setMap(map);
        dataPackJSON.setFlag(0);
        dataPackJSON.setMsg("SUCCESS");
        return dataPackJSON;
    }

    public DataPackJSON pselById(HttpServletRequest request,int id){
        activityMapper.upanumber(id);
        Activity activity=activityMapper.selectByPrimaryKey(id);
        DataPackJSON dataPackJSON=new DataPackJSON();
        Map map=new HashMap();
        HttpSession session = request.getSession();
        map.put("activity",activity);
        map.put("session_user",(UserInfo) session.getAttribute("session_user"));
        dataPackJSON.setMap(map);
        dataPackJSON.setFlag(0);
        dataPackJSON.setMsg("SUCCESS");
        return dataPackJSON;
    }

    public DataPackJSON selAll(HttpServletRequest request){
        List list=activityMapper.selAll();
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

        List list=activityMapper.selLike(condition,type,date);

        //得到分页的结果对象
        PageInfo<Activity> pageInfo = new PageInfo<>(list);
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

    public DataPackJSON getActivity(HttpServletRequest request, int pageNum, int pageSize, Integer id) {
        DataPackJSON dataPackJSON=new DataPackJSON();
        Map map=new HashMap();
        PageHelper.startPage(pageNum,pageSize);
        List<Activity> list=activityMapper.selAllDesc();
        for(Activity activity:list){

            activity.setRemarks(belongCalendar(new Date(),activity.getActivitytime(),
                    activity.getEndtime()));

        }
        PageInfo<Activity> pageInfo = new PageInfo<>(list);
        List pageList = pageInfo.getList();
        dataPackJSON.setNumber((int)pageInfo.getTotal());
        dataPackJSON.setFlag(0);
        dataPackJSON.setMsg("SUCCESS");
        map.put("pageList",pageList);
        dataPackJSON.setMap(map);
        return dataPackJSON;
    }

    public static String belongCalendar(Date nowTime, Date beginTime, Date endTime) {
            Calendar date = Calendar.getInstance();
            date.setTime(nowTime);
            Calendar begin = Calendar.getInstance();
            begin.setTime(beginTime);
            Calendar end = Calendar.getInstance();
            end.setTime(endTime);
            if(!date.after(begin)){
                return "未开始";
            }
            if (date.after(begin) && date.before(end)) {
                return "已开始";
            }
            if (nowTime.compareTo(beginTime) == 0 || nowTime.compareTo(endTime) == 0) {
                return "已开始";
            } else {
                return "已结束";
            }
    }

}
