/*
 * @Description:
 * @version: 1.0.0
 * @Author: Jayden Chang
 * @Date: 2022-07-06 09:22:18
 * @LastEditors: Jayden Chang
 * @LastEditTime: 2022-07-06 14:58:27
 */
package com.tedu.element;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class MapObj extends ElementObj {
    private int hp;
    private String wallType;

    @Override
    public void showElement(Graphics g) {
        g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(), this.getW(), this.getH(), null);

    }

    @Override
    public ElementObj createElement(String str) {
        // System.out.println(str);
        String[] arr = str.split(",");
        ImageIcon icon = null;
        switch (arr[0]) {
            case "GRASS":
                icon = new ImageIcon(MapObj.class.getResource("/image/wall/grass.png"));
                break;
            case "BRICK":
                icon = new ImageIcon(MapObj.class.getResource("/image/wall/brick.png"));
                this.hp = 1;
                break;
            case "RIVER":
                icon = new ImageIcon(MapObj.class.getResource("/image/wall/river.png"));
                break;
            case "IRON":
                icon = new ImageIcon(MapObj.class.getResource("/image/wall/iron.png"));
                this.hp = 8;
                wallType = "IRON";
                break;
        }
        int x = Integer.parseInt(arr[1]);
        int y = Integer.parseInt(arr[2]);
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();
        this.setX(x);
        this.setY(y);
        this.setW(w);
        this.setH(h);
        this.setIcon(icon);
        return this;
    }

    @Override
    public void setLife(boolean life) {
        if ("IRON".equals(wallType)) {
            this.hp--;
            if (this.hp > 0) {
                return;
            }
        }
        super.setLife(life);
    }
}
