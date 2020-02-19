package HD;
/**
 * ���Իص�
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
    	System.out.println("时间:"+nowtime);
    	System.out.println("参数"+request.getQueryString());
       
        String serialNumber = request.getParameter("serial");//
        String useridNumber = request.getParameter("userid");//
        String recordidNumber = request.getParameter("recordid");//
        String fileidNumber = request.getParameter("fileid");//
        String roleNumber = request.getParameter("role");//
        String statusNumber = request.getParameter("status");//  
        String usernameNumber = request.getParameter("username");//
        
       //登入登出
    if(statusNumber!=null) {
        if(Integer.parseInt(statusNumber)==0) {
        	System.out.println("进出"+useridNumber+"名字:"+usernameNumber);
        }else if(1==Integer.parseInt(statusNumber)) {
        	System.out.println("进入"+useridNumber+"名字:"+usernameNumber);
        }else {
        	System.out.println("出错");
        };
    }
    //录制件
    if(recordidNumber !=null) {
    	System.out.println("视屏号码:"+serialNumber+"及唯一id:"+recordidNumber);
       
    }else{
    	
    }
    //文件
    if(fileidNumber !=null) {
    	System.out.println("文件id"+fileidNumber);
    }
        //身份
        try {
        	if(0==Integer.parseInt(roleNumber)) {
            	System.out.println("0:");
            }else if(1==Integer.parseInt(roleNumber)){
            	System.out.println("1:");
            }else if(2==Integer.parseInt(roleNumber)){
            	System.out.println("2听课:");
            }else if(3==Integer.parseInt(roleNumber)){
            	System.out.println("ֱ3直播用户:");
            }else if(4==Integer.parseInt(roleNumber)){
            	System.out.println("巡检:");
            }else {
            	System.out.println("未知人物:");
            }
        	
            
		} catch (Exception e) {
			// TODO: handle exception
		}
        System.out.println("号码"+serialNumber);
        //结束
        return username;
    }
    //
    @RequestMapping(value="start.do")
    
     public String starClass(@RequestBody String requestBody, HttpServletRequest request) {
    	System.out.println("requestBody"+requestBody);
    	String req=request.getParameter(requestBody);
    	System.out.println("开始上课"+req);
    
return "success";
     }
    //¼�Ƽ�ת��
   @RequestMapping(value="recordlist.do",method=RequestMethod.GET)
    public void dolist(HttpServletRequest req) {
    //	String s =requestBody;
	   String a= req.getParameter("serial");
	   String b=req.getParameter("recordid");
	   String c=req.getParameter("recordtype");
	   String d=req.getParameter("starttime");
	   String e=req.getParameter("endtime");
    	System.out.println("�ط�"+a+"��"+b+"��"+c+"��"+d+"��"+e);
    	
    	
    }
   
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        Map<String, String[]> params = request.getParameterMap();
        System.out.println("params��"+params);
        String queryString = "";
        for (String key : params.keySet()) {
            String[] values = params.get(key);
            for (int i = 0; i < values.length; i++) {
                String value = values[i];
                queryString += key + "=" + value + "&";
            }
        }
        
        
        // ȥ�����һ���ո�
        System.out.println("queryStringȥ��ǰ"+queryString);
        queryString = queryString.substring(0, queryString.length() - 1);
        writer.println("POST " + request.getRequestURL() + " " + queryString);
        System.out.println("POST " + request.getRequestURL() + " " + queryString);
        
         
    }
    //ֻ下课回调
    @RequestMapping("getpost.do")  
    public String doPost1(HttpServletRequest request,
            HttpServletResponse response) throws UnsupportedEncodingException, IOException {
    	 BufferedReader streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(), "UTF-8"));
         StringBuilder responseStrBuilder = new StringBuilder(); 

         String inputStr;

         StringBuilder Str = null;
		while ((inputStr = streamReader.readLine()) != null)

     Str =  responseStrBuilder.append(inputStr); 
		System.out.println("下课"+Str);
	
		return "success";
    }
    
}
