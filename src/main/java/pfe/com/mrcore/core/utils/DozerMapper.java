package pfe.com.mrcore.core.utils;


import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.List;

public class DozerMapper {

    public static <T, U> List<U> map(Mapper mapper, List<T> source, Class<U> destType) {

        List<U> dest = new ArrayList<>();

        for (T element : source) {

            dest.add(mapper.map(element, destType));
        }

        return dest;
    }
}
