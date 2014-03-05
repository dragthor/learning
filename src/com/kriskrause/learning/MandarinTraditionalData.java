package com.kriskrause.learning;

import java.util.ArrayList;
import java.lang.UnsupportedOperationException;

public final class MandarinTraditionalData implements IData  {
    public MandarinTraditionalData() { }

    public ArrayList<DataItem> getItems(Integer keyIndex) {
        throw new UnsupportedOperationException();
    }
}