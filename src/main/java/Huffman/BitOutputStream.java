package Huffman;

import java.io.IOException;
import java.io.OutputStream;

public class BitOutputStream {
    private OutputStream outputStream;
    private int currentByte;
    private int numBitsRemaining;

    public BitOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
        this.currentByte = 0;
        this.numBitsRemaining = 8;
    }

    public void writeBit(boolean bit) throws IOException {
        currentByte = (currentByte << 1) | (bit ? 1 : 0);
        numBitsRemaining--;
        if (numBitsRemaining == 0) {
            outputStream.write(currentByte);
            numBitsRemaining = 8;
        }
    }

    public void writeByte(byte b) throws IOException {
        if (numBitsRemaining == 8) {
            outputStream.write(b);
        } else {
            for (int i = 7; i >= 0; i--) {
                boolean bit = ((b >>> i) & 1) == 1;
                writeBit(bit);
            }
        }
    }

    public void close() throws IOException {
        while (numBitsRemaining != 8) {
            writeBit(false);
        }
        outputStream.close();
    }
}
