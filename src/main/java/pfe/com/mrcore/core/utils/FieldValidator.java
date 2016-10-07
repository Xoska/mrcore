package pfe.com.mrcore.core.utils;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

@Component
public class FieldValidator {

    public boolean checkFieldsLength(Integer length, String... fields) {

        for (String field : fields) {

            if (field.length() > length) {

                return false;
            }
        }

        return true;
    }

    public boolean checkEmails(String... emails) {

        for (String email : emails) {

            if (!EmailValidator.getInstance().isValid(email)) {

                return false;
            }
        }

        return true;
    }
}
