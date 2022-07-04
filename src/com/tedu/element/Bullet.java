/*
 * @Description: 玩家子弹类, 本类是玩家实体对象调用和创建
 * @version: 1.0.0
 * @Author: Jayden Chang
 * @Date: 2022-07-02 10:46:06
 * @LastEditors: Jayden Chang
 * @LastEditTime: 2022-07-04 11:05:12
 *
 * @子类开发步骤
 * 1.继承与元素基类
 * 2.重写show方法
 * 3.按照需求选择性重写其他方法,例如move
 */
package com.tedu.element;

import java.awt.*;

import javax.swing.ImageIcon;

import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;

public class Bullet extends ElementObj {
    private int attackValue; // 攻击力
    private int moveNum = 15; // 移动速度
    private String direction;
    // 还可以拓展其他子弹

    // private Bullet(int x, int y, int w, int h, ImageIcon icon, String direction)
    // {
    // super(x, y, w, h, icon);
    // this.attackValue = 1; // 默认攻击力为1
    // this.moveNum = 3; // 默认移动速度为3
    // this.direction = direction;
    // }

    public Bullet() {

    }

    // 对创建这个对象的过程进行封装, 外界只需要传输必要的约定参数,返回值就是对象实体
    @Override
    public ElementObj createElement(String str) {
        String[] split = str.split(",");
        for (String str1 : split) { // X:3
            String[] split2 = str1.split(":"); // 0是下标 是x,y,direction 1下标是值
            switch (split2[0]) {
                case "x":
                    this.setX(Integer.parseInt(split2[1]));
                    break;
                case "y":
                    this.setY(Integer.parseInt(split2[1]));
                    break;
                case "direction":
                    this.direction = split2[1];
                    break;
            }
        }
        this.setW(10);
        this.setH(10);
        return this;
    }

    @Override
    public void showElement(Graphics g) {
        // TODO Auto-generated method stub
        g.setColor(Color.red);
        g.fillOval(this.getX(), this.getY(), this.getW(), this.getH());

        // int x = this.getX(), y = this.getY();
        // switch (this.direction) {
        // case "up":
        // x += 20;
        // break;
        // case "down":
        // x += 20;
        // y += 40;
        // break;
        // case "left":
        // y += 20;
        // break;
        // case "right":
        // x += 40;
        // y += +20;
        // break;
        // }

        // System.out.println("x:" + x + " y:" + y);

        // g.fillOval(x, y, this.getW(), this.getH());
    }

    @Override
    protected void move() {
        if (this.getX() < 0 || this.getX() > 500 || this.getY() < 0 || this.getY() > 600) {
            this.setLife(false); // 执行后子弹先停住
            return;
        }

        switch (direction) {
            case "up":
                this.setY(this.getY() - moveNum);
                break;
            case "down":
                this.setY(this.getY() + moveNum);
                break;
            case "left":
                this.setX(this.getX() - moveNum);
                break;
            case "right":
                this.setX(this.getX() + moveNum);
                break;
        }
    }

    /*
     * 对于子弹来说,死亡的情况
     * 1.出边界
     * 2.碰撞 (包括玩家和敌人的子弹膨胀)
     * 3.玩家放保险(免疫)
     *
     * 处理方式: 当达到死亡条件,只进行 修改死亡状态的操作
     */

    // @Override
    // public void die() {
    // ElementManager em = ElementManager.getManager();
    // ImageIcon icon = new
    // ImageIcon(Bullet.class.getResource("/image/boom/boom.png"));
    // ElementObj obj = new Play(this.getX(), this.getY(), 50, 50, icon);
    // em.addElement(obj, GameElement.DIE);
    // }

    /* 子弹变装 */
    // private long time;
    // protected void updateImage(long gameTimes) {
    // if (gameTimes - time > 20) {
    // time = gameTimes;
    // this.setW(this.getW() + 2);
    // this.setH(this.getH() + 2);
    // // 上面是子弹变大,可以改成图片
    // }
    // }
}
