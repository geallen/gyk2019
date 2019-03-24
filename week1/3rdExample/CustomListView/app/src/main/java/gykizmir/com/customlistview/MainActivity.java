package gykizmir.com.customlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<Kisi> personList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.liste);

        personList.add(new Kisi("Gamze Sen", "11.06.2017", R.drawable.hurriyet));
        personList.add(new Kisi("Ayhan Sener", "15.06.2017",R.drawable.sabah));
        personList.add(new Kisi("Tugce Sen", "12.06.2017", R.drawable.sabah));
        personList.add(new Kisi("Ayse Sen", "13.06.2017", R.drawable.sabah));
        personList.add(new Kisi("Aynur Sen", "29.06.2017", R.drawable.sabah));
        personList.add(new Kisi("Ali Sener", "30.06.2017", R.drawable.sabah));
        personList.add(new Kisi("Aysen Sener", "23.06.2017", R.drawable.sabah));
        personList.add(new Kisi("Aysem Sencan", "17.06.2017", R.drawable.sabah));

        KisiAdapter kisiAdapter = new KisiAdapter(personList, this);
        listView.setAdapter(kisiAdapter);


    }
}
