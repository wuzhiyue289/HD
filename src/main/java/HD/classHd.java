package HD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author 作者 : 吴某人
 * 
 * @version 创建时间：2020年2月1日 下午6:03:34
 * 
 *          类说明 :回调
 * 
 * 
 */
@Controller

public class classHd {
	Logger logger = Logger.getLogger(this.getClass());
	/*
	 * serial 课堂号 role 用户角色说明 : 0：主讲(老师 ) 1：助教 2: 学员 3：直播用户 4:巡检员 " userid 用户id
	 * status 登入登出说明 : 等于0为登出 等于1为登入 username 用户昵称
	 */
	String serial, role, userid, status, username, recordid, recordtype, starttime, endtime = "无";

	// 进出记录回调
	@RequestMapping("inOutClass.do")
	public void inOutClass(HttpServletRequest req) {
		serial = req.getParameter("serial");
		role = req.getParameter("role");
		userid = req.getParameter("userid");
		status = req.getParameter("status");
		username = req.getParameter("username");

		if ("0".equals(status)) {
			status = "退出教室";
		} else {
			status = "进入教室";
		}
		// 获取身份
		if (!"".equals(role)) {
			classRole();
		}
		logger.info("教室号:" + serial + "叫:" + username + "--" + role + "★" + status + "★" + "id为" + userid);
		

	}

	@RequestMapping("startOrEndClass.do")
	@ResponseBody
	/**
	 * 拓课云上下课回调
	 */
	public String TKstartClass(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException {
		BufferedReader TKstreamReader = new BufferedReader(new InputStreamReader(
				request.getInputStream(), "UTF-8"));
		StringBuilder TKresponseStrBuilder = new StringBuilder();
		String TKinputStr; //拓课云上下课post回调示例
		StringBuilder Str = null;
		while ((TKinputStr = TKstreamReader.readLine()) != null) {
			Str = TKresponseStrBuilder.append(TKinputStr);
		}
		System.out.println("上下课"+Str);
	// str是data的数据
		logger.info(Str);
		return "success";
	}

	// 录制件
	/**
	 * 
	 * @param req
	 *            recordid 数据唯一标识 recordtype 录制件标识说明 : 等于0为常规录制 等于5为课后MP4或者渲染录制件
	 *            starttime 开始录制件事时间 endtime 结束录制时
	 */
	@RequestMapping("record.do")
	@ResponseBody
	public void recordListGenerate(HttpServletRequest req) {

		recordid = req.getParameter("recordid");
		serial = req.getParameter("serial");
		recordtype = req.getParameter("recordtype");
		starttime = req.getParameter("starttime");
		endtime = req.getParameter("endtime");
		if ("0".equals(recordtype)) {
			recordtype = "常规录制件";
		} else {
			recordtype = "MP4录制件";
		}
		System.out.println("房间号"+serial+"开始时间"+starttime+"结束时间"+endtime+"录制件类型"+recordtype);
	}

	@RequestMapping("fileHd.do")
	public void fileHd(HttpServletRequest req) {
		String fileid = req.getParameter("fileid");
		String converStatus = req.getParameter("converStatus");
		//converStatus  0代表转换完成    -1代表转换失败
		logger.info(fileid + "课件转换"+converStatus);
		System.out.println("课件转换回调"+fileid);
		System.out.println("参数"+req.getQueryString());
		
	}

	public void classRole() {
		switch (role) {
		case "0":
			role = "老师";
			break;
		case "1":
			role = "助教";
			break;
		case "2":
			role = "学员";
			break;
		case "3":
			role = "直播用户";
			break;
		case "4":
			role = "巡课";
			break;
		default:
			break;
		}
	}
}
