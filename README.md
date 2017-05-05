# Poker 斗牛 - 纸牌游戏

### 获胜判定

Judge | Example
--- | ---
炸弹 | [ K K K K ] + ( A )
五花牛 | [ 10 J Q K K ]
五小牛 | [ A A 2 2 4 ]
牛牛 | [ 2 3 5 ] + ( 4 6 )
牛九 | [ K Q J ] + ( 4 5 )
牛八 | [ 10 A 9 ] + ( 2 6 )
牛七 | [ A 4 5 ] + ( 3 4 )
牛六 | [ 3 8 9 ] + ( 3 3 )
牛五 | [ 10 J Q ] + ( 9 6 )
牛四 | [ 4 3 3 ] + ( 2 A )
牛三 | [ 9 9 2 ] + ( 5 8 )
牛二 | [ 7 4 9 ] + ( A A )
牛丁 | [ 8 8 4 ] + ( A Q )
没牛 | ( 3 5 7 A 9 )


## 参数要求 （通用：适用于下述所有语言版本）

输入仅识别数字和 *a* / *j* / *q* / *k* / *A* / *J* / *Q* / *K*，其余字符不作处理

*1* 看做 *A*, *0* 看做 *10*

超过5个参数以前5个做计算

参数不足需重新输入5个参数

## C 版本

#### 运行样例：

程序开始：
```
Welcome to the game:
what pokers ?
("0" for 10, joker not allowed)
```
输入5张牌，输出结果：
```
2 3 4 5 6
========= BULL =========
10 : [ 2 3 5 ] + ( 4 6 )
...
12345
========= BULL =========
5 : [ A 4 5 ] + ( 2 3 )
...
Jqa13
--------- LOSE ---------
```

#### DEMO

*C/Poker.exe* (gcc 4.9.2, Windows 10 x64)

## Java 版本

#### 用法

```
Poker pokers = new Poker();
pokers.gameStart();
```

#### DEMO

*Java/Demo.java* (JDK 1.8.0_121)

## Java_MVC 版本

#### 用法

```
View game = new View();
while (true) {
    game.menu();
    game.bonus();
}
```

#### DEMO

*Java_MVC/Demo.java* (JDK 1.8.0_121)

## JavaScript 版本

#### 用法

HTML head 需引用 *Poker.js* ：
```
<script type="text/javascript" src="Poker.js"></script>
```
HTML body 需设置以下两个标签和参数：
```
<input type="text" id="in">
<button onclick="run()">OK</button>
<p id="result"></p>
```
结果将会展示在：
```
<p id="result"></p>
```

#### DEMO

*JavaScript/Poker.htm* (Chrome 58.0.3029.96 (64-bit), Windows 10 x64)

## PHP_OOP 版本

#### 用法

php 中 include *Poker.php* ：
```
include 'Poker.php';
```
创建对象：
```
$game = new Poker();
```
传入参数并计算：
```
$game->gameStart($_SERVER['QUERY_STRING']);
$game->gameStart('12345');
```
返回 JSON
```
echo $games->toJSON();
```

#### JSON 返回值

errCode | errText | judgeText
--- | --- | ---
400 | "unknown error" | NULL
401 | "empty pokers" | NULL
402 | "not enough pokers" | NULL
0 | NULL | *judgeText_JSON*

#### judgeText JSON 返回值

type | value | toSum | toLeft
--- | --- | --- | ---
"BOMB" | which four-of-a-kind | NULL | NULL
"FIVE BULLS" | NULL | NULL | NULL | NULL
"FIVE SMALL BULLS" | NULL | NULL | NULL | NULL
"BULLS" | value of bull | 3 of pokers to sum | 2 of pokers then left

#### DEMO
*PHP_OOP/Demo.php* (CentOS 7.2 x64, Nginx 1.10.0, PHP 5.5.36)

## PHP_POP 版本

#### 用法

GET *http(s)://your.web.site/Poker.php?{参数}*

返回 JSON

#### JSON 返回值

errCode | errText | judgeText
--- | --- | ---
400 | "unknown error" | NULL
401 | "empty pokers" | NULL
402 | "not enough pokers" | NULL
0 | NULL | *judgeText_JSON*

#### judgeText JSON 返回值

type | value | toSum | toLeft
--- | --- | --- | ---
"BOMB" | which four-of-a-kind | NULL | NULL
"FIVE BULLS" | NULL | NULL | NULL | NULL
"FIVE SMALL BULLS" | NULL | NULL | NULL | NULL
"BULLS" | value of bull | 3 of pokers to sum | 2 of pokers then left

#### DEMO

*PHP_POP/Poker.htm*  (CentOS 7.2 x64, Nginx 1.10.0, PHP 5.5.36)

## Python 版本

#### 用法

终端运行：
```
python3 Poker.py
```

#### DEMO
*Python/Poker.py* (Python 3.6, Windows 10 x64)