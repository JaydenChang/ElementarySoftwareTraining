/*
 * @Descripttion: 游戏的主要面板
 * @version: 1.0.0
 * @Author: Jayden Chang
 * @Date: 2022-06-30 10:29:20
 * @LastEditors: Jayden Chang
 * @LastEditTime: 2022-06-30 15:44:31
 * @功能说明: 主要进行元素的显示,同时进行界面的刷新(多线程)
 */
package com.tedu.game;

import com.tedu.element.ElementObj;
import com.tedu.element.Play;
import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;

import javax.swing.*;
import java.awt.Graphics;
import java.util.*;

public class GameMainJPanel extends JPanel {
    // 联动管理器
    private ElementManager em;

    public GameMainJPanel() {
        init();
        // 以下代码 后面会换地方重写(测试代码)
        load();
    }

    public void load() {
        ImageIcon icon = new ImageIcon("image\\tank\\play1\\player1_up.png");
        ElementObj obj = new Play(100, 100, 50, 50, icon); // 实例化对象
        // em.getElementsByKey(GameElement.PLAY).add(obj); // 将对象放到 元素管理器
        em.addElement(obj, GameElement.PLAY); // 直接添加
    }

    public void init() {
        em = ElementManager.getManager();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Map<GameElement, List<ElementObj>> all = em.getGameElements();

        Set<GameElement> set = all.keySet();
        for (GameElement ge : set) {
            List<ElementObj> list = all.get(ge);
            for (int i = 0; i < list.size(); i++) {
                ElementObj obj = list.get(i);
                obj.showElement(g);
            }
        }
    }
}
