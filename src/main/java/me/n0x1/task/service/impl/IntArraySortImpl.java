package me.n0x1.task.service.impl;

import me.n0x1.task.entity.CustomEntityIntArray;
import me.n0x1.task.exception.CustomArrayException;
import me.n0x1.task.service.IntArraySort;

public class IntArraySortImpl implements IntArraySort {

    private static final int[] LP = {
            1, 1, 3, 5, 9, 15, 25, 41, 67, 109, 177, 287, 465, 753, 1219, 1973,
            3193, 5167, 8361, 13529, 21891, 35421, 57313, 92735, 150049, 242785,
            392835, 635621, 1028457, 1664079, 2692537, 4356617, 7049155, 11405773
    };

    @Override
    public void bubbleSort(CustomEntityIntArray entity) throws CustomArrayException {
        checkEntity(entity);
        int[] array = entity.getData();

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    @Override
    public void smoothSort(CustomEntityIntArray entity) throws CustomArrayException {
        checkEntity(entity);
        int[] array = entity.getData();
        if (array.length < 2) return;

        smoothSortProcess(array);
    }

    private void checkEntity(CustomEntityIntArray entity) throws CustomArrayException {
        if (entity == null) {
            throw new CustomArrayException("Entity is null, nothing to sort");
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void smoothSortProcess(int[] a) {
        int n = a.length;
        int p = 1;
        int b = 1;
        int c = 1;

        for (int i = 0; i < n - 1; i++) {
            if ((p & 3) == 3) {
                p >>= 2;
                p |= 1;
                b += 2;
                c += 2;
            } else {
                if (LP[b - 1] < n - i) {
                    p <<= 1;
                    p |= 1;
                    b--;
                    c--;
                } else {
                    p <<= 1;
                    b = 1;
                    c = 1;
                }
            }
            trinkle(a, p, b, c, i, false);
        }
        trinkle(a, p, b, c, n - 1, false);

        for (int i = n - 1; i > 0; i--) {
            if (b == 0 || b == 1) {
                p--;
                while ((p & 1) == 0) {
                    p >>= 1;
                    b++;
                    c++;
                }
            } else {
                p--;
                p <<= 1;
                p |= 1;
                b--;
                c--;
                trinkle(a, p >> 1, b, c, i - LP[b] - 1, true);
                p <<= 1;
                p |= 1;
                b--;
                c--;
                trinkle(a, p, b, c, i - 1, true);
            }
        }
    }

    private void sift(int[] a, int b, int c, int root) {
        while (b >= 2) {
            int r2 = root - 1;
            int r1 = root - 1 - LP[c];
            if (a[r1] >= a[r2]) {
                if (a[root] >= a[r1]) break;
                swap(a, root, r1);
                root = r1;
            } else {
                if (a[root] >= a[r2]) break;
                swap(a, root, r2);
                root = r2;
                int t = b - 1;
                b = c;
                c = t - c;
            }
        }
    }

    private void trinkle(int[] a, int p, int b, int c, int root, boolean isTrust) {
        while (p > 1) {
            int stepson = root - LP[b];
            if (isTrust || a[stepson] <= a[root]) break;

            if (b >= 2) {
                int r2 = root - 1;
                int r1 = root - 1 - LP[c];
                if (a[stepson] <= a[r1] || a[stepson] <= a[r2]) break;
            }

            swap(a, root, stepson);
            root = stepson;
            while ((p & 1) == 0) {
                p >>= 1;
                b++;
                c++;
            }
            p--;
            isTrust = false;
        }
        if (!isTrust) {
            sift(a, b, c, root);
        }
    }
}