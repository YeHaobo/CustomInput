package com.yhb.inputtest.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.yhb.inputtest.R;

/**
 *         textInputEditText.setHintText("用户名",true)
 *                 .isPassWord(true)
 *                 .isShowLine(false)
 *                 .setErrorListener(new IInputListener() {
 *                     @Override
 *                     public String inputStatus(String str) {
 *                         if(str.length()>5) {
 *                             return "最大只能输入 5 个字符";
 *                         }
 *                         else {
 *                             return null;
 *                         }
 *                     }
 *                 });
 * 输入框
 */
public class TextInput extends LinearLayout implements IInput{
    private TextInputLayout textInputLayout;
    private TextInputEditText textInputEditText;
    private Context mContext;
    public TextInput(Context context) {
        super(context);
        initView(context);
        this.mContext = context;
    }

    public TextInput(Context context, @NonNull AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        this.mContext = context;
    }

    private void initView(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_input, this);
        this.textInputLayout = view.findViewById(R.id.vTextInput);
        this.textInputEditText = view.findViewById(R.id.vEditText);
        this.textInputLayout.setHintTextAppearance(R.style.hintInput);
        this.setLetterSpac(0.3f);
    }

    /**  去除下划线 */
    @Override
    public TextInput isShowLine(boolean isShow){
        if( !isShow ){
            textInputEditText.setBackground(null);
        }
        return this;
    }

    /**  只允许输入数字 */
    @Override
    public TextInput isNumber(boolean isNum) {
        if(isNum){
            textInputEditText.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_VARIATION_NORMAL);
        }
        return this;
    }

    /** 数字密文*/
    @Override
    public TextInput isNumberPsd(boolean isNnmPsd) {
        if(isNnmPsd){
            textInputEditText.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        }
        return this;
    }

    /**  带密文输入 */
    @Override
    public TextInput isPassWord(boolean isPsd) {
        if(isPsd){
            textInputEditText.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
            textInputEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override //除去中文
                public void afterTextChanged(Editable s) { cutChinese(s); }
            });
        }
        return this;
    }

    /**   文本输入(除汉字外) */
    @Override
    public TextInput isText(boolean isText) {
        if(isText){
            textInputEditText.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            textInputEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override//除去中文
                public void afterTextChanged(Editable s) { cutChinese(s); }
            });
        }
        return this;
    }

    /**  字间距设置 */
    @Override
    public TextInput setLetterSpac(float f) {
        if(f > 0){
            textInputEditText.setLetterSpacing(f);
        }
        return this;
    }

    /**  设置图片 */
    private int dp2int(int dp){
        float oo = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, mContext.getResources().getDisplayMetrics());
        return (Float.valueOf(oo)).intValue();
    }
    @Override
    public TextInput setStartDrawable(int resDrawable,int paddingDp) {
        Drawable drawable = getResources().getDrawable(resDrawable);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        textInputEditText.setCompoundDrawables(drawable,null,null,null);
        textInputEditText.setCompoundDrawablePadding(dp2int(paddingDp));
        return this;
    }

    /**  设置提示文字 获取焦点时提示上浮*/
    @Override
    public TextInput setHintText(final String hintText, boolean isEnabled) {
        if(isEnabled){
            textInputLayout.setHint(hintText);
            textInputLayout.setHintEnabled(true);
            textInputLayout.setHintAnimationEnabled(true);
        }
        else{
            textInputEditText.setHint(hintText);
            textInputEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus){
                        textInputEditText.setHint("");
                    }else{
                        textInputEditText.setHint(hintText);
                    }
                }
            });
        }
        return this;
    }

    /**  设置最大长度 (是否超长可以继续输入)*/
    @Override
    public TextInput setMaxText(int max, boolean isShowNum) {
        if(isShowNum){
            textInputLayout.setCounterEnabled(true);
            textInputLayout.setCounterMaxLength(max);
        }
        else{
            textInputEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(max)});
        }
        return this;
    }

    /**   设置眼睛图标 */
    @Override
    public TextInput setPwdDrawable(boolean isShow) {
        if(isShow){
            textInputLayout.setPasswordVisibilityToggleEnabled(true);
        }
        return this;
    }

    /**  设置错误提示 */
    @Override
    public TextInput setErrorListener(final IInputListener listener) {
        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) { }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(! TextUtils.isEmpty(s.toString())){//判断非空
                    String errorStr = listener.inputStatus(s.toString());
                    if(errorStr != null){ //判断输入是否正确
                        textInputLayout.setError(errorStr);
                        textInputLayout.setErrorEnabled(true);
                    }else {
                        textInputLayout.setErrorEnabled(false);
                    }
                }
            }
        });
        return this;
    }

    /**  获取字符 */
    @Override
    public String getTextContext() {
        if(textInputEditText.getText() != null){
            return textInputEditText.getText().toString().trim();
        }
        return null;
    }

    /**  设置字符 */
    @Override
    public void setTextContext(String text) {
        textInputEditText.setText(text);
    }

    /** 除去中文 */
    public void cutChinese(Editable s){
        if (s.length() > 0) {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c >= 0x4e00 && c <= 0X9fff) { // 根据字节码判断
                    // 如果是中文，则清除输入的字符，否则保留
                    s.delete(i,i+1);
                }
            }
        }
    }
}

