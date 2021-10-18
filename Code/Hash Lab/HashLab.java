import java.util.Arrays;
import java.util.HashSet;

/**
 * @Author Carlos Herrera
 */
public class HashLab {
//    Methods aren't generalized, just going based off of the slides
    public static Integer[] linearQuotientHashing(Integer[] keyVals){
        int offset = 0;
        Integer[] linearHashing = new Integer[13];
        for(int i = 0; i<keyVals.length; i++){
            int ip = keyVals[i] % 13;
            int q = keyVals[i]/13;
            if (q%13 != 0) {
                offset = q;
            } else {
                offset = 19;
            }
            while (linearHashing[ip] != null){
                ip = (ip+offset) % 13;
            }
            linearHashing[ip] = keyVals[i];
        }
        return linearHashing;
    }
    public static int findLQH(Integer[] hashMap, int key) throws NullPointerException{
        int comparisons = 0;
        int q = key/13;
        int ip = key % 13;
        int offset= q%13 != 0 ? q:19;
        if (hashMap[ip]==key){
            comparisons++;
            return comparisons;
        } else{
            while(hashMap[ip] !=key){
                ip = (ip+offset) % 13;
                comparisons++;
            }
            return comparisons;
        }
    }
    public static Node[] bucketHashing(Integer[] keyVals){
        Node[] bucketHashing = new Node[10];
        for(int i = 0; i<keyVals.length; i++){
            int ip = keyVals[i] % 10;
            if(bucketHashing[ip] == null){
                Node<Integer> val = new Node<>(keyVals[i]);
                bucketHashing[ip] = val;
            } else {
                Node point = bucketHashing[ip];
                while(point != null){
                    if(point.next == null){
                        bucketHashing[ip].next = new Node<>(keyVals[i]);
                        point = point.next;
                    } else {
                        point = bucketHashing[ip].next;
                    }
                }
            }
        }
        return bucketHashing;
    }
    public static int findBucketHashing(Node[] keyVals, int key){
        int comparisons = 0;
        int ip = key % 10;
        if(key == (int)keyVals[ip].getData()){
            return ++comparisons;
        } else {
            Node point = keyVals[ip];
            while(point!= null){
                if((int)point.getData() == key)
                    return ++comparisons;
                point = point.next;
                ++comparisons;
            }
        }
        return comparisons;
    }
    public static void main(String[] args) {
        Integer[] keyVals = {27, 53, 13, 10, 138, 109, 49, 174, 26, 24};
        HashSet<Integer> keys = new HashSet<>(Arrays.asList(keyVals));
        System.out.println("Normal HashSet: "+keys);
        Integer[] lqH = linearQuotientHashing(keyVals);
        Node[] bucketHashing = bucketHashing(keyVals);
        System.out.println("Linear Quotient Hashing: "+Arrays.toString(lqH));
        System.out.println("Bucket Hashing: "+ Arrays.toString(bucketHashing));
        Integer[] searchVals = {53,138,109,49,174,26};
        for(int i : searchVals){
            System.out.printf("Number of comparisons to find %d in LQH hash: %d\n", i,findLQH(lqH,i));
            System.out.printf("Number of comparisons to find %d in Bucket Hash: %d\n", i,findBucketHashing(bucketHashing,i));
        }

    }

    private static class Node<T> {
        Node<T> next;
        T data;
        Node(T data){
            this.data = data;
        }

        public T getData() {
            return data;
        }

        @Override
        public String toString() {
            String returnString = "";
            if(this.next == null ) {
                returnString += this.data;
            } else {
                Node point = this;
                returnString += "[";
                while(point != null){
                    if (point.next == null)
                        returnString += point.data;
                    else
                        returnString += point.data + ", ";
                    point = point.next;
                }
                returnString += "]";
            }
            return returnString;
        }
    }
}
