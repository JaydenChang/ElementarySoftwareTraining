/*
 * @Description: 游戏主线程,用于控制游戏加载,关卡,运行时自动化,游戏判定,游戏地图切换,资源释放,重新读取
 * @version: 1.0.0
 * @Author: Jayden Chang
 * @Date: 2022-07-01 15:57:05
 * @LastEditors: Jayden Chang
 * @LastEditTime: 2022-07-06 21:54:53
 *
 * @继承 使用继承的方式实现多线程(一般建议使用接口实现)
 */
package com.tedu.controller;

import java.util.List;
import java.util.Map;

import javax.lang.model.element.Element;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import com.tedu.element.ElementObj;
import com.tedu.element.Enemy;
import com.tedu.element.Play;
import com.tedu.game.GameMainJPanel;
import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
import com.tedu.manager.GameLoad;
import com.tedu.show.Dialog;

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
        GameLoad.loadImg(); // 加载图片
        GameLoad.mapLoad(Play.level); // 加载地图
        // 加载主角 可带参,一人or两人
        GameLoad.loadPlay();
        // 加载敌人

        GameLoad.loadEnemy();
        // 加载完,游戏启动

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
        List<ElementObj> enemies = em.getElementsByKey(GameElement.ENEMY);
        int total = enemies.size();
        System.out.println(total);
        while (true) {
            Map<GameElement, List<ElementObj>> all = em.getGameElements();

            List<ElementObj> bullets = em.getElementsByKey(GameElement.BULLET);
            List<ElementObj> maps = em.getElementsByKey(GameElement.MAPS);
            Play.score = total - enemies.size();

            if (enemies.size() == 0) {
                break;
            }
            autoUpdate(all, gameTime);
            elementCrash(enemies, bullets);
            elementCrash(bullets, maps);
            // 这里加一个判定水,草地,铁
            gameTime++;// 唯一的时间控制
            try {
                sleep(50); // 默认理解为一秒刷新20次
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (Play.level == 1) {
            new Dialog(Play.score, Play.level);
        }
        if (Play.level == 2) {
            new Dialog(Play.score, Play.level);
            try {
                stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 元素碰撞判定函数
    public void elementCrash(List<ElementObj> listA, List<ElementObj> listB) {

        // 在这里用循环,if为真,设置两个对象的死亡状态

        for (int i = 0; i < listA.size(); i++) {
            ElementObj enemy = listA.get(i);
            for (int j = 0; j < listB.size(); j++) {
                ElementObj bullet = listB.get(j);
                if (enemy.crash(bullet)) {
                    // System.out.println(listB);
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
        List<ElementObj> play = em.getElementsByKey(GameElement.PLAY);
        for (int i = 0; i < play.size(); i++) {
            ElementObj obj = play.get(i);
            obj.setLife(false);
        }
        Play.level++;
        try {
            sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
