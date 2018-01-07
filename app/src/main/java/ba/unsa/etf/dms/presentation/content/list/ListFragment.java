package ba.unsa.etf.dms.presentation.content.list;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ba.unsa.etf.dms.R;
import ba.unsa.etf.dms.data.content.ContentResponse;
import ba.unsa.etf.dms.presentation.content.ListType;
import ba.unsa.etf.dms.presentation.content.list.host.WorkspaceActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends Fragment implements WorkspaceActivity.ListFragmentInterface {

    @BindView(R.id.content_list) RecyclerView contentList;

    private ContentAdapter contentAdapter;
    private List<ContentResponse> contentResponseList = new ArrayList<>();

    public static final String LIST_TYPE = "LIST_TYPE";

    private ListType type;

    private ContentListViewModel viewModel;

    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance(ListType listType) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putSerializable(LIST_TYPE, listType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);

        if (getArguments() != null) {
            type = (ListType) getArguments().getSerializable(LIST_TYPE);
        }

        contentList.setHasFixedSize(true);
        GridLayoutManager mLayoutManager = new GridLayoutManager(view.getContext(), 2);
        contentList.setLayoutManager(mLayoutManager);
        contentAdapter = new ContentAdapter(contentResponseList);
        contentList.setAdapter(contentAdapter);

        viewModel = new ContentListViewModel(this, type);

        return view;
    }

    public void updateList(List<ContentResponse> contentResponses) {
        contentResponseList.clear();
        contentResponseList.addAll(contentResponses);
        contentAdapter.notifyDataSetChanged();
    }

    public void showError(String errorText) {
        if (getView() == null) {
            return;
        }
        Toast.makeText(getView().getContext(), errorText, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void uploadFile(Uri file) {
        viewModel.uploadFile(file);
    }
}
