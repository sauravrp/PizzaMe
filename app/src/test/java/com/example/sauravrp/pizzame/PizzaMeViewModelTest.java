package com.example.sauravrp.pizzame;

import com.example.sauravrp.pizzame.datamodels.interfaces.IDataModel;
import com.example.sauravrp.pizzame.models.network.Result;
import com.example.sauravrp.pizzame.viewmodels.PizzaMeViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.assertEquals;

public class PizzaMeViewModelTest {

    @Mock
    private IDataModel dataModel;

    private PizzaMeViewModel pizzaMeViewModel;

    private ImmediateSchedulerProvider immediateSchedulerProvider;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        pizzaMeViewModel = new PizzaMeViewModel(dataModel);
        immediateSchedulerProvider = new ImmediateSchedulerProvider();
    }


    @Test
    public void test1() {
        Result listing = new Result();
        listing.setId("1");
        listing.setTitle("Delicious Pizza");
        listing.setAddress("123 MLK blvd");
        listing.setCity("Austin");
        listing.setState("TX");
        listing.setPhone("111-111-1111");
        listing.setDistance(".7");

        ArrayList<Result> results = new ArrayList();
        results.add(listing);

        Mockito.when(dataModel.getPizzaListings(0)).thenReturn(Single.just(results));


//        TestObserver<List<Result>> testObserver = pizzaMeViewModel.getResults()
//                .subscribeOn(immediateSchedulerProvider.computation())
//                .observeOn(immediateSchedulerProvider.computation()).test();
////        pizzaMeViewModel.offsetSelected(0);
//
////        testObserver.assertValueCount(1);
//        Result resultValue = testObserver.values().get(0).get(0);
//       assertEquals(listing, resultValue);
//        testObserver.assertNoErrors();
//        testObserver.assertComplete();
//        testObserver.assertValue(results);

    }
}
