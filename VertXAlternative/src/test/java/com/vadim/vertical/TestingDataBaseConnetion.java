package com.vadim.vertical;

import org.junit.Before;
import org.junit.Test;

public class TestingDataBaseConnetion {

    DataDao dao;
    final String NAME = "blog";

    @Before
    public void setUp() throws Exception {
        dao = new DataDao(NAME, NAME);
    }

    @Test
    public void getDocuments() {
        dao.getDocuments();
    }
}
