package com.kriskrause.learning;

import java.util.ArrayList;

public final class GermanData implements IData  {
    public GermanData() { }

    @Override
    public ArrayList<DataItem> getItems(Integer keyIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getReviewIndex() {
        return 0;
    }
}