/*
 * @Description: 游戏主线程,用于控制游戏加载,关卡,运行时自动化,游戏判定,游戏地图切换,资源释放,重新读取
 * @version: 1.0.0
 * @Author: Jayden Chang
 * @Date: 2022-07-01 15:57:05
 * @LastEditors: Jayden Chang
 * @LastEditTime: 2022-07-04 10:42:14
 *
 * @继承 使用继承的方式实现多线程(一般建议使用接口实现)
 */
package com.tedu.controller;

import java.util.List;
import java.util.Map;

import javax.lang.model.element.Element;
import javax.swing.ImageIcon;

import com.tedu.element.ElementObj;
import com.tedu.element.Enemy;
import com.tedu.element.Play;
import com.tedu.game.GameMainJPanel;
import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;

public class GameThread extends Thread {
    private ElementManager em;

    public GameThread() {
        em = ElementManager.getManager();
    }

    private long gameTime = 0L;

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

            autoUpdate(all, gameTime);

            elementCrash();

            gameTime++;// 唯一的时间控制
            try {
                sleep(50); // 默认理解为一秒刷新100次
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void elementCrash() {
        List<ElementObj> enemies = em.getElementsByKey(GameElement.ENEMY);
        List<ElementObj> bullets = em.getElementsByKey(GameElement.BULLET);

        // 在这里用循环,if为真,设置两个对象的死亡状态

        for (int i = 0; i < enemies.size(); i++) {
            ElementObj enemy = enemies.get(i);
            for (int j = 0; j < bullets.size(); j++) {
                ElementObj bullet = bullets.get(j);
                if (enemy.crash(bullet)) {
                    enemy.setLife(false);
                    bullet.setLife(false);
                    break;
                }
            }
        }

    }

    public void autoUpdate(Map<GameElement, List<ElementObj>> all, long gameTime) {
        // GameElement.values(); // 隐藏方法 返回值是一个数组，数组的顺序就是定义枚举的顺序
        for (GameElement ge : GameElement.values()) {
            List<ElementObj> list = all.get(ge);

            // 编写直接操作集合数据的代码建议不用迭代器 (foreach),如果数据改变,会空指针报错
            for (int i = list.size() - 1; i >= 0; i--) {
                // for (int i = 0; i < list.size(); i++) {
                ElementObj obj = list.get(i);
                if (!obj.isLife()) {
                    // list.remove(i--);
                    // 启动一个死亡方法(方法中可以做很多事,eg死亡动画,掉装备)
                    obj.die();
                    list.remove(i);
                    continue;
                }
                obj.model(gameTime);
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

        // 添加一个敌人类, 仿造玩家类写, 不需要时间,不需要键盘监听
        // 实现敌人的显示,同时实现最简单的移动, 如某坐标移动到某坐标

        // 实现子弹发射, 子弹移动, 元素死亡

        // 道具掉落, 参考子弹发射和死亡

        // 创建敌人
        for (int i = 0; i < 10; i++) {
            em.addElement(new Enemy().createElement(""), GameElement.ENEMY);
        }

    }
}
