package com.founder.interservice.controller;

import com.founder.interservice.caseinformation.model.CaseInformation;
import com.founder.interservice.enums.ResultEnum;
import com.founder.interservice.qgzyfw.action.CallDygabxxfw;
import com.founder.interservice.qgzyfw.domain.GabConfig;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin  //跨域访问
public class MinistrySuspectInfoController {
    @Autowired
    private GabConfig gabConfig;

    @RequestMapping("/queryMinistrySuspectInfo")
    @ResponseBody
    public JSONObject queryMinistrySuspectInfo(String sfzh){//ZHFZXYR_CYZJ_ZJHM 查询资源所需的条件  513624198110310011  510211198301103713  510218198208064953
        JSONObject resultObj = new JSONObject();
        List<Map<String,Object>> resultList = new ArrayList<Map<String, Object>>();
        try {
            //通过身份证号调取案事件编号list（asjbhList）
            CallDygabxxfw call = new CallDygabxxfw();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ZHFZXYR_CYZJ_ZJHM", sfzh);
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(jsonObject);
            Object[] os = jsonArray.toArray();
            Map<String,Object> mapQg = new HashMap<String, Object>();
            mapQg = call.getGabZyInfoByJyaq("fzxyr",os,gabConfig);
            List<Map<String,Object>> asjbhList = (List<Map<String,Object>>)mapQg.get("dataResult");
            System.out.println("------------------asjbhList(第一)------------------------"+ asjbhList);
            //通过案事件编号调取案事件信息resultList
            for (Map<String,Object> asjbh:asjbhList) {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("ASJBH", asjbh);
                JSONArray jsonArray1 = new JSONArray();
                jsonArray1.add(jsonObject1);
                Object[] objects = jsonArray1.toArray();
                Map<String,Object> m = new HashMap<String, Object>();
                m = call.getGabZyInfoByJyaq("asj",objects,gabConfig);
                int count = Integer.valueOf((String)m.get("account"));
                List<Map<String,Object>> result = (List<Map<String,Object>>)m.get("dataResult");
                for (int i=0;i<count;i++){
                    resultList.add(result.get(i));
                }
            }
            if(!resultList.isEmpty() && resultList != null){
                resultObj.put("code", ResultEnum.SUCCESS.getCode());
                resultObj.put("message",ResultEnum.SUCCESS.getMessage());
            }else {
                resultObj.put("code", ResultEnum.SUCCESS.getCode());
                resultObj.put("message","无数据");
            }
            resultObj.put("dataList",resultList);
            System.out.println("------------------返回dataList------------------------"+resultList);
        } catch (Exception e) {
            resultObj.put("code", ResultEnum.RESULT_ERROR.getCode());
            resultObj.put("message",ResultEnum.RESULT_ERROR.getMessage());
            e.printStackTrace();
        }
        return resultObj;
    }

}
