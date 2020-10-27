package adapter;

/**
 * @briefing ʹ����Ϸ�ʽ�����Ϊ���������� �����ã�
 */
public class Adapter2 implements NetToUsb{
    private Netwire netwire;

    public Adapter2(Netwire netwire){
        this.netwire = netwire;
    }

    @Override
    public void handleRequest() {
        System.out.println("������������ת��Ϊusb");
        netwire.connectInternet(); // �����ǲ���ֱ�ӵ�������ģ���Ҫ��������ת��
    }
}
