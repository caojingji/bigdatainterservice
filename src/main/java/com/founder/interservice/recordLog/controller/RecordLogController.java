package com.founder.interservice.recordLog.controller;

import com.founder.interservice.VO.ResultVO;
import com.founder.interservice.enums.ResultEnum;
import com.founder.interservice.recordLog.VO.RecordLogVo;
import com.founder.interservice.recordLog.model.Querylog;
import com.founder.interservice.recordLog.queryModel.QuerylogFilter;
import com.founder.interservice.recordLog.service.RecordLogService;
import com.founder.interservice.util.EasyUIPage;
import com.founder.interservice.util.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName： RecordLogController
 * @Auther： 罗启豪
 * @Description: 日志记录controller
 * @CreateDate： 2018-10-17
 * @Version: 1.0
 */
@Controller
public class RecordLogController {

    @Autowired
    private RecordLogService recordLogService;

    @RequestMapping(value = "/saveQueryLog",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResultVO saveQueryLog(Querylog querylog){
        ResultVO resultVO = null;
        try {
            recordLogService.saveQueryLog(querylog);
            resultVO = ResultVOUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            resultVO = ResultVOUtil.error(ResultEnum.RESULT_ERROR.getCode(),ResultEnum.RESULT_ERROR.getMessage());
        }

        return resultVO;
    }


    @RequestMapping(value = "/queryQueryLogList",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> queryQueryLog(QuerylogFilter filter,
                                            @RequestParam(value = "page",defaultValue = "0") int page,
                                            @RequestParam(value = "rows",defaultValue = "0") int rows){
        Map<String,Object> objectMap = new HashMap<String, Object>();
        List<RecordLogVo> recordLogVos = null;
        try{
            EasyUIPage easyUIPage = new EasyUIPage();
            easyUIPage.setPage(page);
            easyUIPage.setPagePara(rows);
            int begin = easyUIPage.getBegin();
            int end = easyUIPage.getEnd();

            filter.setKssj(filter.getKssj());
            filter.setJssj(filter.getJssj());
            filter.setBegin(begin);
            filter.setEnd(end);
            Map<String, Object> resultMap = recordLogService.queryQueryLogList(filter);
            int total = (int) resultMap.get("total");
            List<Querylog> queryLogList = (List<Querylog>) resultMap.get("queryLogList");
            if (queryLogList != null && !queryLogList.isEmpty()) {
                recordLogVos = new ArrayList<>();
                for (Querylog queryLog : queryLogList) {
                    RecordLogVo recordLogVo = new RecordLogVo();
                    BeanUtils.copyProperties(queryLog, recordLogVo);
                    recordLogVos.add(recordLogVo);
                }
            objectMap.put("total", total);
            objectMap.put("rows", recordLogVos);
            }else {
                objectMap.put("total",0);
                objectMap.put("rows",new ArrayList<>());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return objectMap;
    }

    @RequestMapping(value = "/recordLogView",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView recordLogView(){
        return new ModelAndView("RecordLog/RecordLog");
    }

}
