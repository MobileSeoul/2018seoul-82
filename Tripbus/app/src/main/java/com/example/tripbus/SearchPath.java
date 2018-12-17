package com.example.tripbus;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.SearchView;
import android.widget.SearchView.OnCloseListener;
import android.widget.SearchView.OnQueryTextListener;
import java.util.ArrayList;
import java.util.List;

public class SearchPath extends AppCompatActivity {
//
//
//    List<String> list;          // 데이터를 넣은 리스트변수
//    searchView searchView;
////    ListView listView;          // 검색을 보여줄 리스트변수
////    EditText editSearch;        // 검색어를 입력할 Input 창
//    SearchAdapter adapter;      // 리스트뷰에 연결할 아답터
//    ArrayList<String> arraylist;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_search_path);
//
//    searchView(searchView) findViewById(R.id.searchView)
////    editSearch = (EditText) findViewById(R.id.searchView);
////    listView = (ListView) findViewById(R.id.listView);
//
//    // 리스트를 생성한다.
//    list = new ArrayList<String>();
//
//    // 검색에 사용할 데이터을 미리 저장한다.
//    settingList();
//
//    // 리스트의 모든 데이터를 arraylist에 복사한다.// list 복사본을 만든다.
//    arraylist = new ArrayList<String>();
//            arraylist.addAll(list);
//
//    // 리스트에 연동될 아답터를 생성한다.
//    adapter = new SearchAdapter(list, this);
//
//    // 리스트뷰에 아답터를 연결한다.
//        searchView.setAdapter(adapter);
//
//    // input창에 검색어를 입력시 "addTextChangedListener" 이벤트 리스너를 정의한다.
//            editSearch.addTextChangedListener(new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//        }
//
//        @Override
//        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//        }
//
//        @Override
//        public void afterTextChanged(Editable editable) {
//            // input창에 문자를 입력할때마다 호출된다.
//            // search 메소드를 호출한다.
//            String text = editSearch.getText().toString();
//            search(text);
//        }
//    });
//
//
//}
//
//    // 검색을 수행하는 메소드
//    public void search (String charText){
//
//        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
//        list.clear();
//
//        // 문자 입력이 없을때는 모든 데이터를 보여준다.
//        if (charText.length() == 0) {
//            list.addAll(arraylist);
//        }
//        // 문자 입력을 할때..
//        else {
//            // 리스트의 모든 데이터를 검색한다.
//            for (int i = 0; i < arraylist.size(); i++) {
//                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
//                if (arraylist.get(i).toLowerCase().contains(charText)) {
//                    // 검색된 데이터를 리스트에 추가한다.
//                    list.add(arraylist.get(i));
//                }
//            }
//        }
//        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
//        adapter.notifyDataSetChanged();
//    }
//
//    // 검색에 사용될 데이터를 리스트에 추가한다.
//    private void settingList () {
//        list.add("채수빈");
//        list.add("박지현");
//        list.add("수지");
//        list.add("남태현");
//        list.add("하성운");
//        list.add("크리스탈");
//        list.add("강승윤");
//        list.add("손나은");
//        list.add("남주혁");
//        list.add("루이");
//        list.add("진영");
//        list.add("슬기");
//        list.add("이해인");
//        list.add("고원희");
//        list.add("설리");
//        list.add("공명");
//        list.add("김예림");
//        list.add("혜리");
//        list.add("웬디");
//        list.add("박혜수");
//        list.add("카이");
//        list.add("진세연");
//        list.add("동호");
//        list.add("박세완");
//        list.add("도희");
//        list.add("창모");
//        list.add("허영지");
//    }                 //검색바의 리스트뷰를 넣으면 화면 다 밀려서 주석처리해놓음
//interface OnQueryTextListenerCompatBridge {
//    public boolean onQueryTextSubmit(String query);
//    public boolean onQueryTextChange(String newText);
//}
//
//    interface OnCloseListenerCompatBridge {
//        public boolean onClose();
//    }
//
//    public static View newSearchView(Context context) {
//        return new SearchView(context);
//    }
//
//    public static void setSearchableInfo(View searchView, ComponentName searchableComponent) {
//        SearchView sv = ((SearchView) searchView);
//        SearchManager searchManager = (SearchManager)
//                sv.getContext().getSystemService(Context.SEARCH_SERVICE);
//        sv.setSearchableInfo(searchManager.getSearchableInfo(searchableComponent));
//    }
//
//    public static Object newOnQueryTextListener(final OnQueryTextListener listener) {
//        return new OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return listener.onQueryTextSubmit(query);
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return listener.onQueryTextChange(newText);
//            }
//        };
//    }     //검색바 기능구현에 onQueryTextListener사용 시도함


    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_path);

        searchView=(SearchView) findViewById(R.id.searchView);
        searchView.setQueryHint("Search View");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getBaseContext(), query, Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getBaseContext(), newText, Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }


//    @Override

    {ImageButton button1 = (ImageButton) findViewById(R.id.rcmnd_nature);
            button1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "자연적 노선", Toast.LENGTH_LONG).show();

                    // 액티비티 전환 코드
                    Intent intent = new Intent(getApplicationContext(), PathNature.class);
                    startActivity(intent);
                }
            });

            ImageButton button2 = (ImageButton) findViewById(R.id.rcmnd_night);
            button2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "야경 노선", Toast.LENGTH_LONG).show();

                    // 액티비티 전환 코드
                    Intent intent = new Intent(getApplicationContext(), PathNight.class);
                    startActivity(intent);
                }
            });

            ImageButton button3 = (ImageButton) findViewById(R.id.rcmnd_culture);
            button3.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "유적지 노선", Toast.LENGTH_LONG).show();

                    // 액티비티 전환 코드
                    Intent intent = new Intent(getApplicationContext(), PathCulture.class);
                    startActivity(intent);
                }
            });

            ImageButton button4 = (ImageButton) findViewById(R.id.rcmnd_downtown);
            button4.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "번화가 노선", Toast.LENGTH_LONG).show();

                    // 액티비티 전환 코드
                    Intent intent = new Intent(getApplicationContext(), PathDowntown.class);
                    startActivity(intent);
                }
            });

        }
    }

