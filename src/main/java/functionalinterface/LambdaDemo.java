package functionalinterface;

/**
 * Lambda 表达式免去了使用匿名方法的麻烦，并且给予Java简单但是强大的函数化的编程能力
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

        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;

        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;

        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> { return a * b; };

        // 没有大括号及返回语句
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

        // 不用括号
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);

        // 用括号
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);

        greetService1.sayMessage("Run");
        greetService2.sayMessage("Google");

        GreetingService greetService3 = message ->
                System.out.println(salutation + message);
        greetService3.sayMessage("You");
    }
}
