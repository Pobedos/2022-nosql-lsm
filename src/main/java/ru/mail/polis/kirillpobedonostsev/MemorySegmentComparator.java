package ru.mail.polis.kirillpobedonostsev;

import jdk.incubator.foreign.MemorySegment;

import java.util.Arrays;
import java.util.Comparator;

public class MemorySegmentComparator implements Comparator<MemorySegment> {
    @Override
    public int compare(MemorySegment m1, MemorySegment m2) {
        long offset = m1.mismatch(m2);
        if (offset == -1) {
            return 0;
        } else if (offset == m2.byteSize()) {
            return 1;
        } else if (offset == m1.byteSize()) {
            return -1;
        }
        byte[] memory1 = m1.asSlice(offset, 1).toByteArray();
        byte[] memory2 = m2.asSlice(offset, 1).toByteArray();
        return Arrays.compare(memory1, memory2);
    }
}