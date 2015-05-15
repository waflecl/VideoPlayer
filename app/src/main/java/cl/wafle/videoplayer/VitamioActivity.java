package cl.wafle.videoplayer;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


public class VitamioActivity extends ActionBarActivity {
    private final String TAG = VitamioActivity.class.getSimpleName();

    private VideoView vvContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitamio);

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
        getMenuInflater().inflate(R.menu.menu_vitamio, menu);
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
