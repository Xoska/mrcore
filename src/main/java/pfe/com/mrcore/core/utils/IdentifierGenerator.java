package pfe.com.mrcore.core.utils;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;

@Component
public class IdentifierGenerator {

    private SecureRandom random = new SecureRandom();

    public String nextId(Integer length) {

        return new BigInteger(130, random).toString(length);
    }
}