package Huffman;

import java.io.*;

public class HuffmanCompression {
    public static void compress(String inputFile, String outputFile) throws IOException {
        // 读取待压缩的文件
        File file = new File(inputFile);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line);
        }
        reader.close();

        // 统计字符频次
        int[] charFrequency = new int[256];
        for (char c : content.toString().toCharArray()) {
            charFrequency[c]++;
        }

        // 构建优先队列
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < charFrequency.length; i++) {
            if (charFrequency[i] > 0) {
                priorityQueue.enqueue(new HuffmanNode((char) i, charFrequency[i]), charFrequency[i]);
            }
        }

        // 构建Huffman树
        HuffmanTree tree = new HuffmanTree(priorityQueue);

        // 创建输出流
        BitOutputStream outputStream = new BitOutputStream(new FileOutputStream(outputFile));

        // 写入Huffman树的结构信息
        tree.writeTree(outputStream);

        // 将字符替换为对应的Huffman编码，并写入输出流
        for (char c : content.toString().toCharArray()) {
            String code = tree.getCode(c);
            for (char bit : code.toCharArray()) {
                outputStream.writeBit(bit == '1');
            }
        }

        // 写入特殊标记，表示文件结束
        outputStream.writeBit(true);
        outputStream.close();
    }

    public static void main(String[] args) {
        try {
            HuffmanCompression.compress("C:\\Users\\86188\\IdeaProjects\\heima\\src\\main\\java\\Huffman\\input.txt", "C:\\Users\\86188\\IdeaProjects\\heima\\src\\main\\java\\Huffman\\output.bin");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
