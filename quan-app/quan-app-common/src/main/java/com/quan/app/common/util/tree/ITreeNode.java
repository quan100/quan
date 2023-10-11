package com.quan.app.common.util.tree;

import java.io.Serializable;
import java.util.List;

/**
 * 树形结构工具类
 *
 * @param <T>
 * @author javaquan
 */
public interface ITreeNode<T> extends Comparable<T>, Serializable {

    /**
     * 节点ID
     *
     * @return
     */
    Object getId();

    /**
     * 父级节点ID
     *
     * @return
     */
    Object getParentId();

    /**
     * 子节点列表
     */
    List<T> getChildren();

}
