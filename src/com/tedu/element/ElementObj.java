/*
 * @Description: 所有元素的基类
 * @version: 1.0.0
 * @Author: Jayden Chang
 * @Date: 2022-06-29 11:17:43
 * @LastEditors: Jayden Chang
 * @LastEditTime: 2022-07-04 09:33:27
 */
package com.tedu.element;

import javax.swing.*;
import java.awt.*;

public abstract class ElementObj {
    private int x;
    private int y;
    private int w;
    private int h;

    private ImageIcon icon;

    private boolean life = true; // 生存状态,true存活,false死亡
    // 可以用枚举值来定义(生存,死亡,隐身,无敌)
    // 当重新定义一个用于判定状态的变量,需要思考:1.初始化 2.值的变化 3.值的判定

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
     * @Description: 说明,使用父类帝国一接受键盘时间方法
     * 只有需要实现键盘监听的子类, 重写这个方法(约定)
     *
     * @说明2 使用接口的方式;使用接口方式需要在监听类进行类型转换
     *
     * @题外话 约定 配置 现在大部分Java框架都要进行配置
     * 约定优于配置
     *
     * @oaram bool 点击的类型 true是按下, false是松开
     *
     * @param key是触发的键盘的code值
     *
     * @扩展 本方法可分为俩方法,一个接收按下,一个接收松开
     */

    // 此方法不是强制重写
    public void keyClick(boolean bool, int key) {

    }

    /*
     * @说明 需要移动的子类重写这个方法
     */
    protected void move() {

    }
    /*
     * @设计模式 模板模式 在其定义 对象执行方法的先后顺序 由子类选择性重写方法
     * 1.移动
     * 2.换装
     * 3.子弹发射
     */

    public final void model(long gameTime) {
        // 每个对象先换装
        updateImage(gameTime);
        // 再移动
        move();
        // 再发射子弹
        addBullet(gameTime);
    }

    protected void updateImage(long... gameTime) {
    }

    protected void addBullet(long gameTime) {
    }

    // 死亡方法 给子类继承
    // 死亡也是一个对象
    public void die() {
    }

    public ElementObj createElement(String str) {
        return null;
    }

    /*
     * @说明 本方法返回 元素的碰撞矩形对象
     */
    public Rectangle getRectangle() {
        return new Rectangle(x, y, w, h);
    }

    /*
     * @说明 碰撞方法
     * 一个是this对象,一个是传入值obj
     *
     * @param 返回true有碰撞,返回false无碰撞
     */
    public boolean crash(ElementObj obj) {
        return this.getRectangle().intersects(obj.getRectangle());
    }

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

    public boolean isLife() {
        return life;
    }

    public void setLife(boolean life) {
        this.life = life;
    }

}