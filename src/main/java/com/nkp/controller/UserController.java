package com.nkp.controller;

import com.nkp.config.utils.DataPackJSON;
import com.nkp.dao.UserMapper;
import com.nkp.dao.WorkMapper;
import com.nkp.pojo.User;
import com.nkp.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user_info")
@CrossOrigin
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/check")
    public DataPackJSON check(HttpServletRequest request, String name,String password){
        Map map=new HashMap();
        HttpSession session = request.getSession();
        DataPackJSON dataPackJSON=new DataPackJSON();
        User user=userMapper.check(name,password);
        if(user!=null){
            session.setAttribute("session_user",user);
            map.put("session_user",(User)request.getSession().getAttribute("session_user"));
            dataPackJSON.setFlag(0);
            dataPackJSON.setMsg("SUCCESS");
            dataPackJSON.setMap(map);
            return dataPackJSON;
        }
        dataPackJSON.setFlag(1);
        dataPackJSON.setMsg("账号或密码错误");
        return dataPackJSON;
    }
    @RequestMapping("/sign_out")
    public DataPackJSON signOut(HttpServletRequest request){
        DataPackJSON dataPackJSON=new DataPackJSON();
        request.getSession().invalidate();
        dataPackJSON.setFlag(0);
        dataPackJSON.setMsg("SUCCESS");
        return dataPackJSON;

    }


}
