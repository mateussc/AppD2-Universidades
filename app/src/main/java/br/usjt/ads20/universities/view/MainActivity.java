package br.usjt.ads20.universities.view;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.ads20.universities.R;
import br.usjt.ads20.universities.model.University;
import br.usjt.ads20.universities.model.UniversityNetwork;
import br.usjt.ads20.universities.presenter.MainPresenter;
import br.usjt.ads20.universities.view.ListUniversities;

/**
 * Nome: Mateus Santos Carvalho
 * RA: 818229525
 */

public class MainActivity extends AppCompatActivity implements MainActivityView {
    private EditText txtName;
    private ProgressBar progressBar;
    public  static final String NAME = "br.usjt.ads20.universities.name";
    public  static final String UNIVERSITY = "br.usjt.ads20.universities.university";
    private String url = "http://universities.hipolabs.com/search?name=";
    private Context context;

    MainPresenter presenter = new MainPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtName = findViewById(R.id.search_university);
        progressBar = findViewById(R.id.progressBarMain);
        context = this;
        presenter.onCreate();
    }

    public void searchUniversities(View view) {
        if (UniversityNetwork.isConnected(this)) {
            progressBar.setVisibility(View.VISIBLE);
            new DownloadJsonUniversities().execute(String.format(url + txtName.getText()));
        } else {
            String msg = this.getResources().getString(R.string.networkError);
            Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private class DownloadJsonUniversities extends AsyncTask<String, Void, ArrayList<University>> {

        @Override
        protected ArrayList<University> doInBackground(String... strings) {
            ArrayList<University> university = new ArrayList<>();
            try {
                university = UniversityNetwork.searchUniversities(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return university;
        }

        protected void onPostExecute(ArrayList<University> university){
            if (txtName.getText().length() > 0) {
                Intent intent = new Intent(context, ListUniversities.class);
                String nome = txtName.getText().toString();
                intent.putExtra(NAME, nome);
                intent.putExtra(UNIVERSITY, university);
                progressBar.setVisibility(View.INVISIBLE);
                startActivity(intent);
            }
            else progressBar.setVisibility(View.INVISIBLE);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        presenter.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }
}