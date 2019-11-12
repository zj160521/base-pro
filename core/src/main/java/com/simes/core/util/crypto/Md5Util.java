package com.simes.core.util.crypto;

import com.simes.core.exception.SystemException;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/5 16:17
 */
public class Md5Util {
    /**
     * 用于获取一个String的md5值
     * @param str
     * @return
     */
    public static String getMd5(String str) {
    	try {
//   		加密后的字符串
            String encodeStr=DigestUtils.md5Hex(str);
            return encodeStr;
		} catch (Exception e) {
			throw new SystemException("md5 error");
		}
    }
}
