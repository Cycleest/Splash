package cycleer.splash;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import cycleer.splash.widget.RainbowAdapter;

public class HomeActivity extends Activity implements AdapterView.OnItemClickListener {

    private int focusedItemPosition;
    private final int NO_FOCUS = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new RainbowAdapter(this, 100));
        listView.setOnItemClickListener(this);
        if (savedInstanceState != null && savedInstanceState.containsKey("focusedItemPosition")) {
            focusedItemPosition = savedInstanceState.getInt("focusedItemPosition");
            showDialog();
        } else {
            focusedItemPosition = NO_FOCUS;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        if (focusedItemPosition != NO_FOCUS) {
            savedInstanceState.putInt("focusedItemPosition", focusedItemPosition);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        focusedItemPosition = position;
        showDialog();
    }

    private void showDialog() {
        final Dialog dialog;
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_list_item_onclick);
        dialog.setTitle(getString(R.string.dialog_list_rainbow_title));
        TextView text = (TextView) dialog.findViewById(R.id.text);
        text.setText(getString(R.string.dialog_list_rainbow_text) + (focusedItemPosition + 1));
        Button dialogButton = (Button) dialog.findViewById(R.id.dismissButton);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                focusedItemPosition = NO_FOCUS;
                dialog.dismiss();
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                focusedItemPosition = NO_FOCUS;
            }
        });
        dialog.show();
    }
}
