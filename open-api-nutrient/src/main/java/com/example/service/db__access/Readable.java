package com.example.service.db__access;

import java.util.ArrayList;
import java.util.List;

public interface Readable {

    boolean isContainsField(Object object);

    default List<?> getListAll() { return new ArrayList<>(); }

}
