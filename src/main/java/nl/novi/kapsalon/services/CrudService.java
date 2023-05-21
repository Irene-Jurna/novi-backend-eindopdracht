package nl.novi.kapsalon.services;

import nl.novi.kapsalon.exceptions.BusinessException;
import nl.novi.kapsalon.models.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface CrudService<REQUEST, RESPONSE, ENTITY extends BaseEntity>  {

    RESPONSE create(REQUEST request) throws BusinessException;

    // Consider pagination
    List<RESPONSE> getAll() throws BusinessException;

    RESPONSE get(Long id) throws BusinessException;

    RESPONSE update(Long id, REQUEST request) throws BusinessException;

    void delete(Long id) throws BusinessException;

    ENTITY toEntity(REQUEST request);

    RESPONSE toResponse(ENTITY entity);

}
