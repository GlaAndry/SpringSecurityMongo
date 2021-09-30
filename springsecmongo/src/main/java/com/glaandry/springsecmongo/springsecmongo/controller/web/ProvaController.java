package com.glaandry.springsecmongo.springsecmongo.controller.web;

import com.glaandry.springsecmongo.springsecmongo.controller.fs.FileSystemMetadataController;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class ProvaController {

    @Autowired
    FileSystemMetadataController fileSystemMetadataController;

    @GetMapping("/prvAdmin")
    //@Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    private String prova() {
        return "prova admin";
    }

    @GetMapping("/prvPrincipal")
    private String authorities(Principal principal) {
        //ritorna il nome dell'account loggato
        return principal.getName() + " ciao!";
    }

    @GetMapping("/provaPath")
    private void prvpath(){
        Path path = new Path("/tmp/prova.txt");
        fileSystemMetadataController.retrieveFileOwnerFromFS(path);
    }


    @GetMapping("/prvUser")
    //@Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    private String prova2() {
        return "prova user";
    }

    @GetMapping("/prvMod")
    //@Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    private String prova3() {
        return "prova mod";
    }
}
