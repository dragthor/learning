package com.kriskrause.learning;

import java.util.Random;

public class UpdateRandomCharTask extends CharTask {

    @Override
    public DataItem getNext(DataItem... params) {
    	int idx = new Random().nextInt(params.length);

        return params[idx];
    }
}