package com.founder.interservice.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName： RequestLog
 * @Auther： 曹鹏
 * @Description: java类作用描述
 * @CreateDate： 2018-08-13 15:56
 * @Version: 1.0
 */
@Entity
@DynamicUpdate
@Data
@Table(name = "SYS_REQ_LOG")
public class RequestLog {

    @Id
    private String rzlsh;
    private String reqmethod;
    private String yh_ip;
    private String requri;
    private String requrides;
    private String yhcatecode;
    private String yhcatename;
    private String objtypecode;
    private String objtypename;
    private String objvalue;
    private String xxsc_pdbz;
    private Date fwsj;

}
