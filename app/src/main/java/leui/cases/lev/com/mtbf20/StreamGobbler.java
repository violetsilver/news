package leui.cases.lev.com.mtbf20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.FileUtils;
/**
 * Created by zhoujine on 2018/10/16.
 */

public class StreamGobbler extends Thread{
    InputStream is;
    String type;
    OutputStream os;
    StreamGobbler(InputStream is, String type) {
        this(is, type, null);
    }

    StreamGobbler(InputStream is, String type, OutputStream redirect) {
        this.is = is;
        this.type = type;
        this.os = redirect;
    }

    public void run() {
        InputStreamReader isr = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            if (os != null)
                pw = new PrintWriter(os);

            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String line=null;
            while ( (line = br.readLine()) != null) {
                if (pw != null)
                    pw.println(line);
                System.out.println(type + ">" + line);
            }

            if (pw != null)
                pw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally{
            pw.close();
            try {
                br.close();
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
           // FileUtil.close(pw);
            //FileUtil.close(br);
            //FileUtil.close(isr);
        }
    }

}
