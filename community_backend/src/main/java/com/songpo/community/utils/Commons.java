package com.songpo.community.utils;


import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Commons {


    /**
     * 获取文章第一张图片
     *
     * @return
     */
    public static String show_thumb(String content) {
        content = TaleUtils.mdToHtml(content);
        if (content.contains("<img")) {
            String img = "";
            String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
            Pattern p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
            Matcher m_image = p_image.matcher(content);
            if (m_image.find()) {
                img = img + "," + m_image.group();
                // //匹配src
                Matcher m = Pattern.compile("src\\s*=\\s*\'?\"?(.*?)(\'|\"|>|\\s+)").matcher(img);
                if (m.find()) {
                    return m.group(1);
                }
            }
        }
        return "";
    }

    /**
     * 获取文章中的所有图片
     * @param content
     * @return
     */
    public static List<String> show_all_thumb(String content) {
        List<String> rs = new LinkedList();
        content = TaleUtils.mdToHtml(content);
        if (content.contains("<img")) {
            String img = "";
            String regEx_img = "<[a-zA-Z]+.*?>([\\s\\S]*?)</[a-zA-Z]*>";
            Pattern p_image = Pattern.compile(regEx_img, Pattern.MULTILINE);
            Matcher m_image = p_image.matcher(content);
            while (m_image.find()) {
                String data = m_image.group(1).trim();
                if(!"".equals(data) && data.contains("<img")) {
//                    System.out.println(data);
                    // //匹配src
                    Matcher m = Pattern.compile("src\\s*=\\s*\'?\"?(.*?)(\'|\"|>|\\s+)").matcher(data);
                    while (m.find()){
                        //  if (m.find()) {
                        rs.add(m.group(1));
                    }
                }

            }
        }
        return rs;
    }

    /**
     * 获取文章的文字预览
     * @param content
     * @return
     */
    public static String show_p(String content){
        String result = "";
        content = TaleUtils.mdToHtml(content);
        String reg = "<[a-zA-Z]+.*?>([\\s\\S]*?)</[a-zA-Z]*>";

        Pattern p = Pattern.compile(reg, Pattern.MULTILINE);
        content = content.replace("&nbsp;", "");
        Matcher m = p.matcher(content);
        if (m.find()) {
            String data = m.group(1).trim();
            if(!"".equals(data) && !data.contains("<img")) {
                System.out.println(data);
                result = data;
            }
        }
        result = result.replace("<img>","");
        result = result.replace("</img>","");
        result = result.replace("<p>","");
        result = result.replace("</p>","");
        if (result.length() > 20)
            result = result.substring(0, 20);
        return result;
    }

    /**
     * 获取文章中所有的文字
     * @param content
     * @return
     */
    public static List<String> show_all_p(String content){
        List<String> rs = new LinkedList();
        content = TaleUtils.mdToHtml(content);
//        System.out.println(content);
        String reg = "<[a-zA-Z]+.*?>([\\s\\S]*?)</[a-zA-Z]*>";

        Pattern p = Pattern.compile(reg, Pattern.MULTILINE);
        content = content.replace("&nbsp;", "");
        Matcher m = p.matcher(content);
        while(m.find()) {
            String data = m.group(1).trim();
            if(!"".equals(data) && !data.contains("<img")) {
//                System.out.println(data);
//                data = "<p>" + data + "</p>";
                rs.add(data);
            }
        }
        return rs;
    }

    /**
     * 截取文章摘要
     *
     * @param value 文章内容
     * @param len   要截取文字的个数
     * @return
     */
    public static String intro(String value, int len) {
        Integer pos = value.indexOf("<!--more-->");
        if (null == pos || pos == 0 || pos == -1)
            pos = value.indexOf("<!-- more -->");
        if (pos != -1) {
            String html = value.substring(0, pos);
            return TaleUtils.mdToHtml(TaleUtils.mdToHtml(html));
        } else {
            String text = TaleUtils.mdToHtml(TaleUtils.mdToHtml(value));
            if (text.length() > len) {
                return text.substring(0, len);
            }
            return text;
        }
    }


    public static String getLifeCreateTime(String c_time) {
        long unixTime = DateKit.getUnixTimeByString(c_time);
        String res = DateKit.fmtdate_en(unixTime);
        return res;
    }

    public static void main(String[] args) {
        String test_time = "2020-12-01 00:29:01";
        System.out.println(getLifeCreateTime(test_time));

//        String test_content = "测试正文内容\n" + "\n" + "测试正文fasfsf内容\n" + "\n" +  "![](fdfsfsf.png)";
        String test_content = "南京之行\n" +
                "123，456，789\n" +
                "112233\n" +
                "![](img.deepcode.ltd/20210208173436952.jpg)\n" +
                "![](img.deepcode.ltd/20210208173436952.jpg)\n" +
                "![](img.deepcode.ltd/20210208173436952.jpg)";
        System.out.println(show_all_p(test_content));
        System.out.println(show_thumb(test_content));
        System.out.println(show_all_thumb(test_content));
    }
}
