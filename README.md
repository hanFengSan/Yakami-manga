# Yakami-manga
这个是我自己的一个课程设计，通过爬虫爬取了新新漫画上的漫画资源，从而实现了漫画搜索与阅读功能。

## 主要的功能页面截图
<img src="https://github.com/hanFengSan/Yakami-manga/blob/master/image/1.jpg" width="400"/>
<img src="https://github.com/hanFengSan/Yakami-manga/blob/master/image/2.jpg" width="400"/>
<img src="https://github.com/hanFengSan/Yakami-manga/blob/master/image/3.jpg" width="400"/>
<img src="https://github.com/hanFengSan/Yakami-manga/blob/master/image/4.jpg" width="400"/>


## 项目中印象比较深刻的地方
1. 新新漫画上，html里的漫画图片地址是假的，真的地址是根据js文件里的一些算法动态运算出来的。实现上，我在pc上尝试阅读了一下混淆后并主观上特地九拐十八弯实现的js图片地址运算算法，百思不得其解。后来我把关键的那部分代码复制了出来，在java上开js虚拟机进行运算hhh，直接绕过了破解获取地址。
2. java上的js库，jdk里有，而android上是没有的...第一次意识到了jdk和android上的Harmony的差别。最后，找了一通，把rhino库，也就是jdk里包含的js库给单独引入了android代码里，解决了这个问题。
3. 新新漫画上，有些页面的请求上需要添加个来源地址，否则请求会被拒绝，因此导致功能时灵时不灵的，困惑了我两个小时。


# License

    Copyright (C) 2016 hanFengSan

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

