package com.example.formulaapp.Fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import com.example.formulaapp.R;

public class SpravochnikFragment extends Fragment {

    WebView webView;
    ProgressBar loadingBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_spravochnik, container, false);
        getActivity().setTitle(R.string.spravochnik_lowerСase);
        loadingBar = view.findViewById(R.id.progressBar_spravochnik);
        webView = view.findViewById(R.id.webview_spravochnik);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setWebViewClient(new MyWebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                loadingBar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });
        webView.loadUrl("https://avto.pro/catalog/acura/mdx/");
        return view;
    }
    private class MyWebViewClient extends WebViewClient {
        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl("https://avto.pro/catalog/acura/mdx/");
            return true;
        }
    }
}