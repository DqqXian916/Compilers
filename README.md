# README

[TOC]

## 介绍

编译原理课程设计大作业，其中包含`实型常数识别`、`递归下降子程序的实现`和`简单的LR(0)分析`。C语言实型常数识别用java实现，递归下降子程序和LR(0)分析用c++实现。

## 实验内容

###  C语言实型常数识别

输入：要判断的字符串  
输出：判断结果(Yes/No)  
**样例输入**

```
12E+6
```

**样例输出**

```
Yes
```

###  递归下降子程序实现

程序输入：以#结束的输入串  
程序输出：输入串递归下降法分析匹配过程  
**样例输入**

```
i+i*i# 
```

**样例输出**

```
E->TE
T->FT'
E->i
T'->ε
E->+TE'
T->FT'
E->i
T'->FE'
E->i
T'->ε
E->ε
i+i*i# is a match!
```

###  LR0分析程序

程序输入：以#结束的上下文无关文法  
程序输出：LR(0)各项集和构造的LR(0)分析表   
**样例输入**

```
E->aA
E->bB
A->cA
A->d
B->cB
B->d
#
```

**样例输出**

```
各项的序号和文法内容：
0:
Z->.E
E->.aA
E->.bB

1:
Z->E.

2:
E->a.A
A->.cA
A->.d

3:
E->b.B
B->.cB
B->.d

4:
E->aA.

5:
A->c.A
A->.cA
A->.d

6:
A->d.

7:
E->bB.

8:
B->c.B
B->.cB
B->.d

9:
B->d.

10:
A->cA.

11:
B->cB.

LR0分析表：
              Action              Goto
       a    b    c    d    #    E    A    B
 0:   S2   S3                   1
 1:                       acc
 2:             S5   S6              4
 3:             S8   S9                   7
 4:   r0   r0   r0   r0   r0
 5:             S5   S6             10
 6:   r1   r1   r1   r1   r1
 7:   r2   r2   r2   r2   r2
 8:             S8   S9                  11
 9:   r3   r3   r3   r3   r3
10:   r4   r4   r4   r4   r4
11:   r5   r5   r5   r5   r5
```

## 参考资料

>1.[无符号实数的识别](https://blog.csdn.net/could9/article/details/105308106?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase)     
>2.[递归下降子程序](http://www.doc88.com/p-6079867238812.html)   
>3.[LR(0)分析表的构建](https://blog.csdn.net/ScottWei_007/article/details/90244531?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-4.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-4.nonecase) 
