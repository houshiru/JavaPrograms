package Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
//Download the picture from the website
public class Test01 {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://cas.fit.edu/cas/images/webapp.png");
            URLConnection connection = url.openConnection();
            InputStream is = connection.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(new File("image/img.png"));
            byte[] b = new byte[1024];
            int i = 0;
            while ((i = is.read(b)) != -1){
                fileOutputStream.write(b, 0, i);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            is.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
