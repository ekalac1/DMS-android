package ba.unsa.etf.dms.data.content;

/**
 * Created by Hugsby on 1/2/18.
 */

public class ContentDTO {

    private int id;
    private String fileName;
    private String datatype;
    private String content;

    private String owner;

    public ContentDTO(String fileName, String datatype, String content, String owner) {
        this.fileName = fileName;
        this.datatype = datatype;
        this.content = content;
        this.owner = owner;
    }

    public ContentDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
