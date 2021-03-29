package space.innerclass;

/**
 * @comment test demo
 * @author space
 * 局部内部类的特点：
 * 1. 不能使用权限修饰符的，那么 其成员也不能用static（因为当内部类中定义了静态成员，该内部类必须是static的）
 * 2. 可以直接访问外部类的成员，因为还持有外部类中的引用
 * 3. 不可以访问它所在的局部中的变量，只能访问被final修饰的局部变量 todo
 */
public class LocalInnerClassDemo {
    public static void main(String[] args) {
        new Outer().method();
//        new Outer.Inner2();
    }
}

class Outer{
    int x = 3;
    public void method(){

        new A(){//匿名局部内部类
            void show(){
                System.out.println(x);
            }
        }.show();

        A a = new A(){//多态，向上转型
            void show(){
                System.out.println(x);
            }
            void fun(){
                System.out.println(4);
            }
        };
        a.show(); //注意这里就不能调用fun了，因为fun是子类特有的

        int d = 4;
        class Inner{
            //local inner class
            final int b = 5;
            void fun(){
                System.out.println(x);
                System.out.println(d);
                System.out.println(b);
            }
        }
        new Inner().fun();
    }
    //inner class
    static class Inner2{
        private void fun(){
            System.out.println("inner2 class");
        }
        public void fun2(){
            System.out.println("inner2 class public method");
        }
    }
}

abstract class A{
    abstract void show();
}