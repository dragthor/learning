package com.kriskrause.learning;

import java.util.ArrayList;
import java.lang.UnsupportedOperationException;

public final class MandarinSimpleData implements IData  {
    public MandarinSimpleData() { }

    public ArrayList<DataItem> getItems(Integer keyIndex) {
        throw new UnsupportedOperationException();
    }
}