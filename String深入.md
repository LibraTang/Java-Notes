# String

## 关于String不可改变的特性

1. 声明String对象

```java
String s = "abc";
```

此时会在堆内存中生成一个新的String对象，栈内存中的引用s指向它

2. 将一个字符串变量赋值给另一个String

```java
String s2 = s;
```

此时栈内存中的引用s2与s指向同一个String对象"abc"

3. 字符串连接

```java
s = s.concat("def"); 
```

此时在堆内存中新生成了String对象"abcdef"，栈内存中的s指向它（原来堆内存中的对象"abc"将被回收）

## 字符串常量池

**关于literal（字面量）**<br>

就是字符串、数字等值本身。当编码的时候写下一个值比如`10`，`"abc"`，这就是一个literal。

### 为什么需要字符串常量池

JVM为了提高性能和减少内存开销，为字符串开辟一个常量池，类似于缓存区。创建字符串常量时，首先检查字符串常量池中是否存在该字符串，若存在则返回引用实例，不存在则创建实例并放入池中。

### 字符串常量池实现基础

* 字符串不可变所以不用担心数据冲突
* 字符串常量池中有一个表，为池中的每个唯一的字符串对象维护一个引用，因此常量池中的对象不会被垃圾回收器回收

### 字符串常量池位置

位于方法区中

![运行时数据区](https://github.com/LibraTang/Pics/blob/master/Java-Notes/%E8%BF%90%E8%A1%8C%E6%97%B6%E6%95%B0%E6%8D%AE%E5%8C%BA.png)

方法区被所有线程共享，其中包含的都是在整个程序中永远唯一的元素，比如class，static变量

### 字符串对象创建

```java
String str = new String("abc");
```

* 若常量池中有"abc"对象，则返回对应的引用实例（1个对象）
* 若常量池中没有"abc"对象，则创建对应的实例对象放入池中，并在堆中也创建对应的实例对象（2个对象）

### intern()

通过new创建字符串对象不指向字符串池中任何一个对象，但是可以通过调用字符串的intern()方法来指向其中一个。

```java
String s1 = new String("abc");
String s2 = "abc";
System.out.println(s1 == s2); //false
System.out.println(s1.intern() == s2); //true
```

s1原本指向堆内存中的对象，调用intern()方法后指向池中的对象。