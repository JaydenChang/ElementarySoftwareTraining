/*
 * @Description: 加载器(工具:用户读取配置文件的工具)工具类,大多提供static方法
 * @version: 1.0.0
 * @Author: Jayden Chang
 * @Date: 2022-07-02 09:01:02
 * @LastEditors: Jayden Chang
 * @LastEditTime: 2022-07-05 10:41:09
 */
package com.tedu.manager;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.ImageIcon;

import com.tedu.element.ElementObj;
import com.tedu.element.MapObj;
import com.tedu.element.Play;

public class GameLoad {
    public static Map<String, ImageIcon> imgMap;
    private static ElementManager em = ElementManager.getManager();

    static {
        imgMap = new HashMap<>();
        imgMap.put("left", new ImageIcon(Play.class.getResource("/image/tank/play1/player1_left.png")));
        imgMap.put("down", new ImageIcon(Play.class.getResource("/image/tank/play1/player1_down.png")));
        imgMap.put("up", new ImageIcon(Play.class.getResource("/image/tank/play1/player1_up.png")));
        imgMap.put("right", new ImageIcon(Play.class.getResource("/image/tank/play1/player1_right.png")));

        // Collections 用于集合排序的工具类,可以为所有的对象类型的 记录进行排序
        // 排序只能为collection的子类
    }

    private static Properties properties = new Properties();

    /*
     * @说明 传入地图id有加载方法 依据文件规则自动产生地图文件名称,加载文件
     *
     * @param mapId 文件编号 文件id
     */
    public static void MapLoad(int mapId) {
        // 得到文件路径
        String mapName = "com/tedu/text/" + mapId + ".map";
        // 用io流来获取文件对象
        ClassLoader classLoader = GameLoad.class.getClassLoader();
        InputStream maps = classLoader.getResourceAsStream(mapName);
        if (maps == null) {
            System.out.println("null map");
            return;
        }
        try {
            properties.load(maps);

            Enumeration<?> names = properties.propertyNames();
            while (names.hasMoreElements()) { // 获取是无序的
                String key = names.nextElement().toString();
                // System.out.println(properties.getProperty(key));
                // 这样就可以自动加载地图
                String[] arrs = properties.getProperty(key).split(";");
                for (int i = 0; i < arrs.length; i++) {
                    ElementObj element = new MapObj().createElement(key + "," + arrs[i]);
                    em.addElement(element, GameElement.MAPS);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * @说明 加载图片
     * 加载图片 代码和图片之间差一个 路径问题
     */
    public static void loadImg() {

    }

    public static void main(String[] args) {
        MapLoad(1);
    }
}
