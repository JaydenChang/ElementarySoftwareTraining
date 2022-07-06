/*
 * @Description:
 * @version: 1.0.0
 * @Author: Jayden Chang
 * @Date: 2022-07-04 07:21:27
 * @LastEditors: Jayden Chang
 * @LastEditTime: 2022-07-06 21:58:33
 */
package com.tedu.element;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

import com.tedu.manager.GameLoad;

public class Enemy extends ElementObj {
    public int level = 1;
    private String direction = "bot_left";

    @Override
    public void showElement(Graphics g) {
        g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(), this.getW(), this.getH(), null);

    }

    @Override
    public ElementObj createElement(String str) {
        Random ran = new Random();

        int y = 0;
        int x = ran.nextInt(730);
        ImageIcon icon = new ImageIcon(Enemy.class.getResource("/image/tank/bot/bot_right.png"));
        this.setX(x);
        // this.setX(x);
        this.setY(y);
        this.setW(icon.getIconWidth());
        this.setH(icon.getIconHeight());
        // this.setIcon(new
        // ImageIcon(Enemy.class.getResource("/image/tank/bot/bot_up.png")));
        this.setIcon(icon);
        return this;
    }

    public Enemy(int lev) {
        level = lev;
    }

    @Override
    protected void move() {
        if (this.direction.equals("bot_left")) {
            if (this.getX() < 10) {
                this.direction = "bot_right";
            }
            this.setX(this.getX() - 10);
        }
        if (this.direction.equals("bot_right")) {
            if (this.getX() > 720) {
                this.direction = "bot_left";
            }
            this.setX(this.getX() + 10);
        }
        if (level == 2) {
            if (this.direction.equals("bot_left") || this.direction.equals("bot_right")) {
                if (this.getX() < 10 || this.getX() > 720) {
                    this.direction = "bot_down";
                    this.setY(this.getY() + 10);
                }
                if (this.getY() > 200) {
                    this.direction = "bot_up";
                }
            }
            if (this.direction == "bot_down") {
                if (this.getY() < 200) {
                    this.setY(this.getY() + 10);
                } else {
                    this.direction = "bot_up";
                }
            }
            if (this.direction.equals("bot_up")) {
                if (this.getY() > 10) {
                    this.setY(this.getY() - 10);
                } else {
                    if (this.getX() < 10) {
                        this.direction = "bot_right";
                    }
                    if (this.getX() > 720) {
                        this.direction = "bot_left";
                    }
                }
            }

        }
    }

    @Override
    protected void updateImage(long... gameTime) {
        this.setIcon(GameLoad.imgMap.get(direction));
    }

}
