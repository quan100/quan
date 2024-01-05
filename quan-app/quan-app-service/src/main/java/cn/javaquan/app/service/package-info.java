/**
 * 提供业务处理能力
 * <p>
 * 它主要为 bff 提供业务支撑。
 * 所有具体业务的实现会在 Service 中提供，避免其它层掺杂进这些实现，从 而“污染”领域模型。
 * <p>
 * controller以{@code /service/}开头，避免在聚合模块时，与其它模块冲突
 */
package cn.javaquan.app.service;
