/*
 * @Description: 加载器
 * @version: 1.0.0
 * @Author: Jayden Chang
 * @Date: 2022-07-02 09:01:02
 * @LastEditors: Jayden Chang
 * @LastEditTime: 2022-07-06 10:54:50
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
                // System.out.println(o.toString() + " " + url);
                // System.out.println(url);
                // imgMap.put(o.toString(), new ImageIcon(url));
                imgMap.put(o.toString(), new ImageIcon(Play.class.getResource(url)));
                // System.out.println(imgMap);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // System.out.println(imgMap);
    }

    public static void main(String[] args) {
        mapLoad(5);
    }
}
