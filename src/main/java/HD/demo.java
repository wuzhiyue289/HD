package HD;
/**
 * 测试回调
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.sf.json.JSONObject;



@Controller
@RestController
public class demo {
    @RequestMapping(value = "a.do", produces = "application/json;charset=UTF-8")
    
    public String tsa(HttpServletRequest request,@Param("username") String username) throws IOException {
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String nowtime = sdf.format(new Date());
    	System.out.println("名字"+username);
    	System.out.println();
    	System.out.println("时间是:"+nowtime);
    	System.out.println("参数"+request.getQueryString());
       
        String serialNumber = request.getParameter("serial");//课堂号
        String useridNumber = request.getParameter("userid");//用户id
        String recordidNumber = request.getParameter("recordid");//录制件
        String fileidNumber = request.getParameter("fileid");//文档
        String roleNumber = request.getParameter("role");//用户类型
        String statusNumber = request.getParameter("status");// 状态（0 为登出 1 为登入） 
        String usernameNumber = request.getParameter("username");//用户名
        
       //登入登出
    if(statusNumber!=null) {
        if(Integer.parseInt(statusNumber)==0) {
        	System.out.println("登出(退出)状态,用户id为"+useridNumber+"及用户名为"+usernameNumber);
        }else if(1==Integer.parseInt(statusNumber)) {
        	System.out.println("登入(进入)状态,用户id为"+useridNumber+"及用户名为"+usernameNumber);
        }else {
        	System.out.println("不知道是进还是出");
        };
    }
    //录制件
    if(recordidNumber !=null) {
    	System.out.println("录制件完成了,课堂号是:"+serialNumber+"编号为:"+recordidNumber);
       
    }else{
    	
    }
    //课件转换
    if(fileidNumber !=null) {
    	System.out.println("课件转换完成:id为"+fileidNumber);
    }
        //进入身份
        try {
        	if(0==Integer.parseInt(roleNumber)) {
            	System.out.println("老师进来了:");//+"课堂号是"+serialNumber
            }else if(1==Integer.parseInt(roleNumber)){
            	System.out.println("助教进来了:");
            }else if(2==Integer.parseInt(roleNumber)){
            	System.out.println("学员进来了:");
            }else if(3==Integer.parseInt(roleNumber)){
            	System.out.println("直播用户进来了:");
            }else if(4==Integer.parseInt(roleNumber)){
            	System.out.println("巡课进来了:");
            }else {
            	System.out.println("不知道谁进来了:");
            }
        	
            
		} catch (Exception e) {
			// TODO: handle exception
		}
        System.out.println("课堂号是"+serialNumber);
        //回调结束
        return username;
    }
    //上课回调
    @PostMapping(value="startclass.do")
    
     public void starClass(@RequestBody String requestBody, HttpServletRequest request) {
    	System.out.println("requestBody是"+requestBody);
    	String req=request.getParameter(requestBody);
    	System.out.println("req是"+req);
    

     }
    //录制件转换
   @RequestMapping(value="recordlist.do",method=RequestMethod.GET)
    public void dolist(HttpServletRequest req) {
    //	String s =requestBody;
	   String a= req.getParameter("serial");
	   String b=req.getParameter("recordid");
	   String c=req.getParameter("recordtype");
	   String d=req.getParameter("starttime");
	   String e=req.getParameter("endtime");
    	System.out.println("回放"+a+"→"+b+"→"+c+"→"+d+"→"+e);
    	
    	
    }
   
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        Map<String, String[]> params = request.getParameterMap();
        System.out.println("params是"+params);
        String queryString = "";
        for (String key : params.keySet()) {
            String[] values = params.get(key);
            for (int i = 0; i < values.length; i++) {
                String value = values[i];
                queryString += key + "=" + value + "&";
            }
        }
        
        
        // 去掉最后一个空格
        System.out.println("queryString去掉前"+queryString);
        queryString = queryString.substring(0, queryString.length() - 1);
        writer.println("POST " + request.getRequestURL() + " " + queryString);
        System.out.println("POST " + request.getRequestURL() + " " + queryString);
        
         
    }
    //只拿数据体不拿地址栏的数据
    @RequestMapping("getpost.do")  
    public String doPost1(HttpServletRequest request,
            HttpServletResponse response) throws UnsupportedEncodingException, IOException {
    	 BufferedReader streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(), "UTF-8"));
         StringBuilder responseStrBuilder = new StringBuilder(); 

         String inputStr;

         StringBuilder Str = null;
		while ((inputStr = streamReader.readLine()) != null)

     Str =  responseStrBuilder.append(inputStr); 
		System.out.println(Str);
	
		return "success";
    }
    
}
