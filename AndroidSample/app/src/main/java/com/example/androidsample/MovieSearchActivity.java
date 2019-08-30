package com.example.androidsample;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class SearchMoiveRunnable implements Runnable{
    private String keyword;
    private Handler handler;

    SearchMoiveRunnable(Handler handler, String keyword){
        this.handler = handler;
        this.keyword = keyword;
    }
    @Override
    public void run(){
        // keyword를 이용해서 web program에 접속한후 결과를 받아온다
        // 결과로 받아온 JSON문자열을 이용해서 Listview에 출력해야한다.
        // 그런데 여기서는 Listview를 제어할수 없다
        // Handler를 이용해서 UI Thread에 Listview에 사용할 데이터를 넘긴다.
        String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=b664097c21ff240dd2ee9dd27d0b108f&targetDt=20190801"
                + keyword;
        // Network code는 예외처리가 필요
        try{
            URL urlObj = new URL(url);
            HttpURLConnection con = (HttpURLConnection)urlObj.openConnection();

            // network연결이 성공한 후 데이터를 읽어들이기 위한 데이터 연결통로
            // Stream을 생성
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String input = "";
            StringBuffer sb = new StringBuffer();
            while((input = br.readLine()) != null){
                sb.append(input);
            }
            Log.i("DATA",sb.toString());
            // 얻어온 결과 JSON 문자열을 Jackson Library를 이용해서
            // Java 객체형태(String[])로 변형
//            ObjectMapper mapper = new ObjectMapper();
//            // Jackson Library를 이용하여 JSON문자열을 String[] 형태로 변환
//            String[] resultArr = mapper.readValue(sb.toString(),String[].class);

            Bundle bundle = new Bundle();
            bundle.putString("BOOKARRAY",sb.toString());
            Message msg = new Message();
            msg.setData(bundle);
            handler.sendMessage(msg);

            Log.i("DATA",sb.toString());
        }catch(Exception e){
            Log.e("DATAError",e.toString());
        }
    }
}


public class MovieSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_search);

        Button searchBtn = (Button) findViewById(R.id.searchBookBtn);
        final EditText keywordEt = (EditText) findViewById(R.id.keywordEt);

        final ListView lv = (ListView) findViewById(R.id.lv);



    }
}
