package isel.codes;

public class TaxFruit {
    public String test(int tax, int[] fruitsPrice, String[] fruitsName) { // 세금에 따라서 과일 가격을 sout 해줌 / parameter에 해당하는 부분을 추가해서 생각 해주는 것이 좋다고 판단이 됨
        int percent;
        int a = 10;
        int b = 20;
        String returnValue = null;

        if(tax < a) {
            percent = 30;
            for(int i = 0; i < fruitsPrice.length; i++) { // fruitsPrice.length는 MethodInvocation node로 분류가 되어야 할 것 같은데, 왜 안되지
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
