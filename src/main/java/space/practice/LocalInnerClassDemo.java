package space.practice;

/**
 * @comment test demo
 * @author space
 * �ֲ��ڲ�����ص㣺
 * 1. ����ʹ��Ȩ�����η��ģ���ô ���ԱҲ������static����Ϊ���ڲ����ж����˾�̬��Ա�����ڲ��������static�ģ�
 * 2. ����ֱ�ӷ����ⲿ��ĳ�Ա����Ϊ�������ⲿ���е�����
 * 3. �����Է��������ڵľֲ��еı�����ֻ�ܷ��ʱ�final���εľֲ�����
 */
public class LocalInnerClassDemo {
    public static void main(String[] args) {
        new Outer().method();
    }
}

class Outer{
    int x = 3;
    public void method(){
        /*
         * local inner class �ֲ��ڲ���
         */
        int a = 4;
        class Inner{
            void fun(){
                System.out.println(Outer.this.x);
                System.out.println(a);
            }
        }
        new Inner().fun();
    }
}
