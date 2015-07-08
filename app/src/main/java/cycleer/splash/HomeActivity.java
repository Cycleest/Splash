package cycleer.splash;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import cycleer.splash.widget.RainbowAdapter;

public class HomeActivity extends Activity {

    RainbowAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new RainbowAdapter(this, 100);
        listView.setAdapter(adapter);
        if(savedInstanceState != null && savedInstanceState.containsKey("selection")){
            adapter.setSelection(savedInstanceState.getInt("selection"));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        int listSelection = adapter.getSelection();
        if(listSelection != -1) {
            savedInstanceState.putInt("selection", listSelection);
        }
    }
}
