# CustomInput
好用的输入框（链式编程一步到位）
## 引入依赖
  引入V4依赖包
    ![V4依赖](https://github.com/YeHaobo/CustomInput/blob/master/1.png)
    
## 导入项目
   将View文件夹下的IInput、IInputListener、TextInput三个文件放入自己的项目中
    ![文件导入](https://github.com/YeHaobo/CustomInput/blob/master/5.png)
    将style文件中的样式拷贝入自己的样式文件，样式可自行修改
    ![样式引用](https://github.com/YeHaobo/CustomInput/blob/master/4.png)
    
## 使用方法
  使用实例
    ![V4依赖](https://github.com/YeHaobo/CustomInput/blob/master/2.png)
    所有暴露的接口
    ![V4依赖](https://github.com/YeHaobo/CustomInput/blob/master/3.png)
    
## 注意事项
  setStartDrawable方法不可与setPwdDrawable方法并用，若想在输入框头部添加图片，则可在XML中设置drawableStart、drawableLeft和drawablePadding属性
    setPwdDrawable 该属性使用前必须设置密文输入不然无效果
