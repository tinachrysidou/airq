package com.example.airq.ui.george;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Map;

public class GeorgeViewModel extends ViewModel {
    private MutableLiveData<String> url;
    private MutableLiveData<String> url_light;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference doc;
    private MutableLiveData<String> pred;
    private MutableLiveData<String> knn;
    private MutableLiveData<String> svm;
    private Map<String, Object> pre;


    public GeorgeViewModel() {
        url = new MutableLiveData<>();
        url_light = new MutableLiveData<>();
        knn = new MutableLiveData<>();
        pred = new MutableLiveData<>();
        svm = new MutableLiveData<>();

        url.setValue("https://grafana.airq.tech/d/F280YdtMz/george-mobile?orgId=1&refresh=5s&from=now-1h&to=now&kiosk&theme=dark");
        url_light.setValue("https://grafana.airq.tech/d/F280YdtMz/george-mobile?orgId=1&refresh=5s&from=now-1h&to=now&kiosk&theme=light");

        doc = db.collection("prediction").document("george");
        doc.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot != null && documentSnapshot.exists()) {

                    pre = documentSnapshot.getData();
                    for (Map.Entry<String, Object> entry : pre.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();

                        if (key.equals("prediction")){
                            pred.setValue((String)value);
                        }else if (key.equals("knn")){
                            knn.setValue((String)value);
                        }else{
                            svm.setValue((String)value);
                        }

                    }
                }
            }
        });
    }

    public LiveData<String> getUrl() {
        return url;
    }
    public LiveData<String> getLight() {
        return url_light;
    }

    public LiveData<String> getPre(){
        return pred;
    }
    public LiveData<String> getKnn(){
        return knn;
    }
    public LiveData<String> getSvm(){
        return svm;
    }
}
