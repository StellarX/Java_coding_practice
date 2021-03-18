package space.functionalinterface;

/**
 * Lambda ���ʽ��ȥ��ʹ�������������鷳�����Ҹ���Java�򵥵���ǿ��ĺ������ı������
 */
public class LambdaDemo {
    static String salutation = "Hello! ";

    @FunctionalInterface
    public interface MathOperation {
        int operation(int a, int b);
    }
    @FunctionalInterface
    private interface GreetingService {
        void sayMessage(String message);
    }
    @FunctionalInterface
    public interface Converter<T1, T2> {
        void convert(int i);
    }

    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }

    public static void main(String[] args) {
        LambdaDemo lambdaDemo = new LambdaDemo();

        // ��������
        MathOperation addition = (int a, int b) -> a + b;

        // ������������
        MathOperation subtraction = (a, b) -> a - b;

        // �������еķ������
        MathOperation multiplication = (int a, int b) -> { return a * b; };

        // û�д����ż��������
        MathOperation division = (int a, int b) -> a / b;

        MathOperation test = (a,b) -> 5;

        // write some implements of functional interface above


        // then test them below...
        System.out.println("10 + 5 = " + lambdaDemo.operate(10, 5, addition));
        System.out.println("10 - 5 = " + lambdaDemo.operate(11, 5, subtraction));
        System.out.println("10 x 5 = " + lambdaDemo.operate(10, 6, multiplication));
        System.out.println("10 / 5 = " + lambdaDemo.operate(10, 5, division));

        System.out.println(lambdaDemo.operate(1,1,test));
        System.out.println(test.operation(1, 1));

        // ��������
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);

        // ������
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);

        greetService1.sayMessage("Run");
        greetService2.sayMessage("Google");

        GreetingService greetService3 = message ->
                System.out.println(salutation + message);
        greetService3.sayMessage("You");
    }
}
