/*
 * @Description: 假的加载器
 * @version: 1.0.0
 * @Author: Jayden Chang
 * @Date: 2022-07-02 09:01:02
 * @LastEditors: Jayden Chang
 * @LastEditTime: 2022-07-02 09:33:34
 */
package com.tedu.manager;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import com.tedu.element.Play;

public class GameLoad {
    public static Map<String, ImageIcon> imgMap;

    static {
        imgMap = new HashMap<>();
        imgMap.put("left", new ImageIcon(Play.class.getResource("/image/tank/play1/player1_left.png")));
        imgMap.put("down", new ImageIcon(Play.class.getResource("/image/tank/play1/player1_down.png")));
        imgMap.put("up", new ImageIcon(Play.class.getResource("/image/tank/play1/player1_up.png")));
        imgMap.put("right", new ImageIcon(Play.class.getResource("/image/tank/play1/player1_right.png")));
    }
}
