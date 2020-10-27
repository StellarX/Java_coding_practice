package adapter;

/**
 * @briefing 使用组合方式，这称为对象适配器 （常用）
 */
public class Adapter2 implements NetToUsb{
    private Netwire netwire;

    public Adapter2(Netwire netwire){
        this.netwire = netwire;
    }

    @Override
    public void handleRequest() {
        System.out.println("适配器将网线转化为usb");
        netwire.connectInternet(); // 电脑是不能直接调用这个的，需要适配器来转化
    }
}
