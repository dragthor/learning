package com.kriskrause.learning;

import java.util.ArrayList;
import java.lang.UnsupportedOperationException;

public final class GermanData implements IData  {
    public GermanData() { }

    public ArrayList<DataItem> getItems(Integer keyIndex) {
        throw new UnsupportedOperationException();
    }
}