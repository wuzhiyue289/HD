package HD;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.naming.directory.SearchResult;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import net.sf.json.JSONArray;

/**
 * 
 * @author 作者 : 吴某人
 * 
 * @version 创建时间：2019年11月21日 下午3:15:12
 * 
 *          类说明 :
 * 
 */
@RestController
public class getpc {
	HashMap<String, String> map = new HashMap<String, String>();

	@RequestMapping(value = "c.do", produces = "application/json;charset=UTF-8")
	public String demo(HttpServletRequest request) {
		/*
		 * String Agent = request.getHeader("User-Agent"); StringTokenizer st = new
		 * StringTokenizer(Agent,";"); st.nextToken(); //得到用户的浏览器名 String browser =
		 * st.nextToken(); System.out.println("浏览器  "+browser); map.put("浏览器", browser);
		 * //得到用户的操作系统名 String os = st.nextToken(); System.out.println("os  "+os);
		 * map.put("系统名", os); //获得客户端的IP地址 String ip = ""; try { ip =
		 * InetAddress.getLocalHost().getHostAddress(); //ip 地址 } catch
		 * (UnknownHostException e) { e.printStackTrace(); }
		 * System.out.println("ip   "+ip); map.put("ip", ip);
		 */

		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));// req就是request请求

		Browser browser1 = userAgent.getBrowser();// 获取浏览器信息 
		OperatingSystem os1 = userAgent.getOperatingSystem(); // 获取操作系统信息
		StringBuffer userInfo = new StringBuffer();
		String userip = request.getRemoteAddr();
		userInfo.append("操作系统：" + os1.toString() + " 浏览器：" + browser1.toString());
		System.out.println("操作系统：" + os1.toString() + " 浏览器：" + browser1.toString() + "user" + userip);
		map.put("浏览器", browser1.toString());
		map.put("操作系统",os1.toString());
		map.put("ip", userip);
		JSONArray Js = JSONArray.fromObject(map);
		return Js.toString();

	}
}
