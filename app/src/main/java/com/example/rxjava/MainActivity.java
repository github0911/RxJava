package com.example.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button rxBtn;
    private TextView rxTv;

    private static final String TAG = "Rxjava";
    private static final String WEATHER_URL = "http://php.weather.sina.com.cn/xml.php?city=%s&password=DJOYnieT8234jlsK&day=0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rxBtn = (Button) findViewById(R.id.hello);
        rxTv = (TextView) findViewById(R.id.rx_tv);
        rxBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hello:
                helloRxjava();
                break;
        }
    }

    private void helloRxjava(){
        final Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted:");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError:");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext: " + s);
                rxTv.setText(s);
            }
        };

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>(){

            @Override
            public void call(Subscriber<? super String> subscriber) {
                observer.onNext("Hello ");
                observer.onNext("World!");
                observer.onCompleted();
            }
        });

        observable.subscribe(observer);
    }
}
