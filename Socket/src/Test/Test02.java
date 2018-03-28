package Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test02 {
    public static void main(String[] args) {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            System.out.println(inetAddress.getHostName());
            System.out.println(inetAddress.getHostAddress());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
