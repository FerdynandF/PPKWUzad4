package pl.ferdynand.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.logging.Logger;

@Controller
public class VcardController {

    @Autowired
    private VcardService vcardService;

    private Logger logger = Logger.getLogger(getClass().getName());

    @GetMapping("/vcard/{vcard}")
    public ResponseEntity<String> generateVcard(@PathVariable("vcard") String vcard) {
        String vcardFile = vcardService.fromURL(vcard);
        logger.info("Generated new vCard:\n" + vcardFile);
        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=\"VCARD.VCF\"")
                .header("Content-Type","text/x-vcard")
                .body(vcardFile);
    }

}
