package com.founder.interservice.qgzyfw.domain;

import java.sql.Blob;
import java.util.Date;

public class TbStAsj {
	public String	ASJBH	;//	案事件编号
	public String	XCKYBH	;//	现场勘验编号
	public String	XSJQLBDM	;//	刑事警情类别代码
	public String	ZCJDDM	;//	侦查阶段代码
	public String	XSAJLASCJGDM	;//	刑事案件立案审查结果代码
	public String	AJLBDM	;//	案件类别代码
	public String	AJMC	;//	案件名称
	public Date 	FXASJSJ	;//	发现案事件时间
	public String	FXASJDD_XZQHDM	;//	发现案事件地点_行政区划代码
	public String	FXASJDD_DZMC	;//	发现案事件地点_地址名称
	public Date	ASJFSSJ_ASJFSKSSJ	;//	案事件发生时间_案事件发生开始时间
	public String	ASJFSSJ_RSDDM	;//	案事件发生时间_日时段代码
	public String	ASJFSSJ_ZASJ_ZASJLBDM	;//	案事件发生时间_作案时机_作案时机类别代码
	public String	ASJFSSJ_ZASJ_DMBCMS	;//	案事件发生时间_作案时机_代码补充描述
	public String	ASJFSDD_XZQHDM	;//	案事件发生地点_行政区划代码
	public String	ASJFSDD_DZMC	;//	案事件发生地点_地址名称
	public String	ASJFSDD_DQJD	;//	案事件发生地点_经度
	public String	ASJFSDD_DQWD	;//	案事件发生地点_纬度
	public String	ASJFSDD_DYLBDM	;//	案事件发生地点_地域类别代码
	public String	ASJFSDD_SACS_SACSLBDM	;//	案事件发生地点_涉案场所_涉案场所类别代码
	public String	ASJFSDD_SACS_DMBCMS	;//	案事件发生地点_涉案场所_代码补充描述
	public String	ASJFSDD_KJBW_KJBWLBDM	;//	案事件发生地点_空间部位_空间部位类别代码
	public String	ASJFSDD_KJBW_DMBCMS	;//	案事件发生地点_空间部位_代码补充描述
	public String	ASJFSDD_SFJZWN_PDBZ	;//	案事件发生地点_是否建筑物内_判断标识
	public String	ASJFSDD_JZWCS	;//	案事件发生地点_建筑物层数
	public String	ASJFSDD_ASJFSLC	;//	案事件发生地点_案事件发生楼层
	public String	ASJFSDD_SFZLZZ_PDBZ	;//	案事件发生地点_是否租赁住宅_判断标识
	public Blob	JYAQ	;//	简要案情
	public String	SFSQ_PDBZ	;//	是否涉枪_判断标识
	public String	SFSB_PDBZ	;//	是否涉爆_判断标识
	public String	SFMA_PDBZ	;//	是否命案_判断标识
	public String	SFSH_PDBZ	;//	是否涉黑_判断标识
	public String	SFSJDQPCL_PDBZ	;//	是否涉及盗抢骗车辆_判断标识
	public String	SFSW_PDBZ	;//	是否涉外_判断标识
	public String	SWASJSWQK	;//	涉外案事件涉外情况
	public String	ASJDBJBDM	;//	案事件督办级别代码
	public String	LLFS_LLFSDM	;//	联络方式_联络方式代码
	public String	LLFS_DMBCMS	;//	联络方式_代码补充描述
	public Date	 LLSJ	;//	联络时间
	public Blob	SY_JYQK	;//	事由_简要情况
	public String	ZAZBSD_ZAZBSDDM	;//	作案准备手段_作案准备手段代码
	public String	ZAZBSD_DMBCMS	;//	作案准备手段_代码补充描述
	public String	JJSD_JJSDDM	;//	接近手段_接近手段代码
	public String	JJSD_DMBCMS	;//	接近手段_代码补充描述
	public String	MCMYSD_MCMYSDDM	;//	冒充冒用手段_冒充冒用手段代码
	public String	MCMYSD_DMBCMS	;//	冒充冒用手段_代码补充描述
	public String	MCSF_MCSFDM	;//	冒充身份_冒充身份代码
	public String	MCSF_DMBCMS	;//	冒充身份_代码补充描述
	public String	MCGXR_MCGXRDM	;//	冒充关系人_冒充关系人代码
	public String	MCGXR_DMBCMS	;//	冒充关系人_代码补充描述
	public String	MYDWMY_MCDWMYDM	;//	冒用单位名义_冒用单位名义代码
	public String	MYDWMY_DMBCMS	;//	冒用单位名义_代码补充描述
	public String	ZPSD_ZPSDDM	;//	诈骗手段_诈骗手段代码
	public String	ZPSD_DMBCMS	;//	诈骗手段_代码补充描述
	public String	XPSD_XPSDDM	;//	胁迫手段_胁迫手段代码
	public String	XPSD_DMBCMS	;//	胁迫手段_代码补充描述
	public String	XPSYWP_SAWPDM	;//	胁迫使用物品_涉案物品代码
	public String	XPSYWP_DMBCMS	;//	胁迫使用物品_代码补充描述
	public String	SBSD_SBSDDM	;//	施暴手段_施暴手段代码
	public String	SBSD_DMBCMS	;//	施暴手段_代码补充描述
	public String	JRJZKJFSJSD_JRJZKJFSJSDDM	;//	进入建筑空间方式及手段_进入建筑空间方式及手段代码
	public String	JRJZKJFSJSD_DMBCMS	;//	进入建筑空间方式及手段_代码补充描述
	public String	LKJZKJFS_LKJZKJFSDM	;//	离开建筑空间方式_离开建筑空间方式代码
	public String	LKJZKJFS_DMBCMS	;//	离开建筑空间方式_代码补充描述
	public String	JSSD_JSSDDM	;//	解锁手段_解锁手段代码
	public String	JSSD_DMBCMS	;//	解锁手段_代码补充描述
	public String	XTKJTPSD_XTKJTPSDDM	;//	箱体空间突破手段_箱体空间突破手段代码
	public String	XTKJTPSD_DMBCMS	;//	箱体空间突破手段_代码补充描述
	public String	QQSD_QQSDDM	;//	窃取手段_窃取手段代码
	public String	QQSD_DMBCMS	;//	窃取手段_代码补充描述
	public String	YBSDDM	;//	引爆手段代码
	public String	WLZASD_WLZASDDM	;//	网络作案手段_网络作案手段代码
	public String	WLZASD_DMBCMS	;//	网络作案手段_代码补充描述
	public String	XCWPFDCD_XCWPFDCDDM	;//	现场物品翻动程度_现场物品翻动程度代码
	public String	XCWPFDCD_DMBCMS	;//	现场物品翻动程度_代码补充描述
	public String	GRZCSD_GRZCSDDM	;//	干扰侦查手段_干扰侦查手段代码
	public String	GRZCSD_DMBCMS	;//	干扰侦查手段_代码补充描述
	public String	FZXYRTSXW_FZXYRTSXWDM	;//	犯罪嫌疑人特殊行为_犯罪嫌疑人特殊行为代码
	public String	FZXYRTSXW_DMBCMS	;//	犯罪嫌疑人特殊行为_代码补充描述
	public String	FZXYRSXZAHJQK	;//	犯罪嫌疑人熟悉作案环境情况
	public Blob	QTSDTD_JYQK	;//	其他手段特点_简要情况
	public Date	 ASJFSSJFX_ASJFSKSSJ	;//	案事件发生时间分析_案事件发生开始时间
	public Date	 ASJFSSJFX_ASJFSJSSJ	;//	案事件发生时间分析_案事件发生结束时间
	public String	ZARFX_RSXX	;//	作案人分析_人数下限
	public String	ZARFX_RSSX	;//	作案人分析_人数上限
	public Blob	FZXYRTZ_JYQK	;//	犯罪嫌疑人特征_简要情况
	public String	FZXYRTSZC_FZXYRTSZCDM	;//	犯罪嫌疑人特殊专长_犯罪嫌疑人特殊专长代码
	public String	FZXYRTSZC_DMBCMS	;//	犯罪嫌疑人特殊专长_代码补充描述
	public String	ZAGJ_SAWPDM	;//	作案工具_涉案物品代码
	public Blob	ZAGJ_JYQK	;//	作案工具_简要情况
	public Blob	ZADJMS	;//	作案动机描述
	public Blob	ZAMDMS	;//	作案目的描述
	public String	KJFWDM	;//	跨界范围代码
	public Blob	LCZA_JYQK	;//	流窜作案_简要情况
	public Blob	JHZA_JYQK	;//	结伙作案_简要情况
	public String	GTFZFZXYRZHXSDM	;//	共同犯罪犯罪嫌疑人组合形式代码
	public String	GTFZFZXYRGC_GTFZFZXYRGCDM	;//	共同犯罪犯罪嫌疑人构成_共同犯罪犯罪嫌疑人构成代码
	public String	GTFZFZXYRGC_DMBCMS	;//	共同犯罪犯罪嫌疑人构成_代码补充描述
	public Blob	SSWPQX_JYQK	;//	损失物品去向_简要情况
	public Blob	FXXS_JYQK	;//	发现线索_简要情况
	public Number	ASJSWRY_RS	;//	案事件死亡人员_人数
	public Number	ASJSSRY_RS	;//	案事件受伤人员_人数
	public Blob	ASJSSCW_JYQK	;//	案事件损失财物_简要情况
	public String	SSJZRMBY	;//	损失价值（人民币元）
	public String	FZXYRYLWP_JYQK	;//	犯罪嫌疑人遗留物品_简要情况
	public Blob	XCKYWP_JYQK	;//	现场可疑物品_简要情况
	public Number	KYZSQZ_WPSL	;//	扣押制式枪支_物品数量
	public Number	KYMYQZ_WPSL	;//	扣押民用枪支_物品数量
	public Number	KYZZQZ_WPSL	;//	扣押自制枪支_物品数量
	public Number	KYFZQZ_WPSL	;//	扣押仿制枪支_物品数量
	public Number	KYQTQZ_WPSL	;//	扣押其他枪支_物品数量
	public Date	 SLSJ	;//	受理时间
	public String	SLDW_GAJGJGDM	;//	受理单位_公安机关机构代码
	public String	SLDW_GAJGMC	;//	受理单位_公安机关名称
	public Date	LARQ	;//	立案日期
	public String	LADW_GAJGJGDM	;//	立案单位_公安机关机构代码
	public String	LADW_GAJGMC	;//	立案单位_公安机关名称
	public String	ZARY_RS	;//	作案人员_人数
	public String	SJCWJZRMBY	;//	收缴财物价值（人民币元）
	public Blob	ZCZJ_ZCXWYJMS	;//	侦查终结_侦查行为依据描述
	public Date	 ZCZJ_ZXSJ01	;//	侦查终结_执行时间
	public String	ZCZJDW_GAJGJGDM	;//	侦查终结单位_公安机关机构代码
	public String	ZCZJDW_GAJGMC	;//	侦查终结单位_公安机关名称
	public String	YSSCQSRQ	;//	移送审查起诉日期
	public String	YSSCQSAJSCFHJDDM	;//	移送审查起诉案件审查返回决定代码
	public String	SFCXAJ_PDBZ	;//	是否撤销案件_判断标识
	public String	CXAJYYDM	;//	撤销案件原因代码
	public String	CXAJDW_GAJGJGDM	;//	撤销案件单位_公安机关机构代码
	public String	CXAJDW_GAJGMC	;//	撤销案件单位_公安机关名称
	public Date	 CXAJRQ	;//	撤销案件日期
	public String getASJBH() {
		return ASJBH;
	}
	public void setASJBH(String aSJBH) {
		ASJBH = aSJBH;
	}
	public String getXCKYBH() {
		return XCKYBH;
	}
	public void setXCKYBH(String xCKYBH) {
		XCKYBH = xCKYBH;
	}
	public String getXSJQLBDM() {
		return XSJQLBDM;
	}
	public void setXSJQLBDM(String xSJQLBDM) {
		XSJQLBDM = xSJQLBDM;
	}
	public String getZCJDDM() {
		return ZCJDDM;
	}
	public void setZCJDDM(String zCJDDM) {
		ZCJDDM = zCJDDM;
	}
	public String getXSAJLASCJGDM() {
		return XSAJLASCJGDM;
	}
	public void setXSAJLASCJGDM(String xSAJLASCJGDM) {
		XSAJLASCJGDM = xSAJLASCJGDM;
	}
	public String getAJLBDM() {
		return AJLBDM;
	}
	public void setAJLBDM(String aJLBDM) {
		AJLBDM = aJLBDM;
	}
	public String getAJMC() {
		return AJMC;
	}
	public void setAJMC(String aJMC) {
		AJMC = aJMC;
	}
	public Date getFXASJSJ() {
		return FXASJSJ;
	}
	public void setFXASJSJ(Date fXASJSJ) {
		FXASJSJ = fXASJSJ;
	}
	public String getFXASJDD_XZQHDM() {
		return FXASJDD_XZQHDM;
	}
	public void setFXASJDD_XZQHDM(String fXASJDD_XZQHDM) {
		FXASJDD_XZQHDM = fXASJDD_XZQHDM;
	}
	public String getFXASJDD_DZMC() {
		return FXASJDD_DZMC;
	}
	public void setFXASJDD_DZMC(String fXASJDD_DZMC) {
		FXASJDD_DZMC = fXASJDD_DZMC;
	}
	public Date getASJFSSJ_ASJFSKSSJ() {
		return ASJFSSJ_ASJFSKSSJ;
	}
	public void setASJFSSJ_ASJFSKSSJ(Date aSJFSSJ_ASJFSKSSJ) {
		ASJFSSJ_ASJFSKSSJ = aSJFSSJ_ASJFSKSSJ;
	}
	public String getASJFSSJ_RSDDM() {
		return ASJFSSJ_RSDDM;
	}
	public void setASJFSSJ_RSDDM(String aSJFSSJ_RSDDM) {
		ASJFSSJ_RSDDM = aSJFSSJ_RSDDM;
	}
	public String getASJFSSJ_ZASJ_ZASJLBDM() {
		return ASJFSSJ_ZASJ_ZASJLBDM;
	}
	public void setASJFSSJ_ZASJ_ZASJLBDM(String aSJFSSJ_ZASJ_ZASJLBDM) {
		ASJFSSJ_ZASJ_ZASJLBDM = aSJFSSJ_ZASJ_ZASJLBDM;
	}
	public String getASJFSSJ_ZASJ_DMBCMS() {
		return ASJFSSJ_ZASJ_DMBCMS;
	}
	public void setASJFSSJ_ZASJ_DMBCMS(String aSJFSSJ_ZASJ_DMBCMS) {
		ASJFSSJ_ZASJ_DMBCMS = aSJFSSJ_ZASJ_DMBCMS;
	}
	public String getASJFSDD_XZQHDM() {
		return ASJFSDD_XZQHDM;
	}
	public void setASJFSDD_XZQHDM(String aSJFSDD_XZQHDM) {
		ASJFSDD_XZQHDM = aSJFSDD_XZQHDM;
	}
	public String getASJFSDD_DZMC() {
		return ASJFSDD_DZMC;
	}
	public void setASJFSDD_DZMC(String aSJFSDD_DZMC) {
		ASJFSDD_DZMC = aSJFSDD_DZMC;
	}
	public String getASJFSDD_DQJD() {
		return ASJFSDD_DQJD;
	}
	public void setASJFSDD_DQJD(String aSJFSDD_DQJD) {
		ASJFSDD_DQJD = aSJFSDD_DQJD;
	}
	public String getASJFSDD_DQWD() {
		return ASJFSDD_DQWD;
	}
	public void setASJFSDD_DQWD(String aSJFSDD_DQWD) {
		ASJFSDD_DQWD = aSJFSDD_DQWD;
	}
	public String getASJFSDD_DYLBDM() {
		return ASJFSDD_DYLBDM;
	}
	public void setASJFSDD_DYLBDM(String aSJFSDD_DYLBDM) {
		ASJFSDD_DYLBDM = aSJFSDD_DYLBDM;
	}
	public String getASJFSDD_SACS_SACSLBDM() {
		return ASJFSDD_SACS_SACSLBDM;
	}
	public void setASJFSDD_SACS_SACSLBDM(String aSJFSDD_SACS_SACSLBDM) {
		ASJFSDD_SACS_SACSLBDM = aSJFSDD_SACS_SACSLBDM;
	}
	public String getASJFSDD_SACS_DMBCMS() {
		return ASJFSDD_SACS_DMBCMS;
	}
	public void setASJFSDD_SACS_DMBCMS(String aSJFSDD_SACS_DMBCMS) {
		ASJFSDD_SACS_DMBCMS = aSJFSDD_SACS_DMBCMS;
	}
	public String getASJFSDD_KJBW_KJBWLBDM() {
		return ASJFSDD_KJBW_KJBWLBDM;
	}
	public void setASJFSDD_KJBW_KJBWLBDM(String aSJFSDD_KJBW_KJBWLBDM) {
		ASJFSDD_KJBW_KJBWLBDM = aSJFSDD_KJBW_KJBWLBDM;
	}
	public String getASJFSDD_KJBW_DMBCMS() {
		return ASJFSDD_KJBW_DMBCMS;
	}
	public void setASJFSDD_KJBW_DMBCMS(String aSJFSDD_KJBW_DMBCMS) {
		ASJFSDD_KJBW_DMBCMS = aSJFSDD_KJBW_DMBCMS;
	}
	public String getASJFSDD_SFJZWN_PDBZ() {
		return ASJFSDD_SFJZWN_PDBZ;
	}
	public void setASJFSDD_SFJZWN_PDBZ(String aSJFSDD_SFJZWN_PDBZ) {
		ASJFSDD_SFJZWN_PDBZ = aSJFSDD_SFJZWN_PDBZ;
	}
	public String getASJFSDD_JZWCS() {
		return ASJFSDD_JZWCS;
	}
	public void setASJFSDD_JZWCS(String aSJFSDD_JZWCS) {
		ASJFSDD_JZWCS = aSJFSDD_JZWCS;
	}
	public String getASJFSDD_ASJFSLC() {
		return ASJFSDD_ASJFSLC;
	}
	public void setASJFSDD_ASJFSLC(String aSJFSDD_ASJFSLC) {
		ASJFSDD_ASJFSLC = aSJFSDD_ASJFSLC;
	}
	public String getASJFSDD_SFZLZZ_PDBZ() {
		return ASJFSDD_SFZLZZ_PDBZ;
	}
	public void setASJFSDD_SFZLZZ_PDBZ(String aSJFSDD_SFZLZZ_PDBZ) {
		ASJFSDD_SFZLZZ_PDBZ = aSJFSDD_SFZLZZ_PDBZ;
	}
	public Blob getJYAQ() {
		return JYAQ;
	}
	public void setJYAQ(Blob jYAQ) {
		JYAQ = jYAQ;
	}
	public String getSFSQ_PDBZ() {
		return SFSQ_PDBZ;
	}
	public void setSFSQ_PDBZ(String sFSQ_PDBZ) {
		SFSQ_PDBZ = sFSQ_PDBZ;
	}
	public String getSFSB_PDBZ() {
		return SFSB_PDBZ;
	}
	public void setSFSB_PDBZ(String sFSB_PDBZ) {
		SFSB_PDBZ = sFSB_PDBZ;
	}
	public String getSFMA_PDBZ() {
		return SFMA_PDBZ;
	}
	public void setSFMA_PDBZ(String sFMA_PDBZ) {
		SFMA_PDBZ = sFMA_PDBZ;
	}
	public String getSFSH_PDBZ() {
		return SFSH_PDBZ;
	}
	public void setSFSH_PDBZ(String sFSH_PDBZ) {
		SFSH_PDBZ = sFSH_PDBZ;
	}
	public String getSFSJDQPCL_PDBZ() {
		return SFSJDQPCL_PDBZ;
	}
	public void setSFSJDQPCL_PDBZ(String sFSJDQPCL_PDBZ) {
		SFSJDQPCL_PDBZ = sFSJDQPCL_PDBZ;
	}
	public String getSFSW_PDBZ() {
		return SFSW_PDBZ;
	}
	public void setSFSW_PDBZ(String sFSW_PDBZ) {
		SFSW_PDBZ = sFSW_PDBZ;
	}
	public String getSWASJSWQK() {
		return SWASJSWQK;
	}
	public void setSWASJSWQK(String sWASJSWQK) {
		SWASJSWQK = sWASJSWQK;
	}
	public String getASJDBJBDM() {
		return ASJDBJBDM;
	}
	public void setASJDBJBDM(String aSJDBJBDM) {
		ASJDBJBDM = aSJDBJBDM;
	}
	public String getLLFS_LLFSDM() {
		return LLFS_LLFSDM;
	}
	public void setLLFS_LLFSDM(String lLFS_LLFSDM) {
		LLFS_LLFSDM = lLFS_LLFSDM;
	}
	public String getLLFS_DMBCMS() {
		return LLFS_DMBCMS;
	}
	public void setLLFS_DMBCMS(String lLFS_DMBCMS) {
		LLFS_DMBCMS = lLFS_DMBCMS;
	}
	public Date getLLSJ() {
		return LLSJ;
	}
	public void setLLSJ(Date lLSJ) {
		LLSJ = lLSJ;
	}
	public Blob getSY_JYQK() {
		return SY_JYQK;
	}
	public void setSY_JYQK(Blob sY_JYQK) {
		SY_JYQK = sY_JYQK;
	}
	public String getZAZBSD_ZAZBSDDM() {
		return ZAZBSD_ZAZBSDDM;
	}
	public void setZAZBSD_ZAZBSDDM(String zAZBSD_ZAZBSDDM) {
		ZAZBSD_ZAZBSDDM = zAZBSD_ZAZBSDDM;
	}
	public String getZAZBSD_DMBCMS() {
		return ZAZBSD_DMBCMS;
	}
	public void setZAZBSD_DMBCMS(String zAZBSD_DMBCMS) {
		ZAZBSD_DMBCMS = zAZBSD_DMBCMS;
	}
	public String getJJSD_JJSDDM() {
		return JJSD_JJSDDM;
	}
	public void setJJSD_JJSDDM(String jJSD_JJSDDM) {
		JJSD_JJSDDM = jJSD_JJSDDM;
	}
	public String getJJSD_DMBCMS() {
		return JJSD_DMBCMS;
	}
	public void setJJSD_DMBCMS(String jJSD_DMBCMS) {
		JJSD_DMBCMS = jJSD_DMBCMS;
	}
	public String getMCMYSD_MCMYSDDM() {
		return MCMYSD_MCMYSDDM;
	}
	public void setMCMYSD_MCMYSDDM(String mCMYSD_MCMYSDDM) {
		MCMYSD_MCMYSDDM = mCMYSD_MCMYSDDM;
	}
	public String getMCMYSD_DMBCMS() {
		return MCMYSD_DMBCMS;
	}
	public void setMCMYSD_DMBCMS(String mCMYSD_DMBCMS) {
		MCMYSD_DMBCMS = mCMYSD_DMBCMS;
	}
	public String getMCSF_MCSFDM() {
		return MCSF_MCSFDM;
	}
	public void setMCSF_MCSFDM(String mCSF_MCSFDM) {
		MCSF_MCSFDM = mCSF_MCSFDM;
	}
	public String getMCSF_DMBCMS() {
		return MCSF_DMBCMS;
	}
	public void setMCSF_DMBCMS(String mCSF_DMBCMS) {
		MCSF_DMBCMS = mCSF_DMBCMS;
	}
	public String getMCGXR_MCGXRDM() {
		return MCGXR_MCGXRDM;
	}
	public void setMCGXR_MCGXRDM(String mCGXR_MCGXRDM) {
		MCGXR_MCGXRDM = mCGXR_MCGXRDM;
	}
	public String getMCGXR_DMBCMS() {
		return MCGXR_DMBCMS;
	}
	public void setMCGXR_DMBCMS(String mCGXR_DMBCMS) {
		MCGXR_DMBCMS = mCGXR_DMBCMS;
	}
	public String getMYDWMY_MCDWMYDM() {
		return MYDWMY_MCDWMYDM;
	}
	public void setMYDWMY_MCDWMYDM(String mYDWMY_MCDWMYDM) {
		MYDWMY_MCDWMYDM = mYDWMY_MCDWMYDM;
	}
	public String getMYDWMY_DMBCMS() {
		return MYDWMY_DMBCMS;
	}
	public void setMYDWMY_DMBCMS(String mYDWMY_DMBCMS) {
		MYDWMY_DMBCMS = mYDWMY_DMBCMS;
	}
	public String getZPSD_ZPSDDM() {
		return ZPSD_ZPSDDM;
	}
	public void setZPSD_ZPSDDM(String zPSD_ZPSDDM) {
		ZPSD_ZPSDDM = zPSD_ZPSDDM;
	}
	public String getZPSD_DMBCMS() {
		return ZPSD_DMBCMS;
	}
	public void setZPSD_DMBCMS(String zPSD_DMBCMS) {
		ZPSD_DMBCMS = zPSD_DMBCMS;
	}
	public String getXPSD_XPSDDM() {
		return XPSD_XPSDDM;
	}
	public void setXPSD_XPSDDM(String xPSD_XPSDDM) {
		XPSD_XPSDDM = xPSD_XPSDDM;
	}
	public String getXPSD_DMBCMS() {
		return XPSD_DMBCMS;
	}
	public void setXPSD_DMBCMS(String xPSD_DMBCMS) {
		XPSD_DMBCMS = xPSD_DMBCMS;
	}
	public String getXPSYWP_SAWPDM() {
		return XPSYWP_SAWPDM;
	}
	public void setXPSYWP_SAWPDM(String xPSYWP_SAWPDM) {
		XPSYWP_SAWPDM = xPSYWP_SAWPDM;
	}
	public String getXPSYWP_DMBCMS() {
		return XPSYWP_DMBCMS;
	}
	public void setXPSYWP_DMBCMS(String xPSYWP_DMBCMS) {
		XPSYWP_DMBCMS = xPSYWP_DMBCMS;
	}
	public String getSBSD_SBSDDM() {
		return SBSD_SBSDDM;
	}
	public void setSBSD_SBSDDM(String sBSD_SBSDDM) {
		SBSD_SBSDDM = sBSD_SBSDDM;
	}
	public String getSBSD_DMBCMS() {
		return SBSD_DMBCMS;
	}
	public void setSBSD_DMBCMS(String sBSD_DMBCMS) {
		SBSD_DMBCMS = sBSD_DMBCMS;
	}
	public String getJRJZKJFSJSD_JRJZKJFSJSDDM() {
		return JRJZKJFSJSD_JRJZKJFSJSDDM;
	}
	public void setJRJZKJFSJSD_JRJZKJFSJSDDM(String jRJZKJFSJSD_JRJZKJFSJSDDM) {
		JRJZKJFSJSD_JRJZKJFSJSDDM = jRJZKJFSJSD_JRJZKJFSJSDDM;
	}
	public String getJRJZKJFSJSD_DMBCMS() {
		return JRJZKJFSJSD_DMBCMS;
	}
	public void setJRJZKJFSJSD_DMBCMS(String jRJZKJFSJSD_DMBCMS) {
		JRJZKJFSJSD_DMBCMS = jRJZKJFSJSD_DMBCMS;
	}
	public String getLKJZKJFS_LKJZKJFSDM() {
		return LKJZKJFS_LKJZKJFSDM;
	}
	public void setLKJZKJFS_LKJZKJFSDM(String lKJZKJFS_LKJZKJFSDM) {
		LKJZKJFS_LKJZKJFSDM = lKJZKJFS_LKJZKJFSDM;
	}
	public String getLKJZKJFS_DMBCMS() {
		return LKJZKJFS_DMBCMS;
	}
	public void setLKJZKJFS_DMBCMS(String lKJZKJFS_DMBCMS) {
		LKJZKJFS_DMBCMS = lKJZKJFS_DMBCMS;
	}
	public String getJSSD_JSSDDM() {
		return JSSD_JSSDDM;
	}
	public void setJSSD_JSSDDM(String jSSD_JSSDDM) {
		JSSD_JSSDDM = jSSD_JSSDDM;
	}
	public String getJSSD_DMBCMS() {
		return JSSD_DMBCMS;
	}
	public void setJSSD_DMBCMS(String jSSD_DMBCMS) {
		JSSD_DMBCMS = jSSD_DMBCMS;
	}
	public String getXTKJTPSD_XTKJTPSDDM() {
		return XTKJTPSD_XTKJTPSDDM;
	}
	public void setXTKJTPSD_XTKJTPSDDM(String xTKJTPSD_XTKJTPSDDM) {
		XTKJTPSD_XTKJTPSDDM = xTKJTPSD_XTKJTPSDDM;
	}
	public String getXTKJTPSD_DMBCMS() {
		return XTKJTPSD_DMBCMS;
	}
	public void setXTKJTPSD_DMBCMS(String xTKJTPSD_DMBCMS) {
		XTKJTPSD_DMBCMS = xTKJTPSD_DMBCMS;
	}
	public String getQQSD_QQSDDM() {
		return QQSD_QQSDDM;
	}
	public void setQQSD_QQSDDM(String qQSD_QQSDDM) {
		QQSD_QQSDDM = qQSD_QQSDDM;
	}
	public String getQQSD_DMBCMS() {
		return QQSD_DMBCMS;
	}
	public void setQQSD_DMBCMS(String qQSD_DMBCMS) {
		QQSD_DMBCMS = qQSD_DMBCMS;
	}
	public String getYBSDDM() {
		return YBSDDM;
	}
	public void setYBSDDM(String yBSDDM) {
		YBSDDM = yBSDDM;
	}
	public String getWLZASD_WLZASDDM() {
		return WLZASD_WLZASDDM;
	}
	public void setWLZASD_WLZASDDM(String wLZASD_WLZASDDM) {
		WLZASD_WLZASDDM = wLZASD_WLZASDDM;
	}
	public String getWLZASD_DMBCMS() {
		return WLZASD_DMBCMS;
	}
	public void setWLZASD_DMBCMS(String wLZASD_DMBCMS) {
		WLZASD_DMBCMS = wLZASD_DMBCMS;
	}
	public String getXCWPFDCD_XCWPFDCDDM() {
		return XCWPFDCD_XCWPFDCDDM;
	}
	public void setXCWPFDCD_XCWPFDCDDM(String xCWPFDCD_XCWPFDCDDM) {
		XCWPFDCD_XCWPFDCDDM = xCWPFDCD_XCWPFDCDDM;
	}
	public String getXCWPFDCD_DMBCMS() {
		return XCWPFDCD_DMBCMS;
	}
	public void setXCWPFDCD_DMBCMS(String xCWPFDCD_DMBCMS) {
		XCWPFDCD_DMBCMS = xCWPFDCD_DMBCMS;
	}
	public String getGRZCSD_GRZCSDDM() {
		return GRZCSD_GRZCSDDM;
	}
	public void setGRZCSD_GRZCSDDM(String gRZCSD_GRZCSDDM) {
		GRZCSD_GRZCSDDM = gRZCSD_GRZCSDDM;
	}
	public String getGRZCSD_DMBCMS() {
		return GRZCSD_DMBCMS;
	}
	public void setGRZCSD_DMBCMS(String gRZCSD_DMBCMS) {
		GRZCSD_DMBCMS = gRZCSD_DMBCMS;
	}
	public String getFZXYRTSXW_FZXYRTSXWDM() {
		return FZXYRTSXW_FZXYRTSXWDM;
	}
	public void setFZXYRTSXW_FZXYRTSXWDM(String fZXYRTSXW_FZXYRTSXWDM) {
		FZXYRTSXW_FZXYRTSXWDM = fZXYRTSXW_FZXYRTSXWDM;
	}
	public String getFZXYRTSXW_DMBCMS() {
		return FZXYRTSXW_DMBCMS;
	}
	public void setFZXYRTSXW_DMBCMS(String fZXYRTSXW_DMBCMS) {
		FZXYRTSXW_DMBCMS = fZXYRTSXW_DMBCMS;
	}
	public String getFZXYRSXZAHJQK() {
		return FZXYRSXZAHJQK;
	}
	public void setFZXYRSXZAHJQK(String fZXYRSXZAHJQK) {
		FZXYRSXZAHJQK = fZXYRSXZAHJQK;
	}
	public Blob getQTSDTD_JYQK() {
		return QTSDTD_JYQK;
	}
	public void setQTSDTD_JYQK(Blob qTSDTD_JYQK) {
		QTSDTD_JYQK = qTSDTD_JYQK;
	}
	public Date getASJFSSJFX_ASJFSKSSJ() {
		return ASJFSSJFX_ASJFSKSSJ;
	}
	public void setASJFSSJFX_ASJFSKSSJ(Date aSJFSSJFX_ASJFSKSSJ) {
		ASJFSSJFX_ASJFSKSSJ = aSJFSSJFX_ASJFSKSSJ;
	}
	public Date getASJFSSJFX_ASJFSJSSJ() {
		return ASJFSSJFX_ASJFSJSSJ;
	}
	public void setASJFSSJFX_ASJFSJSSJ(Date aSJFSSJFX_ASJFSJSSJ) {
		ASJFSSJFX_ASJFSJSSJ = aSJFSSJFX_ASJFSJSSJ;
	}
	public String getZARFX_RSXX() {
		return ZARFX_RSXX;
	}
	public void setZARFX_RSXX(String zARFX_RSXX) {
		ZARFX_RSXX = zARFX_RSXX;
	}
	public String getZARFX_RSSX() {
		return ZARFX_RSSX;
	}
	public void setZARFX_RSSX(String zARFX_RSSX) {
		ZARFX_RSSX = zARFX_RSSX;
	}
	public Blob getFZXYRTZ_JYQK() {
		return FZXYRTZ_JYQK;
	}
	public void setFZXYRTZ_JYQK(Blob fZXYRTZ_JYQK) {
		FZXYRTZ_JYQK = fZXYRTZ_JYQK;
	}
	public String getFZXYRTSZC_FZXYRTSZCDM() {
		return FZXYRTSZC_FZXYRTSZCDM;
	}
	public void setFZXYRTSZC_FZXYRTSZCDM(String fZXYRTSZC_FZXYRTSZCDM) {
		FZXYRTSZC_FZXYRTSZCDM = fZXYRTSZC_FZXYRTSZCDM;
	}
	public String getFZXYRTSZC_DMBCMS() {
		return FZXYRTSZC_DMBCMS;
	}
	public void setFZXYRTSZC_DMBCMS(String fZXYRTSZC_DMBCMS) {
		FZXYRTSZC_DMBCMS = fZXYRTSZC_DMBCMS;
	}
	public String getZAGJ_SAWPDM() {
		return ZAGJ_SAWPDM;
	}
	public void setZAGJ_SAWPDM(String zAGJ_SAWPDM) {
		ZAGJ_SAWPDM = zAGJ_SAWPDM;
	}
	public Blob getZAGJ_JYQK() {
		return ZAGJ_JYQK;
	}
	public void setZAGJ_JYQK(Blob zAGJ_JYQK) {
		ZAGJ_JYQK = zAGJ_JYQK;
	}
	public Blob getZADJMS() {
		return ZADJMS;
	}
	public void setZADJMS(Blob zADJMS) {
		ZADJMS = zADJMS;
	}
	public Blob getZAMDMS() {
		return ZAMDMS;
	}
	public void setZAMDMS(Blob zAMDMS) {
		ZAMDMS = zAMDMS;
	}
	public String getKJFWDM() {
		return KJFWDM;
	}
	public void setKJFWDM(String kJFWDM) {
		KJFWDM = kJFWDM;
	}
	public Blob getLCZA_JYQK() {
		return LCZA_JYQK;
	}
	public void setLCZA_JYQK(Blob lCZA_JYQK) {
		LCZA_JYQK = lCZA_JYQK;
	}
	public Blob getJHZA_JYQK() {
		return JHZA_JYQK;
	}
	public void setJHZA_JYQK(Blob jHZA_JYQK) {
		JHZA_JYQK = jHZA_JYQK;
	}
	public String getGTFZFZXYRZHXSDM() {
		return GTFZFZXYRZHXSDM;
	}
	public void setGTFZFZXYRZHXSDM(String gTFZFZXYRZHXSDM) {
		GTFZFZXYRZHXSDM = gTFZFZXYRZHXSDM;
	}
	public String getGTFZFZXYRGC_GTFZFZXYRGCDM() {
		return GTFZFZXYRGC_GTFZFZXYRGCDM;
	}
	public void setGTFZFZXYRGC_GTFZFZXYRGCDM(String gTFZFZXYRGC_GTFZFZXYRGCDM) {
		GTFZFZXYRGC_GTFZFZXYRGCDM = gTFZFZXYRGC_GTFZFZXYRGCDM;
	}
	public String getGTFZFZXYRGC_DMBCMS() {
		return GTFZFZXYRGC_DMBCMS;
	}
	public void setGTFZFZXYRGC_DMBCMS(String gTFZFZXYRGC_DMBCMS) {
		GTFZFZXYRGC_DMBCMS = gTFZFZXYRGC_DMBCMS;
	}
	public Blob getSSWPQX_JYQK() {
		return SSWPQX_JYQK;
	}
	public void setSSWPQX_JYQK(Blob sSWPQX_JYQK) {
		SSWPQX_JYQK = sSWPQX_JYQK;
	}
	public Blob getFXXS_JYQK() {
		return FXXS_JYQK;
	}
	public void setFXXS_JYQK(Blob fXXS_JYQK) {
		FXXS_JYQK = fXXS_JYQK;
	}
	public Number getASJSWRY_RS() {
		return ASJSWRY_RS;
	}
	public void setASJSWRY_RS(Number aSJSWRY_RS) {
		ASJSWRY_RS = aSJSWRY_RS;
	}
	public Number getASJSSRY_RS() {
		return ASJSSRY_RS;
	}
	public void setASJSSRY_RS(Number aSJSSRY_RS) {
		ASJSSRY_RS = aSJSSRY_RS;
	}
	public Blob getASJSSCW_JYQK() {
		return ASJSSCW_JYQK;
	}
	public void setASJSSCW_JYQK(Blob aSJSSCW_JYQK) {
		ASJSSCW_JYQK = aSJSSCW_JYQK;
	}
	public String getSSJZRMBY() {
		return SSJZRMBY;
	}
	public void setSSJZRMBY(String sSJZRMBY) {
		SSJZRMBY = sSJZRMBY;
	}
	public String getFZXYRYLWP_JYQK() {
		return FZXYRYLWP_JYQK;
	}
	public void setFZXYRYLWP_JYQK(String fZXYRYLWP_JYQK) {
		FZXYRYLWP_JYQK = fZXYRYLWP_JYQK;
	}
	public Blob getXCKYWP_JYQK() {
		return XCKYWP_JYQK;
	}
	public void setXCKYWP_JYQK(Blob xCKYWP_JYQK) {
		XCKYWP_JYQK = xCKYWP_JYQK;
	}
	public Number getKYZSQZ_WPSL() {
		return KYZSQZ_WPSL;
	}
	public void setKYZSQZ_WPSL(Number kYZSQZ_WPSL) {
		KYZSQZ_WPSL = kYZSQZ_WPSL;
	}
	public Number getKYMYQZ_WPSL() {
		return KYMYQZ_WPSL;
	}
	public void setKYMYQZ_WPSL(Number kYMYQZ_WPSL) {
		KYMYQZ_WPSL = kYMYQZ_WPSL;
	}
	public Number getKYZZQZ_WPSL() {
		return KYZZQZ_WPSL;
	}
	public void setKYZZQZ_WPSL(Number kYZZQZ_WPSL) {
		KYZZQZ_WPSL = kYZZQZ_WPSL;
	}
	public Number getKYFZQZ_WPSL() {
		return KYFZQZ_WPSL;
	}
	public void setKYFZQZ_WPSL(Number kYFZQZ_WPSL) {
		KYFZQZ_WPSL = kYFZQZ_WPSL;
	}
	public Number getKYQTQZ_WPSL() {
		return KYQTQZ_WPSL;
	}
	public void setKYQTQZ_WPSL(Number kYQTQZ_WPSL) {
		KYQTQZ_WPSL = kYQTQZ_WPSL;
	}
	public Date getSLSJ() {
		return SLSJ;
	}
	public void setSLSJ(Date sLSJ) {
		SLSJ = sLSJ;
	}
	public String getSLDW_GAJGJGDM() {
		return SLDW_GAJGJGDM;
	}
	public void setSLDW_GAJGJGDM(String sLDW_GAJGJGDM) {
		SLDW_GAJGJGDM = sLDW_GAJGJGDM;
	}
	public String getSLDW_GAJGMC() {
		return SLDW_GAJGMC;
	}
	public void setSLDW_GAJGMC(String sLDW_GAJGMC) {
		SLDW_GAJGMC = sLDW_GAJGMC;
	}
	public Date getLARQ() {
		return LARQ;
	}
	public void setLARQ(Date lARQ) {
		LARQ = lARQ;
	}
	public String getLADW_GAJGJGDM() {
		return LADW_GAJGJGDM;
	}
	public void setLADW_GAJGJGDM(String lADW_GAJGJGDM) {
		LADW_GAJGJGDM = lADW_GAJGJGDM;
	}
	public String getLADW_GAJGMC() {
		return LADW_GAJGMC;
	}
	public void setLADW_GAJGMC(String lADW_GAJGMC) {
		LADW_GAJGMC = lADW_GAJGMC;
	}
	public String getZARY_RS() {
		return ZARY_RS;
	}
	public void setZARY_RS(String zARY_RS) {
		ZARY_RS = zARY_RS;
	}
	public String getSJCWJZRMBY() {
		return SJCWJZRMBY;
	}
	public void setSJCWJZRMBY(String sJCWJZRMBY) {
		SJCWJZRMBY = sJCWJZRMBY;
	}
	public Blob getZCZJ_ZCXWYJMS() {
		return ZCZJ_ZCXWYJMS;
	}
	public void setZCZJ_ZCXWYJMS(Blob zCZJ_ZCXWYJMS) {
		ZCZJ_ZCXWYJMS = zCZJ_ZCXWYJMS;
	}
	public Date getZCZJ_ZXSJ01() {
		return ZCZJ_ZXSJ01;
	}
	public void setZCZJ_ZXSJ01(Date zCZJ_ZXSJ01) {
		ZCZJ_ZXSJ01 = zCZJ_ZXSJ01;
	}
	public String getZCZJDW_GAJGJGDM() {
		return ZCZJDW_GAJGJGDM;
	}
	public void setZCZJDW_GAJGJGDM(String zCZJDW_GAJGJGDM) {
		ZCZJDW_GAJGJGDM = zCZJDW_GAJGJGDM;
	}
	public String getZCZJDW_GAJGMC() {
		return ZCZJDW_GAJGMC;
	}
	public void setZCZJDW_GAJGMC(String zCZJDW_GAJGMC) {
		ZCZJDW_GAJGMC = zCZJDW_GAJGMC;
	}
	public String getYSSCQSRQ() {
		return YSSCQSRQ;
	}
	public void setYSSCQSRQ(String ySSCQSRQ) {
		YSSCQSRQ = ySSCQSRQ;
	}
	public String getYSSCQSAJSCFHJDDM() {
		return YSSCQSAJSCFHJDDM;
	}
	public void setYSSCQSAJSCFHJDDM(String ySSCQSAJSCFHJDDM) {
		YSSCQSAJSCFHJDDM = ySSCQSAJSCFHJDDM;
	}
	public String getSFCXAJ_PDBZ() {
		return SFCXAJ_PDBZ;
	}
	public void setSFCXAJ_PDBZ(String sFCXAJ_PDBZ) {
		SFCXAJ_PDBZ = sFCXAJ_PDBZ;
	}
	public String getCXAJYYDM() {
		return CXAJYYDM;
	}
	public void setCXAJYYDM(String cXAJYYDM) {
		CXAJYYDM = cXAJYYDM;
	}
	public String getCXAJDW_GAJGJGDM() {
		return CXAJDW_GAJGJGDM;
	}
	public void setCXAJDW_GAJGJGDM(String cXAJDW_GAJGJGDM) {
		CXAJDW_GAJGJGDM = cXAJDW_GAJGJGDM;
	}
	public String getCXAJDW_GAJGMC() {
		return CXAJDW_GAJGMC;
	}
	public void setCXAJDW_GAJGMC(String cXAJDW_GAJGMC) {
		CXAJDW_GAJGMC = cXAJDW_GAJGMC;
	}
	public Date getCXAJRQ() {
		return CXAJRQ;
	}
	public void setCXAJRQ(Date cXAJRQ) {
		CXAJRQ = cXAJRQ;
	}
}
