package pfe.com.mrcore.core.utils;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

@Component
public class DozerMapper<Dto, Entity> {

    @Autowired
    private Mapper mapper;

    public List<Entity> mapToEntityList(List<Dto> dtos) {

        List<Entity> entities = new ArrayList<Entity>();

        for (Dto dto : dtos) {

            entities.add(mapToEntity(dto));
        }

        return entities;
    }

    public List<Dto> mapToDtoList(List<Entity> entities) {

        List<Dto> dtos = new ArrayList<Dto>();

        for (Entity entity : entities) {

            dtos.add(mapToDto(entity));
        }

        return dtos;
    }

    @SuppressWarnings("unchecked")
    private Entity mapToEntity(Dto dto) {

        Class<Entity> entityClass = (Class<Entity>)
                ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        return mapper.map(dto, entityClass);
    }

    @SuppressWarnings("unchecked")
    private Dto mapToDto(Entity entity) {

        Class<Dto> dtoClass = (Class<Dto>)
                ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        return mapper.map(entity, dtoClass);
    }
}
