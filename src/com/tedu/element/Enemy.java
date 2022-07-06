/*
 * @Description:
 * @version: 1.0.0
 * @Author: Jayden Chang
 * @Date: 2022-07-04 07:21:27
 * @LastEditors: Jayden Chang
 * @LastEditTime: 2022-07-06 09:50:54
 */
package com.tedu.element;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

public class Enemy extends ElementObj {

    @Override
    public void showElement(Graphics g) {
        g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(), this.getW(), this.getH(), null);

    }

    @Override
    public ElementObj createElement(String str) {
        Random ran = new Random();
        int x = ran.nextInt(500);
        int y = ran.nextInt(500);
        // int x = ran.nextInt(500);
        // int y = ran.nextInt(500);
        this.setX(x);
        this.setY(y);
        this.setW(50);
        this.setH(50);
        this.setIcon(new ImageIcon(Enemy.class.getResource("/image/tank/bot/bot_up.png")));
        return this;
    }

}
