package com.quan.app.common.module.auth.convert;

import com.quan.app.common.module.auth.AuthQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * {@code auth} 模块中的简单参数转换
 *
 * @author javaquan
 */
@Mapper
public interface AuthAssembler {

    AuthAssembler INSTANCE = Mappers.getMapper(AuthAssembler.class);


    AuthQuery appType(String appType);

}
