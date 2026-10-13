package dev.pages.cuentasvane.webview;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.*;
import android.graphics.Color;

public class MainActivity extends Activity {
 private WebView webView;
 private void immersive() {
  getWindow().getDecorView().setSystemUiVisibility(
   View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN |
   View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
   View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
 }
 @Override public void onCreate(Bundle state) {
  super.onCreate(state); getWindow().setStatusBarColor(Color.BLACK); getWindow().setNavigationBarColor(Color.BLACK); immersive();
  webView=new WebView(this); WebSettings s=webView.getSettings();
  s.setJavaScriptEnabled(true); s.setDomStorageEnabled(true); s.setDatabaseEnabled(true);
  s.setAllowFileAccess(false); s.setAllowContentAccess(true); s.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
  webView.setWebChromeClient(new WebChromeClient());
  webView.setWebViewClient(new WebViewClient(){
   @Override public boolean shouldOverrideUrlLoading(WebView v, WebResourceRequest r) {
    return !("https".equals(r.getUrl().getScheme()));
   }
  });
  setContentView(webView);
  if(state==null) webView.loadUrl("https://cuentasvane.pages.dev/"); else webView.restoreState(state);
 }
 @Override public void onWindowFocusChanged(boolean focus) { super.onWindowFocusChanged(focus); if(focus) immersive(); }
 @Override public void onBackPressed() { if(webView!=null && webView.canGoBack()) webView.goBack(); else super.onBackPressed(); }
 @Override protected void onSaveInstanceState(Bundle out) { if(webView!=null) webView.saveState(out); super.onSaveInstanceState(out); }
 @Override protected void onDestroy() { if(webView!=null) webView.destroy(); super.onDestroy(); }
}
