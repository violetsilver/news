package leui.cases.lev.com.mtbf20;

import android.os.Bundle;
import android.os.Process;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import leui.cases.lev.com.mtbf20.StreamGobbler;

public class MainActivity extends AppCompatActivity {
/*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //得到按钮实例
        Button btn_ls = (Button) findViewById(R.id.runtest);
        //设置监听按钮点击事件
        btn_ls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到textview实例
                TextView text = (TextView) findViewById(R.id.hellotextView);
                //弹出Toast提示按钮被点击了
                //Toast.makeText(MainActivity.this,"Clicked", Toast.LENGTH_SHORT).show();
                //读取strings.xml定义的interact_message信息并写到textview上
                //text.setText(R.string.interact_message);
                // do_exec("ls /data/local/tmp",text);
                // do_exec("/system/bin/sh /mnt/sdcard/test.sh 123",text);
                //exec1("/system/bin/sh /mnt/sdcard/moneyM.sh");
                exec1("/system/bin/sh /data/local/tmp/moneyM.sh");
            }
        });
/*        Button btn_cat = (Button) findViewById(R.id.btn_cat);
        btn_cat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                do_exec("cat /proc/version");
            }
        });

        Button btn_rm = (Button) findViewById(R.id.btn_rm);
        btn_rm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                do_exec("rm /mnt/sdcard/1.jpg");
            }
            });
        Button btn_sh = (Button) findViewById(R.id.btn_sh);
        btn_sh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                do_exec("/system/bin/sh /mnt/sdcard/test.sh 123");
            }
        });*/
    }


    //这个执行简单cmd命令不会发生堵塞
    String do_exec(String cmd, TextView text) {
        String s = "";
        try {
            java.lang.Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                s += line + " ";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        text.setText(s);
        return cmd;
    }

    String exec(String cmd, TextView text) {
        String s = "";
        try {
            java.lang.Process process = Runtime.getRuntime().exec(cmd);
            printMessage(process.getInputStream());
            printMessage(process.getErrorStream());
            int value = process.waitFor();
            System.out.println(value);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //text.setText(s);
        return cmd;
    }

    public void exec1(String cmd) {
        try {
            java.lang.Process p = Runtime.getRuntime().exec(cmd);

            StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(), "ERROR");

            // kick off stderr
            errorGobbler.start();

            StreamGobbler outGobbler = new StreamGobbler(p.getInputStream(), "STDOUT");
            // kick off stdout
            outGobbler.start();

            p.waitFor();
        }catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
}

    private static void printMessage(final InputStream input) {new Thread(new Runnable() {
         public void run() {
         BufferedReader bf = new BufferedReader(new InputStreamReader(input));
         String line = null;
         try {
          while((line=bf.readLine())!=null) {
          System.out.println(line);}
         } catch (IOException e) {
         e.printStackTrace();
         }
         }}).start();
    }
/*        public void onClick(View v) throws IOException {
        String command="sh /data/local/tmp/money.sh";

        java.lang.Process p = Runtime.getRuntime().exec("su -s sh  -c /data/local/tmp/test.sh");
        String data = null;
        BufferedReader ie = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String error = null;
        while ((error = ie.readLine()) != null
                && !error.equals("null")) {
            data += error + "\n";
        }
        String line = null;
        while ((line = in.readLine()) != null
                && !line.equals("null")) {
            data += line + "\n";
        }
        Log.i("ls======", "onClick: "+data);
    }*/
/*
    public static void Memtester(String command){
        Runtime r=Runtime.getRuntime();
        Process p;
        try {
            p=r.exec(command);
            BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
