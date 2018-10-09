package org.xkia.util.http;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;

/**
 * @author hzw
 * @date 2018/7/9  10:44
 * @Description: ${todo}
 */
public class CodeFormat {

    @Test
    public String test01() throws UnsupportedEncodingException {
        //\351\235\236\346\263\225\347\232\204\350\257\267\346\261\202\346\235\245\346\272\220\347\261\273\345\236\213
        //非法的请求来源类型
        String ss = "\\350\\256\\242\\345\\215\\225\\344\\274\\232\\345\\221\\230\\345\\215\\241\\346\\224\\257\\344\\273\\230\\347\\247\\221\\347\\233\\256\\344\\277\\241\\346\\201\\257\\344\\270\\215\\345\\255\\230\\345\\234\\250";

        Pattern pattern = Pattern.compile("(\\\\[0-9]{3})+");

        Matcher matcher = pattern.matcher(ss);

        StringBuffer stringBuffer = new StringBuffer();

        while (matcher.find()) {
            String group = matcher.group();

            String dealResult = dealResult(group);

            matcher.appendReplacement(stringBuffer, dealResult);
        }
        matcher.appendTail(stringBuffer);
        System.out.println(stringBuffer.toString());

        return stringBuffer.toString();
    }

    public static String dealResult(String s8) throws UnsupportedEncodingException {
        StringBuffer s8b = new StringBuffer();
        String[] split = s8.substring(1).split("\\\\");
        for (int i = 0; i < split.length; i++) {
            String s1 = split[i];
            String s2 = Integer.toBinaryString(Integer.valueOf(s1, 8));//二进制

            String s3 = Integer.toHexString(Integer.valueOf(s2, 2));//十六进制

            s8b.append("%" + s3);
        }
        String decode = URLDecoder.decode(s8b.toString(), "UTF-8");
        return decode;
    }

}
