package cycleer.splash.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cycleer.splash.R;

public class RainbowAdapter extends BaseAdapter implements View.OnClickListener {

    private LayoutInflater inflater;
    private int itemsCount;
    private Context context;
    private String textBase;
    private int colorOrange;
    private int colorPurple;
    private int period;
    private final Dialog dialog;
    private int selection;

    public final int NO_SELECTIONS = -1;

    public RainbowAdapter(Context context, int itemsCount) {
        this.itemsCount = itemsCount;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        textBase = context.getResources().getString(R.string.list_item_base);
        colorOrange = context.getResources().getColor(R.color.orange);
        colorPurple = context.getResources().getColor(R.color.purple);
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_list_item_onclick);
        dialog.setTitle(context.getString(R.string.dialog_list_rainbow_title));
        period = 8;
        selection = -1;
    }

    @Override
    public int getCount() {
        return itemsCount;
    }

    @Override
    public Object getItem(int position) {
        return textBase + String.valueOf(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_rainbow, null);
            convertView.setOnClickListener(this);
        }
        int localPosition = position % period;
        ImageView img = (ImageView) convertView.findViewById(R.id.colourfulPart);
        img.setImageDrawable(localPosition < 7 ? context.getResources().getDrawable(R.drawable.shape_round) : null);
        switch (localPosition) {
            case 0:
                img.setColorFilter(Color.RED);
                break;
            case 1:
                img.setColorFilter(colorOrange);
                break;
            case 2:
                img.setColorFilter(Color.YELLOW);
                break;
            case 3:
                img.setColorFilter(Color.GREEN);
                break;
            case 4:
                img.setColorFilter(Color.CYAN);
                break;
            case 5:
                img.setColorFilter(Color.BLUE);
                break;
            case 6:
                img.setColorFilter(colorPurple);
        }

        ((TextView) convertView.findViewById(R.id.text)).setText(textBase + "_" + String.valueOf(position));
        ((TextView) convertView.findViewById(R.id.counter)).setText(String.valueOf(position + 1));

        return convertView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public void onClick(View v) {
        TextView text = (TextView) dialog.findViewById(R.id.text);
        text.setText(context.getString(R.string.dialog_list_rainbow_text) + ((TextView) v.findViewById(R.id.counter)).getText());
        Button dialogButton = (Button) dialog.findViewById(R.id.dismissButton);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selection = NO_SELECTIONS;
                dialog.dismiss();
            }
        });
        selection = Integer.parseInt(String.valueOf(((TextView) v.findViewById(R.id.counter)).getText()));
        dialog.show();
    }

    public void setSelection(int number) throws IndexOutOfBoundsException{
        if(number < NO_SELECTIONS || number > itemsCount){
            throw new IndexOutOfBoundsException();
        }
        else {
            onClick(getView(number - 1, null, null));
        }
    }

    public int getSelection() {
        return selection;
    }
}