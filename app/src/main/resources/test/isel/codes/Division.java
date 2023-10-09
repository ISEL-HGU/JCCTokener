package isel.codes;

public class Division {
    public long test() { // 세금에 따라서 과일 가격을 sout 해줌
        int sum = 0;
        int[] numbers = {1, 2, 3, 4, 5};

        for(int number : numbers) {
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
