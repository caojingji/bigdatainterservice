package com.founder.interservice.splog.controller;

import com.founder.interservice.VO.TrackVO;
import com.founder.interservice.exception.InterServiceException;
import com.founder.interservice.splog.model.SpLog;
import com.founder.interservice.splog.service.SpLogService;
import com.founder.interservice.util.EasyUIPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 审批日志controller
 */
@Controller
public class SpLogController {

    @Autowired
    private SpLogService spLogService;

    @RequestMapping(value = "/toSpLogJsp",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ModelAndView toSpLogJsp(){
        ModelAndView modelAndView = new ModelAndView("splog/spLog");
        return modelAndView;
    }

    /**
     * 分页查询发起审批
     * @param spLog
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "querySpLogsPage",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> querySpLogsPage(SpLog spLog,
                                              @RequestParam(value = "page",defaultValue = "0") int page,
                                              @RequestParam(value = "rows",defaultValue = "0") int rows){
        Map<String,Object> objectMap = new HashMap<>();
        List<SpLog> trackVOS = null;
        try{
            spLog.setPage(page-1);
            spLog.setSize(rows);
            Page<SpLog> spLogs = spLogService.querySpLogs(spLog);
            long totalCount = spLogs.getTotalElements();
            trackVOS = spLogs.getContent();
            objectMap.put("total", totalCount);
            objectMap.put("rows", trackVOS);
        }catch (InterServiceException e){
            e.printStackTrace();
            objectMap.put("total", 0);
            objectMap.put("rows", trackVOS);
        }
        return objectMap;
    }



}
