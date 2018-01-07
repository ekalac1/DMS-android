package ba.unsa.etf.dms.presentation.content.list;

import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import ba.unsa.etf.dms.data.Utils;
import ba.unsa.etf.dms.data.content.ContentDTO;
import ba.unsa.etf.dms.data.content.ContentResponse;
import ba.unsa.etf.dms.data.content.SaveDocumentExcecutor;
import ba.unsa.etf.dms.data.content.UserProfileExecutor;
import ba.unsa.etf.dms.data.content.UserWorkspaceExecutor;
import ba.unsa.etf.dms.presentation.BaseLoggedActivity;
import ba.unsa.etf.dms.presentation.DocumentCallback;
import ba.unsa.etf.dms.presentation.content.ListType;

import static android.content.Context.MODE_PRIVATE;
import static ba.unsa.etf.dms.presentation.BaseLoggedActivity.USERNAME_CONST;

/**
 * Created by Hugsby on 1/2/18.
 */

public class ContentListViewModel implements ContentListInterface.ViewModel,
        DocumentCallback,
        SaveDocumentExcecutor.DocumentExecutorInterface {

    private ListFragment parent;
    private ListType type;

    public ContentListViewModel(ListFragment workspaceActivity, ListType type) {
        this.parent = workspaceActivity;
        this.type = type;

        if (type == ListType.PROFILE) {
            new UserProfileExecutor(this, parent.getContext(), this);
        } else {
            new UserWorkspaceExecutor(this, parent.getContext(), this);
        }


    }

    @Override
    public void onSaveSuccess() {
        if (type == ListType.PROFILE) {
            new UserProfileExecutor(this, parent.getContext(), this);
        } else {
            new UserWorkspaceExecutor(this, parent.getContext(), this);
        }
    }


    @Override
    public void onSuccess(List<ContentResponse> response) {
        parent.updateList(response);
    }

    @Override
    public void onError(String errorMessage) {
        parent.showError(errorMessage);
    }

    @Override
    public void onNetworkError(String errorMessage) {
        parent.showError(errorMessage);
    }

    public void uploadFile(Uri uri) {

        File file;
        String path;

        if (uri != null) {
            path = uri.getPath();
            if (path != null) {
                file = new File(path);
                ContentDTO contentDTO = new ContentDTO();
                contentDTO.setFileName(file.getName());
                contentDTO.setDatatype(Utils.getMimeType(Uri.fromFile(file).toString()));

                String text = readTextFromUri(uri);

                String base64 = convertByteToString(text);
                SharedPreferences settings = parent.getActivity().getSharedPreferences(BaseLoggedActivity.USERNAME_CONST, MODE_PRIVATE);
                contentDTO.setContent(base64);

                contentDTO.setOwner(settings.getString(USERNAME_CONST, ""));

                new SaveDocumentExcecutor(this, contentDTO);

            } else {
                onError("Could not load file");
            }
        }


    }

    private String convertByteToString(String data) {

        String base64 = "";
        try {
            base64 = Base64.encodeToString(data.getBytes(), Base64.DEFAULT);
        } catch (Exception e) {
            Log.e("byte to string base64", e.toString());
        }
        return base64;
    }

    private String readTextFromUri(Uri uri) {
        InputStream inputStream = null;
        try {
            inputStream = parent.getContext().getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = null;
        StringBuilder stringBuilder = new StringBuilder();
        if (inputStream != null) {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return stringBuilder.toString();
    }
}
