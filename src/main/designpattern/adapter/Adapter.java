package adapter;

/**
 * @briefing ʹ�ü̳з�ʽ����Ϊ��������
 */
public class Adapter extends Netwire implements NetToUsb {

    @Override
    public void handleRequest() {
        System.out.println("������������ת��Ϊusb");
        super.connectInternet();
    }
}
