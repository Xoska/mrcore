package pfe.com.mrcore.core.utils;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Component;

@Component
public class InputSanitizer {

    public String cleanUnsafeString(String unsafeString) {

        return Jsoup.clean(unsafeString, Whitelist.basic());
    }
}
