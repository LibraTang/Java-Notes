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

在Java7之前，字符串常量池被放在运行时常量池中，它属于永久代。而在Java7，字符串常量池被移到堆中，因为永久代空间有限，在大量使用字符串的场景下会导致OutOfMemoryError错误。

### 字符串对象创建

```java
String str = new String("abc");
```

* 若常量池中有"abc"对象，则返回对应的引用实例（1个对象）
* 若常量池中没有"abc"对象，则创建对应的实例对象放入池中，并在堆中也创建对应的实例对象（2个对象）

### intern()
```java
public static void main(String[] args) {
    String s = new String("1");
    s.intern();
    String s2 = "1";
    System.out.println(s == s2);

    String s3 = new String("1") + new String("1");
    s3.intern();
    String s4 = "11";
    System.out.println(s3 == s4); 
}
```
* 在JDK6中，上述所有打印都是false，因为jdk6中的常量池是在Perm中的，Perm区和Heap区是完全分开的。直接由引号生成的字符串是在常量池中的，而new出来的字符串是在Heap区中的，它们的地址完全不同，即使用`s.intern()`也没有任何关系
* 在JDK7中，打印结果为`false true`，在第一段代码中，先看 s3和s4字符串。`String s3 = new String("1") + new String("1");`，这句代码中现在生成了两个最终对象，是字符串常量池中的“1” 和 Heap 中的 s3引用指向的对象。中间还有2个匿名的`new String("1")`我们不去讨论它们。此时s3引用对象内容是"11"，但此时常量池中是没有 “11”对象的。
* 接下来`s3.intern();`这一句代码，是将 s3中的“11”字符串放入 String 常量池中，因为此时常量池中不存在“11”字符串，因此常规做法是跟 JDK6 中表示的那样，在常量池中生成一个 "11" 的对象，关键点是 jdk7 中常量池不在 Perm 区域了，这块做了调整。**常量池中不需要再存储一份对象了，可以直接存储堆中的引用**。这份引用指向 s3 引用的对象。 也就是说引用地址是相同的。
* 最后`String s4 = "11";` 这句代码中"11"是显式声明的，因此会直接去常量池中创建，创建的时候发现已经有这个对象了，此时也就是指向 s3 引用对象的一个引用。所以 s4 引用就指向和 s3 一样了。因此最后的比较 `s3 == s4` 是 true。
* 再看 s 和 s2 对象。 `String s = new String("1");` 第一句代码，生成了2个对象。常量池中的“1” 和 JAVA Heap 中的字符串对象。`s.intern();` 这一句是 s 对象去常量池中寻找后发现 “1” 已经在常量池里了。
* 接下来`String s2 = "1";` 这句代码是生成一个 s2的引用指向常量池中的“1”对象。 结果就是 s 和 s2 的引用地址明显不同。

```java
public static void main(String[] args) {
    String s = new String("1");
    String s2 = "1";
    s.intern();
    System.out.println(s == s2);

    String s3 = new String("1") + new String("1");
    String s4 = "11";
    s3.intern();
    System.out.println(s3 == s4);
}
```

- 来看第二段代码。第一段代码和第二段代码的改变就是 `s3.intern();` 的顺序是放在`String s4 = "11";`后了。这样，首先执行`String s4 = "11";`声明 s4 的时候常量池中是不存在“11”对象的，执行完毕后，“11“对象是 s4 声明产生的新对象。然后再执行`s3.intern();`时，常量池中“11”对象已经存在了，因此 s3 和 s4 的引用是不同的。
- 第二段代码中的 s 和 s2 代码中，`s.intern();`，这一句往后放也不会有什么影响了，因为对象池中在执行第一句代码`String s = new String("1");`的时候已经生成“1”对象了。下边的s2声明都是直接从常量池中取地址引用的。 s 和 s2 的引用地址是不会相等的。

