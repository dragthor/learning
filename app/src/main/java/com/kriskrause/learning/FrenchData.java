package com.kriskrause.learning;

import java.util.ArrayList;

public final class FrenchData implements IData  {
    public FrenchData() { }

    @Override
    public ArrayList<DataItem> getItems(Integer keyIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getReviewIndex() {
        return 0;
    }
}