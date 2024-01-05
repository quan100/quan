package cn.javaquan.app.pm.bff.system.convert;

import cn.javaquan.app.common.module.auth.AuthQuery;
import cn.javaquan.security.common.dto.entity.AuthEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthEntityAssembler {

    AuthEntityAssembler INSTANCE = Mappers.getMapper(AuthEntityAssembler.class);

    AuthQuery toAuthQuery(AuthEntity authEntity);

}
