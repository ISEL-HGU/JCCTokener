package isel.csee.jcctokener.calculate;

import isel.csee.jcctokener.node.jCCNode;

import java.util.List;

/*
여기서 n개씩 묶어준 다음에 비교해주는 과정을 거쳐야 함

-> MultiValueMap 사용 (key value의 중복을 허용해줌)

같은 이름의 클래스끼리 값을 가지고 와야 함 -> lastIndex 사용해서 _로 구분(_뒤에 위치하는 값을 가지고 와서 새로운 폴더 생성 해주기)
-> 클래스 이름을 읽어서 그 이름에 해당하는 폴더가 존재하면 그 안에 저장하고, 해당하는 폴더가 없을 경우에는 새로 그 이름으로 폴더 생성 해주기

1. 폴더 재귀적으로 추적 후 클래스 파일 추출(이 때 추출한 클래스 파일은 selectedCodes 폴더에 저장) - exist, isDirectory method 사용
    1) 저장할 때, 그 안에 하위 폴더를 만들고, 그 폴더의 이름을 학번으로 만들어주기
2. 만들어진 클래스 이름 별 모든 파일에 대해서 clone 검사 실행
3. 검사 결과를 토대로 clone code 학번을 output으로 return
 */

public class CandidateTokensFilter {
    private List<jCCNode> jCCNodeList;











    public CandidateTokensFilter(List<jCCNode> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }
}
