package com.example.airq.ui.logs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.airq.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;

import java.util.List;

public class LogsFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private LogsViewModel logsViewModel;
    private View root;
    private int position ;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logsViewModel = new ViewModelProvider(this).get(LogsViewModel.class);
        root = inflater.inflate(R.layout.logs_fragment, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.logs_list);
        position = 0;
        logsViewModel.setView(position);
        recyclerView = (RecyclerView) root.findViewById(R.id.logs_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));


        TabLayout tabLayout = (TabLayout) root.findViewById(R.id.logs_tabLayout);


        tabLayout.addOnTabSelectedListener(new OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();
                logsViewModel.setView(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                logsViewModel.setView(position);
            }
        });


        logsViewModel.getData().observe(getViewLifecycleOwner(), new Observer<List<logs>>() {
            @Override
            public void onChanged(List<logs> logs) {
                mAdapter = new LogsAdapter(root.getContext(),logs);
                recyclerView.setAdapter(mAdapter);
            }
        });

    return root;
    }

}
