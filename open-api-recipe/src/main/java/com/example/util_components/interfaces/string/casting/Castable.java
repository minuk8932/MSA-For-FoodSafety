package com.example.util_components.interfaces.string.casting;

public interface Castable extends StringCastable, LongCastable, DoubleCastable{

    String IS_NUMERIC = "-?\\d+(\\.\\d+)?";

    String EMPTY_STRING = "";

    Object valueValidator(Object value);

}
