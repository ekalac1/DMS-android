package ba.unsa.etf.dms.data;

import android.webkit.MimeTypeMap;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.nio.charset.Charset;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by Hugsby on 12/25/17.
 */

public class Utils {
    public static String parseError(Response<?> response) {
        String error = "";

        try {
            error = response.errorBody().source().readString(Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return error;
    }

    public static String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }
}
