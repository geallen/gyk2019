package gykizmir.com.mediaoperations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {

    private WebView webSitesi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webSitesi = (WebView) findViewById(R.id.webSitesi);
        webSitesi.getSettings().setJavaScriptEnabled(true);
        webSitesi.loadUrl("https://gelecegiyazanlar.turkcell.com.tr/");


    }
}
