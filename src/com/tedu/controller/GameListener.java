/*
 * @Descripttion: 监听类，用于监听用户操作 KeyListener
 * @version:
 * @Author: Jayden Chang
 * @Date: 2022-07-01 09:49:55
 * @LastEditors: Jayden Chang
 * @LastEditTime: 2022-07-02 09:00:56
 */
package com.tedu.controller;

import java.awt.event.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tedu.element.ElementObj;
import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;

public class GameListener implements KeyListener {
    private ElementManager em = ElementManager.getManager();

    /*
     * 通过一个集合来记录所有按下的键,如果重复触发就直接结束
     * 同时,第一次按下,记录到集合中,第二次判定集合中是否有,松开就直接删除集合中的元素
     * set集合
     */

    private Set<Integer> set = new HashSet<Integer>();

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // 拿到玩家集合
        int key = e.getKeyCode();
        if (set.contains(key)) {
            return;
        }
        set.add(key);

        List<ElementObj> play = em.getElementsByKey(GameElement.PLAY);
        for (ElementObj obj : play) {
            obj.keyClick(true, e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (!set.contains(key)) {
            // 如果这个不存在,就停止
            return;
        }
        set.remove(key);
        List<ElementObj> play = em.getElementsByKey(GameElement.PLAY);
        for (ElementObj obj : play) {
            obj.keyClick(false, e.getKeyCode());
        }
    }
}
