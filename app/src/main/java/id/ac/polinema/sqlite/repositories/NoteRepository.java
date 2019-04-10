package id.ac.polinema.sqlite.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import id.ac.polinema.sqlite.dao.AppDatabase;
import id.ac.polinema.sqlite.dao.NoteDao;
import id.ac.polinema.sqlite.models.Note;

public class NoteRepository {
    private NoteDao noteDao;

    private LiveData<List<Note>> notes;

    public NoteRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        noteDao = db.noteDao();
        notes = noteDao.getAll();
    }

    public LiveData<List<Note>> getNotes() {
        return notes;
    }

    public void insert (Note note){
        new InsertAsyncTask(noteDao).execute(note);
    }

    public void update(Note note){
        new UpdateAsynsTask(noteDao).execute(note);

    }

    private static class InsertAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao asyncTaskDao;

        InsertAsyncTask(NoteDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            asyncTaskDao.insert(notes);
            return null;
        }
    }

    private static class UpdateAsynsTask extends AsyncTask<Note, Void, Void>{
        private NoteDao asyncTaskDao;

        public UpdateAsynsTask(NoteDao noteDao){
            asyncTaskDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            asyncTaskDao.delete(notes);
            return null;
        }
    }
}
