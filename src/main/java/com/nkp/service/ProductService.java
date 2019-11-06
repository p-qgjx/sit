package com.nkp.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nkp.config.utils.DataPackJSON;
import com.nkp.config.utils.NewDateTime;
import com.nkp.dao.CooperationMapper;
import com.nkp.dao.ProductMapper;
import com.nkp.pojo.Product;
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
public class ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CooperationMapper cooperationMapper;

    public DataPackJSON add(HttpServletRequest request, Product product){
        try {
            product.setCreattime(NewDateTime.getDateTime("yyyy-MM-dd :HH:mm:ss"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(product.getKeyword()!=null){
            String str=product.getKeyword().replace(",","，");
            product.setKeyword(str);
        }
        int res=productMapper.insertSelective(product);
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
            res=+productMapper.deleteByPrimaryKey(Integer.valueOf(i));
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

    public DataPackJSON up(HttpServletRequest request, Product product){
        int res=productMapper.updateByPrimaryKeySelective(product);
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
        Product product=productMapper.selectByPrimaryKey(id);
        DataPackJSON dataPackJSON=new DataPackJSON();
        Map map=new HashMap();
        List logos=cooperationMapper.selAll();
        HttpSession session = request.getSession();
        map.put("product",product);
        map.put("logos",logos);
        map.put("session_user",(UserInfo) session.getAttribute("session_user"));
        dataPackJSON.setMap(map);
        dataPackJSON.setFlag(0);
        dataPackJSON.setMsg("SUCCESS");
        return dataPackJSON;
    }
    public DataPackJSON selAll(HttpServletRequest request){
        List list=productMapper.selAll();
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

    public DataPackJSON pagingSel(HttpServletRequest request, int pageNum, int pageSize, Integer id, Integer integer, String condition, Integer type, Date date) {
        DataPackJSON dataPackJSON=new DataPackJSON();
        Map map=new HashMap();

        HttpSession session = request.getSession();

        PageHelper.startPage(pageNum,pageSize);

        List list=productMapper.selLike(condition,type,date);

        //得到分页的结果对象
        PageInfo<Product> pageInfo = new PageInfo<>(list);
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
