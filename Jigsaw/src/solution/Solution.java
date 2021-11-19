package solution;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;


/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {
    /**
     * 拼图构造函数
     */
    public Solution() {
    }

    /**
     * 拼图构造函数
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     */
    public Solution(JigsawNode bNode, JigsawNode eNode) {
        super(bNode, eNode);
    }

    /**
     *（实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     * @return 搜索成功时为true,失败为false
     */
    public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
    	Queue<JigsawNode> open = new LinkedList<>();
    	Set<JigsawNode> close = new HashSet<>();
        open.add(bNode);

        while (!open.isEmpty()) {
        	JigsawNode v = open.poll();
        	this.currentJNode = v;
            if (v.equals(eNode)) {
                return true;
            }
            else {
            	close.add(v);
            }
            for (int i = 0; i < 4; i++) {
                JigsawNode neighbor = new JigsawNode(v);
                if (neighbor.move(i) && !close.contains(neighbor)) {
                	open.add(neighbor);
                }
            }
        }
        return false;
    }

    /**
     *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
     * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
     * @param jNode - 要计算代价估计值的节点
     */
    public void estimateValue(JigsawNode jNode) {
        int f1 = misplacedNumber(jNode);
        int f2 = misplacedDistance(jNode);
        int f3 = followWrongNumber(jNode);
        jNode.setEstimatedValue(1 * f1 + 4 * f2 + 5 * f3);
    }
    
    /**
     * Number of all misplaced numbers.
     */
    public int misplacedNumber(JigsawNode jNode) {
    	int num = 0;
    	int dimension = JigsawNode.getDimension();
    	for (int i = 1; i <= dimension * dimension; ++i) {
    		if (jNode.getNodesState()[i] != endJNode.getNodesState()[i]) {
    			num++;
    		}
    	}
    	return num;
    }
    
    /**
     * The sum of the distances between all misplaced numbers 
     * and their correct positions.
     */
    public int misplacedDistance(JigsawNode jNode) {
    	double num = 0;
    	int dimension = JigsawNode.getDimension();
    	for (int i = 0; i <= dimension * dimension; ++i) {
    		if (jNode.getNodesState()[i] != endJNode.getNodesState()[i]) {
    			int jNodeRow = (jNode.getNodesState()[i] - 1) / dimension;
    			int jNodeCol = (jNode.getNodesState()[i] - 1) % dimension;
    			int endJNodeRow = (endJNode.getNodesState()[i] - 1) / dimension;
    			int endJNodeCol = (endJNode.getNodesState()[i] - 1) % dimension;
    			int dr = Math.abs(jNodeRow - endJNodeRow);
    			int dc = Math.abs(jNodeCol - endJNodeCol);
    			num += Math.sqrt(Math.pow(dr, 2) + Math.pow(dc, 2));
    		}
    	}
    	return (int)num;
    }
    
    /**
     * Incorrect number of subsequent nodes.
     */
    public int followWrongNumber(JigsawNode jNode) {
    	int s = 0; // 后续节点不正确的数码个数
        int dimension = JigsawNode.getDimension();
        for (int index = 1; index < dimension * dimension; index++) {
            if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1]) {
                s++;
            }
        }
        return s;
    }
}
