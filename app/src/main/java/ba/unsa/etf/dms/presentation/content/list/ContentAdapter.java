package ba.unsa.etf.dms.presentation.content.list;

import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ba.unsa.etf.dms.R;
import ba.unsa.etf.dms.data.content.ContentResponse;
import ba.unsa.etf.dms.data.content.DeleteDocumentExecutor;

/**
 * Created by Hugsby on 1/2/18.
 */

public class ContentAdapter extends RecyclerView.Adapter<ContentViewHolder> {

    private List<ContentResponse> contentList;
    private ContentListViewModel contentListViewModel;

    public ContentAdapter(List<ContentResponse> contentList, ContentListViewModel contentListViewModel) {
        this.contentList = contentList;
        this.contentListViewModel = contentListViewModel;
    }

    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View thisItemsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_list_element, parent, false);
        return new ContentViewHolder(thisItemsView);
    }

    @Override
    public void onBindViewHolder(final ContentViewHolder holder, final int position) {
        final ContentResponse contentResponse = contentList.get(position);
        holder.title.setText(contentResponse.getFileName());
        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                switch (view.getId()) {
                    case R.id.dots:

                        PopupMenu popup = new PopupMenu(view.getContext(), view);
                        popup.getMenuInflater().inflate(R.menu.list_menu,
                                popup.getMenu());
                        popup.show();
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {

                                switch (item.getItemId()) {
                                    case R.id.rename:
                                        //Or Some other code you want to put here.. This is just an example.
                                        break;
                                    case R.id.delete:
                                        new DeleteDocumentExecutor(contentListViewModel, "elza", contentResponse.getId());
                                        break;

                                    default:
                                        break;
                                }

                                return true;
                            }
                        });
                        break;

                    default:
                        break;
                }
            }
        });

    }


        @Override
        public int getItemCount () {
            return contentList.size();
        }
    }
