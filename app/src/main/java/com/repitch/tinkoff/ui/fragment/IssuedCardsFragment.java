package com.repitch.tinkoff.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.repitch.tinkoff.R;
import com.repitch.tinkoff.adapters.IssuedCardsAdapter;
import com.repitch.tinkoff.models.Card;
import com.repitch.tinkoff.ui.views.recycler.DividerItemDecoration;

/**
 * Created by repitch on 10.09.15.
 */
public class IssuedCardsFragment extends BaseFragment {
    @Override
    public String getFragmentTitle() {
        return "Выпущенные карты";
    }

    public static IssuedCardsFragment newInstance() {
        return new IssuedCardsFragment();
    }

    Card[] testCards = new Card[]{
            new Card("12345678", "IGOR BARYSHNIKOV", Card.TYPE_MASTERCARD, 100000, 40000, false),
            new Card("78460098", "VALERA BORZOJ", Card.TYPE_VISA, 100000, 25000, false),
            new Card("18924720", "MARIA KRAVETZ", Card.TYPE_VISA, 100000, 100000, false),
            new Card("19058247", "HELLO WORLD", Card.TYPE_MASTERCARD, 120000, 100000, true),
            new Card("09283123", "ALEXANDR SVISTOPLYASOV", Card.TYPE_VISA, 100000, 1000, true),
            new Card("80939212", "IGOR PUSHKIN", Card.TYPE_MASTERCARD, 100000, 10, false),
    };

    RecyclerView rvIssuedCards;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_issued_cards, container, false);

        rvIssuedCards = (RecyclerView) rootView.findViewById(R.id.rvIssuedCards);
        rvIssuedCards.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        RecyclerView.LayoutManager lmIssuedCards = new LinearLayoutManager(getActivity());
        rvIssuedCards.setLayoutManager(lmIssuedCards);
        // adapter
        RecyclerView.Adapter aIssuedCards = new IssuedCardsAdapter(testCards);
        rvIssuedCards.setAdapter(aIssuedCards);

        return rootView;
    }
}
