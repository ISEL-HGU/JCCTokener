package isel.codes;

public class Test {

    public String test(int tax, int[] fruitsPrice, String[] fruitsName) { // 세금에 따라서 과일 가격을 sout 해줌
        int percent;
        int a = 10;
        int b = 20;
        String returnValue = null;

        if(tax < a) {
            percent = 30;
            for(int i = 0; i < fruitsPrice.length; i++) {
                returnValue += (percent * fruitsPrice[i]) + fruitsName[i];
            }
        } else if (a <= tax && tax < b) {
            percent = 40;
            for(int i = 0; i < fruitsPrice.length; i++) {
                returnValue += (percent * fruitsPrice[i]) + fruitsName[i];
            }
        } else {
            percent = 50;
            for(int i = 0; i < fruitsPrice.length; i++) {
                returnValue += (percent * fruitsPrice[i]) + fruitsName[i];
            }
        }

        return returnValue;
    }
}
