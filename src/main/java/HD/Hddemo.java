package HD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author 作者 :
 * 
 * @version 创建时间：
 * 
 *          类说明 :
 * 
 */
public class Hddemo {

	@RequestMapping("getpost.do")
	public String doPost1(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException {
		BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		StringBuilder responseStrBuilder = new StringBuilder();

		String inputStr;

		StringBuilder Str = null;
		while ((inputStr = streamReader.readLine()) != null)

			Str = responseStrBuilder.append(inputStr);
		System.out.println(Str);

		return "success";
	}

}
