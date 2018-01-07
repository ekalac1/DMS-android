package ba.unsa.etf.dms.data.content;

/**
 * Created by Hugsby on 1/2/18.
 */

public class ContentResponse {
    private int id;
    private String fileName;
    private String datatype;
    private String content;


    public int getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
