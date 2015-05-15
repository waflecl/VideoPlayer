package cl.wafle.videoplayer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import io.vov.vitamio.LibsChecker;


public class MainActivity extends ActionBarActivity {
    private final String TAG = MainActivity.class.getSimpleName();

    private VideoView vvContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(LibsChecker.checkVitamioLibs(this))
            return;
        setContentView(R.layout.activity_main);

        vvContainer = (VideoView) findViewById(R.id.vvContainer);

        MediaController mc = new MediaController(this);
        vvContainer.setMediaController(mc);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                vvContainer.setVideoURI(Uri.parse(Constants.URL_M3U8));
                vvContainer.start();
            }
        });

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
        switch (id){
            case R.id.action_settings:
                return true;
            case R.id.action_switch_vitamio:
                startActivity(new Intent(this, VitamioActivity.class));
                return true;
        }
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
