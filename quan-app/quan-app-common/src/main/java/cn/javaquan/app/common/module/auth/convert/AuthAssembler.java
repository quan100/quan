package cn.javaquan.app.common.module.auth.convert;

import cn.javaquan.app.common.module.auth.AuthQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * {@code auth} 模块中的简单参数转换.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Mapper
public interface AuthAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    AuthAssembler INSTANCE = Mappers.getMapper(AuthAssembler.class);

    /**
     * 转换为 {@link AuthQuery} 实例.
     * @param appType 设置的应用类型参数
     * @return {@link AuthQuery} 实例
     */
    AuthQuery appType(String appType);

}
