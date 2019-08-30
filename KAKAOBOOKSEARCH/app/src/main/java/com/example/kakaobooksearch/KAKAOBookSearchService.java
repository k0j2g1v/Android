package com.example.kakaobooksearch;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class KAKAOBookSearchService extends Service {

    // inner class 형태로 Thread를 생성하기 위한 Runnable interface를
    // 구현한 class를 정의
    private class BookSearchRunnable implements Runnable{
        private String keyword;

        BookSearchRunnable(String keyword){
            this.keyword = keyword;
        }
        @Override
        public void run() {
            // 전달된 keyword를 이용해서 network 처리(API 호출)
            String url = "https://dapi.kakao.com/v3/search/book?target=title";
            url = url + "&query=" + keyword;
            //  String url = "https://dapi.kakao.com/v3/search/book?target=title"&query="+ keyword;
            String myKey = "a397880c70cf4e6ab10084b6e7d93cd8";
            try {
                URL urlObj = new URL(url);
                // 네트워크 접속
                HttpURLConnection con = (HttpURLConnection)urlObj.openConnection();
                // request 방식을 지정
                con.setRequestMethod("GET");
                // 인증에대한 설정 key와 value의 쌍으로 들어가있다
                con.setRequestProperty("Authorization","KakaoAK " + myKey);
                // 정상적으로 설정을 하면 API 호출이 성공하고 결과를 받아 볼 수 있다.
                // 연결통로(stream)을 통해서 결과를 문자열로 얻어낸다.
                // 기본적인 Stream은 BufferedReader형태로 생성
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                // 데이터 통로로 부터 하나씩 읽어낸다
                // 통로안의 데이터를 하나의 문자열로 만든다
                String line = null;
                StringBuffer sb = new StringBuffer();
                while((line = br.readLine())!=null){
                    sb.append(line);
                }
                // 통로를 다 읽어 들였으니 통로(Stream)을 닫는다
                br.close();
                // 데이터가 JSON형태로 정상적으로 출력되면 외부 API호출 성공!
                Log.i("KAKAOBOOKLog",sb.toString());
                // Jackson library를 이용해서 JSON데이터를 처리
                // { documents : [] }
                ObjectMapper mapper = new ObjectMapper();
                Map<String,Object> map = mapper.readValue(sb.toString(),
                        new TypeReference<Map<String,Object>>() {
                });
                Object obj = map.get("documents");
                String resultJsonData = mapper.writeValueAsString(obj);
                Log.i("KAKAOBOOKLog",resultJsonData);
                // 결과적으로 우리가 얻은 데이터의 형태는
                // [ {책 1권의 데이터}, {책 1권의 데이터}, {책 1권의 데이터}, ....]
                // 책 1권의 데이터를 객체화 => KAKAOBookVO class 를 이용
                // 책 여러권의 데이터는 ArrayList로 표현
                // 책 1권의 데이터는 key와 value의 쌍으로 표현되고 있다.
                ArrayList<KAKAOBookVO> myObject = mapper.readValue(resultJsonData,
                        new TypeReference<ArrayList<KAKAOBookVO>>(){});

                // 정상적으로 객체화 되었는지를 확인
                for(KAKAOBookVO book : myObject){
                    Log.i("KAKAOBOOKLog",book.getTitle());
                }

                // 이미지데이터를 처리하기 위한 추가 코드
                // 책 표지 데이터는 문자열 URL로 되어 있는데 해당 URL에 접속해서
                // byte[]형태의 데이터로 추출해서 KAKAOBookVO에 Field를 추가해서
                // byte[]을 저장.
                for(KAKAOBookVO book : myObject) {
                    book.urlToByteArray();
                }

                // 정상적으로 객체화가 되었으면 intent를 해당 데이터를 붙여서
                // Activity에게 전달해야 한다
                Intent i = new Intent(getApplicationContext(),MainActivity.class);

                // 만약 Activity가 메모리에 존재하면 새로 생성하지 않고 기존
                // Activity를 이용
                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                // 전달할 데이터를 intent에 붙인다
                // parcelable interface를 구현한 객체를 붙이기 위해서
                // method를 다른 method로 교체
                i.putParcelableArrayListExtra("resultData",myObject);
                //i.putExtra("resultData",myObject);

                //Activity에게 데이터를 전달
                startActivity(i);

            }catch (Exception e){
                Log.i("KAKAOBOOKLog",e.toString());
            }
        }
    }


    public KAKAOBookSearchService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");

    }

    @Override
    public void onCreate() {
        // 서비스 객체가 만들어지는 시점에 1번 호출
        // 사용할 resource를 준비하는 과정
        Log.i("KAKAOBOOKLog","onCreate가 호출됬어요");
        super.onCreate();
    }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            // onCreate()후에 자동적으로 호출되며
            // startService()에 의해서 호출된다!
            // 실제 로직처리는 onStartCommand에서 진행
            Log.i("KAKAOBOOKLog","onStartCommand가 호출됬어요");
            // 전달된 키워드를 이용해서 외부 네트워크 접속을 위한
            // Thread를 하나 생성해야 한다!
            String keyword = intent.getExtras().getString("searchKeyword");
        // Thread를 만들기 위한 Runnable 액체부터 생성
        BookSearchRunnable runnable = new BookSearchRunnable(keyword);
        Thread t = new Thread(runnable);
        t.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        // 서비스 객체가 메모리상에서 삭제될 때 1번 호출
        // 사용한 resource를 정리하는 과정.
        Log.i("KAKAOBOOKLog","onDestory가 호출됬어요");
        super.onDestroy();
    }
}
