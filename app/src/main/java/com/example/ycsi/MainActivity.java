package com.example.ycsi;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WebView mWebView; // 웹뷰 선언
    private WebSettings mWebSettings; //웹뷰세팅

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkSelfPermission();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //권한을 허용 했을 경우
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            int length = permissions.length;
            for (int i = 0; i < length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    // 동의
                    Log.d("MainActivity", "권한 허용 : " + permissions[i]);
                    mWebView = (WebView) findViewById(R.id.webView);

                    mWebView.setWebChromeClient(new WebChromeClient() {
                        @Override
                        public void onPermissionRequest(final PermissionRequest request) {
                            request.grant(request.getResources());
                        }
                    });
                    // 웹뷰 시작
                    mWebView.setWebViewClient(new WebViewClient()); // 클릭시 새창 안뜨게
                    mWebSettings = mWebView.getSettings(); //세부 세팅 등록
                    mWebSettings.setJavaScriptEnabled(true); // 웹페이지 자바스클비트 허용 여부
                    mWebSettings.setMediaPlaybackRequiresUserGesture(false);
                    mWebSettings.setSupportMultipleWindows(false); // 새창 띄우기 허용 여부
                    mWebSettings.setJavaScriptCanOpenWindowsAutomatically(false); // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
                    mWebSettings.setLoadWithOverviewMode(true); // 메타태그 허용 여부
                    mWebSettings.setUseWideViewPort(true); // 화면 사이즈 맞추기 허용 여부
                    mWebSettings.setSupportZoom(false); // 화면 줌 허용 여부
                    mWebSettings.setBuiltInZoomControls(false); // 화면 확대 축소 허용 여부
                    mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 컨텐츠 사이즈 맞추기
                    mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 브라우저 캐시 허용 여부
                    mWebSettings.setDomStorageEnabled(true); // 로컬저장소 허용 여부
                    /*mWebView.setWebChromeClient(new WebChromeClient());*/


                    mWebView.loadUrl("https://anhye0n.me/demo.html"); // 웹뷰에 표시할 웹사이트 주소, 웹뷰 시작
                }
            }
        }
    }

    public void checkSelfPermission() {
        String temp = "";
        //파일 읽기 권한 확인
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            temp += Manifest.permission.CAMERA + " ";
        } //파일 쓰기 권한 확인
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            temp += Manifest.permission.RECORD_AUDIO + " ";
        }
        if (TextUtils.isEmpty(temp) == false) {
            // 권한 요청
            ActivityCompat.requestPermissions(this, temp.trim().split(" "), 1);
        } else {
            // 모두 허용 상태
            Toast.makeText(this, "권한을 모두 허용", Toast.LENGTH_SHORT).show();
            mWebView = (WebView) findViewById(R.id.webView);

            mWebView.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onPermissionRequest(final PermissionRequest request) {
                    request.grant(request.getResources());
                }
            });
            // 웹뷰 시작
            mWebView.setWebViewClient(new WebViewClient()); // 클릭시 새창 안뜨게
            mWebSettings = mWebView.getSettings(); //세부 세팅 등록
            mWebSettings.setJavaScriptEnabled(true); // 웹페이지 자바스클비트 허용 여부
            mWebSettings.setMediaPlaybackRequiresUserGesture(false);
            mWebSettings.setSupportMultipleWindows(false); // 새창 띄우기 허용 여부
            mWebSettings.setJavaScriptCanOpenWindowsAutomatically(false); // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
            mWebSettings.setLoadWithOverviewMode(true); // 메타태그 허용 여부
            mWebSettings.setUseWideViewPort(true); // 화면 사이즈 맞추기 허용 여부
            mWebSettings.setSupportZoom(false); // 화면 줌 허용 여부
            mWebSettings.setBuiltInZoomControls(false); // 화면 확대 축소 허용 여부
            mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 컨텐츠 사이즈 맞추기
            mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT); // 브라우저 캐시 허용 여부
            mWebSettings.setDomStorageEnabled(true); // 로컬저장소 허용 여부
            /*mWebView.setWebChromeClient(new WebChromeClient());*/


            mWebView.loadUrl("https://anhye0n.me/demo.html"); // 웹뷰에 표시할 웹사이트 주소, 웹뷰 시작
        }
    }

}