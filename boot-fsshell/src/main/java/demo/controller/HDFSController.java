package demo.controller;

import demo.common.Constants;
import demo.model.MetadataFile;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.hadoop.fs.FsShell;
import org.springframework.stereotype.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

@Controller
public class HDFSController {

    @Autowired
    private FsShell shell;


    public String copyInHDFS(){
        System.out.println("Copio il file locale\n");
        shell.copyFromLocal("/home/alessiomazzola/IdeaProjects/SpringSecurityMongo/boot-fsshell/src/main/resources/demo_data/test.meta", "/tmp2/file"+Constants.FILE_EXTENSION);
        System.out.println("Fatto!\n");
        return "ok";
    }

    public String prova(){
        MetadataFile m = retrieveFromHDFS("/tmp2/file");
        return m.getFile_name();
    }



    public MetadataFile retrieveFromHDFS(String filePath){

        MetadataFile metadataFile = null;

        try{
            String[] file = shell.cat(filePath + Constants.FILE_EXTENSION).toString().split(",");
            metadataFile = new MetadataFile(file[0], file[1], file[2], file[3], file[4], file[5]);
        } catch (Exception e){
            e.printStackTrace();
        }

        return metadataFile;

    }
}
