package gykizmir.com.webviewex;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("https://gelecegiyazanlar.turkcell.com.tr/");
        webView.getSettings().setJavaScriptEnabled(true);

        final ProgressDialog progressDialog = ProgressDialog.show(this, "Gelecegi Yazanlar" ,
                "Sayfa Yukleniyor");

        progressDialog.show();

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Yuklendi", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description,
                                        String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Hata", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
