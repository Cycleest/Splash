package cycleer.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;


public class SplashActivity extends Activity {

    private final long delay = 2000;
    private boolean isInitialized = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView splashImage;
        splashImage = (ImageView) findViewById(R.id.splashView);
        splashImage.setImageResource(R.drawable.gears);

        if (savedInstanceState != null && savedInstanceState.containsKey("iniFlag")) {
            isInitialized = savedInstanceState.getBoolean("iniFlag");
        }

        if (!isInitialized) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    SplashActivity.this.finish();
                }
            }, delay);
            isInitialized = true;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("iniFlag", isInitialized);
    }

}
