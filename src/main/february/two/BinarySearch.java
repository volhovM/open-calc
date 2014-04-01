package main.february.two;

/**
 * Created by volhovm on 3/2/14.
 */
public class BinarySearch {
    //-----------------------
    // TO_READ: 'right-sorted' means: for each i from {0, mainArray.length - 1} mainArray[i+1] >= mainArray[i];
    //          'valueToFindIndex' is an index of the first occurrence of 'valueToFind'
    //-----------------------

    //args.length > 0 and if there's an array, it's 'right-sorted'
    public static void main(String[] args) {

        //        @almost-junix-test
        //        args = new String[]{"5", "-4", "0", "3", "3", "5"}; //3 1 | 3 0

        long valueToFind = Long.parseLong(args[0]);
        if (args.length == 1) {
            System.out.println("0 0");
            return;
        }
        //args.length > 1 (if 0 -- everything's existence looses it's sense!)
        long[] mainArray = new long[args.length - 1];
        //mainArray.length > 0
        for (int i = 0; i < args.length - 1; i++) {
            mainArray[i] = Long.parseLong(args[i + 1]);
        }
        if (mainArray[mainArray.length - 1] < valueToFind) {
            System.out.println(mainArray.length + " 0");
            return;
        }
        //valueToFind <= mainArray[mainArray.length - 1]

        long[] pair = recursiveBinarySearchStart(mainArray, valueToFind);
        //        long[] pair = iterativeBinarySearchStart(mainArray, valueToFind);
        System.out.println(pair[0] + " " + pair[1]);
    }


    //Recursive part//
    //////////////////////////////////////////////////////////////////////////////

    //Pre: mainArray is 'right-sort'
    //Pre:
    public static long[] recursiveBinarySearchStart(long[] mainArray, long valueToFind) {
        long fromIndex = 0, toIndex = mainArray.length;
        long i = recursiveMechanismF(mainArray, valueToFind, fromIndex, toIndex);
        valueToFind++;
        long j;
        if (valueToFind > mainArray[mainArray.length - 1]) {
            j = mainArray.length;
        } else {
            j = recursiveMechanismF(mainArray, valueToFind, fromIndex, toIndex);
        }
        return new long[]{i, j - i};
    }
    //Post: return = pair(min {k: mainArray[k] >= valueToFind}; number of {k: mainArray[k] == valueToFind})


    //Pre: 0 <= fromIndex <= valueToFindIndex < toIndex
    public static long recursiveMechanismF(long[] mainArray,
                                           long valueToFind,
                                           long fromIndex,
                                           long toIndex) {
        // 0 <= fromIndex <= valueToFindIndex < toIndex
        if (fromIndex >= toIndex - 1) {
            // fromIndex == toIndex - 1
            return fromIndex;
            // (0 <= fromIndex <= valueToFindIndex < toIndex) && !(fromIndex >= toIndex - 1) && mainArray is 'right-sorted' =>
            // => return min (i: mainArray[i] >= valueToFind)
        }
        // 0 <= fromIndex <= valueToFindIndex < toIndex - 1
        long median = fromIndex + (toIndex - fromIndex - 1) / 2;
        // (0 <= fromIndex <= valueToFindIndex < toIndex - 1) & (median formula) =>
        // fromIndex < median < toIndex
        if (mainArray[((int) median)] >= valueToFind) {
            // (0 <= fromIndex <= valueToFindIndex < toIndex - 1) &
            // (fromIndex < median < toIndex) &
            // (mainArray[median] >= valueToFind) => (0 <= fromIndex <= valueToFindIndex < median + 1 < toIndex)
            return recursiveMechanismF(mainArray, valueToFind, fromIndex, median + 1);
            // return min (i: mainArray[i] >= valueToFind)
        } else {
            // (0 <= fromIdex <= valueToFindIndex < toIndex - 1) &
            // (fromIndex < median < toIndex) &
            // (mainArray[median] < valueToFind) => (0 <= fromIndex < median + 1 <= valueToFindIndex < toIndex)
            return recursiveMechanismF(mainArray, valueToFind, median + 1, toIndex);
            // return min (i: mainArray[i] >= valueToFind)
        }
        // return min (i: mainArray[i] >= valueToFind)
    }
    //Post: return min (i: mainArray[i] >= valueToFind)


    //Iterative part//
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    //Pre: mainArray is 'right-sorted';
    public static long[] iterativeBinarySearchStart(long[] mainArray, long valueToFind) {
        long i = iterativeBinarySearch(mainArray, valueToFind);
        valueToFind++;
        long j;
        if (valueToFind > mainArray[mainArray.length - 1]) {
            j = mainArray.length;
        } else {
            j = iterativeBinarySearch(mainArray, valueToFind);
        }
        return new long[]{i, j - i};
    }
    //Post: return = pair(min {k: mainArray[k] >= valueToFind}; number of {k: mainArray[k] == valueToFind})


    //Pre: mainArray -- 'right-sorted'
    //Pre: mainArray.length > 0
    //Pre: valueToFind >= mainArray[0] && valueToFind <= mainArray[mainArray.length - 1]
    private static long iterativeBinarySearch(long[] mainArray, long valueToFind) {
        long fromIndex = 0;
        long toIndex = mainArray.length;
        //Pre: mainArray,length > 0 => fromIndex < toIndex
        //Inv: valueToFind in [mainArray[fromIndex]; mainArray[toIndex])
        while (fromIndex + 1 < toIndex) {
            // fromIndex + 1 < toIndex
            // fromIndex <= valueToFindIndex < toIndex
            long median = fromIndex + (toIndex - fromIndex - 1) / 2;
            // fromIndex + 1 < toIndex & valueToFindIndex >= fromIndex & valueToFindIndex < toIndex
            // (fromIndex + 1 < toIndex => fromIndex < median < toIndex - 1)
            if (mainArray[((int) median)] >= valueToFind) {
                // fromIndex + 1 < toIndex & fromIndex <= valueToFindIndex < toIndex &
                // fromIndex < median < toIndex - 1 & mainArray[median] >= valueToFind
                toIndex = median + 1;
                // (toIndex = median + 1) & (fromIndex < median < toIndex') => fromIndex < toIndex
                // (toIndex = median + 1) & (fromIndex <= valueToFindIndex < toIndex) & (mainArray[median] >= valueToFind) =>
                // => (fromIndex <= valueToFind < toIndex) => (fromIndex + 1 <= toIndex)
            } else {
                // fromIndex + 1 < toIndex & fromIndex <= valueToFindIndex < toIndex &
                // fromIndex < median < toIndex - 1 & mainArray[median] < valueToFind
                fromIndex = median + 1;
                // (fromIndex = median + 1) & (fromIndex < median < toIndex - 1) => fromIndex < toIndex
                // (fromIndex < toIndex) & (fromIndex <= valueToFindIndex < toIndex) & (mainArray[median] < valueToFind) =>
                // => (fromIndex <= valueToFindIndex < toIndex) => (fromIndex + 1 <= toIndex)
            }
            //(fromIndex <= valueToFindIndex < toIndex) => valueToFind in [fromIndex; toIndex) #[inv proved]
        }
        // (fromIndex + 1 <= toIndex) & !(fromIndex < toIndex) => fromIndex = toIndex + 1
        // (fromIndex <= valueToFindIndex < toIndex) && (fromIndex = toIndex + 1) && mainArray is 'right-sorted' {non-descending} =>
        // => fromIndex = min (i: mainArray[i] >= valueToFind)
        return fromIndex;
        // return min (i: mainArray[i] >= valueToFind)
    } //////удивительно, но строчка выше похожа на строчку ниже, лол (удивительно в счет текущего ночного времени)
    //Post: return min {k: mainArray[k] >= valueToFind}
}
