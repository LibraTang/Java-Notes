## Java引用别名机制

其实就是**多态**，多个引用变量可以指向同一个物理对象，这些引用变量可以是不同的类型。

```java
public class TestPolyMorphism {
    public static class P{
        public void print(String message){
            System.out.println("P-->"+message);
        }
    }
    public static class S extends P{
        public void print(String message){
            System.out.println("S-->"+message);
        }
    }
    public static void main(String[] args) {
        S[] ss = new S[10];
        P[] pp = ss;
        ss[0] = new S();
        pp[0].print("你好");
        //运行时错误,不能将父类对象,赋给子类数组;
        pp[1] = new P();//java.lang.ArrayStoreException
    }
}
```

运行结果：

```
S-->你好
Exception in thread "main" java.lang.ArrayStoreException: Test$P
	at Test.main(Test.java:18)
```



在内存中，pp和ss都指向了同样的地址

![引用别名机制]()

Java在运行时才处理别名引用，在程序运行过程中，虚拟机发现数组pp的第一个元素是S类型的对象,而不是P类型的。

**在实际运行过程中，多态根据实际对象类型决定调用父类还是子类方法，而不是根据引用类型**