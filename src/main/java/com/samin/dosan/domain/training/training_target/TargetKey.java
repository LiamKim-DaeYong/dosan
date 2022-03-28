package com.samin.dosan.domain.training.training_target;

import lombok.Getter;

@Getter
public enum TargetKey {
    NUMBER_OF_FIRST("1학년 인원 수"),
    NUMBER_OF_SECOND("2학년 인원 수"),
    NUMBER_OF_THIRD("3학년 인원 수"),
    NUMBER_OF_FOURTH("4학년 인원 수"),
    NUMBER_OF_FIFTH("5학년 인원 수"),
    NUMBER_OF_SIXTH("6학년 인원 수"),

    CLASS_OF_FIRST("1학년 학급 수"),
    CLASS_OF_SECOND("2학년 학급 수"),
    CLASS_OF_THIRD("3학년 학급 수"),
    CLASS_OF_FOURTH("4학년 학급 수"),
    CLASS_OF_FIFTH("5학년 학급 수"),
    CLASS_OF_SIXTH("6학년 학급 수"),

    TEENAGER("10대"),
    TWENTIES("20대"),
    THIRTIES("30대"),
    FORTIES("40대"),
    FIFTIES("50대"),
    SIXTIES("60대"),
    SEVENTIES("70대"),
    EIGHTIES("80대"),
    NINETIES("90대"),
    CENTENARIAN("100대"),

    ONE_TO_FIVE_YEARS("1~5년"),
    SIX_TO_TEN_YEARS("6~10년"),
    ELEVEN_TO_FIFTEEN_YEARS("11~15년"),
    SIXTEEN_TO_TWENTY_YEARS("16~20년"),
    TWENTY_ONE_TO_TWENTY_FIVE_YEARS("21~25년"),
    TWENTY_SIX_TO_THIRTY_YEARS("26~30년"),
    THIRTY_ONE_TO_THIRTY_FIVE_YEARS("31~35년"),
    THIRTY_SIX_TO_FORTY_YEARS("36~40년"),
    FORTY_ONE_TO_FORTY_FIVE_YEARS("41~45년"),
    FORTY_SIX_TO_FIFTY_YEARS("46~50년"),

    POSITION_NM1("직위(직급) 이름1"),
    POSITION_NM2("직위(직급) 이름2"),
    POSITION_NM3("직위(직급) 이름3"),
    POSITION_NM4("직위(직급) 이름4"),
    POSITION_NM5("직위(직급) 이름5"),
    POSITION_NM6("직위(직급) 이름6"),
    POSITION_NM7("직위(직급) 이름7"),
    POSITION_NM8("직위(직급) 이름8"),
    POSITION_NM9("직위(직급) 이름9"),
    POSITION_NM10("직위(직급) 이름10"),

    POSITION_NUMBER1("직위(직급) 인원 수 1"),
    POSITION_NUMBER2("직위(직급) 인원 수 2"),
    POSITION_NUMBER3("직위(직급) 인원 수 3"),
    POSITION_NUMBER4("직위(직급) 인원 수 4"),
    POSITION_NUMBER5("직위(직급) 인원 수 5"),
    POSITION_NUMBER6("직위(직급) 인원 수 6"),
    POSITION_NUMBER7("직위(직급) 인원 수 7"),
    POSITION_NUMBER8("직위(직급) 인원 수 8"),
    POSITION_NUMBER9("직위(직급) 인원 수 9"),
    POSITION_NUMBER10("직위(직급) 인원 수 10");

    private final String description;

    TargetKey(String description) {
        this.description = description;
    }
}
