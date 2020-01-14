package pl.ferdynand.services;

import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class Base64Service {

    public String base64Encode(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    public String base64Decode(String input) {
        return new String(Base64.getDecoder().decode(input));
    }

}
