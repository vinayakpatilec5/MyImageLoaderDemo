package com.vinayakpatilec5.admin.myimageloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setList();

    }

    private void setList(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        DemoAdapter mAdapter = new DemoAdapter(getDummyData());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    private List<AudioData> getDummyData(){
        List<AudioData> audioDataList = new ArrayList<>();
        audioDataList.add(new AudioData("https://is5-ssl.mzstatic.com/image/thumb/Music114/v4/19/16/8b/19168bca-514a-32d3-8a4a-09498203f69a/source/100x100bb.jpg","Discothèque","U2"));
        audioDataList.add(new AudioData("https://is2-ssl.mzstatic.com/image/thumb/Music123/v4/e9/ee/ce/e9eece41-c683-040a-544e-3ce7188a8465/source/100x100bb.jpg","The Hands That Built America","U2"));
        audioDataList.add(new AudioData("https://is5-ssl.mzstatic.com/image/thumb/Music114/v4/05/fd/eb/05fdeb7d-4610-ce1b-dc60-807dfd4d153f/source/100x100bb.jpg","Talk Of The Town","Jack Johnson"));
        audioDataList.add(new AudioData("https://is5-ssl.mzstatic.com/image/thumb/Music114/v4/05/fd/eb/05fdeb7d-4610-ce1b-dc60-807dfd4d153f/source/100x100bb.jpg","Lullaby (feat. Matt Costa)","Jack Johnson"));
        audioDataList.add(new AudioData("https://is5-ssl.mzstatic.com/image/thumb/Music124/v4/05/59/c1/0559c1d7-2800-7c1e-9620-04063cd96ea5/source/100x100bb.jpg","Breakdown","Jack Johnson"));
        audioDataList.add(new AudioData("https://is5-ssl.mzstatic.com/image/thumb/Music124/v4/05/59/c1/0559c1d7-2800-7c1e-9620-04063cd96ea5/source/100x100bb.jpg","Constellations (feat. Kaukahi)","Jack Johnson"));
        audioDataList.add(new AudioData("https://is5-ssl.mzstatic.com/image/thumb/Music124/v4/05/59/c1/0559c1d7-2800-7c1e-9620-04063cd96ea5/source/100x100bb.jpg","Girl, I Wanna Lay You Down (feat. Jack Johnson)","ALO"));
        audioDataList.add(new AudioData("https://is2-ssl.mzstatic.com/image/thumb/Music123/v4/e9/ee/ce/e9eece41-c683-040a-544e-3ce7188a8465/source/100x100bb.jpg","The Hands That Built America","U2"));
        audioDataList.add(new AudioData("https://is5-ssl.mzstatic.com/image/thumb/Music114/v4/05/fd/eb/05fdeb7d-4610-ce1b-dc60-807dfd4d153f/source/100x100bb.jpg","Talk Of The Town","Jack Johnson"));
        audioDataList.add(new AudioData("https://is5-ssl.mzstatic.com/image/thumb/Music114/v4/05/fd/eb/05fdeb7d-4610-ce1b-dc60-807dfd4d153f/source/100x100bb.jpg","Lullaby (feat. Matt Costa)","Jack Johnson"));
        audioDataList.add(new AudioData("https://is5-ssl.mzstatic.com/image/thumb/Music124/v4/05/59/c1/0559c1d7-2800-7c1e-9620-04063cd96ea5/source/100x100bb.jpg","Breakdown","Jack Johnson"));
        audioDataList.add(new AudioData("https://is5-ssl.mzstatic.com/image/thumb/Music124/v4/05/59/c1/0559c1d7-2800-7c1e-9620-04063cd96ea5/source/100x100bb.jpg","Constellations (feat. Kaukahi)","Jack Johnson"));
        return audioDataList;

    }
}
