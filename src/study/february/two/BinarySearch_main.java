package study.february.two;

/**
 * Created by volhovm on 3/2/14.
 */
public class BinarySearch_main {
    static long[] mainArray;
    static long valueToFind;

    //Pre: args.length > 2, ai > a(i-1) for i >= 1
    public static void main(String[] args) {

        //        @almost-junix-test
        //        args = new String[]{"0", "0", "0", "1", "1"};

        valueToFind = Long.parseLong(args[0]);
        mainArray = new long[args.length - 1];
        //Inv: #cycle's body || 0 <= i < args.length - 1
        for (long i = 0; i < args.length - 1; i++) {
            mainArray[((int) i)] = Long.parseLong(args[((int) (i + 1))]);
        }
        //        Arrays.sort(mainArray);

        System.out.print(recursiveBinarySearch(0, mainArray.length));
        //        System.out.print(iterativeBinarySearch());
    }
    //Post: program exited with code 0?

    //Pre: fromIndex >=0 && toIndex >= fromIndex + 1
    public static long recursiveBinarySearch(long fromIndex, long toIndex) {
        long i = recursiveMechanism(fromIndex, toIndex);
        return i == Long.MAX_VALUE ? -1 : i;
    }
    //Post: if (exists mainArray : mainArray[i] = min(mainArray[j]),
    // where mainArray[j] >= valueToFind))
    //          return : a[return] = min(a[j]), where a[j] >= valueToFind
    //      else
    //          return = -1

    //Pre: fromIndex >=0 && toIndex >= fromIndex + 1
    public static long recursiveMechanism(long fromIndex, long toIndex) {
        if (fromIndex == toIndex - 1) {
            if (mainArray[((int) fromIndex)] >= valueToFind) {
                return fromIndex;
            } else {
                return Long.MAX_VALUE;
            }
        }
        long median = fromIndex + (toIndex - fromIndex - 1) / 2;
        if (mainArray[((int) median)] >= valueToFind) {
            return recursiveMechanism(fromIndex, median + 1);
        } else {
            return recursiveMechanism(median + 1, toIndex);
        }
    }
    //Post: if (exists mainArray : mainArray[i] = min(mainArray[j]),
    // where mainArray[j] >= valueToFind))
    //          return : a[return] = min(a[j]), where a[j] >= valueToFind
    //      else
    //          return = 2147483647


    //Pre: fromIndex >=0 && toIndex >= fromIndex + 1
    public static long iterativeBinarySearch() {
        long fromIndex = 0;
        long toIndex = mainArray.length;
        long exactIndex = Long.MAX_VALUE;

        //Inv: fromIndex >=0 && toIndex >= fromIndex + 1 &&
        //fromIndex <= exactIndex < toIndex || exactIndex = 2147483647
        do {
            //fromIndex >=0 && toIndex > fromIndex + 1
            //fromIndex <= exactIndex < toIndex || exactIndex = 2147483647
            long median = fromIndex + (toIndex - fromIndex - 1) / 2;
            //fromIndex >=0 && toIndex > fromIndex + 1
            //fromIndex <= exactIndex < toIndex || exactIndex = 2147483647
            //fromIndex <= median < toIndex
            if (mainArray[((int) median)] >= valueToFind) {
                exactIndex = median;
                //0 <= exactIndex < exactIndex' (prev) < 2147483647
                toIndex = median;
                //toIndex < toIndex'
                //-//-
            } else {
                fromIndex = median;
                //fromIndex > fromIndex'
            }
            //0 <= exactIndex <= 2147483647
            //fromIndex > fromIndex' || toIndex < toIndex'
        } while (fromIndex + 1 < toIndex);
        //fromIndex + 1 == toIndex
        // 0 <= exactIndex <= 2147483674
        return exactIndex == Long.MAX_VALUE ? -1 : exactIndex;
    }
    //Post: if (exists mainArray : mainArray[i] = min(mainArray[j]),
    // where mainArray[j] >= valueToFind))
    //          i : a[i] = min(a[j]), where a[j] >= valueToFind
    //      else
    //          i = -1
}
