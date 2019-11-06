package com.nkp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nkp.config.utils.DataPackJSON;
import com.nkp.dao.ShrioMapper;
import com.nkp.dao.UserInfoMapper;
import com.nkp.pojo.Shrio;
import com.nkp.pojo.UserInfo;
import com.nkp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class BackstageController {
    @Autowired
    private UserService userService;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ShrioMapper shrioMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @RequestMapping("/check")
    public DataPackJSON backStage(String phone, String code, HttpServletRequest request){
        HttpSession session=request.getSession();
        DataPackJSON dataPackJSON=new DataPackJSON();
        Map map=new HashMap();
        UserInfo userInfo=userInfoMapper.selectByPhone(phone);
        if(userInfo!=null){
            /*String sessionCode=session.getAttribute("code").toString();
            if(sessionCode==null){
                dataPackJSON.setFlag(1);
                dataPackJSON.setMsg("验证码过期");
                return dataPackJSON;
            }*/
            if(/*sessionCode.equals(code)*/true){
                session.setAttribute("session_user",userInfo);
                map.put("session_user",(UserInfo)request.getSession().getAttribute("session_user"));
                dataPackJSON.setFlag(0);
                dataPackJSON.setMsg("SUCCESS");
                dataPackJSON.setMap(map);
                return dataPackJSON;
            }else {
                dataPackJSON.setFlag(1);
                dataPackJSON.setMsg("验证码错误");
                return dataPackJSON;
            }
        }
        dataPackJSON.setFlag(1);
        dataPackJSON.setMsg("ERROR");
        return dataPackJSON;

    }
    //访问外部API公共方法
    public  String visitThirdParties(MultiValueMap<String, String> params,String url){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        return response.getBody();

    }
    //获取验证码
    @RequestMapping("/getcode")
    public DataPackJSON getCode(String phone,HttpServletRequest request){
        HttpSession session=request.getSession();
        int code=(int)((Math.random()*9+1)*1000);
        session.setAttribute("code",code);
        DataPackJSON dataPackJSON=new DataPackJSON();
        UserInfo userInfo=userInfoMapper.selectByPhone(phone);
        if(userInfo!=null){
            //发送验证码
            MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
            params.add("phone",phone);
            params.add("verifyCode",String.valueOf(code));
            params.add("token","DLO3303A10XMDO39X0TYZQAP3LXDJ3X19283E2RXWIK");
            String res=this.visitThirdParties(params,"https://console.yingpiao360.com/api/common/send-verify-code");
            if(res!=null){
                JSONObject data = (JSONObject) JSON.parse(res);
                String res_data=data.getString("data");
                if("true".equals(res_data)){
                    dataPackJSON.setFlag(0);
                    dataPackJSON.setMsg("验证码已发送");
                    return dataPackJSON;
                }
            }
        }
        dataPackJSON.setFlag(1);
        dataPackJSON.setMsg("此用户不存在");
        return dataPackJSON;
    }


    @RequestMapping("/findAllUser")
    public DataPackJSON findAllUser(HttpServletRequest request){
        return userService.findAllUser(request);
    }

    @RequestMapping("/test")
    public String test(){
        return "ok";
    }


    @RequestMapping("/add")
    @Transactional
    public DataPackJSON addUser(HttpServletRequest request,@RequestBody JSONObject jsonParam){
        DataPackJSON dataPackJSON=new DataPackJSON();
        UserInfo userInfo=new UserInfo();
        userInfo.setUsername(jsonParam.getString("username"));
        userInfo.setPurl(jsonParam.getString("purl"));
        userInfo.setUserphone(jsonParam.getString("userphone"));
        userInfo.setType(jsonParam.getInteger("type"));
        int num=userInfoMapper.zc(jsonParam.getString("username"),jsonParam.getString("userphone"));
        if(num!=0){
            dataPackJSON.setFlag(1);
            dataPackJSON.setMsg("ERROR");
            return dataPackJSON;
        }
        boolean res=userService.addUser(userInfo);
        if(res){
            upshrio(jsonParam,userInfo.getUserid());
            dataPackJSON.setFlag(0);
            dataPackJSON.setMsg("SUCCESS");
            return dataPackJSON;
        }
        dataPackJSON.setFlag(1);
        dataPackJSON.setMsg("ERROR");
        return dataPackJSON;
    }

    @RequestMapping("/upUser")
    public DataPackJSON upUser(HttpServletRequest request,@RequestBody JSONObject jsonParam){

        DataPackJSON dataPackJSON=new DataPackJSON();
        UserInfo userInfo=new UserInfo();
        userInfo.setUsername(jsonParam.getString("username"));
        userInfo.setPurl(jsonParam.getString("purl"));
        userInfo.setUserphone(jsonParam.getString("userphone"));
        userInfo.setType(jsonParam.getInteger("type"));
        userInfo.setUserid(jsonParam.getInteger("id"));
        boolean res=userService.upUser(userInfo);
        if(res){
            upshrio(jsonParam,userInfo.getUserid());
            dataPackJSON.setFlag(0);
            dataPackJSON.setMsg("SUCCESS");
            return dataPackJSON;
        }
        dataPackJSON.setFlag(1);
        dataPackJSON.setMsg("ERROR");
        return dataPackJSON;
    }

    @RequestMapping("/del")
    public DataPackJSON del(HttpServletRequest request,String ids){
        int res=0;
        String[] id=ids.split(",");
        for(String i:id){
            res=+userInfoMapper.deleteByPrimaryKey(Integer.valueOf(i));
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
    @RequestMapping("/selById")
    public DataPackJSON selById(HttpServletRequest request,int id){
        return userService.selById(request,id);


    }

    @RequestMapping("/oneId")
    public DataPackJSON oneId(int id){
        return userService.oneId(id);


    }

    public  void upshrio(JSONObject jsonParam,int uid){
        //删除用户现有权限
        shrioMapper.delShrio(uid);
        List<Map<String,Object>> list=(List)jsonParam.get("jsonParam");
        //更新权限
        for(int i=0;i<5;i++){
            List<Map<String,Object>> res=(List)list.get(i).get("subChecked");
            for(int j=0;j<4;j++){
                Integer result=(Integer)res.get(j).get("isChecked");
                Shrio shrio=new Shrio();
                shrio.setUid(uid);
                shrio.setCid(result);
                shrioMapper.insertSelective(shrio);

            }
        }


    }

    @RequestMapping("/pagingSel")
    public DataPackJSON pagingSel(HttpServletRequest request,int pageNum,int pageSize,Integer id,String condition, Integer type, Date date){
        return userService.pagingSel(request,pageNum,pageSize,id,condition,type,date);
    }
    @RequestMapping("/checkShrio")
    public DataPackJSON checkShrio(HttpServletRequest request,int id,String url){
        DataPackJSON dataPackJSON=new DataPackJSON();
        Map<String,Object> map=new HashMap<>();
        map.put("id",id);
        map.put("url",url);
        Shrio res=shrioMapper.selectByUrl(map);
        if(res!=null){
            dataPackJSON.setFlag(0);
            dataPackJSON.setMsg("SUCCESS");
            return dataPackJSON;
        }
        dataPackJSON.setFlag(1);
        dataPackJSON.setMsg("当前用户无权限");

        return dataPackJSON;
    }









}
