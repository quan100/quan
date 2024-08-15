package cn.javaquan.app.common.util.tree;

import java.io.Serializable;
import java.util.List;

/**
 * 树形结构工具类.
 *
 * @param <T> 约定的数据类型
 * @author javaquan
 * @since 1.0.0
 */
public interface ITreeNode<T> extends Comparable<T>, Serializable {

    /**
     * 节点ID.
     * @return 节点ID
     */
    Object getId();

    /**
     * 父级节点ID.
     * @return 父级节点ID
     */
    Object getParentId();

    /**
     * 子节点列表.
     * @return 子节点列表
     */
    List<T> getChildren();

}
