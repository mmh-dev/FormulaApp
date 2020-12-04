package com.example.formulaapp.Fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


import androidx.fragment.app.Fragment;

import com.example.formulaapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ArticleFragment extends Fragment {

    String header;
    FloatingActionButton floatingActionButton;
    WebView article;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            header = bundle.getString("title");
        }
        getActivity().setTitle(header);

        floatingActionButton = view.findViewById(R.id.floatingActionButton);
        article = view.findViewById(R.id.article);

        article.getSettings().setJavaScriptEnabled(true);
        article.getSettings().setLoadsImagesAutomatically(true);
        article.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        article.setWebViewClient(new MyWebViewClient());
        article.loadUrl(getLink(header));

        return view;
    }

    private class MyWebViewClient extends WebViewClient {
        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(getLink(header));
            return true;
        }
    }

    private String getLink(String header) {
        String link = "";
        switch (header){
            case "Воздушный фильтр":
                link = "https://amastercar.ru/articles/engine_car_7.shtml";
                break;
            case "Глушитель":
                link = "https://techautoport.ru/dvigatel/vypusknaya-sistema/glushitel.html";
            break;
            case "Лямбда-зонд":
                link = "https://ru.m.wikipedia.org/wiki/%D0%9B%D1%8F%D0%BC%D0%B1%D0%B4%D0%B0-%D0%B7%D0%BE%D0%BD%D0%B4";
                break;
            case "Коллектор":
                link = "https://ru.m.wikipedia.org/wiki/%D0%92%D1%8B%D0%BF%D1%83%D1%81%D0%BA%D0%BD%D0%BE%D0%B9_%D0%BA%D0%BE%D0%BB%D0%BB%D0%B5%D0%BA%D1%82%D0%BE%D1%80";
                break;
            case "Турбина":
                link = "https://www.drive.ru/technic/4efb330200f11713001e3303.html";
                break;
            case "ГРМ":
                link = "https://ru.m.wikipedia.org/wiki/%D0%93%D0%B0%D0%B7%D0%BE%D1%80%D0%B0%D1%81%D0%BF%D1%80%D0%B5%D0%B4%D0%B5%D0%BB%D0%B8%D1%82%D0%B5%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9_%D0%BC%D0%B5%D1%85%D0%B0%D0%BD%D0%B8%D0%B7%D0%BC";
                break;
            case "Ремень ГРМ":
                link = "https://www.tts.ru/blog/dvigatel/chto-takoe-remen-grm-zachem-nuzhen-i-chto-sluchaetsya-pri-obryve/";
                break;
            case "Клапаны":
                link = "https://ru.m.wikipedia.org/wiki/%D0%A2%D0%B0%D1%80%D0%B5%D0%BB%D1%8C%D1%87%D0%B0%D1%82%D1%8B%D0%B9_%D0%BA%D0%BB%D0%B0%D0%BF%D0%B0%D0%BD";
                break;
            case "Блок цилиндров":
                link = "https://ru.m.wikipedia.org/wiki/%D0%91%D0%BB%D0%BE%D0%BA_%D1%86%D0%B8%D0%BB%D0%B8%D0%BD%D0%B4%D1%80%D0%BE%D0%B2";
                break;
            case "Головка блока цилиндров":
                link = "https://ru.m.wikipedia.org/wiki/%D0%93%D0%BE%D0%BB%D0%BE%D0%B2%D0%BA%D0%B0_%D0%B1%D0%BB%D0%BE%D0%BA%D0%B0_%D1%86%D0%B8%D0%BB%D0%B8%D0%BD%D0%B4%D1%80%D0%BE%D0%B2";
                break;
            case "Поршни":
                link = "https://ru.m.wikipedia.org/wiki/%D0%9F%D0%BE%D1%80%D1%88%D0%B5%D0%BD%D1%8C";
                break;
            case "Цилиндры":
                link = "https://ru.m.wikipedia.org/wiki/%D0%A6%D0%B8%D0%BB%D0%B8%D0%BD%D0%B4%D1%80_(%D0%B4%D0%B2%D0%B8%D0%B3%D0%B0%D1%82%D0%B5%D0%BB%D1%8C)";
                break;
            case "Коленчатый вал":
                link = "https://ru.m.wikipedia.org/wiki/%D0%9A%D0%BE%D0%BB%D0%B5%D0%BD%D1%87%D0%B0%D1%82%D1%8B%D0%B9_%D0%B2%D0%B0%D0%BB";
                break;
            case "Распределительный вал":
                link = "https://ru.m.wikipedia.org/wiki/%D0%A0%D0%B0%D1%81%D0%BF%D1%80%D0%B5%D0%B4%D0%B5%D0%BB%D0%B8%D1%82%D0%B5%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9_%D0%B2%D0%B0%D0%BB";
                break;
            case "Масляный фильтр":
                link = "https://ru.m.wikipedia.org/wiki/%D0%9C%D0%B0%D1%81%D0%BB%D1%8F%D0%BD%D1%8B%D0%B9_%D1%84%D0%B8%D0%BB%D1%8C%D1%82%D1%80";
                break;
            case "Устройство МКПП":
                link = "https://ru.m.wikipedia.org/wiki/%D0%9C%D0%B5%D1%85%D0%B0%D0%BD%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%B0%D1%8F_%D0%BA%D0%BE%D1%80%D0%BE%D0%B1%D0%BA%D0%B0_%D0%BF%D0%B5%D1%80%D0%B5%D0%B4%D0%B0%D1%87#%D0%9E%D1%81%D0%BD%D0%BE%D0%B2%D1%8B_%D1%83%D1%81%D1%82%D1%80%D0%BE%D0%B9%D1%81%D1%82%D0%B2%D0%B0";
                break;
            case "Двухвальные и трехвальные МКПП":
                link = "https://ru.m.wikipedia.org/wiki/%D0%9C%D0%B5%D1%85%D0%B0%D0%BD%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%B0%D1%8F_%D0%BA%D0%BE%D1%80%D0%BE%D0%B1%D0%BA%D0%B0_%D0%BF%D0%B5%D1%80%D0%B5%D0%B4%D0%B0%D1%87#%D0%94%D0%B2%D1%83%D1%85%D0%B2%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B5_%D0%B8_%D1%82%D1%80%D1%91%D1%85%D0%B2%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B5_%D0%9C%D0%9A%D0%9F";
                break;
            case "Синхронизированные и несинхронизированные МКПП":
                link = "https://ru.m.wikipedia.org/wiki/%D0%9C%D0%B5%D1%85%D0%B0%D0%BD%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%B0%D1%8F_%D0%BA%D0%BE%D1%80%D0%BE%D0%B1%D0%BA%D0%B0_%D0%BF%D0%B5%D1%80%D0%B5%D0%B4%D0%B0%D1%87#%D0%A1%D0%B8%D0%BD%D1%85%D1%80%D0%BE%D0%BD%D0%B8%D0%B7%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%BD%D1%8B%D0%B5_%D0%B8_%D0%BD%D0%B5%D1%81%D0%B8%D0%BD%D1%85%D1%80%D0%BE%D0%BD%D0%B8%D0%B7%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%BD%D1%8B%D0%B5_%D0%9C%D0%9A%D0%9F";
                break;
            case "Количество ступеней МКПП":
                link = "https://ru.m.wikipedia.org/wiki/%D0%9C%D0%B5%D1%85%D0%B0%D0%BD%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%B0%D1%8F_%D0%BA%D0%BE%D1%80%D0%BE%D0%B1%D0%BA%D0%B0_%D0%BF%D0%B5%D1%80%D0%B5%D0%B4%D0%B0%D1%87#%D0%9A%D0%BE%D0%BB%D0%B8%D1%87%D0%B5%D1%81%D1%82%D0%B2%D0%BE_%D1%81%D1%82%D1%83%D0%BF%D0%B5%D0%BD%D0%B5%D0%B9";
                break;
            case "Принцип функционирования МКПП":
                link = "https://ru.m.wikipedia.org/wiki/%D0%9C%D0%B5%D1%85%D0%B0%D0%BD%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%B0%D1%8F_%D0%BA%D0%BE%D1%80%D0%BE%D0%B1%D0%BA%D0%B0_%D0%BF%D0%B5%D1%80%D0%B5%D0%B4%D0%B0%D1%87#%D0%9F%D1%80%D0%B8%D0%BD%D1%86%D0%B8%D0%BF_%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D1%8B";
                break;
            case "Схемы переключения МКПП":
                link = "https://ru.m.wikipedia.org/wiki/%D0%9C%D0%B5%D1%85%D0%B0%D0%BD%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%B0%D1%8F_%D0%BA%D0%BE%D1%80%D0%BE%D0%B1%D0%BA%D0%B0_%D0%BF%D0%B5%D1%80%D0%B5%D0%B4%D0%B0%D1%87#%D0%A0%D0%B0%D1%81%D0%BA%D0%BB%D0%B0%D0%B4%D0%BA%D0%B8_%D0%BF%D0%B5%D1%80%D0%B5%D0%BA%D0%BB%D1%8E%D1%87%D0%B5%D0%BD%D0%B8%D1%8F";
                break;
            case "Что такое АКПП?":
                link = "https://ru.m.wikipedia.org/wiki/%D0%90%D0%B2%D1%82%D0%BE%D0%BC%D0%B0%D1%82%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%B0%D1%8F_%D0%BA%D0%BE%D1%80%D0%BE%D0%B1%D0%BA%D0%B0_%D0%BF%D0%B5%D1%80%D0%B5%D0%B4%D0%B0%D1%87";
                break;
            case "Гидротрансформатор АКПП":
                link = "https://ru.m.wikipedia.org/wiki/%D0%93%D0%B8%D0%B4%D1%80%D0%BE%D1%82%D1%80%D0%B0%D0%BD%D1%81%D1%84%D0%BE%D1%80%D0%BC%D0%B0%D1%82%D0%BE%D1%80";
                break;
            case "Коробка передач АКПП":
                link = "https://ru.m.wikipedia.org/wiki/%D0%90%D0%B2%D1%82%D0%BE%D0%BC%D0%B0%D1%82%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%B0%D1%8F_%D0%BA%D0%BE%D1%80%D0%BE%D0%B1%D0%BA%D0%B0_%D0%BF%D0%B5%D1%80%D0%B5%D0%B4%D0%B0%D1%87#%D0%9A%D0%BE%D0%BD%D1%81%D1%82%D1%80%D1%83%D0%BA%D1%86%D0%B8%D1%8F_%D0%B3%D0%B8%D0%B4%D1%80%D0%BE%D0%BC%D0%B5%D1%85%D0%B0%D0%BD%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%BE%D0%B9_%D0%90%D0%9A%D0%9F";
                break;
            case "Режимы работы АКПП":
                link = "https://ru.m.wikipedia.org/wiki/%D0%90%D0%B2%D1%82%D0%BE%D0%BC%D0%B0%D1%82%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%B0%D1%8F_%D0%BA%D0%BE%D1%80%D0%BE%D0%B1%D0%BA%D0%B0_%D0%BF%D0%B5%D1%80%D0%B5%D0%B4%D0%B0%D1%87#%D0%9E%D1%81%D0%BD%D0%BE%D0%B2%D0%BD%D1%8B%D0%B5_%D1%80%D0%B5%D0%B6%D0%B8%D0%BC%D1%8B_%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D1%8B";
                break;
            case "Система управления АКПП":
                link = "https://ru.m.wikipedia.org/wiki/%D0%90%D0%B2%D1%82%D0%BE%D0%BC%D0%B0%D1%82%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%B0%D1%8F_%D0%BA%D0%BE%D1%80%D0%BE%D0%B1%D0%BA%D0%B0_%D0%BF%D0%B5%D1%80%D0%B5%D0%B4%D0%B0%D1%87#%D0%A3%D0%BF%D1%80%D0%B0%D0%B2%D0%BB%D0%B5%D0%BD%D0%B8%D0%B5_%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D0%BE%D0%B9_%D0%90%D0%9A%D0%9F";
                break;
            case "Система смазки и охлаждения АКПП":
                link = "https://akpphelp.ru/ohlazhdenije_akppsistema.html";
                break;
            case "Особенность АКПП у Honda":
                link = "https://aishaspb.ru/info/akpp_honda_osobiennosti_obsluzhivaniie_zamiena_";
                break;
            case "Устройство РКПП":
                link = "https://ru.m.wikipedia.org/wiki/%D0%A0%D0%BE%D0%B1%D0%BE%D1%82%D0%B8%D0%B7%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%BD%D0%B0%D1%8F_%D0%BA%D0%BE%D1%80%D0%BE%D0%B1%D0%BA%D0%B0_%D0%BF%D0%B5%D1%80%D0%B5%D0%BA%D0%BB%D1%8E%D1%87%D0%B5%D0%BD%D0%B8%D1%8F_%D0%BF%D0%B5%D1%80%D0%B5%D0%B4%D0%B0%D1%87";
                break;
            case "КП с двумя сцеплениями":
                link = "https://ru.m.wikipedia.org/wiki/%D0%9A%D0%BE%D1%80%D0%BE%D0%B1%D0%BA%D0%B0_%D0%BF%D0%B5%D1%80%D0%B5%D0%BA%D0%BB%D1%8E%D1%87%D0%B5%D0%BD%D0%B8%D1%8F_%D0%BF%D0%B5%D1%80%D0%B5%D0%B4%D0%B0%D1%87_%D1%81_%D0%B4%D0%B2%D0%BE%D0%B9%D0%BD%D1%8B%D0%BC_%D1%81%D1%86%D0%B5%D0%BF%D0%BB%D0%B5%D0%BD%D0%B8%D0%B5%D0%BC";
                break;
            case "Принцип работы РКПП":
                link = "https://www.drive.ru/technic/4efb332e00f11713001e3f50.html";
                break;
            case "Летняя резина":
                link = "https://mapon.com.ua/neobhodimost-letney-reziny/#:~:text=%D0%9B%D0%B5%D1%82%D0%BD%D1%8F%D1%8F%20%D1%80%D0%B5%D0%B7%D0%B8%D0%BD%D0%B0%20%D0%B8%D0%B7%D0%B3%D0%BE%D1%82%D0%B0%D0%B2%D0%BB%D0%B8%D0%B2%D0%B0%D0%B5%D1%82%D1%81%D1%8F%20%D1%82%D0%B0%D0%BA%D0%B8%D0%BC%20%D0%BE%D0%B1%D1%80%D0%B0%D0%B7%D0%BE%D0%BC,%D0%B2%D1%80%D0%B5%D0%BC%D1%8F%20%D0%B5%D0%B7%D0%B4%D1%8B%20%D0%BF%D0%BE%20%D0%BC%D0%BE%D0%BA%D1%80%D0%BE%D0%B9%20%D0%B4%D0%BE%D1%80%D0%BE%D0%B3%D0%B5.";
                break;
            case "Диски":
                link = "https://mapioza.com/vidy-kolesnyx-diskov-dlya-avtomobilej/";
                break;
            case "Шипованная резина":
                link = "https://ru.m.wikipedia.org/wiki/%D0%97%D0%B8%D0%BC%D0%BD%D1%8F%D1%8F_%D1%88%D0%B8%D0%BD%D0%B0";
                break;
            case "Амортизатор":
                link = "https://ru.m.wikipedia.org/wiki/%D0%90%D0%BC%D0%BE%D1%80%D1%82%D0%B8%D0%B7%D0%B0%D1%82%D0%BE%D1%80";
                break;
            case "Стабилизатор поперечной устойчивости":
                link = "https://ru.m.wikipedia.org/wiki/%D0%A1%D1%82%D0%B0%D0%B1%D0%B8%D0%BB%D0%B8%D0%B7%D0%B0%D1%82%D0%BE%D1%80_%D0%BF%D0%BE%D0%BF%D0%B5%D1%80%D0%B5%D1%87%D0%BD%D0%BE%D0%B9_%D1%83%D1%81%D1%82%D0%BE%D0%B9%D1%87%D0%B8%D0%B2%D0%BE%D1%81%D1%82%D0%B8#:~:text=%D0%A1%D1%82%D0%B0%D0%B1%D0%B8%D0%BB%D0%B8%D0%B7%D0%B0%D1%82%D0%BE%D1%80%20%D0%BF%D0%BE%D0%BF%D0%B5%D1%80%D0%B5%D1%87%D0%BD%D0%BE%D0%B9%20%D1%83%D1%81%D1%82%D0%BE%D0%B9%D1%87%D0%B8%D0%B2%D0%BE%D1%81%D1%82%D0%B8%20%E2%80%94%20%D1%83%D1%81%D1%82%D1%80%D0%BE%D0%B9%D1%81%D1%82%D0%B2%D0%BE%20%D0%B2,%D1%83%D0%BC%D0%B5%D0%BD%D1%8C%D1%88%D0%B5%D0%BD%D0%B8%D1%8F%20%D0%B1%D0%BE%D0%BA%D0%BE%D0%B2%D1%8B%D1%85%20%D0%BA%D1%80%D0%B5%D0%BD%D0%BE%D0%B2%20%D0%B2%20%D0%BF%D0%BE%D0%B2%D0%BE%D1%80%D0%BE%D1%82%D0%B0%D1%85.";
                break;
            case "Ступица":
                link = "https://ru.m.wikipedia.org/wiki/%D0%A1%D1%82%D1%83%D0%BF%D0%B8%D1%86%D0%B0";
                break;
            case "Втулка стабилизатора":
                link = "https://okeydrive.ru/kak-pomenyat-vtulki-stabilizatora/";
                break;
            case "Пружины":
                link = "http://sibal-auto.ru/news/prujina-podveski";
                break;
            case "Стойки":
                link = "http://carsmotion.ru/ustrojstvo/chto-takoe-stoyki-avtomobilya.html";
                break;
            case "ШРУС - шарнир угловых скоростей":
                link = "https://ru.m.wikipedia.org/wiki/%D0%A8%D0%B0%D1%80%D0%BD%D0%B8%D1%80_%D1%80%D0%B0%D0%B2%D0%BD%D1%8B%D1%85_%D1%83%D0%B3%D0%BB%D0%BE%D0%B2%D1%8B%D1%85_%D1%81%D0%BA%D0%BE%D1%80%D0%BE%D1%81%D1%82%D0%B5%D0%B9";
                break;
            case "Устройство колеса":
                link = "https://amastercar.ru/articles/wheel_tire_1.shtml";
                break;
            case "Устройство электроусилителя руля":
                link = "https://ru.m.wikipedia.org/wiki/%D0%AD%D0%BB%D0%B5%D0%BA%D1%82%D1%80%D0%BE%D1%83%D1%81%D0%B8%D0%BB%D0%B8%D1%82%D0%B5%D0%BB%D1%8C_%D1%80%D1%83%D0%BB%D1%8F";
                break;
            case "Устройство гидроусилителя руля":
                link = "https://ru.m.wikipedia.org/wiki/%D0%93%D0%B8%D0%B4%D1%80%D0%BE%D1%83%D1%81%D0%B8%D0%BB%D0%B8%D1%82%D0%B5%D0%BB%D1%8C_%D1%80%D1%83%D0%BB%D1%8F";
                break;
            case "Рулевое управление автомобилем":
                link = "https://ru.m.wikipedia.org/wiki/%D0%A0%D1%83%D0%BB%D0%B5%D0%B2%D0%BE%D0%B5_%D1%83%D0%BF%D1%80%D0%B0%D0%B2%D0%BB%D0%B5%D0%BD%D0%B8%D0%B5#:~:text=%D0%A0%D1%83%D0%BB%D0%B5%D0%B2%D0%BE%D0%B5%20%D1%83%D0%BF%D1%80%D0%B0%D0%B2%D0%BB%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B0%D0%B2%D1%82%D0%BE%D0%BC%D0%BE%D0%B1%D0%B8%D0%BB%D0%B5%D0%B9,-%D0%9F%D1%80%D0%B8%D0%BC%D0%B5%D1%80%20%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D1%8B%20%D1%81%D0%B8%D1%81%D1%82%D0%B5%D0%BC%D1%8B&text=4%20%D1%83%D0%BF%D1%80%D0%B0%D0%B2%D0%BB%D1%8F%D0%B5%D0%BC%D1%8B%D1%85%20%D0%BA%D0%BE%D0%BB%D0%B5%D1%81%D0%B0)%20%2D%20%D1%81%D0%B8%D1%81%D1%82%D0%B5%D0%BC%D0%B0%20%D0%BF%D0%BE%D0%B4%D1%80%D1%83%D0%BB%D0%B8%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F%20%D0%B7%D0%B0%D0%B4%D0%BD%D0%B8%D1%85%20%D0%BA%D0%BE%D0%BB%D0%B5%D1%81%20%D1%83%20%D0%B0%D0%B2%D1%82%D0%BE%D0%BC%D0%BE%D0%B1%D0%B8%D0%BB%D1%8F.&text=%D0%9D%D0%B0%20%D0%B0%D0%B2%D1%82%D0%BE%D0%BC%D0%BE%D0%B1%D0%B8%D0%BB%D1%8F%D1%85%20%D1%80%D1%83%D0%BB%D0%B5%D0%B2%D0%BE%D0%B5%20%D1%83%D0%BF%D1%80%D0%B0%D0%B2%D0%BB%D0%B5%D0%BD%D0%B8%D0%B5%20%D1%81%D0%BE%D1%81%D1%82%D0%BE%D0%B8%D1%82,%D0%BF%D0%BE%D0%B2%D0%BE%D1%80%D0%BE%D1%82%20%D1%83%D0%BF%D1%80%D0%B0%D0%B2%D0%BB%D1%8F%D0%B5%D0%BC%D1%8B%D1%85%20(%D0%BF%D0%B5%D1%80%D0%B5%D0%B4%D0%BD%D0%B8%D1%85)%20%D0%BA%D0%BE%D0%BB%D1%91%D1%81.";
                break;
            case "Устройство и принцип работы рулевой рейки":
                link = "https://techautoport.ru/hodovaya-chast/rulevoe-upravlenie/rulevaya-reyka.html#:~:text=%D0%A0%D0%B0%D1%81%D1%81%D0%BC%D0%BE%D1%82%D1%80%D0%B8%D0%BC%20%D0%BF%D1%80%D0%B8%D0%BD%D1%86%D0%B8%D0%BF%20%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D1%8B%20%D1%80%D1%83%D0%BB%D0%B5%D0%B2%D0%BE%D0%B9%20%D1%80%D0%B5%D0%B9%D0%BA%D0%B8,%D0%BF%D0%BE%D0%B2%D0%BE%D1%80%D0%B0%D1%87%D0%B8%D0%B2%D0%B0%D1%8E%D1%82%20%D1%81%D1%82%D1%83%D0%BF%D0%B8%D1%86%D1%8B%20%D0%B8%D0%BB%D0%B8%20%D0%BF%D0%BE%D0%B2%D0%BE%D1%80%D0%BE%D1%82%D0%BD%D1%8B%D0%B5%20%D0%BA%D1%83%D0%BB%D0%B0%D0%BA%D0%B8.";
                break;
            case "Устройство рулевой колонки автомобиля":
                link = "https://techautoport.ru/hodovaya-chast/rulevoe-upravlenie/rulevaya-kolonka.html";
                break;
            case "Система активного рулевого управления - AFS":
                link = "https://techautoport.ru/hodovaya-chast/rulevoe-upravlenie/sistema-afs.html";
                break;
            case "Принцип работы системы охлаждения":
                link = "https://ru.m.wikipedia.org/wiki/%D0%A1%D0%B8%D1%81%D1%82%D0%B5%D0%BC%D0%B0_%D0%BE%D1%85%D0%BB%D0%B0%D0%B6%D0%B4%D0%B5%D0%BD%D0%B8%D1%8F_%D0%B4%D0%B2%D0%B8%D0%B3%D0%B0%D1%82%D0%B5%D0%BB%D1%8F_%D0%B2%D0%BD%D1%83%D1%82%D1%80%D0%B5%D0%BD%D0%BD%D0%B5%D0%B3%D0%BE_%D1%81%D0%B3%D0%BE%D1%80%D0%B0%D0%BD%D0%B8%D1%8F";
                break;
            case "Радиатор":
                link = "http://avtomotoprof.ru/obsluzhivanie-i-uhod-za-avtomobilem/radiator-ohlazhdeniya-dvigatelya-ustroystvo-rabota-i-promyivka-radiatora/";
                break;
            case "Вентилятор радиатора":
                link = "https://avtotachki.com/chto-takoe-ventilyator-radiatora-avtomobilya/";
                break;
            case "Центробежный насос":
                link = "https://dmliefer.ru/katalog/nasosnoe-oborudovanie/nasosy-centrobezhnye";
                break;
            case "Термостат":
                link = "https://amastercar.ru/articles/engine_car_53_termostat.shtml";
                break;
            case "Расширительный бачок":
                link = "https://auto.today/bok/4335-dlya-chego-nuzhen-rasshiritelnyy-bachok-sistemy-ohlazhdeniya.html";
                break;
            case "Свечи зажигания":
                link = "https://ru.m.wikipedia.org/wiki/%D0%A1%D0%B2%D0%B5%D1%87%D0%B0_%D0%B7%D0%B0%D0%B6%D0%B8%D0%B3%D0%B0%D0%BD%D0%B8%D1%8F";
                break;
            case "Катушка зажигания":
                link = "https://ru.m.wikipedia.org/wiki/%D0%9A%D0%B0%D1%82%D1%83%D1%88%D0%BA%D0%B0_%D0%B7%D0%B0%D0%B6%D0%B8%D0%B3%D0%B0%D0%BD%D0%B8%D1%8F";
                break;
            case "Устройство датчика Холла":
                link = "https://auto.today/bok/2724-sposoby-proverki-datchika-zazhiganiya-holla.html#:~:text=%D0%9D%D0%B0%20%D0%B0%D0%B2%D1%82%D0%BE%D0%BC%D0%BE%D0%B1%D0%B8%D0%BB%D1%8F%D1%85%20%D1%81%20%D0%B1%D0%B5%D1%81%D0%BA%D0%BE%D0%BD%D1%82%D0%B0%D0%BA%D1%82%D0%BD%D0%BE%D0%B9%20%D1%81%D0%B8%D1%81%D1%82%D0%B5%D0%BC%D0%BE%D0%B9,%D0%B2%20%D0%BF%D0%BE%D0%BB%D1%83%D0%BF%D1%80%D0%BE%D0%B2%D0%BE%D0%B4%D0%BD%D0%B8%D0%BA%D0%BE%D0%B2%D0%BE%D0%B9%20%D0%BF%D0%BB%D0%B0%D1%81%D1%82%D0%B8%D0%BD%D0%B5%20%D1%84%D0%BE%D1%80%D0%BC%D0%B8%D1%80%D1%83%D0%B5%D1%82%D1%81%D1%8F%20%D0%BD%D0%B0%D0%BF%D1%80%D1%8F%D0%B6%D0%B5%D0%BD%D0%B8%D0%B5.";
                break;
            case "Принцип работы топливных систем":
                link = "https://techautoport.ru/dvigatel/toplivnaya-sistema/toplivnye-sistemy-benzinovyh-i-dizelnyh-dvigateley.html";
                break;
            case "Топливный насос":
                link = "https://techautoport.ru/dvigatel/toplivnaya-sistema/toplivnyi-nasos.html";
                break;
            case "Топливный фильтр":
                link = "https://avtoexperts.ru/article/toplivny-j-fil-tr-gde-nahoditsya-kogda-menyat/#:~:text=%D0%A2%D0%BE%D0%BF%D0%BB%D0%B8%D0%B2%D0%BD%D1%8B%D0%B9%20%D1%84%D0%B8%D0%BB%D1%8C%D1%82%D1%80%20%E2%80%93%20%D0%BD%D0%B5%D0%B1%D0%BE%D0%BB%D1%8C%D1%88%D0%BE%D0%B9%2C%20%D0%BD%D0%BE%20%D0%B2%D0%B0%D0%B6%D0%BD%D1%8B%D0%B9,%D0%BF%D0%BE%D0%BF%D0%B0%D0%B4%D0%B0%D0%BD%D0%B8%D0%B5%D0%BC%20%D0%B2%20%D0%BA%D0%B0%D0%BC%D0%B5%D1%80%D1%8B%20%D1%81%D0%B3%D0%BE%D1%80%D0%B0%D0%BD%D0%B8%D1%8F%20%D0%BC%D1%83%D1%81%D0%BE%D1%80%D0%B0.";
                break;
            case "Топливный бак":
                link = "https://techautoport.ru/dvigatel/toplivnaya-sistema/toplivnyi-bak-avtomobilya.html";
                break;
            case "Датчик уровня топлива ":
                link = "http://systemsauto.ru/fuel/fuel-level-sensor.html";
                break;
            case "Система впрыска дизельных двигателей":
                link = "https://pro-sensys.com/info/articles/obzornye-stati/dizelnye-sistemy-vpryska/";
                break;
            case "Система впрыска бензиновых двигателей":
                link = "https://ru.m.wikipedia.org/wiki/%D0%98%D0%BD%D0%B6%D0%B5%D0%BA%D1%82%D0%BE%D1%80%D0%BD%D0%B0%D1%8F_%D1%81%D0%B8%D1%81%D1%82%D0%B5%D0%BC%D0%B0_%D0%BF%D0%BE%D0%B4%D0%B0%D1%87%D0%B8_%D1%82%D0%BE%D0%BF%D0%BB%D0%B8%D0%B2%D0%B0#:~:text=%D0%A1%D0%B8%D1%81%D1%82%D0%B5%D0%BC%D0%B0%20%D0%B2%D0%BF%D1%80%D1%8B%D1%81%D0%BA%D0%B0%20%D1%82%D0%BE%D0%BF%D0%BB%D0%B8%D0%B2%D0%B0%20%E2%80%94%20%D1%81%D0%B8%D1%81%D1%82%D0%B5%D0%BC%D0%B0%20%D0%BF%D0%BE%D0%B4%D0%B0%D1%87%D0%B8,%D0%B2%D0%BF%D1%83%D1%81%D0%BA%D0%BD%D0%BE%D0%B9%20%D0%BA%D0%BE%D0%BB%D0%BB%D0%B5%D0%BA%D1%82%D0%BE%D1%80%20%D0%B8%D0%BB%D0%B8%20%D0%B2%20%D1%86%D0%B8%D0%BB%D0%B8%D0%BD%D0%B4%D1%80.";
                break;
            case "Адсорбер":
                link = "http://avto-blogger.ru/dv/adsorber-chto-eto-takoe-v-mashine.html";
                break;
            case "Форсунки":
                link = "https://avtoexperts.ru/article/forsunki-dvigatelya/";
                break;
            case "Дроссельная заслонка":
                link = "http://systemsauto.ru/vpusk/throttle_body.html";
                break;
            case "Устройство тормозной системы":
                link = "https://www.autoezda.com/tormoza.html";
                break;
            case "Принцип работы тормозной системы":
                link = "https://techautoport.ru/hodovaya-chast/tormoznaya-sistema/tormoznaya-sistema-avtomobilya.html";
                break;
            case "Принцип работы дисковых тормозов":
                link = "https://techautoport.ru/hodovaya-chast/tormoznaya-sistema/diskovyi-tormoz.html#:~:text=%D0%9F%D1%80%D0%B8%D0%BD%D1%86%D0%B8%D0%BF%20%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D1%8B%20%D0%B4%D0%B8%D1%81%D0%BA%D0%BE%D0%B2%D1%8B%D1%85%20%D1%82%D0%BE%D1%80%D0%BC%D0%BE%D0%B7%D0%BE%D0%B2,-%D0%A2%D0%BE%D1%80%D0%BC%D0%BE%D0%B7%D0%BD%D0%BE%D0%B9%20%D0%BC%D0%B5%D1%85%D0%B0%D0%BD%D0%B8%D0%B7%D0%BC%20%D1%81&text=%D0%9F%D1%80%D0%B8%20%D0%BD%D0%B0%D0%B6%D0%B0%D1%82%D0%B8%D0%B8%20%D0%B2%D0%BE%D0%B4%D0%B8%D1%82%D0%B5%D0%BB%D0%B5%D0%BC%20%D0%BD%D0%B0%20%D0%BF%D0%B5%D0%B4%D0%B0%D0%BB%D1%8C,%D0%BE%D1%87%D0%B5%D1%80%D0%B5%D0%B4%D1%8C%2C%20%D0%BF%D1%80%D0%B8%D0%B6%D0%B8%D0%BC%D0%B0%D1%8E%D1%82%20%D0%BA%20%D0%BD%D0%B5%D0%BC%D1%83%20%D0%BA%D0%BE%D0%BB%D0%BE%D0%B4%D0%BA%D0%B8.";
                break;
            case "Органические тормозные колодки":
                link = "http://avtosreda.ru/info/organicheskie-kolodki-protiv-keramicheskihmnenie-spetsialistov/";
                break;
            case "Керамические тормозные колодки":
                link = "https://techautoport.ru/hodovaya-chast/tormoznaya-sistema/keramicheskie-tormoza.html";
                break;
            case "Полуметаллические тормозные колодки":
                link = "https://autoabra.com/avtomehanika/keramicheskie-tormoznye-kolodki-vs-semi-metallic/";
                break;
            case "Невентилируемые тормозные диски":
                link = "https://vchemraznica.ru/chem-otlichayutsya-ventiliruemye-tormoznye-diski-ot-neventiliruemyx/";
                break;
            case "Вентилируемые тормозные диски":
                link = "http://avto-blogger.ru/chto-takoe-v-avtomobile/ventiliruemye-tormoznye-diski.html";
                break;
            case "Просверленные тормозные диски":
                link = "https://nissan-modus.ru/shhetki-stekloochistitelya/perforatsiya-tormoznyh-diskov-plyusy-i-minusy.html";
                break;
            case "Диск с прорезями":
                link = "https://nissan-modus.ru/shhetki-stekloochistitelya/perforatsiya-tormoznyh-diskov-plyusy-i-minusy.html";
                break;
            case "Диск с канавками":
                link = "http://protocka.ml/slot/";
                break;
            case "Волнообразный диск":
                link = "https://1gai.ru/publ/520942-kakie-tormoznye-diski-byvayut-tipy-klassifikaciya-preimuschestva.html";
                break;
            case "Антиблокировочная система - ABS":
                link = "https://ru.m.wikipedia.org/wiki/%D0%90%D0%BD%D1%82%D0%B8%D0%B1%D0%BB%D0%BE%D0%BA%D0%B8%D1%80%D0%BE%D0%B2%D0%BE%D1%87%D0%BD%D0%B0%D1%8F_%D1%81%D0%B8%D1%81%D1%82%D0%B5%D0%BC%D0%B0";
                break;
            case "Противобуксовочная система - TCS":
                link = "https://ru.m.wikipedia.org/wiki/%D0%9F%D1%80%D0%BE%D1%82%D0%B8%D0%B2%D0%BE%D0%B1%D1%83%D0%BA%D1%81%D0%BE%D0%B2%D0%BE%D1%87%D0%BD%D0%B0%D1%8F_%D1%81%D0%B8%D1%81%D1%82%D0%B5%D0%BC%D0%B0";
                break;
            case "Электронный контроль устойчивости - ESP":
                link = "https://ru.m.wikipedia.org/wiki/%D0%AD%D0%BB%D0%B5%D0%BA%D1%82%D1%80%D0%BE%D0%BD%D0%BD%D0%B0%D1%8F_%D1%81%D0%B8%D1%81%D1%82%D0%B5%D0%BC%D0%B0_%D0%BA%D0%BE%D0%BD%D1%82%D1%80%D0%BE%D0%BB%D1%8F_%D1%83%D1%81%D1%82%D0%BE%D0%B9%D1%87%D0%B8%D0%B2%D0%BE%D1%81%D1%82%D0%B8_%D0%B0%D0%B2%D1%82%D0%BE%D0%BC%D0%BE%D0%B1%D0%B8%D0%BB%D1%8F";
                break;
            case "Система курсовой устойчивости - ESC":
                link = "https://techautoport.ru/hodovaya-chast/tormoznaya-sistema/sistema-esc.html";
                break;
            case "Система распределения тормозных усилий - EBD":
                link = "https://ru.m.wikipedia.org/wiki/%D0%A1%D0%B8%D1%81%D1%82%D0%B5%D0%BC%D0%B0_%D1%80%D0%B0%D1%81%D0%BF%D1%80%D0%B5%D0%B4%D0%B5%D0%BB%D0%B5%D0%BD%D0%B8%D1%8F_%D1%82%D0%BE%D1%80%D0%BC%D0%BE%D0%B7%D0%BD%D1%8B%D1%85_%D1%83%D1%81%D0%B8%D0%BB%D0%B8%D0%B9";
                break;
            case "Электронная блокировка дифференциала - EDS":
                link = "http://systemsauto.ru/active/eds.html";
                break;
            case "Антипробуксовочная система - ASR":
                link = "https://mytopgear.ru/interesting/brakes/sistema-asr-v-avtomobile-chto-eto-takoe/";
                break;
            case "Ручной тормоз":
                link = "https://ru.m.wikipedia.org/wiki/%D0%A1%D1%82%D0%BE%D1%8F%D0%BD%D0%BE%D1%87%D0%BD%D1%8B%D0%B9_%D1%82%D0%BE%D1%80%D0%BC%D0%BE%D0%B7";
                break;
            case "Аккумулятор":
                link = "https://ru.m.wikipedia.org/wiki/%D0%90%D0%B2%D1%82%D0%BE%D0%BC%D0%BE%D0%B1%D0%B8%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9_%D0%B0%D0%BA%D0%BA%D1%83%D0%BC%D1%83%D0%BB%D1%8F%D1%82%D0%BE%D1%80";
                break;
            case "Стартер":
                link = "https://ru.m.wikipedia.org/wiki/%D0%AD%D0%BB%D0%B5%D0%BA%D1%82%D1%80%D0%BE%D1%81%D1%82%D0%B0%D1%80%D1%82%D0%B5%D1%80";
                break;
            case "Генератор":
                link = "https://ru.m.wikipedia.org/wiki/%D0%90%D0%B2%D1%82%D0%BE%D0%BC%D0%BE%D0%B1%D0%B8%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9_%D0%B3%D0%B5%D0%BD%D0%B5%D1%80%D0%B0%D1%82%D0%BE%D1%80";
                break;
            case "Предохранители":
                link = "https://electrosam.ru/glavnaja/jelektrooborudovanie/rozetki-vykljuchateli/avtomobilnye-predokhraniteli/";
                break;
            case "Лампы освещения":
                link = "https://ru.m.wikipedia.org/wiki/%D0%90%D0%B2%D1%82%D0%BE%D0%BC%D0%BE%D0%B1%D0%B8%D0%BB%D1%8C%D0%BD%D0%B0%D1%8F_%D1%81%D0%B2%D0%B5%D1%82%D0%BE%D1%82%D0%B5%D1%85%D0%BD%D0%B8%D0%BA%D0%B0";
                break;
            case "Бортовой компьютер":
                link = "https://ru.m.wikipedia.org/wiki/%D0%9A%D0%B0%D1%80%D0%BF%D1%8C%D1%8E%D1%82%D0%B5%D1%80";
                break;
            case "Магнитола":
                link = "https://xn--l1adadp.od.ua/interesnoe/avtomagnitola-chto-eto/";
                break;
            case "Приборная панель":
                link = "https://ru.m.wikipedia.org/wiki/%D0%9F%D1%80%D0%B8%D0%B1%D0%BE%D1%80%D0%BD%D0%B0%D1%8F_%D0%BF%D0%B0%D0%BD%D0%B5%D0%BB%D1%8C#%D0%90%D0%B2%D1%82%D0%BE%D0%BC%D0%BE%D0%B1%D0%B8%D0%BB%D1%8C%D0%BD%D0%B0%D1%8F_%D0%BF%D1%80%D0%B8%D0%B1%D0%BE%D1%80%D0%BD%D0%B0%D1%8F_%D0%BF%D0%B0%D0%BD%D0%B5%D0%BB%D1%8C";
                break;
            case "Панель управления обогревом салона - печка":
                link = "https://blamper.ru/auto/wiki/salon/sistema-otopleniya-avtomobilya-3606/";
                break;
            case "Подушка безопасности":
                link = "https://ru.m.wikipedia.org/wiki/%D0%9F%D0%BE%D0%B4%D1%83%D1%88%D0%BA%D0%B0_%D0%B1%D0%B5%D0%B7%D0%BE%D0%BF%D0%B0%D1%81%D0%BD%D0%BE%D1%81%D1%82%D0%B8";
                break;
            case "Тип кузовов":
                link = "https://ru.m.wikipedia.org/wiki/%D0%A2%D0%B8%D0%BF%D1%8B_%D0%B0%D0%B2%D1%82%D0%BE%D0%BC%D0%BE%D0%B1%D0%B8%D0%BB%D1%8C%D0%BD%D1%8B%D1%85_%D0%BA%D1%83%D0%B7%D0%BE%D0%B2%D0%BE%D0%B2";
                break;
            case "Капот":
                link = "https://ru.m.wikipedia.org/wiki/%D0%9A%D0%B0%D0%BF%D0%BE%D1%82_(%D0%B0%D0%B2%D1%82%D0%BE%D0%BC%D0%BE%D0%B1%D0%B8%D0%BB%D1%8C)";
                break;
            case "Багажник":
                link = "https://ru.m.wikipedia.org/wiki/%D0%91%D0%B0%D0%B3%D0%B0%D0%B6%D0%BD%D0%B8%D0%BA";
                break;
            case "Двери":
                link = "https://ru.m.wikipedia.org/wiki/%D0%90%D0%B2%D1%82%D0%BE%D0%BC%D0%BE%D0%B1%D0%B8%D0%BB%D1%8C%D0%BD%D0%B0%D1%8F_%D0%B4%D0%B2%D0%B5%D1%80%D1%8C";
                break;
            case "Система очистки стекол":
                link = "https://www.mirstekla-expo.ru/ru/articles/sistemy-ochistki-stekol/";
                break;
            case "Бампер передний":
                link = "https://kuzovnoy.ru/infobase/cat_bumper/pub_peredniy-bamper-naznachenie-i-kharakteristiki/";
                break;
            case "Бампер задний":
                link = "https://avto.pro/autonews/kak_vibrat_zadniy_bamper-20170730/";
                break;
            case "Пороги":
                link = "https://porog.su/porogi-dlya-avtomobilya/";
                break;
            case "Молдинги":
                link = "http://avto-i-avto.ru/tyuning-avto/molding-vidy-kleit.html";
                break;
            case "Ветровики":
                link = "https://unit-car.com/termini-i-sokrasheniya/171-deflektory-kapota-i-okon.html";
                break;
            case "Крылья":
                link = "https://ru.m.wikipedia.org/wiki/%D0%9A%D1%80%D1%8B%D0%BB%D0%BE_(%D0%BF%D0%BE%D0%BA%D1%80%D1%8B%D1%82%D0%B8%D0%B5_%D0%BD%D0%B0%D0%B4_%D0%BA%D0%BE%D0%BB%D0%B5%D1%81%D0%BE%D0%BC)";
                break;
            case "Зеркала":
                link = "https://ru.m.wikipedia.org/wiki/%D0%97%D0%B5%D1%80%D0%BA%D0%B0%D0%BB%D0%BE_%D0%B7%D0%B0%D0%B4%D0%BD%D0%B5%D0%B3%D0%BE_%D0%B2%D0%B8%D0%B4%D0%B0";
                break;
            case "Крышка бензобака":
                link = "https://blamper.ru/auto/wiki/dvigatel/kryshka-benzobaka-3661/";
                break;
            case "Спойлер":
                link = "https://ru.m.wikipedia.org/wiki/%D0%A1%D0%BF%D0%BE%D0%B9%D0%BB%D0%B5%D1%80_(%D0%B0%D0%B2%D1%82%D0%BE%D0%BC%D0%BE%D0%B1%D0%B8%D0%BB%D0%B8)";
                break;
            case "Датчик вентилятора":
                link = "https://okeydrive.ru/kak-proverit-datchik-ventilyatora-avtomobilya/";
                break;
            case "Датчик скорости":
                link = "https://www.autoezda.com/workstationauto/963-princip-rabotu-datchika-skorosti-avtomobilia.html#:~:text=%D0%94%D0%B0%D1%82%D1%87%D0%B8%D0%BA%20%D1%81%D0%BA%D0%BE%D1%80%D0%BE%D1%81%D1%82%D0%B8%20%D0%B0%D0%B2%D1%82%D0%BE%D0%BC%D0%BE%D0%B1%D0%B8%D0%BB%D1%8F%20%D1%81%D0%BB%D1%83%D0%B6%D0%B8%D1%82%20%D0%B4%D0%BB%D1%8F,%D0%B4%D0%B0%D1%82%D1%87%D0%B8%D0%BA%D0%B0%20%D1%81%D0%BA%D0%BE%D1%80%D0%BE%D1%81%D1%82%D0%B8%20%D0%B2%D1%8B%D0%BF%D0%BE%D0%BB%D0%BD%D1%8F%D0%B5%D1%82%20%D0%B4%D0%B0%D1%82%D1%87%D0%B8%D0%BA%20%D0%A5%D0%BE%D0%BB%D0%BB%D0%B0.";
                break;
            case "Датчик вентилятора охлаждения радиатора":
                link = "https://okeydrive.ru/kak-proverit-datchik-ventilyatora-avtomobilya/";
                break;
            case "Датчик давления в шинах":
                link = "https://www.drive.ru/technic/4efb330d00f11713001e35c3.html";
                break;
            case "Датчик холостого хода":
                link = "https://ru.m.wikipedia.org/wiki/%D0%9A%D0%BB%D0%B0%D0%BF%D0%B0%D0%BD_%D1%85%D0%BE%D0%BB%D0%BE%D1%81%D1%82%D0%BE%D0%B3%D0%BE_%D1%85%D0%BE%D0%B4%D0%B0";
                break;
            case "Датчик уровня топлива":
                link = "http://systemsauto.ru/fuel/fuel-level-sensor.html";
                break;
            case "Датчик положения дроссельной заслонки":
                link = "https://ru.m.wikipedia.org/wiki/%D0%94%D0%B0%D1%82%D1%87%D0%B8%D0%BA_%D0%BF%D0%BE%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D1%8F_%D0%B4%D1%80%D0%BE%D1%81%D1%81%D0%B5%D0%BB%D1%8C%D0%BD%D0%BE%D0%B9_%D0%B7%D0%B0%D1%81%D0%BB%D0%BE%D0%BD%D0%BA%D0%B8";
                break;
            case "Датчик положения коленчатого вала":
                link = "https://auto.today/bok/2671-chto-takoe-datchik-polozheniya-kolenvala-i-za-chto-on-otvechaet.html";
                break;
            case "Датчик температуры охлаждающей жидкости":
                link = "https://blamper.ru/auto/wiki/dvigatel/datchik-temperatury-ohlazhdayushchey-zhidkosti-14296/";
                break;
            case "Датчик расхода воздуха":
                link = "https://ru.m.wikipedia.org/wiki/%D0%94%D0%B0%D1%82%D1%87%D0%B8%D0%BA_%D0%BC%D0%B0%D1%81%D1%81%D0%BE%D0%B2%D0%BE%D0%B3%D0%BE_%D1%80%D0%B0%D1%81%D1%85%D0%BE%D0%B4%D0%B0_%D0%B2%D0%BE%D0%B7%D0%B4%D1%83%D1%85%D0%B0";
                break;
            case "Кислородный датчик - лямбда-зонд":
                link = "https://amastercar.ru/articles/injection_fuel_6.shtml";
                break;
            case "Датчик давления масла":
                link = "https://avtoexperts.ru/article/datchik-davleniya-masla/";
                break;
            case "Датчик детонации":
                link = "http://systemsauto.ru/electric/knock_sensor.html";
                break;
            case "Датчик угла поворота распредвала":
                link = "http://systemsauto.ru/electric/hall_sensor.html";
                break;
            case "Датчик ABS":
                link = "https://blamper.ru/auto/wiki/tormoznaya-sistema/datchiki-abs-2949/";
                break;
            case "Водительское сиденье":
                link = "http://365cars.ru/tovari/kak-vybrat-sidene.html";
                break;
            case "Пассажирское сиденье":
                link = "http://systemsauto.ru/carring/car_seat.html";
                break;
            case "Руль":
                link = "https://ru.m.wikipedia.org/wiki/%D0%A0%D1%83%D0%BB%D0%B5%D0%B2%D0%BE%D0%B5_%D0%BA%D0%BE%D0%BB%D0%B5%D1%81%D0%BE";
                break;
            case "Коврики":
                link = "https://ru.m.wikipedia.org/wiki/%D0%90%D0%B2%D1%82%D0%BE%D0%BC%D0%BE%D0%B1%D0%B8%D0%BB%D1%8C%D0%BD%D1%8B%D0%B5_%D0%BA%D0%BE%D0%B2%D1%80%D0%B8%D0%BA%D0%B8";
                break;
            case "Чехлы":
                link = "https://avtofishki.com.ua/articles/zachem-nuzhny-avtochehly";
                break;
            case "Ремни безопасности":
                link = "https://ru.m.wikipedia.org/wiki/%D0%A0%D0%B5%D0%BC%D0%B5%D0%BD%D1%8C_%D0%B1%D0%B5%D0%B7%D0%BE%D0%BF%D0%B0%D1%81%D0%BD%D0%BE%D1%81%D1%82%D0%B8";
                break;
            case "Солнцезащитные козырьки":
                link = "https://samsebeskazal.livejournal.com/71282.html";
                break;
            case "Подстаканники":
                link = "http://autoutro.ru/review/2016/06/10/kak-podstakanniki-pronikli-v-avtomobil/";
                break;
            case "Ручник - стояночный тормоз":
                link = "https://ru.m.wikipedia.org/wiki/%D0%A1%D1%82%D0%BE%D1%8F%D0%BD%D0%BE%D1%87%D0%BD%D1%8B%D0%B9_%D1%82%D0%BE%D1%80%D0%BC%D0%BE%D0%B7";
                break;
            case "Ручка управления КПП":
                link = "https://automotolife.com/services/chto-takoe-kulisa-v-korobke-peredach";
                break;
            case "Минеральное масло - органическое":
                link = "https://m.etlib.ru/blog/246-chto-takoe-mineralnoe-maslo";
                break;
            case "Полусинтетическое масло":
                link = "https://m.etlib.ru/blog/529-chto-takoe-polusinteticheskoe-maslo";
                break;
            case "Синтетическое масло":
                link = "https://m.etlib.ru/blog/528-chto-takoe-sinteticheskoe-maslo";
                break;
            case "Моторное масло":
                link = "https://ru.m.wikipedia.org/wiki/%D0%9C%D0%BE%D1%82%D0%BE%D1%80%D0%BD%D1%8B%D0%B5_%D0%BC%D0%B0%D1%81%D0%BB%D0%B0";
                break;
            case "Трансмиссионное масло":
                link = "https://ru.m.wikipedia.org/wiki/%D0%A2%D1%80%D0%B0%D0%BD%D1%81%D0%BC%D0%B8%D1%81%D1%81%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5_%D0%BC%D0%B0%D1%81%D0%BB%D0%B0";
                break;
            case "Масло для гидроусилителя":
                link = "https://www.google.com/amp/s/okeydrive.ru/kakuyu-zhidkost-zalivayut-v-gidrousilitel-rulya/amp/";
                break;
            case "Зимнее масло":
                link = "https://liga-m.pro/blog/vybiraem-motornoe-maslo-na-zimu.html";
                break;
            case "Летнее масло":
                link = "https://rolfoil.ru/motornoe-maslo-dlya-leta.html";
                break;
            case "Всесезонное масло":
                link = "https://sintec-masla.ru/motornoe-maslo/vsesezonnoe/";
                break;
        }
        return link;
    }
}