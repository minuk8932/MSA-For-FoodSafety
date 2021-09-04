package com.example.util_components.interfaces.connecting;

import com.example.model.Recipes;

import java.util.List;

public interface Connectable {

    List<Recipes> requestRemoteData();

}
