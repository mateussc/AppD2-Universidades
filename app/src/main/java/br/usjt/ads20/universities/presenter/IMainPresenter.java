package br.usjt.ads20.universities.presenter;

interface IMainPresenter {
    void onCreate();

    void onStart();

    void onRestart();

    void onPause();

    void onResume();

    void onStop();

    void onDestroy();
}