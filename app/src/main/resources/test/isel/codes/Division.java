package isel.codes;

public class Division {
    public long test() {
        int sum = 0;
        int[] numbers = {1, 2, 3, 4, 5};

        for(int number : numbers) { // enhanced For loop에서 이 condition 부분은 어떻게 가져올 것인가?
            try {
                if (number % 2 == 0) {
                    sum += doubleValue(number);
                } else {
                    sum += number;
                }
                switch (number) {
                    case 1:
                        map.put("one", number);
                        break;
                    case 2:
                        map.put("two", number);
                        break;
                    default:
                        map.put("other", number);
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }

        return sum;
    }
}
