package demo.model;

/**
 * Questa entità va a mappare una classe Java rappresentante i metadati per andare ad identificare
 * il file in questione.
 */

public class MetadataFile {

    private String file_id;
    private String file_name;
    private String file_size;
    private String file_type;
    private String file_path;
    private String file_owner;

    public MetadataFile(String file_id, String file_name, String file_size, String file_type, String file_path, String file_owner) {
        this.file_id = file_id;
        this.file_name = file_name;
        this.file_size = file_size;
        this.file_type = file_type;
        this.file_path = file_path;
        this.file_owner = file_owner;
    }

    public MetadataFile() {
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_size() {
        return file_size;
    }

    public void setFile_size(String file_size) {
        this.file_size = file_size;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getFile_owner() {
        return file_owner;
    }

    public void setFile_owner(String file_owner) {
        this.file_owner = file_owner;
    }
}
