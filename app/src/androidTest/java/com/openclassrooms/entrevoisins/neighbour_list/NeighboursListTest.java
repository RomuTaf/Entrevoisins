
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.NoActivityResumedException;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.contrib.ViewPagerActions.scrollRight;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static junit.framework.TestCase.fail;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {
    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(withId(R.id.list_neighbours))
                .perform(actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT-1));
    }

//Mes Tests

    @Test
    public void myNeighboursList_clickOnView_shouldGoToDetailPage(){
        //Given : click on the item
        onView(withId(R.id.list_neighbours))
                .perform(actionOnItemAtPosition(0, click()));
        //Then : go to page details
        onView(withId(R.id.details))
                .check(matches(isDisplayed()));
    }


    @Test
    public void checkTextView_isDisplayed_and_notEmpty()  {

        //Given : click on the item
        onView(withId(R.id.list_neighbours))
                .perform(actionOnItemAtPosition(ITEMS_COUNT-1, click()));
        //Then : go to page details
        onView(withId(R.id.details))
                .check(matches(isDisplayed()));
        // passes if the textView does not match the empty string
        onView(withId(R.id.mName_neigbbour)).check(matches(not(withText(""))));
    }

    @Test
    public void Given_two_favorites_When_clickOnFavoriteButton_Then_AttemptToShowThem(){
        //When : Check if the View is Displayed
        onView(withId(R.id.list_neighbours)).check(matches(isDisplayed()));
        for (int i = 0; i < 2; i++){
            //click on the first neighbour to show detail
            onView(withId(R.id.list_neighbours))
                    .perform(actionOnItemAtPosition(i, click()));
            //Then : go to page details
            onView(withId(R.id.details))
                    .check(matches(isDisplayed()));
            //click on the fab button to add it to favorite
            onView(withId(R.id.fab)).perform(click());
            //return back
            pressBack ();

        }
        //scroll to the favorite page in the container
        onView(withId(R.id.container)).perform(scrollRight());
        //Check if favorite list has 2 items
        onView(withId(R.id.list_favorites)).check(withItemCount(2));
        //Then : assert that the page is displayed
        onView(withId(R.id.list_favorites)).check(matches(isDisplayed()));
    }



}


