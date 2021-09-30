package com.glaandry.springsecmongo.springsecmongo.controller.fs;

import com.glaandry.springsecmongo.springsecmongo.model.fs.MetadataFile;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.hadoop.fs.FsShell;
import org.springframework.stereotype.Controller;

import java.util.Collection;

/**
 * Classe contenente i metodi d'accesso ai metadati nel File System,
 * con lo scopo di andare ad estrapolarne i fattori fondamentali
 * ed effettuare conseguentemente aggregazione di questi ultimi con
 * spring security.
 */

@Controller
public class FileSystemMetadataController {

    @Autowired
    private FsShell shell;

    public String retrieveFileOwnerFromFS(Path path){

        Collection<Path> cat = shell.cat(String.valueOf(path.toUri()));
        for (Path p : cat){
            System.out.println(p);
        }
        return "ok";
    }

}
