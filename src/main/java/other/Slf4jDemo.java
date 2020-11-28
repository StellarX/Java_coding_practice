package other;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Slf4jDemo {

    public static void main(String[] args) {
        log.info("start...");
        System.out.println("I am working on it......");
//        System.out.println(4/0);
        log.error("end...");
    }

}
