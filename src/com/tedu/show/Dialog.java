/*
 * @Description:
 * @version: 1.0.0
 * @Author: Jayden Chang
 * @Date: 2022-07-06 19:37:09
 * @LastEditors: Jayden Chang
 * @LastEditTime: 2022-07-06 21:25:18
 */
package com.tedu.show;

import java.awt.Container;

import javax.swing.*;

public class Dialog extends JDialog {
    private void init(int score, int level) {
        this.setTitle("通关提示");
        this.setVisible(true);
        this.setSize(200, 100);
        this.setLocation(960, 540);
        Container contentPane = this.getContentPane();
        if (level == 1) {
            JLabel label = new JLabel("本关得分: " + score);
            contentPane.add(label);
        }
        if (level == 2) {
            JLabel label = new JLabel("游戏结束, 本关得分: " + score);
            contentPane.add(label);
        }

    }

    public Dialog(int score, int level) {
        init(score, level);
    }

}
