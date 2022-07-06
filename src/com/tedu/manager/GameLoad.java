/*
 * @Description: 加载器
 * @version: 1.0.0
 * @Author: Jayden Chang
 * @Date: 2022-07-02 09:01:02
 * @LastEditors: Jayden Chang
 * @LastEditTime: 2022-07-06 14:21:56
 */
package com.tedu.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.swing.ImageIcon;

import com.tedu.element.ElementObj;
import com.tedu.element.MapObj;
import com.tedu.element.Play;

public class GameLoad {
    private static ElementManager em = ElementManager.getManager();
    public static Map<String, ImageIcon> imgMap = new HashMap<>();
    private static Properties pro = new Properties();

    public static void mapLoad(int mapId) {
        String mapName = "com/tedu/text/" + mapId + ".map";
        ClassLoader classLoader = GameLoad.class.getClassLoader();
        InputStream maps = classLoader.getResourceAsStream(mapName);
        if (maps == null) {
            System.out.println("null maps");
            return;
        }
        try {
            pro.clear();
            pro.load(maps);
            Enumeration<?> names = pro.propertyNames();
            while (names.hasMoreElements()) {
                String key = names.nextElement().toString();
                // System.out.println(pro.getProperty(key));
                String[] arrs = pro.getProperty(key).split(";");
                for (int i = 0; i < arrs.length; i++) {
                    ElementObj element = new MapObj().createElement(key + "," + arrs[i]);
                    em.addElement(element, GameElement.MAPS);

                }
            }
        } catch (IOException e) {
            // TODO: handle exception

            e.printStackTrace();
        }
    }

    public static void loadImg() {
        String textURL = "com/tedu/text/GameData.pro";
        ClassLoader classLoader = GameLoad.class.getClassLoader();
        InputStream texts = classLoader.getResourceAsStream(textURL);
        pro.clear();
        try {
            pro.load(texts);
            Set<Object> set = pro.keySet();
            for (Object o : set) {
                String url = pro.getProperty(o.toString());
                imgMap.put(o.toString(), new ImageIcon(Play.class.getResource(url)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * 加载主角
     */
    public static void loadPlay() {
        loadObj();
        String playStr = "500,500,up";

        ElementObj obj = getObj("play");

        // ElementObj play = new Play().createElement(playStr);
        ElementObj play = obj.createElement(playStr);
        // 解耦,降低代码之间的耦合度,可以直接通过接口or抽象父类就可以获取到实体对象
        em.addElement(play, GameElement.PLAY);
    }

    public static ElementObj getObj(String str) {
        try {
            Class<?> class1 = objMap.get(str);
            Object newInstance = class1.newInstance();
            if (newInstance instanceof ElementObj) {
                return (ElementObj) newInstance; // 这个对象和new Play()等价
            }
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /*
     * 扩展: 使用配置文件,来实例化对象,通过固定的key(字符串来实例化)
     *
     * @param key
     */
    private static Map<String, Class<?>> objMap = new HashMap<>();

    public static void loadObj() {
        String textURL = "com/tedu/text/obj.pro";
        ClassLoader classLoader = GameLoad.class.getClassLoader();
        InputStream texts = classLoader.getResourceAsStream(textURL);
        pro.clear();
        try {
            pro.load(texts);
            Set<Object> set = pro.keySet();
            for (Object o : set) {
                String classUrl = pro.getProperty(o.toString());
                // 用反射的方式直接将 类进行获取
                Class<?> forName = Class.forName(classUrl);
                objMap.put(o.toString(), forName);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        mapLoad(5);
    }
}
