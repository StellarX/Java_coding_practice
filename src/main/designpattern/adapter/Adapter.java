package adapter;

/**
 * @briefing 使用继承方式，称为类适配器
 */
public class Adapter extends Netwire implements NetToUsb {

    @Override
    public void handleRequest() {
        System.out.println("适配器将网线转化为usb");
        super.connectInternet();
    }
}
