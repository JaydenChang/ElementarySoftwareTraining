/*
 * @Descripttion:
 * @version:
 * @Author: Jayden Chang
 * @Date: 2022-06-30 14:57:43
 * @LastEditors: Jayden Chang
 * @LastEditTime: 2022-07-06 21:14:04
 */
package com.tedu.element;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
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

    public static int score = 0;
    public static int level = 1;

    // 图片集合 用map存储 枚举类型配合移动(拓展)
    // private Map<String, ImageIcon> imgMap;
    // 默认主角方向向上
    private String direction = "up";
    private boolean playerAttackType = false; // 攻击状态

    public Play() {
    }

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
                case 32:
                    this.playerAttackType = true;
                    break;
                // 开启攻击状态
            }
        } else {
            switch (key) {
                case 37:
                    this.left = false;
                    break;
                case 38:
                    this.up = false;
                    break;
                case 39:
                    this.right = false;
                    break;
                case 40:
                    this.down = false;
                    break;
                case 32:
                    this.playerAttackType = false;
                    break;
                // 关闭攻击状态
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
        if (this.right && this.getX() < 780 - this.getW()) {
            this.setX(this.getX() + 10);
        }
        if (this.down && this.getY() < 620 - this.getH()) {
            this.setY(this.getY() + 10);
        }
    }

    @Override
    protected void updateImage(long... gameTimes) {
        this.setIcon(GameLoad.imgMap.get(direction));
    }

    @Override
    public ElementObj createElement(String str) {
        String[] split = str.split(",");
        this.setX(Integer.parseInt(split[0]));
        this.setY(Integer.parseInt(split[1]));
        ImageIcon icon2 = GameLoad.imgMap.get(split[2]);
        this.setW(icon2.getIconWidth());
        this.setH(icon2.getIconHeight());
        this.setIcon(icon2);

        return this;
    }

    /*
     * 重写规则
     * 1.重写方法名称和返回值要和父类一样
     * 2.重写方法的传入参数类型要和父类一样
     * 3.重写方法访问修饰符,只能比父类更广泛
     * 比如父类的方法是受保护的,但现在要在非子类中调用,可以子类继承,直接super,再public
     * 4.重写的方法,抛出的异常,不可以比父类更宽泛,eg父类抛了一个'数组下标移除异常', 子类不能直接抛Exception
     * 子弹添加,需要发射者的坐标位置,发射者的方向,
     */
    private long bulletTime = 0;
    // bulletTime和传入的时间gameTime进行比较,赋值等操作运算,控制子弹间隔

    @Override
    public void addBullet(long gameTime) {
        if (!this.playerAttackType) {
            return;
        }
        this.playerAttackType = false; // 按一次,发射一个子弹(也可增加变量来控制)
        // super.addBullet();
        // 构造一个类 需要较多工作 可以选一个方式 如小工厂
        // 将构造对象的多个步骤进行封装成为一个方法 返回值直接是这个对象
        // 传递一个固定格式 {x:1,y:2,direction:"up"} json格式
        ElementObj obj = GameLoad.getObj("bullet");
        ElementObj element = obj.createElement(this.toString());
        ElementManager.getManager().addElement(element, GameElement.BULLET);
        // try {
        // Class<?> forName = Class.forName("com.tedu....");
        // ElementObj element = Bullet.class.newInstance().createElementObj("");

        // } catch (InstantiationException e) {
        // e.printStackTrace();
        // } catch (IllegalAccessException e) {

        // e.printStackTrace();
        // } catch (ClassNotFoundException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

        // 会帮助返回对象的实体,并初始化数据
    }

    // 此处为偷懒,直接用toString,之后还是要自己去定义一个方法
    @Override
    public String toString() {
        // {x:1,y:2,direction:"up"} json格式
        // 子弹在发射时就已经给予固定的轨迹,可以加上目标,修改json格式
        int x = this.getX();
        int y = this.getY();
        switch (this.direction) {
            // 后面会改成图片大小,不是固定数值
            case "up":
                x += 12;
                break;
            case "left":
                y += 12;
                break;
            case "down":
                x += 12;
                y += 27;
                break;
            case "right":
                x += 27;
                y += 12;
                break;
        }
        return "x:" + x + ",y:" + y + ",direction:" + this.direction;

    }

    // @Override
    // public void setLife(boolean life) {
    // this.life = life;
    // }
}
