/*
 * @Descripttion:
 * @version:
 * @Author: Jayden Chang
 * @Date: 2022-06-30 14:57:43
 * @LastEditors: Jayden Chang
 * @LastEditTime: 2022-07-02 08:55:59
 */
package com.tedu.element;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import com.tedu.manager.GameLoad;

public class Play extends ElementObj {
    /*
     * 移动属性
     * 1.单属性 配合 方向枚举类型是哟个;一次只能往一个方向动
     * 2.双属性 上下和左右 可以配合bool使用,true向上,false向下, 需要另一个变量来确定是否按下方向键,0,1,2分别是不动,上,下
     * 3.4属性 上下左右都可以 boolean配合使用,true动,false不动,后按会重置前按的
     *
     * 说明: 以上三种方式 是代码编写和判定方式不一样
     * 说明:游戏中很多判定,要使用灵活判定,很多属性值也用判定属性
     * 多状态可以用map<泛型,boolean>;set<判定对象> 判定对象中也有时间
     *
     * @问题
     * 1.图片要读取到内存中: 加载器 临时处理方式 ,手动编写到存储到内存中
     * 2.when to修改图片(图片是在父类的属性存储)
     * 3.图片用什么集合存储
     */

    private boolean left = false;
    private boolean up = false;
    private boolean right = false;
    private boolean down = false;

    // 图片集合 用map存储 枚举类型配合移动(拓展)
    private Map<String, ImageIcon> imgMap;
    // 默认主角方向向上
    private String direction = "up";

    public Play(int x, int y, int w, int h, ImageIcon icon) {
        super(x, y, w, h, icon);

        imgMap = new HashMap<>();
        imgMap.put("left", new ImageIcon(Play.class.getResource("/image/tank/play1/player1_left.png")));
        imgMap.put("down", new ImageIcon(Play.class.getResource("/image/tank/play1/player1_down.png")));
        imgMap.put("up", new ImageIcon(Play.class.getResource("/image/tank/play1/player1_up.png")));
        imgMap.put("right", new ImageIcon(Play.class.getResource("/image/tank/play1/player1_right.png")));
    }

    /*
     * 面向对象自己第一个思想: 对象自己的事情自己做
     */
    @Override
    public void showElement(Graphics g) {
        g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(), this.getW(), this.getH(), null);
    }

    /*
     * @说明 重写方法: 重写的要求,方法名称 和类型参数序列 必须和父类的方法一样
     *
     * @重点 监听的数据需要改变状态值
     */

    // 在坦克大战这个游戏实例里,只用双属性控制
    public void keyClick(boolean bool, int key) {
        if (bool) {
            switch (key) {
                case 37:
                    this.right = false;
                    this.up = false;
                    this.down = false;
                    this.left = true;
                    this.direction = "left";
                    break;
                case 38:
                    this.down = false;
                    this.left = false;
                    this.right = false;
                    this.up = true;
                    this.direction = "up";
                    break;
                case 39:
                    this.left = false;
                    this.up = false;
                    this.down = false;
                    this.right = true;
                    this.direction = "right";
                    break;
                case 40:
                    this.up = false;
                    this.left = false;
                    this.right = false;
                    this.down = true;
                    this.direction = "down";
                    break;
            }
        } else {
            switch (key) {
                case 37:
                    this.left = false;
                    break;
                case 38:
                    // this.setY(this.getY() - 10);
                    this.up = false;
                    break;
                case 39:
                    // this.setX(this.getX() + 10);
                    this.right = false;
                    break;
                case 40:
                    // this.setY(this.getY() + 10);
                    this.down = false;
                    break;
            }
        }

    }

    @Override
    public void move() {
        // 坐标大小调整
        if (this.left && this.getX() > 0) {
            this.setX(this.getX() - 10);
        }
        if (this.up && this.getY() > 0) {
            this.setY(this.getY() - 10);
        }
        if (this.right && this.getX() < 900 - this.getW()) {
            this.setX(this.getX() + 10);
        }
        if (this.down && this.getY() < 600 - this.getH()) {
            this.setY(this.getY() + 10);
        }
    }

    protected void updateImage() {
        this.setIcon(GameLoad.imgMap.get(direction));
    }
}
