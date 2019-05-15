package com.yhb.inputtest.view;

/**
 * 输入监听
 */
public interface IInputListener {
    /**
     * 重写输入监听方法
     * @param str 返回错误信息字符串
     *            未出现错误则返回空
     * @return
     */
    String inputStatus(String str);
}
