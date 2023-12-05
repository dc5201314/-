package Huffman;

import java.io.IOException;

public class HuffmanTree {
    private HuffmanNode root;
    private String[] codeTable;

    public HuffmanTree(PriorityQueue<HuffmanNode> priorityQueue) {
        // 构建Huffman树
        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.dequeue();
            HuffmanNode right = priorityQueue.dequeue();
            HuffmanNode parent = new HuffmanNode('\0', left.getFrequency() + right.getFrequency());
            parent.setLeft(left);
            parent.setRight(right);
            priorityQueue.enqueue(parent, parent.getFrequency());
        }

        // 保存Huffman树的根节点
        root = priorityQueue.dequeue();

        // 为每个叶子节点生成二进制编码
        codeTable = new String[256];
        generateCodeTable(root, "");
    }

    public String getCode(char character) {
        return codeTable[character];
    }

    public void writeTree(BitOutputStream output) throws IOException {
        writeTree(root, output);
    }

    private void writeTree(HuffmanNode node, BitOutputStream output) throws IOException {
        if (node.isLeaf()) {
            output.writeBit(true); // 叶子节点标记
            output.writeByte(node.getCharacter());
        } else {
            output.writeBit(false); // 非叶子节点标记
            writeTree(node.getLeft(), output);
            writeTree(node.getRight(), output);
        }
    }

    private void generateCodeTable(HuffmanNode node, String code) {
        if (node.isLeaf()) {
            codeTable[node.getCharacter()] = code;
        } else {
            generateCodeTable(node.getLeft(), code + "0");
            generateCodeTable(node.getRight(), code + "1");
        }
    }
}
