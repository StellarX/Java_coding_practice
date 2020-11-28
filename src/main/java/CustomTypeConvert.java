import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义类型转换器  待解决
 */
public class CustomTypeConvert implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        DateFormat dateFormat;
        try {
            if (StringUtils.isEmpty(s))
               throw new NullPointerException("please input a string");
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date da = dateFormat.parse(s);
            return da;
        }catch (Exception e){
            throw new RuntimeException("error...");
        }
    }
}
