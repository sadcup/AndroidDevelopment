package sadcup.android.jnaonas;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.sun.jna.Native;
import com.sun.jna.Pointer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        // example 1
//        String strDisplay = "5 + 6 = ";
//        strDisplay += Integer.toString(JNACalculateFunction(5, 6));
//        ((TextView)findViewById(R.id.text)).setText(strDisplay);


        // example 2
        String strDisplay = JNASubstr("1234567890", 3, 5);
        ((TextView)findViewById(R.id.text)).setText(strDisplay);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    public static int JNACalculateFunction(int a, int b) {
        JNIDemoMapping jnaMapping = (JNIDemoMapping) Native.loadLibrary("JNADemo", JNIDemoMapping.class);
        Pointer demo = jnaMapping.initialize(a);
        int c = jnaMapping.add(demo, b);
        jnaMapping.finalize(demo);
        return c;
    }

    public static String JNASubstr(String oriString, int begin, int end) {
        JNIStringDemoMapping mapping = (JNIStringDemoMapping)Native.loadLibrary("JNADemo", JNIStringDemoMapping.class);
        Pointer demo = mapping.initializeString(oriString);
        String ret = mapping.substrString(demo, begin, end);
        mapping.finalizeString(demo);
        return ret;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
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
    }
}
