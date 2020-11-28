package br.usjt.ads20.universities.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.usjt.ads20.universities.R;
import br.usjt.ads20.universities.model.Data;
import br.usjt.ads20.universities.model.University;
import br.usjt.ads20.universities.presenter.ListUniversitiesPresenter;

/**
 * Nome: Mateus Santos Carvalho
 * RA: 818229525
 */

public class ListUniversities extends AppCompatActivity implements ListUniversitiesView{
    public static final String UNIVERSITY = "br.usjt.ads20.universities.description";

    public University[] universityList;

    private Activity activity;

    ListUniversitiesPresenter presenter = new ListUniversitiesPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_universities);
        activity = this;

        final Intent intent = getIntent();

        String key = intent.getStringExtra(MainActivity.NAME);

        ArrayList<University> universities = (ArrayList<University>) intent.getSerializableExtra(MainActivity.UNIVERSITY);


        Data.setUniversities(universities);


        universityList = presenter.searchUniversities(universities, key);

        BaseAdapter adapter = new UniversityAdapter(this, universityList);

        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Uri uri = Uri.parse(universityList[position].getWebPage());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                startActivity(intent);
            }
        });
    }

    @Override
    public ArrayList<University> loadUniversities() {
        return null;
    }
}