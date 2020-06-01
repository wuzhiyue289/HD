package HD;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/** 

* @author 作者 : 吴某人

* @version 创建时间：2019年12月11日 下午3:06:36 

* 类说明 :下载MP4文件

*/
public class DownloadMp4 {
public static void main(String[] args) throws MalformedURLException {
	downloadNet();
}
	
	public static void downloadNet() throws MalformedURLException {
        // 下载网络文件
        int bytesum = 0;
        int byteread = 0;
 
        URL url = new URL("https://recordcdn.talk-cloud.net/6bd7003d-c326-43bc-801c-df883f9c04ee-2139396347/record.mp4");
        File file=new File("c:\\abc");
        if(!file.exists()) {
        	file.mkdir();
        }else {
        	System.out.println("不用创建");
        }
        try {
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            FileOutputStream fops = new FileOutputStream(file+"\\b.mp4");
 
            byte[] buffer = new byte[1204];
         //   int length;
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
            //    System.out.println(bytesum);
                fops.write(buffer, 0, byteread);
            }
            fops.close();
            inStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }	
}