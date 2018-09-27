package com.example.sauravrp.listings;

import com.example.sauravrp.listings.repo.interfaces.IDataModel;
import com.example.sauravrp.listings.network.models.Listing;
import com.example.sauravrp.listings.viewmodels.ListingsViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import io.reactivex.Single;

import static org.junit.Assert.assertEquals;

public class ListingsViewModelTest {

    @Mock
    private IDataModel dataModel;

    private ListingsViewModel listingsViewModel;

    private ImmediateSchedulerProvider immediateSchedulerProvider;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        listingsViewModel = new ListingsViewModel(dataModel);
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


//        TestObserver<List<Listing>> testObserver = listingsViewModel.getListings()
//                .subscribeOn(immediateSchedulerProvider.computation())
//                .observeOn(immediateSchedulerProvider.computation()).test();
////        listingsViewModel.getMoreListings(0);
//
////        testObserver.assertValueCount(1);
//        Listing resultValue = testObserver.values().get(0).get(0);
//       assertEquals(listing, resultValue);
//        testObserver.assertNoErrors();
//        testObserver.assertComplete();
//        testObserver.assertValue(results);

    }
}
