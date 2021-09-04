package com.example.service.interfaces.categorize;

import com.example.model.data_enums.NutrientElements;

import java.util.List;
import java.util.Map;

public interface Categorizable {

    String[] NUT_FORMATS = {"치킨", "돼지구이", "소구이", "생선구이"
            , "생선회", "라면", "커피", "음료수", "빵", "피자", "김치"
            , "탕", "밥", "떡볶이", "순대", "튀김"};

    Map<String, ?> configurationSettingsCategorize();

    List<?> ingestedNutrientsCategoryArranger(NutrientElements nutrientElements);

}
