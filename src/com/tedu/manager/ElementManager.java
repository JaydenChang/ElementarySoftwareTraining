/*
 * @Descripttion: 元素管理器
 * @version: 1.0.0
 * @Author: Jayden Chang
 * @Date: 2022-06-29 10:57:07
 * @LastEditors: Jayden Chang
 * @LastEditTime: 2022-06-30 15:28:37
 */

package com.tedu.manager;

import java.util.*;
import com.tedu.element.*;

/*
 * @说明 本类是元素管理器, 专门存储所有元素,同时提供方法
 * @问题一: 储存所有元素数据, 怎么放? list map set三大集合
 * @问题二: 管理器是视图和控制要访问, 管理器必须只有一个,单例模式
 */

public class ElementManager {
    private List<Object> listMap;
    private List<Object> listPlay;
    /*
     * String作为key匹配所有元素 play->List<Object> listPlay
     * enemy->List<Object> listEnemy
     * 枚举类型, 当作map的key来区分不一样的资源, 用于获取资源
     * List中元素的泛型, 应该是 元素 基类
     * 所有元素都可以存放的到map集合中, 显示模块只需要获取到这个map就能
     * 显示有的界面需要显示的元素(调用元素基类的showElement())
     */

    private Map<GameElement, List<ElementObj>> gameElements;

    // 本方法一定不够用
    public Map<GameElement, List<ElementObj>> getGameElements() {
        return gameElements;
    }

    // 添加元素(多半由加载器调用)
    public void addElement(ElementObj obj, GameElement ge) {
        // List<ElementObj> list = gameElements.get(ge);
        // list.add(obj);
        gameElements.get(ge).add(obj);
        // 添加对象到集合中,按key值就行存储
    }

    // 根据key返回list集合,取出某一类元素
    public List<ElementObj> getElementsByKey(GameElement ge) {
        return gameElements.get(ge);
    }

    /*
     * 单例模式: 内存中有且仅有一个实例
     * 饿汉模式 -> 启动就自动加载
     * 饱汉模式 -> 需要时才启动加载
     *
     * 编写方式:
     * 1.需要一个静态的属性(定义一个常量) 单例的引用
     * 2.提供一个静态的方法(返回这个实例) return单例的引用
     * 3.一般为防止其他人自己使用(类可实例化), 所以会私有化构造方法
     */
    private static ElementManager EM;

    // 线程锁 -> 本方法执行中只有一个线程
    public static synchronized ElementManager getManager() {
        if (EM == null) {
            // 空值判定
            EM = new ElementManager();
        }
        return EM;
    }

    private ElementManager() {
        // 私有化构造方法
        init(); // 实例化方法
    }

    // static {
    // // 饿汉实例化对象 静态语句块是类被加载时直接执行
    // EM = new ElementManager(); // 只执行一次
    // }

    /*
     * 本方法为将来可能出现的功能拓展,重写init方法准备
     */
    public void init() {
        // hashmap散列
        gameElements = new HashMap<GameElement, List<ElementObj>>();
        // 将每种元素集合放到map
        gameElements.put(GameElement.PLAY, new ArrayList<ElementObj>());
        gameElements.put(GameElement.ENEMY, new ArrayList<ElementObj>());
        gameElements.put(GameElement.BOSS, new ArrayList<ElementObj>());
        gameElements.put(GameElement.MAPS, new ArrayList<ElementObj>());

    }
}
