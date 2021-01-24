package space.temp;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
public class TempTest {
    @Test
    public void test(HttpServletRequest request) throws JsonProcessingException, InterruptedException {
        System.out.println("test.........");
        request.getCookies();
    }
}
