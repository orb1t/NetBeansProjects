package amicablenumbers15072012;

import Support.StopWatch;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    HashMap store = new HashMap();
    
    public int getSumOfFactors(int number, int limit) {
        int sum = 1;
        int sum1 = 0;
        int sum2 = 0;
        int sum3 = 0;
        int sum4 = 0;
        int sum5 = 0;
        int sum6 = 0;
        int sqrt = (int) Math.sqrt(number);
        ArrayList<Integer> a = new ArrayList();
        ArrayList<Integer> a1 = new ArrayList();
        ArrayList<Integer> a2 = new ArrayList();
        ArrayList<Integer> a3 = new ArrayList();
        ArrayList<Integer> a4 = new ArrayList();
        ArrayList<Integer> a5 = new ArrayList();
        ArrayList<Integer> a6 = new ArrayList();


        for (int i = 2; i <= 1 + sqrt; i++) {
            if (number % i == 0 && number != i) {
                if (!a6.contains(i)) {
                    if (i != (number / i)) {
                        sum = sum + i + number / i;
                        a6.add((number / i));
                    } else {
                        sum = sum + i;
                    }
                    a6.add(i);
                }
            }
            if (number * 2 <= limit && !store.containsKey(number * 2)) {
                //System.out.println("Calling getFactorsForRest for "+number*2);
                sum1 = getFactorsForRest(number, i, sum1, a, 2);
            }
            if (number * 3 <= limit && !store.containsKey(number * 3)) {
                //System.out.println("Calling getFactorsForRest for "+number*3);
                sum2 = getFactorsForRest(number, i, sum2, a1, 3);
            }
            if (number * 5 <= limit && !store.containsKey(number * 5)) {
                //System.out.println("Calling getFactorsForRest for "+number*5);
                sum3 = getFactorsForRest(number, i, sum3, a2, 5);
            }
            if (number * 7 <= limit && !store.containsKey(number * 7)) {
                //System.out.println("Calling getFactorsForRest for "+number*7);
                sum4 = getFactorsForRest(number, i, sum4, a3, 7);
            }
            if (number * 11 <= limit && !store.containsKey(number * 11)) {
                //System.out.println("Calling getFactorsForRest for "+number*11);
                sum5 = getFactorsForRest(number, i, sum5, a4, 11);
            }
            if (number * 13 <= limit && !store.containsKey(number * 13)) {
                //System.out.println("Calling getFactorsForRest for "+number*13);
                sum6 = getFactorsForRest(number, i, sum6, a5, 13);
            }
        }
        if (number <= limit)
            //System.out.println(number);
            store.put(number, sum);
        if (number * 2 <= limit)
            //System.out.println(number*2);
            store.put(number * 2, sum1);
        if (number * 3 <= limit)
            //System.out.println(number*3);
            store.put(number * 3, sum2);
        if (number * 5 <= limit)
            //System.out.println(number*5);
            store.put(number * 5, sum3);
        if (number * 7 <= limit)
            //System.out.println(number*7);
            store.put(number * 7, sum4);
        if (number * 11 <= limit)
            //System.out.println(number*11);
            store.put(number * 11, sum5);
        if (number * 13 <= limit)
            //System.out.println(number*13);
            store.put(number * 13, sum6);
        return sum;
    }

    public int getFactorsForRest(int number, int i, int sum1, ArrayList<Integer> a, int multiple) {

        if (i != number / i) {
            if ((number * multiple) % (number / i) == 0 && !a.contains((number / i))) {
                sum1 += (number / i);
                a.add((number / i));
            }
            if (((number * multiple) % ((number / i) * multiple)) == 0 && !a.contains((number / i) * multiple)) {
                sum1 += ((number / i) * multiple);
                a.add(((number / i) * multiple));
            }
        }

        if (((number * multiple) % i) == 0 && !a.contains(i)) {
            sum1 += i;
            a.add(i);
        }
        if (((number * multiple) % (multiple * i)) == 0 && !a.contains(multiple * i) && (number * multiple) != multiple) {
            sum1 += (multiple * i);
            a.add(i * multiple);
        }

        if (number % multiple != 0 && !a.contains(multiple)) {
            sum1 += multiple;
            a.add(multiple);
        }

        if (!a.contains(number)) {
            sum1 += number;
            a.add(number);
        }

        if (!a.contains(1)) {
            sum1 += 1;
            a.add(1);
        }

        return sum1;
    }

    public long process(int a, int b) {
        StopWatch s = new StopWatch();
        s.start();
        int s1;
        int s2;
        for (int i = a; i < b; i++) {
            if(store.containsKey(i))
                s1 = Integer.parseInt(store.get(i).toString());
            else{
                //System.out.println("Calling getSumFactors for "+i);
                s1 = getSumOfFactors(i, b);
            }
            if(store.containsKey(s1) && s1>i)
                 s2 = Integer.parseInt(store.get(s1).toString());
            else
                s2 = (s1 > i) ? getSumOfFactors(s1,b) : 0;
            
            if (s2 == i )
                System.out.println("("+i+","+s1+")");
        }
        s.stop();
        return (s.getElapsedTime());
    }

    public static void main(String[] args) {
        System.out.println(new Main().process(2, 400));
    }
}
