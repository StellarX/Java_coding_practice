package clone;

/**
 * 浅拷贝 示例   todo 深拷贝示例 https://www.cnblogs.com/ysocean/p/8482979.html
 */
public class CloneDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person p1 = new Person("zhangsan",21);
        p1.setAddress("hubeisheng", "wuhan");
        Person p2 = (Person) p1.clone();
        System.out.println("p1:"+p1);
        System.out.println("p1.getPname:"+p1.getPname().hashCode());

        System.out.println("p2:"+p2);
        System.out.println("p2.getPname:"+p2.getPname().hashCode());

        p1.display("p1");
        p2.display("p2");
        p2.setAddress("hubeisheng", "jingzhou");
        p2.setPage(22);
        p2.setPname("jack");
        System.out.println("after modify");
        p1.display("p1");
        p2.display("p2");
    }
}
