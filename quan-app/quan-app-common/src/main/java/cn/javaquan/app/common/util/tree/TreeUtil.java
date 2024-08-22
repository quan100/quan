package cn.javaquan.app.common.util.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成树形结构工具类.
 *
 * @author wangquan
 * @since 1.0.0
 */
public final class TreeUtil {

    /**
     * 私有构造方法.
     */
    private TreeUtil() {
    }

    /**
     * 转换为树形数据.
     * <p>
     * 默认顶级节点ID为0
     * @param originalDataList 原数据
     * @param <T> 约定的数据类型
     * @return 树形结构数据
     */
    public static <T extends ITreeNode<T>> List<T> asTreeNodes(List<T> originalDataList) {
        return asTreeNodes(0L, originalDataList);
    }

    /**
     * 转换为树形数据.
     * @param parentId 父级节点ID
     * @param originalDataList 原数据
     * @param <T> 约定的数据类型
     * @return 树形结构数据
     */
    public static <T extends ITreeNode<T>> List<T> asTreeNodes(Object parentId, List<T> originalDataList) {
        List<T> treeNodeData = new ArrayList<>();
        // 转换数据结构
        buildTreeNode(parentId, originalDataList, treeNodeData);
        return treeNodeData;
    }

    /**
     * 转换数据为树形数据结构.
     * @param parentId 父级节点ID
     * @param originalDataList 待处理的数据列表
     * @param treeNodeData 已处理的数据列表
     * @param <T> 约定的数据类型
     */
    private static <T extends ITreeNode<T>> void buildTreeNode(Object parentId, List<T> originalDataList,
            List<T> treeNodeData) {
        originalDataList.forEach(node -> {
            // 是否为父节点
            if (parentId.equals(node.getParentId())) {
                buildTreeNode(node.getId(), originalDataList, node.determineChildren());
                treeNodeData.add(node);
            }
        });
    }

}
