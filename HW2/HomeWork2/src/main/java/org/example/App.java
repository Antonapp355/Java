package org.example;

import java.util.Arrays;

public class App
{
    public static void main(String[] args) {
        int[] arr = { 8,5,1,3,6,4,2 };
        int[] result = mergeSort(arr);
        System.out.println(Arrays.toString(result));
    }

    public static int[] mergeSort(int[] array1) {
        int[] buf1 = Arrays.copyOf(array1, array1.length);
        int[] buf2 = new int[array1.length];
        int[] res = mergesortInner(buf1, buf2, 0, array1.length);
        return res;
    }

    public static int[] mergesortInner(int[] buf1, int[] buf2,
                                       int startIndex, int endIndex) {
        if (startIndex >= endIndex - 1) {
            return buf1;
        }

        // Разбиваем на отдельные элементы в 2 массива.
        int middle = startIndex + (endIndex - startIndex) / 2;
        int[] sorted1 = mergesortInner(buf1, buf2, startIndex, middle);
        int[] sorted2 = mergesortInner(buf1, buf2, middle, endIndex);

        // Слияние
        int index1 = startIndex;
        int index2 = middle;
        int destIndex = startIndex;
        int[] result = sorted1 == buf1 ? buf2 : buf1;
        while (index1 < middle && index2 < endIndex) {
            result[destIndex++] = sorted1[index1] < sorted2[index2]
                    ? sorted1[index1++] : sorted2[index2++];
        }
        while (index1 < middle) {
            result[destIndex++] = sorted1[index1++];
        }
        while (index2 < endIndex) {
            result[destIndex++] = sorted2[index2++];
        }
        return result;
    }
}

