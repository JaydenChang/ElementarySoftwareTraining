/*
 * @Descripttion: 游戏的主要面板
 * @version: 1.0.0
 * @Author: Jayden Chang
 * @Date: 2022-06-30 10:29:20
 * @LastEditors: Jayden Chang
 * @LastEditTime: 2022-07-02 08:25:52
 * @功能说明: 主要进行元素的显示,同时进行界面的刷新(多线程)
 * @多线程刷新:
 * 1.本类实现线程接口
 * 2.本类顶一个内部类实现
 */
package com.tedu.game;

import com.tedu.element.ElementObj;
// import com.tedu.element.Play;
import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;

import javax.swing.*;
import java.awt.Graphics;
import java.util.*;

public class GameMainJPanel extends JPanel implements Runnable {
    // 联动管理器
    private ElementManager em;

    public GameMainJPanel() {
        init();

    }

    public void init() {
        em = ElementManager.getManager();
    }

    /*
     * 绘画时，先画的图片会在底层，后画的会覆盖先画的
     * 约定: 本方法只执行一次,想实时刷新,要用多线程
     */

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Map<GameElement, List<ElementObj>> all = em.getGameElements();
        // Set<GameElement> set = all.keySet();

        // GameElement.values(); // 隐藏方法 返回值是一个数组，数组的顺序就是定义枚举的顺序
        for (GameElement ge : GameElement.values()) {
            List<ElementObj> list = all.get(ge);
            // System.out.println(list);
            // System.out.println();
            for (int i = 0; i < list.size(); i++) {
                ElementObj obj = list.get(i);
                // System.out.println(obj);
                // System.out.println();
                obj.showElement(g);
            }
        }

    }

    @Override
    public void run() {
        while (true) {

            this.repaint();
            // 一般情况,多线程都会用一个休眠,控制速度
            try {
                Thread.sleep(50);
                // 休眠50ms,一秒刷新20次
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
