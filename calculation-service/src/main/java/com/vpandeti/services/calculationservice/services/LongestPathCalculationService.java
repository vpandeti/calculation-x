package com.vpandeti.services.calculationservice.services;

import com.vpandeti.services.calculationservice.exceptions.LongestPathCalculationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class LongestPathCalculationService {

    public List<Integer> binaryTreeLongestPath(List<String> input) {
        TreeNode root = null;
        List<Integer> path = new ArrayList<>();
        if(input.size() == 0) return path;
        try {
            root = constructTree(input, 0);
            if (null == root) {
                throw new LongestPathCalculationException("Binary Tree can not be constructed for the given input. Please check the API documentation");
            };
            helper(root, new ArrayList<>(), path);
        } catch (NumberFormatException e) {
            log.error("Exception=NumberFormatException StackTrace={}", ExceptionUtils.getStackTrace(e));
            throw new LongestPathCalculationException(e.getMessage());
        } catch (Exception e) {
            log.error("Exception={} StackTrace={}", e.getClass().getCanonicalName(), ExceptionUtils.getStackTrace(e));
            throw new LongestPathCalculationException(e.getMessage());
        }
        return path;
    }

    private void helper(TreeNode root, List<Integer> currentPath, List<Integer> previousPath) {
        if (null == root) {
            if (currentPath.size() > previousPath.size()) { // longest path
                previousPath.clear();
                previousPath.addAll(currentPath);
            } else if (currentPath.size() == previousPath.size()) { // equal path lengths
                int currentPathSum = currentPath.stream().mapToInt(value -> value).sum();
                int previousPathSum = previousPath.stream().mapToInt(value -> value).sum();
                if (currentPathSum > previousPathSum) {
                    previousPath.clear();
                    previousPath.addAll(currentPath);
                }
            }
            return;
        }
        currentPath.add(root.value);
        helper(root.left, currentPath, previousPath);
        helper(root.right, currentPath, previousPath);
        currentPath.remove(currentPath.size() - 1);
    }

    private TreeNode constructTree(List<String> input, int currentInputIndex) {
        if (currentInputIndex >= input.size()) return null;

        String value = input.get(currentInputIndex);
        if (null == value || value.equalsIgnoreCase("null")) return null;

        TreeNode current = new TreeNode(Integer.parseInt(value));
        current.left = constructTree(input, currentInputIndex * 2 + 1);
        current.right = constructTree(input, currentInputIndex * 2 + 2);
        return current;
    }
}

class TreeNode {
    TreeNode left, right;
    int value;
    public TreeNode(int value) {
        this.value = value;
    }
}
