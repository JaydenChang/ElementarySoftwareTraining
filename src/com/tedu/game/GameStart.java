/*
 * @Descripttion: the only entrance for the game
 * @version: 1.0.0
 * @Author: Jayden Chang
 * @Date: 2022-06-30 09:42:57
 * @LastEditors: Jayden Chang
 * @LastEditTime: 2022-06-30 11:15:28
 */
package com.tedu.game;

import com.tedu.show.GameJFrame;

public class GameStart {
    public static void main(String[] args) {
        GameJFrame gj = new GameJFrame();
        // 实例化面板,注入到jframe
        GameMainJPanel jp = new GameMainJPanel();
        gj.setjPanel(jp);
        gj.start();

    }
}
