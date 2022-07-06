/*
 * @Descripttion: 游戏窗体
 * @version: 1.0.0
 * @Author: Jayden Chang
 * @Date: 2022-06-30 09:20:07
 * @LastEditors: Jayden Chang
 * @LastEditTime: 2022-07-06 21:31:58
 *
 * @说明: 游戏窗体 主要实现功能: 关闭,显示,最大最小化
 * @功能说明:
 *        需要嵌入面板,启动主线程等
 * @窗体说明: swing awt 窗体大小(记录用户上次使用软件的窗体样式)
 *
 * @分析:
 * 1.面板绑定到窗体
 * 2.监听绑定
 * 3.游戏主线程绑定
 * 4.显示窗体
 */
package com.tedu.show;

import javax.swing.*;
import java.awt.event.*;
import com.tedu.element.Play;

public class GameJFrame extends JFrame implements Runnable {
    public static int GameX = 780;
    public static int GameY = 620;
    private JPanel jPanel = null; // 正在显示的面板
    private JLabel jLabel = new JLabel(); // 得分显示面板
    private KeyListener keyListener = null; // 键盘监听器
    private Thread thread = null; // 游戏主线程
    // 俩鼠标监听器
    private MouseMotionListener mouseMotionListener = null;
    private MouseListener mouseListener = null;

    public GameJFrame() {
        init();
    }

    /*
     * set注入: 通过set方法注入配置文件中读取的数据
     * 讲配置文件中的数据赋值为类的属性
     * 构造注入: 需要配合构造方法
     * spring 中ioc进行对象的自动生成,管理
     */
    public void setjPanel(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    public void setKeyListener(KeyListener keyListener) {
        this.keyListener = keyListener;
    }

    public void setMouseMotionListener(MouseMotionListener mouseMotionListener) {
        this.mouseMotionListener = mouseMotionListener;
    }

    public void setMouseListener(MouseListener mouseListener) {
        this.mouseListener = mouseListener;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public void init() {
        this.setSize(GameX, GameY); // 设置窗体大小
        this.setTitle("test");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗体关闭操作
        this.setLocationRelativeTo(null); // 设置窗体居中显示
    }

    // 窗体布局: 可以 存档,读档
    public void addButton() {
        // this.setLayout(); // 可以添加控件
    }

    public void start() {
        if (jPanel != null) {
            this.add(jPanel);
        }
        if (keyListener != null) {
            this.addKeyListener(keyListener);
        }
        if (thread != null) {
            thread.start();
        }
        // 界面刷新
        this.setVisible(true);
        // 如果jp 是runnable的子类实体对象
        if (this.jPanel instanceof Runnable) {
            // 已做类型判定,强制类型转换不会出错
            new Thread((Runnable) this.jPanel).start();
            // 等效于下面写法
            // Runnable run=(Runnable)this.jPanel;
            // Thread th = new Thread(run);
            // th.start();
        }

    }

    @Override
    public void run() {
        this.repaint();
    }
}
