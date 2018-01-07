package ba.unsa.etf.dms.presentation.content.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ba.unsa.etf.dms.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hugsby on 1/2/18.
 */

public class ContentViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.content_title)
    TextView title;
    @BindView(R.id.content_image)
    ImageView image;
    @BindView(R.id.dots)
    ImageView menu;

    public ContentViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
