package com.quan.app.pm.bff.system.convert;

import com.quan.app.common.module.auth.AuthQuery;
import com.quan.security.common.dto.entity.AuthEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthEntityAssembler {

    AuthEntityAssembler INSTANCE = Mappers.getMapper(AuthEntityAssembler.class);

    AuthQuery toAuthQuery(AuthEntity authEntity);

}
