/*
 * @Descripttion:
 * @version:
 * @Author: Jayden Chang
 * @Date: 2022-06-29 10:57:07
 * @LastEditors: Jayden Chang
 * @LastEditTime: 2022-06-29 11:26:29
 */

package com.tedu.manager;

import java.util.*;
import com.tedu.element.*;

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

    public Map<GameElement, List<ElementObj>> getGameElements() {
        return gameElements;
    }
}
