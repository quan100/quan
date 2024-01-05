package cn.javaquan.app.common.util.tree;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成树形结构工具类
 *
 * @author wangquan
 * @date 2020/4/14 11:19
 */
@Component
public class TreeUtil {

    /**
     * 转换为树形数据
     * <p>
     * 默认顶级节点ID为0
     *
     * @param originalDataList 原数据
     * @return
     */
    public static <T extends cn.javaquan.app.common.util.tree.ITreeNode<T>> List<T> asTreeNodes(List<T> originalDataList) {
        return asTreeNodes(0L, originalDataList);
    }

    /**
     * 转换为树形数据
     *
     * @param parentId         父级节点ID
     * @param originalDataList 原数据
     * @return
     */
    public static <T extends cn.javaquan.app.common.util.tree.ITreeNode<T>> List<T> asTreeNodes(Object parentId, List<T> originalDataList) {
        List<T> treeNodeData = new ArrayList<>();
        // 转换数据结构
        buildTreeNode(parentId, originalDataList, treeNodeData);
        return treeNodeData;
    }

    /**
     * 转换数据为树形数据结构
     *
     * @param parentId         父级节点ID
     * @param originalDataList 待处理的数据列表
     * @param treeNodeData     已处理的数据列表
     * @param <T>
     */
    private static <T extends cn.javaquan.app.common.util.tree.ITreeNode<T>> void buildTreeNode(Object parentId, List<T> originalDataList, List<T> treeNodeData) {
        originalDataList.stream().forEach(node -> {
            // 是否为父节点
            if (parentId.equals(node.getParentId())) {
                buildTreeNode(node.getId(), originalDataList, node.getChildren());
                treeNodeData.add(node);
            }
        });
    }
}
