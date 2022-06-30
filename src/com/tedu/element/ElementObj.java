/*
 * @Descripttion: 所有元素的基类
 * @version: 1.0.0
 * @Author: Jayden Chang
 * @Date: 2022-06-29 11:17:43
 * @LastEditors: Jayden Chang
 * @LastEditTime: 2022-06-30 15:04:05
 */
package com.tedu.element;

import javax.swing.*;
import java.awt.*;

public abstract class ElementObj {
    private int x, y, w, h;

    private ImageIcon icon;

    // 还有各种必要的状态值,例如:是否生存
    public ElementObj() {

    }
    /*
     * @ 带参数的构造方法,可以由子类传输数据到父类
     *
     * @param x 左上角x坐标
     *
     * @param y 左上角y坐标
     *
     * @param w 左上角宽度
     *
     * @param h 左上角高度
     *
     * @param icon 图片
     */

    public ElementObj(int x, int y, int w, int h, ImageIcon icon) {
        super();
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.icon = icon;
    }

    /*
     * @description 抽象方法,显示元素
     *
     * @param g 画笔 用于绘画
     */

    public abstract void showElement(Graphics g);

    /*
     * 只要是VO(view Object) POJO(plain ordinary java object)类,就要为父类生成get和set方法
     */
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
}