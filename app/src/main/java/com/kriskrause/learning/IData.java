package com.kriskrause.learning;

import java.util.ArrayList;

public interface IData {
    ArrayList<DataItem> getItems(Integer keyIndex);
    int getReviewIndex();
}
