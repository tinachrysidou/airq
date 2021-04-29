package com.example.airq.ui.logs;



import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class LogsViewModel extends ViewModel {
    private MutableLiveData<List<logs>> send;
    private List<logs> mData;
    private List<logs> mDataOrdered;

    private logs log;
    private int pos;

    public LogsViewModel() {
        send = new MutableLiveData<>();
        mData = new ArrayList<>();
        mDataOrdered = new ArrayList<>();


    }
    public LiveData<List<logs>> getData() {
        return send;
    }

    public void setView(int pos){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference docRef_george = db.collection("logg");
        CollectionReference docRef_tina = db.collection("logt");

        if (pos == 0) {
            mData.clear();
            mDataOrdered.clear();
            docRef_tina
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
//                                            Log.d(TAG, document.getId() + " => " + document.getData());

                                    log = new logs();
                                    Set<Map.Entry<String, Object>> entry = document.getData().entrySet();
                                    log.setTime(document.getId());
                                    log.setData(entry.toArray()[0].toString().substring(11));
                                    mData.add(log);


                                }
                                for(int i=mData.size()-1; i>=0; i--){
                                    mDataOrdered.add(mData.get(i));
                                }
                                send.setValue(mDataOrdered);

                            } else {
//                                        Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    });

        } else {


            mData.clear();
            mDataOrdered.clear();

            docRef_george
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
//                                            Log.d(TAG, document.getId() + " => " + document.getData());

                                    log = new logs();
                                    Set<Map.Entry<String, Object>> entry = document.getData().entrySet();
                                    log.setTime(document.getId());
                                    log.setData(entry.toArray()[0].toString().substring(11));
                                    mData.add(log);


                                }
                                for(int i=mData.size()-1; i>=0; i--){
                                    mDataOrdered.add(mData.get(i));
                                }

                                send.setValue(mDataOrdered);

                            } else {
//                                        Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    });

        }
    }
}
