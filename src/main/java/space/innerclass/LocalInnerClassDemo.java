package space.innerclass;

/**
 * @comment test demo
 * @author space
 * �ֲ��ڲ�����ص㣺
 * 1. ����ʹ��Ȩ�����η��ģ���ô ���ԱҲ������static����Ϊ���ڲ����ж����˾�̬��Ա�����ڲ��������static�ģ�
 * 2. ����ֱ�ӷ����ⲿ��ĳ�Ա����Ϊ�������ⲿ���е�����
 * 3. �����Է��������ڵľֲ��еı�����ֻ�ܷ��ʱ�final���εľֲ����� todo
 */
public class LocalInnerClassDemo {
    public static void main(String[] args) {
        new Outer().method();
        new Outer.Inner2();
    }
}

class Outer{
    int x = 3;
    public void method(){

        int a = 4;
        class Inner{
            //local inner class
            void fun(){
                System.out.println(Outer.this.x);
                System.out.println(a);
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
