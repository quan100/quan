/**
 * 提供基础设施能力
 * <p>
 * 它主要为 bff、service 提供支撑。
 * <p>
 * 所有与具体平台、框架相关的实现会在 Core 中提供，避免其它层掺杂进这些实现，从 而“污染”领域模型。
 * Core 中最常见的一类设施是对象持久化的具体实现。
 * <p>
 * controller以{@code /core/}开头，避免在聚合模块时，与其它模块冲突
 */
package com.quan.app.core;
