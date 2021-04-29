package com.example.airq.ui.tina;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.airq.R;

public class TinaFragment extends Fragment {
    private TinaViewModel tinaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        tinaViewModel =
                new ViewModelProvider(this).get(TinaViewModel.class);
        View root = inflater.inflate(R.layout.tina_fragment, container, false);
        final TextView tVRandom = root.findViewById(R.id.text_random);
        final TextView tVKnn = root.findViewById(R.id.text_knn);
        final TextView tVSvm = root.findViewById(R.id.text_svm);

        WebView webview1;

        webview1= (WebView) root.findViewById(R.id.iframe_tina);
        webview1.getSettings().setAppCacheMaxSize(5*1024*1024);
        webview1.getSettings().setPluginState(WebSettings.PluginState.ON);
        webview1.getSettings().setJavaScriptEnabled(true);
        webview1.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview1.getSettings().setAppCachePath(getActivity().getApplicationContext().getCacheDir().getAbsolutePath());
        webview1.getSettings().setAllowFileAccess(true);
        webview1.getSettings().setAppCacheEnabled(true);
        webview1.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webview1.getSettings().setLoadWithOverviewMode(true);
        webview1.getSettings().setUseWideViewPort(true);
        webview1.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webview1.getSettings().setDomStorageEnabled(true);
        webview1.getSettings().setSaveFormData(true);
        webview1.getSettings().setBuiltInZoomControls(true);
        webview1.getSettings().setDisplayZoomControls(false);
        webview1.getSettings().setLoadWithOverviewMode(true);
        webview1.getSettings().setUseWideViewPort(true);
        webview1.getSettings().setLoadWithOverviewMode(true);
        webview1.getSettings().setUseWideViewPort(true);
        final WebSettings webSettings = webview1.getSettings();
        final String newUserAgent;
        newUserAgent = "Mozilla/5.0 (Android) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";
        webSettings.setUserAgentString(newUserAgent);

        int nightModeFlags =
                getContext().getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                tinaViewModel.getUrl().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        webview1.loadUrl(s);
                        webview1.setWebViewClient(new WebViewClient());
                    }
                });
                break;

            case Configuration.UI_MODE_NIGHT_NO:
                tinaViewModel.getLight().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        webview1.loadUrl(s);
                        webview1.setWebViewClient(new WebViewClient());
                    }
                });
                break;


        }
        tinaViewModel.getKnn().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tVKnn.setText("Knn : " + s);
                System.out.println(s);
            }
        });
        tinaViewModel.getSvm().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tVSvm.setText("Svm : " + s);
                System.out.println(s);
            }
        });
        tinaViewModel.getPre().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tVRandom.setText("Random Forest: " + s);
                System.out.println(s);
            }
        });
        return root;
    }
}
