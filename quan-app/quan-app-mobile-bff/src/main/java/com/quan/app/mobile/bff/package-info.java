/**
 * 提供移动端适配层
 * <p>
 * controller以{@code /open/}开头，配合网关鉴权实现开放接口
 * feign主要负责与其它模块或外部系统进行交互&通信，比如一些 dubbo服务、Restful API、RMI等， 这一层主要包括 Facade、DTO还有一些Assembler。
 */
package com.quan.app.mobile.bff;
