/**
 * 应用聚合模块
 * <p>
 * 应用场景：
 * 当服务器资源不足时，无法同时部署多个应用服务，此时可以部署当前服务。
 * 它的主要作用是将多个应用服务聚合到一个应用服务中部署。
 * <p>
 * 当服务器资源充足时，建议每个应用服务单独部署，以发挥分布式架构最大的性能。
 * <p>
 * 聚合: quan-app-mobile-bff、quan-app-pm-bff、quan-app-service、quan-app-core、quan-security-server 模块，使其能够部署在同一个应用中。
 * <p>
 * 当前模块使用了自定义bean名称实现：{@link com.quan.app.aggregate.beans.QuanBeanNameGenerator} 用来处理不同模块相同类名冲突问题；
 * 这种方式不会覆盖已注册的bean，但会修改冲突的bean名称，使其即使类名相同，也能同时注册。这种方式目前仅用于当前聚合模块。其它业务请谨慎使用。
 */
package com.quan.app.aggregate;
