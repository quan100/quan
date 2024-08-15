package cn.javaquan.app.pm.bff.system.convert;

import cn.javaquan.app.common.module.auth.AuthQuery;
import cn.javaquan.security.common.dto.entity.AuthEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户认证信息转换器.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Mapper
public interface AuthEntityAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    AuthEntityAssembler INSTANCE = Mappers.getMapper(AuthEntityAssembler.class);

    /**
     * 用户认证信息转换为查询参数.
     * @param authEntity 用户认证信息
     * @return 查询参数
     */
    AuthQuery toAuthQuery(AuthEntity authEntity);

}
