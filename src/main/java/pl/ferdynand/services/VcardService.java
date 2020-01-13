package pl.ferdynand.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ferdynand.controller.Base64Service;

import java.util.logging.Logger;

@Service
public class VcardService {

    @Autowired
    private Base64Service base64Service;

    private Logger logger = Logger.getLogger(getClass().getName());

    public String fromURL(String vcardUrl) {
        String vcardContent = base64Service.base64Decode(vcardUrl);
        String[] vcardParts = vcardContent.split("\\n");

        String vcardName = vcardParts[0];
        String vcardOrganization = vcardParts[1];

        String[] nameParts = vcardName.split("\\s");
        String vcardStructuredName = nameParts[1] + ";" + nameParts[0];


        String vcardFile =
                "BEGIN:VCARD\n" +
                "VERSION:3.0\n" +
                "N:" + vcardStructuredName + "\n" +
                "FN:" + vcardName + "\n" +
                "ORG:" + vcardOrganization + "\n" +
                "END:VCARD";

        logger.info("VCardinfo \n" + vcardFile);
        return vcardFile;
    }

}
