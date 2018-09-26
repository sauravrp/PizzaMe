package com.example.sauravrp.pizzame;

import com.example.sauravrp.pizzame.repo.interfaces.IDataModel;
import com.example.sauravrp.pizzame.network.models.Listing;
import com.example.sauravrp.pizzame.viewmodels.ListingsViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import io.reactivex.Single;

import static org.junit.Assert.assertEquals;

public class PizzaMeViewModelTest {

    @Mock
    private IDataModel dataModel;

    private ListingsViewModel pizzaMeViewModel;

    private ImmediateSchedulerProvider immediateSchedulerProvider;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        pizzaMeViewModel = new ListingsViewModel(dataModel);
        immediateSchedulerProvider = new ImmediateSchedulerProvider();
    }


    @Test
    public void test1() {
        Listing listing = new Listing();
        listing.setId("1");
        listing.setTitle("Delicious Pizza");
        listing.setAddress("123 MLK blvd");
        listing.setCity("Austin");
        listing.setState("TX");
        listing.setPhone("111-111-1111");
        listing.setDistance(".7");

        ArrayList<Listing> results = new ArrayList();
        results.add(listing);

        Mockito.when(dataModel.getListings(0)).thenReturn(Single.just(results));


//        TestObserver<List<Listing>> testObserver = pizzaMeViewModel.getListings()
//                .subscribeOn(immediateSchedulerProvider.computation())
//                .observeOn(immediateSchedulerProvider.computation()).test();
////        pizzaMeViewModel.getMoreListings(0);
//
////        testObserver.assertValueCount(1);
//        Listing resultValue = testObserver.values().get(0).get(0);
//       assertEquals(listing, resultValue);
//        testObserver.assertNoErrors();
//        testObserver.assertComplete();
//        testObserver.assertValue(results);

    }
}
