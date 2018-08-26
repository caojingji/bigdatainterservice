package com.founder.interservice.model;

import lombok.Data;

import java.util.Date;

/**
 * @Description* @Copyright: HighLand'S Copyright (c) 2014
 * @Company: HighLand
 * @author zhujian
 * @E-mail:zhuj@bjhlxt.com
 * @careate 2014-05-12下午12:54:58
 * @version 1.0 TODO ：
 */
@Data
public class TbStRy{
	// 死亡时间
	private Date swsj = null;
	// 案事件相关人员编号
	private String asjxgrybh = null;
	// 曾用名
	private String cym = null;
	// 常用证件_常用证件代码
	private String cyzjCyzjdm = null;

	// 常用证件_代码补充描述
	private String cyzjDmbcms = null;

	// 常用证件_证件号码
	private String cyzjZjhm = null;
	private String cyzj_zjhm = null;

	// 出生日期_日期估值下限
	private Date csrqRqgzxx = null;
	private String csrqRqgzxxStr = null;
	// 出生日期_日期估值上限
	private Date csrqRqgzsx = null;
	private String csrqRqgzsxStr = null;
	// 出生地_国家和地区代码
	private String csdGjhdqdm = null;

	// 境外住址_国家和地区代码
	private String jwzzGjhdqdm = null;

	// 境外住址_地址名称
	private String jwzzDzmc = null;

	// 人其他特征_简要情况
	private String rqttzJyqk = null;

	// 工作单位
	private String gzdw = null;
	// 职业_职业类别代码
	private String zyZylbdm = null;

	// 职业_代码补充描述
	private String zyDmbcms = null;

	// 案事件相关人员身份_案事件相关人员身份代码
	private String asjxgrysfAsjxgrysfdm = null;

	// 案事件相关人员身份_代码补充描述
	private String asjxgrysfDmbcms = null;

	// 犯罪嫌疑人特殊专长_犯罪嫌疑人特殊专长代码
	private String fzxyrtszcFzxyrtszcdm = null;

	// 犯罪嫌疑人特殊专长_代码补充描述
	private String fzxyrtszcDmbcms = null;
	// 个人爱好及活动特点_简要情况
	private String grahjhdtdJyqk = null;

	// 个人收入支出_简要情况
	private String grsrzcJyqk = null;

	// 社会交往_简要情况
	private String shjwJyqk = null;
	// 是否有吸毒史_判断标识
	private String sfyxdsPdbz = null;

	// 是否艾滋病病毒携带者_判断标识
	private String sfazbbdxdzPdbz = null;

	// 是否精神病人_判断标识
	private String sfjsbrPdbz = null;

	// 信息删除_判断标识
	private String xxscPdbz = null;

	// 信息登记单位_公安机关机构代码
	private String xxdjdwGajgjgdm = null;

	// 信息登记单位_公安机关名称
	private String xxdjdwGajgmc = null;

	// 信息登记人员_姓名
	private String xxdjryXm = null;

	// 信息登记人员_公民身份号码
	private String xxdjryGmsfhm = null;

	// 信息登记人员_联系电话
	private String xxdjryLxdh = null;

	// 登记时间
	private Date djsj = null;

	// 信息操作单位_公安机关机构代码
	private String xxczdwGajgjgdm = null;

	// 信息操作单位_公安机关名称
	private String xxczdwGajgmc = null;

	// 信息操作人员_姓名
	private String xxczryXm = null;

	// 信息操作人员_公民身份号码
	private String xxczryGmsfhm = null;

	// 信息来源描述
	private String xxlyms = null;

	// 信息入省库时间
	private Date xxrsksj = null;

	// 信息入部库时间
	private Date xxrbksj = null;

	// 信息入省库_判断标识
	private String xxrskPdbz = null;

	// 信息入部库_判断标识
	private String xxrbkPdbz = null;

	// 信息封存_判断标识
	private String xxfcPdbz = null;
	// 人员编号
	private String rybh = null;
	// 姓名
	private String xm = null;
	// 别名/绰号
	private String bmch = null;
	// 常用证件代码
	private String cyzjdm = null;
	// 常用证件名称
	private String cyzjdm_str = null;
	// 常用证件代码描述
	private String cyzjdmMs = null;
	// 证件号码
	private String zjhm = null;
	// 出生日期_起始日期
	private Date csrqQsrq = null;
	// 出生日期_截止日期
	private Date csrqJzrq = null;
	// 性别代码
	private String xbdm = null;
	// 国籍代码
	private String gjdm = null;
	// 籍贯代码
	private String jgdm = null;
	// 民族代码
	private String mzdm = null;
	// 户籍地址_行政区划代码
	private String hjdzXzqhdm = null;
	// 户籍地址_地址名称
	private String hjdzDzmc = null;
	// 现住址_行政区划代码
	private String xzzXzqhdm = null;
	// 现住址_地址名称
	private String xzzDzmc = null;
	// 出生地_行政区划代码
	private String csdXzqhdm = null;
	// 出生地_地址名称
	private String csdDzmc = null;
	// 身高上限
	private String sgsx = null;
	// 身高下限
	private String sgxx = null;
	// 体重上限
	private String tzsx = null;
	// 体重下限
	private String tzxx = null;
	// 体貌特征描述
	private String tmtzms = null;
	// 体表标记描述
	private String tbbjms = null;
	// 牙齿特征描述
	private String yctzms = null;
	// 人其他特征描述
	private String rqttzms = null;
	// 工作单位
	private String fwcs = null;
	// 联系电话
	private String lxdh = null;
	// 血型代码
	private String xxdm = null;
	// 宗教信仰代码
	private String zjxydm = null;
	// 政治面貌代码
	private String zzmmdm = null;
	// 学历代码
	private String xldm = null;
	// 婚姻状况代码
	private String jyzkdm = null;
	// 兵役状况代码
	private String byzkdm = null;
	// 职业类别代码
	private String zylbdm = null;
	// 职业类别代码补充描述
	private String zylbdmbcms = null;
	// 涉案人员身份代码
	private String sarysfdm = null;
	// 涉案人员身份代码补充描述
	private String sarysfdmbcms = null;
	// 特殊专长代码
	private String tszcdm = null;
	// 特殊专长代码补充描述
	private String tszcdmbcms = null;
	// 简历
	private String jl = null;
	// 个人收入支出情况
	private String grsrzcqk = null;
	// 社会交往情况
	private String shjwqk = null;
	// 吸毒史标识,1-是,0-否
	private String xdsbs = null;
	// 违法犯罪经历代码
	private String wffzjldm = null;
	// 违法犯罪经历描述
	private String wffzjlms = null;
	// 个人爱好及活动特点描述
	private String grahjhdtdms = null;
	// 流窜作案情况
	private String lczaqk = null;
	// 十指指纹编号
	private String szzwbh = null;
	// DNA编号
	private String dnabh = null;
	// 比中状态，null未比对、16未比中、17比中、18比对中、20复合
	private String rSjlxdh = null;
	// 指纹反馈信息
	private String zwfkxx = null;
	// 指纹采集时间
	private Date zwcjsj = null;
	// 指纹采集方式 0 扫描 1 活采
	private String zwcjfs = null;
	// 滚动指纹条码号
	private String rfp = null;
	// 平面指纹条码号
	private String pfp = null;
	// 掌纹条码号
	private String plp = null;
	// 二代证照片路径
	private String edzzplj;
	// 左面照片路径
	private String zmzplj;
	// 侧面照片路径
	private String cmzplj;
	// 右侧面照片路径
	private String ycmzplj;
	// 二代证照片信息编号
	private String edzzpxxbh;
	// 左面照片信息编号
	private String zmzpxxbh;
	// 侧面照片信息编号
	private String cmzpxxbh;
	// 右侧面照片信息编号
	private String ycmzpxxbh;
	private String zp;
	private String asjbh;// macm 为人员filter加asjbh，为被布控人员add根据asjbh查询人员信息服务
	private String tbbz;
	private String jyaq;
	private String ajlbdm;
	private Date zhsj;// 抓获时间
	private String zhdwdm;
	private String zwcjdwdm;
	private String zwcjr;
	private String zhddqh;
	private String zhddxz;
	private String bzw;// 人员类型
	private String jzrybh;
	private String lrdwdm;// 录入单位代码
	private String zatzJyqk;// 作案特征——简要案情

	private String asjsjryjsdm;// 人员角色代码
	private String xzgzrygzjbdm;// 刑侦关注人员关注级别代码
	private String sbzt;// 上报状态
	private String ryzt;// 人员状态
	private String zxyrdaztdm;// 犯罪嫌疑人到案状态代码
	private String ifdoubt;// 是否犯罪嫌疑人
	private String ifskepticat;// 是否可疑
	private String ifgovern;// $CODE_IF$是否外地通缉令人员
	private String ifprison;// $CODE_IF$是否两劳
	private String ifblack;// $CODE_IF$是否涉黑标志
	private String ifescape;// $CODE_IF$是否在逃
	private String ifcontrol;// $CODE_IF$是否布控
	private String ifpunish;// $CODE_IF$是否在押

	// 人员查询中所需的字段
	private String ztbh = null;// 在逃人员编号
	private String dcdw = null;// 打处单位
	private Date flrsj = null;// 打处开始时间
	private Date elrsj = null;// 打处结束时间
	private String bmchpy = null;// 别名绰号拼音
	private String xmchpy = null;// 姓名拼音
	private String dcbs;// 是否打处字段值
	private String ztbs;// 是否在逃字段值

	// 人员现住址加经纬度字段
	private String xzzJd = null;// 现住址_经度
	private String xzzWd = null;// 现住址_纬度

	// 管辖单位公安机关机构代码
	private String gxdwgajgjgdm = null;
	// 管辖单位公安机关名称
	private String gxdwgajgmc = null;
	// 是否艾滋病病毒携带者
	private String sfazbbdxdz = null;
	// 刑侦关注人员状态
	private String xzgzryzt = null;
	// 待加入人员编号
	private String djrrybh = null;

	// 出生日期
	private String csrq = null;
	// 性别
	private String xbdmStr = null;
	// 民族
	private String mzdmStr = null;
	// 宗教信仰
	private String zjxydmStr = null;
	// 户籍地址行政区划
	private String hjdzXzqhdmStr = null;
	// 现住址行政区划
	private String xzzXzqhdmStr = null;
	// 抓获日期
	private String zhrq_str = null;

	// 抓获单位名称
	private String zhdwmc = null;
	// 足长上限
	private String zcsx = null;
	// 足长下限
	private String zcxx = null;

	// 添加新疆所需要的三个字段
	private String ajzlb; // 案件主类别
	private String ajxzlb; // 刑事类案类别
	private String xalbdmbcms; // 小案类别代码补充描述
	private String bz;
	private String rydnabh;// 人员DNA编号
	private Date swaj;// 死亡时间
	private Date gxsj;// 更新时间
	private String jgssxdm;
	private String lxdm;
	private String saveflag;
}