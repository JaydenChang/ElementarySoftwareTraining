/*
 * @Description: 游戏主线程,用于控制游戏加载,关卡,运行时自动化,游戏判定,游戏地图切换,资源释放,重新读取
 * @version: 1.0.0
 * @Author: Jayden Chang
 * @Date: 2022-07-01 15:57:05
 * @LastEditors: Jayden Chang
 * @LastEditTime: 2022-07-02 08:39:20
 *
 * @继承 使用继承的方式实现多线程(一般建议使用接口实现)
 */
package com.tedu.controller;

import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import com.tedu.element.ElementObj;
import com.tedu.element.Play;
import com.tedu.game.GameMainJPanel;
import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;

public class GameThread extends Thread {
    private ElementManager em;

    public GameThread() {
        em = ElementManager.getManager();
    }

    @Override
    public void run() {
        while (true) {
            // 游戏开始前 读进度条 加载游戏资源(游戏场景)
            gameLoad();
            // 游戏进行时 游戏过程中
            gameRun();
            // 游戏场景结束 游戏资源回收(资源场景)
            gameOver();

            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /*
     * 游戏的加载
     */

    private void gameLoad() {
        load();
    }

    /*
     * 游戏进行时
     * 1.自动化玩家的移动,碰撞,死亡
     * 2.新元素的增加(NPC挂后出现道具)
     * 3.暂停and so on
     *
     * 先实现主角的移动
     */

    private void gameRun() {
        while (true) {
            Map<GameElement, List<ElementObj>> all = em.getGameElements();
            // Set<GameElement> set = all.keySet();

            // GameElement.values(); // 隐藏方法 返回值是一个数组，数组的顺序就是定义枚举的顺序
            for (GameElement ge : GameElement.values()) {
                List<ElementObj> list = all.get(ge);
                for (int i = 0; i < list.size(); i++) {
                    ElementObj obj = list.get(i);
                    obj.model();
                }
            }
            try {
                sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /*
     * 游戏切换关卡
     */

    private void gameOver() {
    }

    public void load() {
        // ImageIcon icon = new
        // ImageIcon("ElementarySoftTraining/image/tank/play1/player1_up.png");
        ImageIcon icon = new ImageIcon(
                GameMainJPanel.class.getResource("/image/tank/play1/player1_up.png"));
        System.out.println(icon);

        ElementObj obj = new Play(100, 100, 50, 50, icon); // 实例化对象
        // em.getElementsByKey(GameElement.PLAY).add(obj); // 将对象放到 元素管理器
        em.addElement(obj, GameElement.PLAY); // 直接添加
        // ElementObj obj1 = new Play(0, 0, 400, 400, icon);
        // em.addElement(obj1, GameElement.MAPS);
        // ElementObj obj2 = new Play(200, 200, 50, 50, icon);
        // em.addElement(obj2, GameElement.BOSS);
    }
}
