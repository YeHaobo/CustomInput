package com.yhb.inputtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yhb.inputtest.view.IInputListener;
import com.yhb.inputtest.view.TextInput;

public class MainActivity extends AppCompatActivity {
    private TextInput textInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.textInput = findViewById(R.id.easyInput);
        /**
         * setStartDrawable方法不可与setPwdDrawable方法并用，
         * 若想在输入框头部添加图片
         * 则可在XML中设置drawableStart、drawableLeft和drawablePadding属性
         */
        /**
         * setPwdDrawable
         * 该属性使用前必须设置密文输入不然无效果
         */
        textInput.isShowLine(true)
                .setLetterSpac(0.3f)
//                .setStartDrawable(R.drawable.user,0)
                .setPwdDrawable(true)
                .setHintText("是否上浮了？",true)
                .setMaxText(10,true)
                .setErrorListener(new IInputListener() {
                    @Override
                    public String inputStatus(String str) {
                        if(str !=null ){
                            if (str.length() > 10)return "已超出十个字符的最大长度";
                        }
                        return null;
                    }
                });
    }
}
