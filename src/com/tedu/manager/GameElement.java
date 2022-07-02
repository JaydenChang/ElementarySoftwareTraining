/*
 * @Descripttion: 游戏元素
 * @version: 1.0.0
 * @Author: Jayden Chang
 * @Date: 2022-06-29 11:08:40
 * @LastEditors: Jayden Chang
 * @LastEditTime: 2022-06-30 19:21:33
 */
package com.tedu.manager;

public enum GameElement {
    /*
     * PLAY 玩家
     * MAP 地图
     * ENEMY 敌人
     * BOSS boss
     */
    MAPS, PLAY, ENEMY, BOSS; // 枚举顺序是 声明 的顺序
    // 我们定义的枚举类型,在编译时,虚拟机回自动帮助生成class文件,并且会加载很多代码和方法

    // private GameElement() {

    // }

    // private GameElement(int id) {

    // }
}
