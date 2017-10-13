package com.sz.ktv.net.util;


public class CommandStr {

	/**
	 * 发送验证码
	 */
	public static final String CMD_SEND_VCODE = "4401#0001*";
	/**
	 * 手持客户端请求获取主机状态
	 */
	public static final String CMD_ZHUJI_STATUS = "6601#";
	/**
	 * 手持客户端请求获取当前音量值
	 */
	public static final String CMD_YINLIANG_VALUE = "6602#";
	/**
	 * 手持客户端请求获取已点歌曲列表
	 */
	public static final String CMD_YIDIAN_SONG_LIST = "6603#";
	/**
	 * 手持客户端请求获取已播歌曲列表
	 */
	public static final String CMD_YIBO_SONG_LIST = "6604#";
	/**
	 * 手持客户端请求获取指定文件名数据 备注： song.txt，表示歌单文件，此文件解析请参考歌单文件格式说明；
	 * singer.txt，表示歌星文件，此文件解析请参考歌星文件格式说明； 　　
	 * newsong.txt，表示新歌文件，此文件解析请参考歌单文件格式说明；
	 * public.txt，表示公播歌曲文件，此文件解析请参考歌单文件格式说明；
	 */
	public static final String CMD_GET_FILE_SONG = "6605#0001*song.txt#";
	public static final String CMD_GET_FILE_SINGER = "6605#0001*singer.txt#";
	public static final String CMD_GET_FILE_NEWSONG = "6605#0001*newsong.txt#"; //newsong
	public static final String CMD_GET_FILE_PUBLIC = "6605#0001*public.txt#";
	/**
	 * 手持客户端请求播放命令
	 */
	public static final String CMD_BOFANG = "6610#";
	/**
	 * 手持客户端请求暂停命令
	 */
	public static final String CMD_ZANTING = "6611#";
	/**
	 * 手持客户端请求重唱命令
	 */
	public static final String CMD_CHONGCHANG = "6612#";

	/**
	 * 手持客户端请求下首命令
	 */
	public static final String CMD_XIASHOU = "6613#";
	/**
	 * 手持客户端请求原唱命令
	 */
	public static final String CMD_YUANCHANG = "6614#";
	/**
	 * 手持客户端请求伴唱命令
	 */
	public static final String CMD_BANCHANG = "6615#";
	/**
	 * 手持客户端请求静音命令
	 */
	public static final String CMD_JINGYIN = "6616#";
	/**
	 * 手持客户端请求非静音命令
	 */
	public static final String CMD_FEIJINGYIN = "6617#";
	/**
	 * 手持客户端请求音量加减命令
	 */
	public static final String CMD_YINLIANG = "6618#";
	public static final String CMD_YINLIANG_JIA = "6618#0001*1#";
	public static final String CMD_YINLIANG_JIAN = "6618#0002*2#";

	/**
	 * 说明：表示手持客户端请求点播歌曲编号为70003的歌曲，0001表示点播歌曲添加到点播歌曲列表尾部，0002表示插播，
	 * 点播歌曲添加到点播歌曲列表首部；当主机接收到此命令后，将返回已点歌曲列表更新通知，返回命令为：7707#；
	 */
	public static final String CMD_DIANBO_1 = "6619#0001*";// 70003#";
	public static final String CMD_DIANBO_2 = "6619#0002*";// 70003#";

	/**
	 * 6620#0001*13488889999!70003#
	 * 说明：表示手持客户端请求将歌曲编号为70003的歌曲，收藏到手机号为13488889999的用户下保存
	 * ，当主机接收到此命令后，将返回命令确认信息，返回命令为：7709#
	 */
	public static final String CMD_SHOUCUN = "6620#0001*";// 13488889999!70003#";
	/**
	 * 说明：表示手持客户端请求优先歌曲编号为70004的已点歌曲，当主机接收到此命令后，将返回已点歌曲列表更新通知，返回命令为：7707#
	 */
	public static final String CMD_YIDIAN_YOUXIAN = "6621#0001*";// 70004#";
	/**
	 * 表示手持客户端请求删除歌曲编号为70004的已点歌曲，当主机接收到此命令后，将返回已点歌曲列表更新通知，返回命令为：7707#
	 */
	public static final String CMD_YIDIAN_SHANCHU = "6622#0001*";// 70004# ";
	/**
	 * 手持客户端请求已点歌曲乱序命令
	 */
	public static final String CMD_YIDIAN_LUAN = "6623#";
	/**
	 * 手持客户端请求呼叫命令
	 */
	public static final String CMD_HUJIAO = "6624#";
	/**
	 * 6625#0001*XXXX# 备注：表示声效气氛，XXXX，表示不同的声效，如0001表示掌声，0002表示口哨，0003表示欢呼；
	 * 6625#0002*XXXX# 备注：表示鲜花气氛，XXXX，表示不同的鲜花，现分4种鲜花； 
	 * 6625#0003*XXXX# 备注：表示搞怪气氛，XXXX，表示不同的搞怪，现分12种搞怪；
	 */
	public static final String CMD_QIFEN_0001 = "6625#0001*";// XXXX# ";
	public static final String CMD_QIFEN_0002 = "6625#0002*";
	public static final String CMD_QIFEN_0003 = "6625#0003*";
	
	
	/**
	 * 6626#0001*祝福语# 说明：当主机接收到此命令后，将返回命令确认信息，返回命令为：7709#
	 */
	public static final String CMD_ZHUFU = "6626#0001*";// 祝福语#";
	/**
	 * 手持客户端请求获取排行歌曲分类
	 * 7727#0001*总排行#0002*周排行#0003*月排行#0004*季排行#0005*新歌排行#0006*国语排行
	 * #0007*粤语排行#0008*闽南语排行#
	 */
	public static final String CMD_PAIHANG = "6627#";

	/**
	 * 手持客户端请求获取某一分类排行歌曲数据
	 */
	public static final String CMD_PAIHANG_LIST = "6628#";// 0001*总排行#";
	/**
	 * 手持客户端请求获取某一标识的收藏歌曲数据
	 */
	public static final String CMD_SHOUCANG_LIST = "6629#0001*";// 13488889999#";

	/**
	 * 手持客户端请求打开幻影命令
	 */
	public static final String CMD_HUANYING_OPEN = "6630#";
	/**
	 * 手持客户端请求关闭幻影命令
	 */
	public static final String CMD_HUANYING_CLOSE = "6631#";

	/**
	 * 手持客户端请求绑定命令
	 */
	public static final String CMD_BANDING = "6632#";
	/**
	 * 手持客户端请求获取LOGO显示名称，只有一个LOGO，数据量小，使用UDP方式
	 */
	public static final String CMD_LOG_GET = "6633#0001*logo.txt#";
}
