package com.glaandry.springsecmongo.springsecmongo.model.fs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Questa entità va a mappare una classe Java rappresentante i metadati per andare ad identificare
 * il file in questione.
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class MetadataFile {

    private String file_id;
    private String file_name;
    private String file_size;
    private String file_type;
    private String file_path;
    private String file_owner;

}
