package com.yhb.inputtest.view;


/**
 * 输入框所有接口
 */
public interface IInput {
    TextInput isShowLine(boolean isShow);//是否显示下划线
    TextInput isNumber(boolean isNum);//只允许输入数字
    TextInput isPassWord(boolean isPsd);//普通密文输入(除汉字外)
    TextInput isNumberPsd(boolean isNnmPsd);//数字密文输入
    TextInput isText(boolean isText);//文本输入(除汉字外)
    TextInput setLetterSpac(float f);//字间距
    TextInput setStartDrawable(int resDrawable, int paddingDp);//设置图片
    TextInput setHintText(String hintText, boolean isEnabled);//设置提示文字
    TextInput setMaxText(int max, boolean isShowNum);//设置最大长度
    TextInput setPwdDrawable(boolean isShow);//设置眼睛图标
    TextInput setErrorListener(IInputListener listener);//设置错误提示
    String getTextContext();//获取输入框字符
    void setTextContext(String text);//设置输入框字符（该方法封闭链式）
}
