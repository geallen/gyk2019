package gykizmir.com.geziuygulamasi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Gamze on 4/21/2019.
 */

public class PostAdapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    List<Post> postListesi;

    public PostAdapter(LayoutInflater layoutInflater, List<Post> postListesi) {
        this.layoutInflater = layoutInflater;
        this.postListesi = postListesi;
    }

    @Override
    public int getCount() {
        return postListesi.size();
    }

    @Override
    public Object getItem(int position) {
        return postListesi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.each_post_item, null);
        ImageView sehirResmi = (ImageView) view.findViewById(R.id.post_picture);
        TextView sehirIsmi = (TextView) view.findViewById(R.id.post_city_name);
        TextView sehirAciklamasi = (TextView) view.findViewById(R.id.post_city_description);

        Post post = postListesi.get(position);
        sehirResmi.setImageResource(post.getPostImage());
        sehirIsmi.setText(post.getPostName());
        sehirAciklamasi.setText(post.getPostDescription());

        return view;
    }
}
