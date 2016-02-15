package com.moldedbits.testingplayground;

import android.test.ActivityInstrumentationTestCase2;
import android.view.WindowManager;

import com.robotium.solo.Solo;

import org.mockito.Mockito;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class MainActivityTest extends ActivityInstrumentationTestCase2 {

    private Solo solo;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.setProperty("dexmaker.dexcache", getInstrumentation().getTargetContext().getCacheDir().getPath());
        solo = new Solo(getInstrumentation(), getActivity());

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
            }
        });

        ApiHelper helper = Mockito.mock(ApiHelper.class);
        doReturn("{\"body\":\"test json response\"}").when(helper).getJsonFromServer();
        ((MainActivity) getActivity()).setHelper(helper);
    }

    public void testUI() throws Exception {
        solo.sleep(1000);
        assertTrue(solo.searchText("Hello world"));
        assertNotNull(solo.getView(R.id.button_1));
        assertNotNull(solo.getView(R.id.button_2));

        solo.clickOnButton(1);
        solo.sleep(100);
        assertTrue(solo.searchText("test json response"));
    }

    @Override
    protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }
}
