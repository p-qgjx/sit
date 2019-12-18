package com.nkp.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nkp.config.utils.DataPackJSON;
import com.nkp.config.utils.ExcelData;
import com.nkp.config.utils.ExcelUtils;
import com.nkp.config.utils.NewDateTime;
import com.nkp.dao.EnrollMapper;
import com.nkp.dao.WorkMapper;
import com.nkp.pojo.Enroll;
import com.nkp.pojo.UserInfo;
import com.nkp.pojo.Work;
import com.nkp.pojo.WorkWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/enroll")
@CrossOrigin
public class EnrollController {
    @Autowired
    private EnrollMapper enrollMapper;

    @Autowired
    private WorkMapper workMapper;

    @RequestMapping("/add")
    public DataPackJSON add(HttpServletRequest request, Enroll enroll) throws ParseException {
        enroll.setCreatetime(NewDateTime.getDateTime("yyyy-MM-dd :HH:mm:ss"));
        int res=enrollMapper.insertSelective(enroll);
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
            res=+enrollMapper.deleteByPrimaryKey(Integer.valueOf(i));
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
    //查询所有的企业名称
    @RequestMapping("/selName")
    public DataPackJSON selName(HttpServletRequest request){
        DataPackJSON dataPackJSON=new DataPackJSON();
        List<Work> list=workMapper.selName();
        Map map=new HashMap();
        map.put("list",list);
        dataPackJSON.setMap(map);
        dataPackJSON.setFlag(0);
        dataPackJSON.setMsg("SUCCESS");
        return dataPackJSON;
    }

    @RequestMapping("/up")
    public DataPackJSON up(HttpServletRequest request, Enroll enroll){
        int res=enrollMapper.updateByPrimaryKeySelective(enroll);
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
        Enroll enroll=enrollMapper.selectByPrimaryKey(id);
        DataPackJSON dataPackJSON=new DataPackJSON();
        Map map=new HashMap();
        HttpSession session = request.getSession();
        map.put("enroll",enroll);
        //map.put("session_user",(UserInfo) session.getAttribute("session_user"));
        dataPackJSON.setMap(map);
        dataPackJSON.setFlag(0);
        dataPackJSON.setMsg("SUCCESS");
        return dataPackJSON;
    }

    @RequestMapping("/pagingSel")
    public DataPackJSON pagingSel(HttpServletRequest request, int pageNum, int pageSize,String name, Date date){
        DataPackJSON dataPackJSON=new DataPackJSON();
        Map map=new HashMap();
        PageHelper.startPage(pageNum,pageSize);
        List<Enroll> list=enrollMapper.selLike(name,date);
        PageInfo<Enroll> pageInfo = new PageInfo<>(list);
        List pageList = pageInfo.getList();
        dataPackJSON.setNumber((int)pageInfo.getTotal());
        map.put("pageList",pageList);
        dataPackJSON.setMap(map);
        dataPackJSON.setFlag(0);
        dataPackJSON.setMsg("SUCCESS");
        return dataPackJSON;

    }

    @RequestMapping("/dc")

    public void dc(HttpServletResponse response){

        int rowIndex = 0;

        List<Enroll> list = enrollMapper.selAll2();

        ExcelData data = new ExcelData();

        data.setName("sign");

        List<String> titles = new ArrayList();

        titles.add("编号");

        titles.add("姓名");

        titles.add("性别");
        titles.add("手机号码");
        titles.add("QQ");
        titles.add("学校");
        titles.add("意向企业");
        titles.add("报名时间");

        data.setTitles(titles);



        List<List<Object>> rows = new ArrayList();

        for(int i = 0, length = list.size();i<length;i++){

            Enroll enroll = list.get(i);

            List<Object> row = new ArrayList();

            row.add(enroll.getId());

            row.add(enroll.getName());

            row.add(enroll.getSex()==1?"男":"女");
            row.add(enroll.getPhone());
            row.add(enroll.getQq());
            row.add(enroll.getSchool());
            row.add(enroll.getWork().getName());
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            row.add(simpleDateFormat.format(enroll.getCreatetime()));

            rows.add(row);

        }

        data.setRows(rows);

        try{

            ExcelUtils.exportExcel(response,"报名表单",data);

        }catch (Exception e){

            e.printStackTrace();

        }

    }






}
