package br.usjt.ads20.universities.presenter;

import java.util.ArrayList;

import br.usjt.ads20.universities.model.Data;
import br.usjt.ads20.universities.model.University;
import br.usjt.ads20.universities.view.ListUniversities;

public class ListUniversitiesPresenter implements IMainPresenter {

    ListUniversities view;
    University[] list;

    public ListUniversitiesPresenter(ListUniversities view) {
        this.view = view;

    }

    public University[] searchUniversities(ArrayList<University> universities, String key) {
        Data.setUniversities(universities);
        list = Data.searchUniversities(key);
        return list;
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }
}