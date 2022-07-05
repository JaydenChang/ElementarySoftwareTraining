/*
 * @Description:
 * @version: 1.0.0
 * @Author: Jayden Chang
 * @Date: 2022-07-05 09:17:09
 * @LastEditors: Jayden Chang
 * @LastEditTime: 2022-07-05 10:38:20
 */
package com.tedu.element;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class MapObj extends ElementObj {
    private int hp;
    private String name; // 墙的type,可以用枚举

    @Override
    public void showElement(Graphics g) {
        g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(), this.getW(), this.getH(), null);
    }

    @Override //
    public ElementObj createElement(String str) {
        String[] arr = str.split(",");
        // 写一个假图片
        ImageIcon icon = null;
        switch (arr[0]) {
            case "GRASS":
                icon = new ImageIcon(MapObj.class.getResource("/image/wall/grass.png"));
                this.hp = 1;
                break;
            case "BRICK":
                icon = new ImageIcon(MapObj.class.getResource("/image/wall/brick.png"));
                break;
            case "RIVER":
                icon = new ImageIcon(MapObj.class.getResource("/image/wall/river.png"));
                break;
            case "IRON":
                icon = new ImageIcon(MapObj.class.getResource("/image/wall/iron.png"));
                this.hp = 4;
                name = "IRON";
                break;
        }

        this.setX(Integer.parseInt(arr[1]));
        this.setY(Integer.parseInt(arr[2]));
        this.setW(icon.getIconWidth());
        this.setH(icon.getIconHeight());
        this.setIcon(icon);

        return this;
    }

    // 设置四级车打铁块
    @Override
    public void setLife(boolean life) {
        if ("IRON".equals(name)) {
            this.hp--;
            if (this.hp > 0) {
                return;
            }
        }

        super.setLife(life);
    }

}
