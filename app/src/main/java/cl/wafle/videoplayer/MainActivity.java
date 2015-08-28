package cl.wafle.videoplayer;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import io.vov.vitamio.LibsChecker;


public class MainActivity extends AppCompatActivity implements  MediaPlayer.OnPreparedListener , MediaPlayer.OnCompletionListener,
                                                                WafleMediaController.MediaPlayerControl,
                                                                View.OnTouchListener
{
    private final String TAG = MainActivity.class.getSimpleName();

    private VideoView vvContainer;
    //private MediaController mcPlayer;
    //private ConstantAnchorMediaController mcPlayer;
    private WafleMediaController mcPlayer;
    private RelativeLayout rlContainer;
    private FrameLayout rlMediaControllerContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!io.vov.vitamio.LibsChecker.checkVitamioLibs(this))
            return;
        Log.v(TAG, "onCreate");


        setContentView(R.layout.activity_main);

        rlContainer = (RelativeLayout) findViewById(R.id.rlContainer);
        vvContainer = (VideoView) findViewById(R.id.vvContainer);
        rlMediaControllerContainer = (FrameLayout) findViewById(R.id.rlMediaControllerContainer);

        vvContainer.setOnTouchListener(this);

        //mcPlayer = new MediaController(this, false);
        //mcPlayer = new ConstantAnchorMediaController(this, rlMediaControllerContainer);
        mcPlayer = new WafleMediaController(this);
        //mcPlayer.setMediaPlayer(vvContainer);
        //mcPlayer.setAnchorView(vvContainer);

        /*
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.BOTTOM;
        mcPlayer.setLayoutParams(lp);

        ((ViewGroup) mcPlayer.getParent()).removeView(mcPlayer);
        ((FrameLayout) findViewById(R.id.rlMediaControllerContainer)).addView(mcPlayer);
        */

        vvContainer.setOnPreparedListener(this);
        vvContainer.setOnCompletionListener(this);
        vvContainer.setVideoURI(Uri.parse(Constants.URL));

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

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.v(TAG, "onPrepared");
        mcPlayer.setMediaPlayer(this);
        mp.start();
        mcPlayer.setAnchorView(rlMediaControllerContainer);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.v(TAG, "onCompletion");
    }

    @Override
    public void start() {
        vvContainer.start();
    }

    @Override
    public void pause() {
        vvContainer.pause();
    }

    @Override
    public int getDuration() {
        return vvContainer.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return vvContainer.getCurrentPosition();
    }

    @Override
    public void seekTo(int pos) {
        vvContainer.seekTo(pos);
    }

    @Override
    public boolean isPlaying() {
        return vvContainer.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public boolean isFullScreen() {
        return true;
    }

    @Override
    public void toggleFullScreen() {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mcPlayer.show();
        return false;
    }
}
