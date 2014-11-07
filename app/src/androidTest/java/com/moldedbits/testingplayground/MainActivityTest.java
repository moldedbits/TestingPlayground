package com.moldedbits.testingplayground;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

public class MainActivityTest extends ActivityInstrumentationTestCase2 {

    private Solo solo;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testUI() throws Exception {
        solo.sleep(1000);
        assertTrue(solo.searchText("Hello world"));
        assertNotNull(solo.getView(R.id.button_1));
        assertNotNull(solo.getView(R.id.button_2));
    }

    @Override
    protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }
}
