package enumeration;

import lombok.Getter;


/**
 * Ã¶¾ÙÊ¾Àý
 */
public enum EnumDemo {
    one(1, "111"), tow(2, "222"), three(3, "333");

    @Getter private Integer retCode;
    @Getter private String retMessage;

    EnumDemo(Integer retcode, String retMessage){
        this.retCode = retcode;
        this.retMessage = retMessage;
    }

    public static EnumDemo foreach_EnumDemo(int index){
        EnumDemo[] arr = EnumDemo.values();
        for(EnumDemo e : arr){
            if(index == e.getRetCode()){
                return e;
            }
        }
        return null;
    }
}
