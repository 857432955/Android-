package utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ZhangRuxing
 */

public class StreamTools {
     /**
     * @param is
     * @return
     * @throws IOException
     * 用于读取网络数据时，需要添加网络访问权限
     */
    public static String readStream(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = is.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        is.close();
        return baos.toString();
    }
}
