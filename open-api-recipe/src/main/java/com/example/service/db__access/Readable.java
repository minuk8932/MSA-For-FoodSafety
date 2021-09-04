package com.example.service.db__access;

import java.util.List;

public interface Readable {

    boolean isContainsField(Object object);

    List<?> getListAll();

}
