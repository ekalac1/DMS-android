package ba.unsa.etf.dms.presentation.content.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ba.unsa.etf.dms.R;
import ba.unsa.etf.dms.data.content.ContentResponse;

/**
 * Created by Hugsby on 1/2/18.
 */

public class ContentAdapter extends RecyclerView.Adapter<ContentViewHolder> {

    private List<ContentResponse> contentList;

    public ContentAdapter(List<ContentResponse> contentList) {
        this.contentList = contentList;
    }

    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View thisItemsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_list_element, parent, false);
        return new ContentViewHolder(thisItemsView);
    }

    @Override
    public void onBindViewHolder(ContentViewHolder holder, int position) {
        ContentResponse contentResponse= contentList.get(position);
        holder.title.setText(contentResponse.getFileName());
    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }
}
