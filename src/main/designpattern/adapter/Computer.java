package adapter;

/**
 * @entity computer
 */
public class Computer {
    public void surfing(NetToUsb adapter){
        System.out.println("×¼±¸ÉÏÍø");
        adapter.handleRequest();
    }

    public static void main(String[] args) {
        Computer computer = new Computer();
//        Netwire netwire = new Netwire();
        Adapter adapter = new Adapter();
        Adapter2 adapter2 = new Adapter2(new Netwire());
        computer.surfing(adapter);
        computer.surfing(adapter2);
    }
}
