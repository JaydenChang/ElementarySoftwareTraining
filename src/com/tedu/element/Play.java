/*
 * @Descripttion:
 * @version:
 * @Author: Jayden Chang
 * @Date: 2022-06-30 14:57:43
 * @LastEditors: Jayden Chang
 * @LastEditTime: 2022-06-30 15:15:50
 */
package com.tedu.element;

import java.awt.*;

import javax.swing.ImageIcon;

public class Play extends ElementObj {
    public Play(int x, int y, int w, int h, ImageIcon icon) {
        super(x, y, w, h, icon);
    }

    /*
     * 面向对象自己第一个思想: 对象自己的事情自己做
     */
    @Override
    public void showElement(Graphics g) {
        g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(), this.getW(), this.getH(), null);
    }
}
