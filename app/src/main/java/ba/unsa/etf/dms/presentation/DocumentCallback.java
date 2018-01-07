package ba.unsa.etf.dms.presentation;

import java.util.List;

import ba.unsa.etf.dms.data.content.ContentResponse;

/**
 * Created by Hugsby on 1/7/18.
 */

public interface DocumentCallback {
    void onSuccess(List<ContentResponse> response);
    void onError(String errorMessage);
    void onNetworkError(String errorMessage);
}
