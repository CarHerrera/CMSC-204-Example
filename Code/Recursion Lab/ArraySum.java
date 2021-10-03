import java.util.Arrays;

public class ArraySum {

    public int sumOfArray(Integer[] myArray, int i) {
        int idx = i;
        if(i==0){
            return myArray[i];
        }
        return sumOfArray(myArray, --idx) + myArray[i];
    }
}
