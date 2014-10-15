package com.wizard.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 检查到某地址的网络是否联通
 * 
 * @author wizard
 * 
 */
public class PingUtil {

	/**
	 * 
	 * @param host
	 * @return boolean
	 */
	public static boolean ping(String host) {
		URL url = null;  
        try {  
            url = new URL(host);  
            try {  
                InputStream in = url.openStream();
                in.close();  
                return true;
            } catch (IOException e) {  
                return false;  
            }  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        }  
        return false;
	}

}
