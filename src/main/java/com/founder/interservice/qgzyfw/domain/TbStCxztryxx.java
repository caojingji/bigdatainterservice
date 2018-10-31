package com.founder.interservice.qgzyfw.domain;

import java.sql.Blob;
import java.util.Date;

public class TbStCxztryxx {
	public String ZTRYBH;//在逃人员编号
	public String ZTRYLXDM;//在逃人员类型代码
	public String ASJBH;//案事件编号
	public Date LARQ;//立案日期
	public String LADWFLDM;//立案单位分类代码
	public String LADW_GAJGJGDM;//立案单位_公安机关机构代码
	public String LADW_GAJGMC;//立案单位_公安机关名称
	public String AJLBDM;//案件类别代码
	public Blob JYAQ;//简要案情
	public String TJLBH;//通缉令编号
	public String TJJBDM;//通缉级别代码
	public String BDB_PDBZ;//部督捕_判断标识
	public String ZTJJ;//抓逃奖金
	public Date TPSJ;//逃跑时间
	public String JNWQXDM;//境内外去向代码
	public String RYCJQRYJ;//人员出境确认依据
	public Blob TPFX_JYQK;//逃跑方向_简要情况
	public Date ZJCJRQ;//最近出境日期
	public Date BIK_KSSJ;//边控_开始时间
	public Date BIK_JSSJ;//边控_结束时间
	public String HSTBHM;//红色通报号码
	public Date HSTBDQRQ;//红色通报到期日期
	public String FZXYRDAZTDM;//犯罪嫌疑人到案状态代码
	public String SSJZRMBY;//损失价值（人民币元）
	public String WHJZ;//挽回价值（万元）
	public String XM;//姓名
	public String CYM;//曾用名
	public String BMCH;//别名/绰号
	public String CYZJDM;//常用证件代码
	public String ZJHM;//证件号码
	public Date CSRQ;//出生日期
	public String NLSX;//年龄上限
	public String NLXX;//年龄下限
	public String XBDM;//性别代码
	public String GJDM;//国籍代码
	public String JGDM;//籍贯省市县代码
	public String KY;//口音
	public String MZDM;//民族代码
	public String HJDZ_XZQHDM;//户籍地址_行政区划代码
	public String HJDZ_DZMC;//户籍地址_地址名称
	public String XZZ_XZQHDM;//现住址_行政区划代码
	public String XZZ_DZMC;//现住址_地址名称
	public String SGXX;//身高下限
	public String SGSX;//身高上限
	public Blob TMTZMS;//体貌特征描述
	public Blob TBBJMS;//体表标记描述
	public String ZYLBDM;//职业类别代码
	public String SZZWBH;//十指指纹编号
	public String ZTRY_DNABH;//在逃人员_DNA编号
	public Date SWSJ;//死亡时间
	public String ZHFZXYR_PDBZ;//抓获犯罪嫌疑人_判断标识
	public Date ZHRQ;//抓获日期
	public String ZHDD_XZQHDM;//抓获地点_行政区划代码
	public String ZHDD_DZMC;//抓获地点_地址名称
	public String ZHDWFLDM;//抓获单位分类
	public String ZHDW_GAJGJGDM;//抓获单位_公安机关机构代码
	public String ZHDW_GAJGMC;//抓获单位_公安机关名称
	public String JWZBXZDW_DWMC;//境外抓捕协助单位_单位名称
	public String ZHFSDM;//抓获方式代码
	public String ZHDD_XZQHDM_DMBCMS;//抓获地点_行政区划代码_补充描述
	public String ZHDWFLDM_DMBCMS;//抓获单位分类_代码补充描述
	public String XZDWFLDM_DMBCMS;//协助单位分类_代码补充描述
	public String CXDW_GAJGJGDM;//撤销单位_公安机关机构代码
	public String CXDW_GAJGMC;//撤销单位_公安机关名称
	public Date CXSJ;//撤销时间
	public String getZTRYBH() {
		return ZTRYBH;
	}
	public void setZTRYBH(String zTRYBH) {
		ZTRYBH = zTRYBH;
	}
	public String getZTRYLXDM() {
		return ZTRYLXDM;
	}
	public void setZTRYLXDM(String zTRYLXDM) {
		ZTRYLXDM = zTRYLXDM;
	}
	public String getASJBH() {
		return ASJBH;
	}
	public void setASJBH(String aSJBH) {
		ASJBH = aSJBH;
	}
	public Date getLARQ() {
		return LARQ;
	}
	public void setLARQ(Date lARQ) {
		LARQ = lARQ;
	}
	public String getLADWFLDM() {
		return LADWFLDM;
	}
	public void setLADWFLDM(String lADWFLDM) {
		LADWFLDM = lADWFLDM;
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
	public String getAJLBDM() {
		return AJLBDM;
	}
	public void setAJLBDM(String aJLBDM) {
		AJLBDM = aJLBDM;
	}
	public Blob getJYAQ() {
		return JYAQ;
	}
	public void setJYAQ(Blob jYAQ) {
		JYAQ = jYAQ;
	}
	public String getTJLBH() {
		return TJLBH;
	}
	public void setTJLBH(String tJLBH) {
		TJLBH = tJLBH;
	}
	public String getTJJBDM() {
		return TJJBDM;
	}
	public void setTJJBDM(String tJJBDM) {
		TJJBDM = tJJBDM;
	}
	public String getBDB_PDBZ() {
		return BDB_PDBZ;
	}
	public void setBDB_PDBZ(String bDB_PDBZ) {
		BDB_PDBZ = bDB_PDBZ;
	}
	public String getZTJJ() {
		return ZTJJ;
	}
	public void setZTJJ(String zTJJ) {
		ZTJJ = zTJJ;
	}
	public Date getTPSJ() {
		return TPSJ;
	}
	public void setTPSJ(Date tPSJ) {
		TPSJ = tPSJ;
	}
	public String getJNWQXDM() {
		return JNWQXDM;
	}
	public void setJNWQXDM(String jNWQXDM) {
		JNWQXDM = jNWQXDM;
	}
	public String getRYCJQRYJ() {
		return RYCJQRYJ;
	}
	public void setRYCJQRYJ(String rYCJQRYJ) {
		RYCJQRYJ = rYCJQRYJ;
	}
	public Blob getTPFX_JYQK() {
		return TPFX_JYQK;
	}
	public void setTPFX_JYQK(Blob tPFX_JYQK) {
		TPFX_JYQK = tPFX_JYQK;
	}
	public Date getZJCJRQ() {
		return ZJCJRQ;
	}
	public void setZJCJRQ(Date zJCJRQ) {
		ZJCJRQ = zJCJRQ;
	}
	public Date getBIK_KSSJ() {
		return BIK_KSSJ;
	}
	public void setBIK_KSSJ(Date bIK_KSSJ) {
		BIK_KSSJ = bIK_KSSJ;
	}
	public Date getBIK_JSSJ() {
		return BIK_JSSJ;
	}
	public void setBIK_JSSJ(Date bIK_JSSJ) {
		BIK_JSSJ = bIK_JSSJ;
	}
	public String getHSTBHM() {
		return HSTBHM;
	}
	public void setHSTBHM(String hSTBHM) {
		HSTBHM = hSTBHM;
	}
	public Date getHSTBDQRQ() {
		return HSTBDQRQ;
	}
	public void setHSTBDQRQ(Date hSTBDQRQ) {
		HSTBDQRQ = hSTBDQRQ;
	}
	public String getFZXYRDAZTDM() {
		return FZXYRDAZTDM;
	}
	public void setFZXYRDAZTDM(String fZXYRDAZTDM) {
		FZXYRDAZTDM = fZXYRDAZTDM;
	}
	public String getSSJZRMBY() {
		return SSJZRMBY;
	}
	public void setSSJZRMBY(String sSJZRMBY) {
		SSJZRMBY = sSJZRMBY;
	}
	public String getWHJZ() {
		return WHJZ;
	}
	public void setWHJZ(String wHJZ) {
		WHJZ = wHJZ;
	}
	public String getXM() {
		return XM;
	}
	public void setXM(String xM) {
		XM = xM;
	}
	public String getCYM() {
		return CYM;
	}
	public void setCYM(String cYM) {
		CYM = cYM;
	}
	public String getBMCH() {
		return BMCH;
	}
	public void setBMCH(String bMCH) {
		BMCH = bMCH;
	}
	public String getCYZJDM() {
		return CYZJDM;
	}
	public void setCYZJDM(String cYZJDM) {
		CYZJDM = cYZJDM;
	}
	public String getZJHM() {
		return ZJHM;
	}
	public void setZJHM(String zJHM) {
		ZJHM = zJHM;
	}
	public Date getCSRQ() {
		return CSRQ;
	}
	public void setCSRQ(Date cSRQ) {
		CSRQ = cSRQ;
	}
	public String getNLSX() {
		return NLSX;
	}
	public void setNLSX(String nLSX) {
		NLSX = nLSX;
	}
	public String getNLXX() {
		return NLXX;
	}
	public void setNLXX(String nLXX) {
		NLXX = nLXX;
	}
	public String getXBDM() {
		return XBDM;
	}
	public void setXBDM(String xBDM) {
		XBDM = xBDM;
	}
	public String getGJDM() {
		return GJDM;
	}
	public void setGJDM(String gJDM) {
		GJDM = gJDM;
	}
	public String getJGDM() {
		return JGDM;
	}
	public void setJGDM(String jGDM) {
		JGDM = jGDM;
	}
	public String getKY() {
		return KY;
	}
	public void setKY(String kY) {
		KY = kY;
	}
	public String getMZDM() {
		return MZDM;
	}
	public void setMZDM(String mZDM) {
		MZDM = mZDM;
	}
	public String getHJDZ_XZQHDM() {
		return HJDZ_XZQHDM;
	}
	public void setHJDZ_XZQHDM(String hJDZ_XZQHDM) {
		HJDZ_XZQHDM = hJDZ_XZQHDM;
	}
	public String getHJDZ_DZMC() {
		return HJDZ_DZMC;
	}
	public void setHJDZ_DZMC(String hJDZ_DZMC) {
		HJDZ_DZMC = hJDZ_DZMC;
	}
	public String getXZZ_XZQHDM() {
		return XZZ_XZQHDM;
	}
	public void setXZZ_XZQHDM(String xZZ_XZQHDM) {
		XZZ_XZQHDM = xZZ_XZQHDM;
	}
	public String getXZZ_DZMC() {
		return XZZ_DZMC;
	}
	public void setXZZ_DZMC(String xZZ_DZMC) {
		XZZ_DZMC = xZZ_DZMC;
	}
	public String getSGXX() {
		return SGXX;
	}
	public void setSGXX(String sGXX) {
		SGXX = sGXX;
	}
	public String getSGSX() {
		return SGSX;
	}
	public void setSGSX(String sGSX) {
		SGSX = sGSX;
	}
	public Blob getTMTZMS() {
		return TMTZMS;
	}
	public void setTMTZMS(Blob tMTZMS) {
		TMTZMS = tMTZMS;
	}
	public Blob getTBBJMS() {
		return TBBJMS;
	}
	public void setTBBJMS(Blob tBBJMS) {
		TBBJMS = tBBJMS;
	}
	public String getZYLBDM() {
		return ZYLBDM;
	}
	public void setZYLBDM(String zYLBDM) {
		ZYLBDM = zYLBDM;
	}
	public String getSZZWBH() {
		return SZZWBH;
	}
	public void setSZZWBH(String sZZWBH) {
		SZZWBH = sZZWBH;
	}
	public String getZTRY_DNABH() {
		return ZTRY_DNABH;
	}
	public void setZTRY_DNABH(String zTRY_DNABH) {
		ZTRY_DNABH = zTRY_DNABH;
	}
	public Date getSWSJ() {
		return SWSJ;
	}
	public void setSWSJ(Date sWSJ) {
		SWSJ = sWSJ;
	}
	public String getZHFZXYR_PDBZ() {
		return ZHFZXYR_PDBZ;
	}
	public void setZHFZXYR_PDBZ(String zHFZXYR_PDBZ) {
		ZHFZXYR_PDBZ = zHFZXYR_PDBZ;
	}
	public Date getZHRQ() {
		return ZHRQ;
	}
	public void setZHRQ(Date zHRQ) {
		ZHRQ = zHRQ;
	}
	public String getZHDD_XZQHDM() {
		return ZHDD_XZQHDM;
	}
	public void setZHDD_XZQHDM(String zHDD_XZQHDM) {
		ZHDD_XZQHDM = zHDD_XZQHDM;
	}
	public String getZHDD_DZMC() {
		return ZHDD_DZMC;
	}
	public void setZHDD_DZMC(String zHDD_DZMC) {
		ZHDD_DZMC = zHDD_DZMC;
	}
	public String getZHDWFLDM() {
		return ZHDWFLDM;
	}
	public void setZHDWFLDM(String zHDWFLDM) {
		ZHDWFLDM = zHDWFLDM;
	}
	public String getZHDW_GAJGJGDM() {
		return ZHDW_GAJGJGDM;
	}
	public void setZHDW_GAJGJGDM(String zHDW_GAJGJGDM) {
		ZHDW_GAJGJGDM = zHDW_GAJGJGDM;
	}
	public String getZHDW_GAJGMC() {
		return ZHDW_GAJGMC;
	}
	public void setZHDW_GAJGMC(String zHDW_GAJGMC) {
		ZHDW_GAJGMC = zHDW_GAJGMC;
	}
	public String getJWZBXZDW_DWMC() {
		return JWZBXZDW_DWMC;
	}
	public void setJWZBXZDW_DWMC(String jWZBXZDW_DWMC) {
		JWZBXZDW_DWMC = jWZBXZDW_DWMC;
	}
	public String getZHFSDM() {
		return ZHFSDM;
	}
	public void setZHFSDM(String zHFSDM) {
		ZHFSDM = zHFSDM;
	}
	public String getZHDD_XZQHDM_DMBCMS() {
		return ZHDD_XZQHDM_DMBCMS;
	}
	public void setZHDD_XZQHDM_DMBCMS(String zHDD_XZQHDM_DMBCMS) {
		ZHDD_XZQHDM_DMBCMS = zHDD_XZQHDM_DMBCMS;
	}
	public String getZHDWFLDM_DMBCMS() {
		return ZHDWFLDM_DMBCMS;
	}
	public void setZHDWFLDM_DMBCMS(String zHDWFLDM_DMBCMS) {
		ZHDWFLDM_DMBCMS = zHDWFLDM_DMBCMS;
	}
	public String getXZDWFLDM_DMBCMS() {
		return XZDWFLDM_DMBCMS;
	}
	public void setXZDWFLDM_DMBCMS(String xZDWFLDM_DMBCMS) {
		XZDWFLDM_DMBCMS = xZDWFLDM_DMBCMS;
	}
	public String getCXDW_GAJGJGDM() {
		return CXDW_GAJGJGDM;
	}
	public void setCXDW_GAJGJGDM(String cXDW_GAJGJGDM) {
		CXDW_GAJGJGDM = cXDW_GAJGJGDM;
	}
	public String getCXDW_GAJGMC() {
		return CXDW_GAJGMC;
	}
	public void setCXDW_GAJGMC(String cXDW_GAJGMC) {
		CXDW_GAJGMC = cXDW_GAJGMC;
	}
	public Date getCXSJ() {
		return CXSJ;
	}
	public void setCXSJ(Date cXSJ) {
		CXSJ = cXSJ;
	}
}
