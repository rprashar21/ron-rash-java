package datastructures.binary.Questions;

public class SearchElementInInfiniteArray {

    public static void main(String[] args) {

        int[] array= new int[]{2,3,5,6,7,8,9,10,20,30,40,50,60,70,80,90};
        int target =20;

        final int answer = findAnswer(array, target);
        System.out.println(answer);
    }

    static int findAnswer(int[] array,int target)
    {
        // first find the range
        //first start with the range of 2
        int start=0;
        int end=1;

        //condition which when breaks we get our range
        // some additional comments
        while(target>array[end])
        {
            int temp = end+1;
            end =end+(end-start+1)*2;
            start=temp;
        }

        return binarySearch(array,target,start,end);
    }

    static int binarySearch(int[] array, int target, int start, int end) {

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (target == array[mid])
                return mid;
            else if (target > array[mid]) {
                start = mid + 1;
            }
            else
            {
                end=mid-1;
            }
        }
        return -1;
    }
}
