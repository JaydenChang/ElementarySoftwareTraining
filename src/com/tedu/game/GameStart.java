/*
 * @Descripttion: the only entrance for the game
 * @version: 1.0.0
 * @Author: Jayden Chang
 * @Date: 2022-06-30 09:42:57
 * @LastEditors: Jayden Chang
 * @LastEditTime: 2022-07-06 10:53:20
 */
package com.tedu.game;

import com.tedu.controller.GameListener;
import com.tedu.controller.GameThread;
import com.tedu.show.GameJFrame;

public class GameStart {
    public static void main(String[] args) {
        GameJFrame gj = new GameJFrame();
        // 实例化面板,注入到jframe
        GameMainJPanel jp = new GameMainJPanel();
        // 实例化监听
        GameListener listener = new GameListener();
        // 实例化主线程
        GameThread th = new GameThread();

        gj.setKeyListener(listener);
        gj.setjPanel(jp);
        gj.setThread(th);
        gj.start();

        // Enum en = null;
    }
}
