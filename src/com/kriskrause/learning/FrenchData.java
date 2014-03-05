package com.kriskrause.learning;

import java.util.ArrayList;
import java.lang.UnsupportedOperationException;

public final class FrenchData implements IData  {
    public FrenchData() { }

    public ArrayList<DataItem> getItems(Integer keyIndex) {
        throw new UnsupportedOperationException();
    }
}