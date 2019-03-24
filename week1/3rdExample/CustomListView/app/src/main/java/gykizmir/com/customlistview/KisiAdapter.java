package gykizmir.com.customlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Gamze on 3/17/2019.
 */

public class KisiAdapter extends BaseAdapter {

    private List<Kisi> kisiList;
    private Context context;
    private LayoutInflater layoutInflater;

    public KisiAdapter(List<Kisi> kisiList, Context context) {
        this.kisiList = kisiList;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.kisiList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.kisiList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View resultView;

        resultView = layoutInflater.inflate(R.layout.each_item, null);

        TextView twName = (TextView) resultView.findViewById(R.id.adSoyad);
        TextView twDate = (TextView) resultView.findViewById(R.id.tarih);
        ImageView img = (ImageView) resultView.findViewById(R.id.resim);

        Kisi kisi1 = (Kisi) getItem(position);
        twName.setText(kisi1.getIsim());
        twDate.setText(kisi1.getTarih());
        img.setImageResource(kisi1.getResim());

        return resultView;
    }
}
