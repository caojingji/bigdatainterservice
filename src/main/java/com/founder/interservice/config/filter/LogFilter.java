package com.founder.interservice.config.filter;

import com.founder.interservice.model.RequestLog;
import com.founder.interservice.repository.RequestLogRepository;
import com.founder.interservice.util.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

public class LogFilter implements Filter {

    @Autowired
    private RequestLogRepository requestLogRepository;

    /**
     * 过滤器初始化方法
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("过滤器初始化...............");


    }

    /**
     * 过滤器过滤方法
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException{
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        ServletContext servletContext = httpServletRequest.getSession().getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        RequestLogRepository requestLogRepository = (RequestLogRepository)webApplicationContext.getBean(RequestLogRepository.class);
        RequestLog requestLog = new RequestLog();
        requestLog.setRzlsh(KeyUtil.getUniqueKey("L"));
        requestLog.setFwsj(new Date());
        requestLog.setRequri(httpServletRequest.getRequestURI());
        requestLog.setYh_ip(httpServletRequest.getRemoteAddr());
        requestLog.setXxsc_pdbz("0");
        requestLog.setReqmethod(httpServletRequest.getMethod());
        requestLog.setObjvalue(httpServletRequest.getParameter("objValue"));
        String yhCateCode = httpServletRequest.getParameter("yhCate");
        requestLog.setYhcatecode(yhCateCode);
        String yhCateName = "";
        if(yhCateCode != null){
                switch (yhCateCode){
                case "00":
                    yhCateName="方正";
                    break;
                case "01":
                    yhCateName="嘉崎";
                    break;
                case "02":
                    yhCateName="新德汇";
                    break;
                case "03":
                    yhCateName="天彦";
                    break;
                case "04":
                    yhCateName="海鑫";
                    break;
                default:
                    yhCateName="方正";
                    break;
            }
        }else{
            yhCateName = "";
        }
        requestLog.setYhcatename(yhCateName);
        String objTypeCode = httpServletRequest.getParameter("objType");
        String objTypeName = "";
        if(objTypeCode != null)
        switch (objTypeCode){
            case "001":
                objTypeName="手机号码";
                break;
            case "002":
                objTypeName="QQ号码";
                break;
            case "003":
                objTypeName = "微信号";
                break;
            default:
                objTypeName="手机号码";
                break;
        }
        requestLog.setObjtypecode(objTypeCode);
        requestLog.setObjtypename(objTypeName);
        if (yhCateCode != null) {
            requestLogRepository.save(requestLog);
        }
        //往下执行
        filterChain.doFilter(httpServletRequest,servletResponse);
    }

    /**
     * 过滤器销毁方法
     */
    @Override
    public void destroy() {
        System.out.println("过滤器销毁.............");
    }
}
