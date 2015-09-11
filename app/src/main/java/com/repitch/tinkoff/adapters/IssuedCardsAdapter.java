package com.repitch.tinkoff.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.repitch.tinkoff.R;
import com.repitch.tinkoff.models.Card;

/**
 * Created by repitch on 10.09.15.
 */
public class IssuedCardsAdapter extends RecyclerView.Adapter<IssuedCardsAdapter.ViewHolder> {

    private static final int VIEW_TYPE_NORMAL = 1;
    private static final int VIEW_TYPE_BLOCKED = 2;

    private Card[] cards;

    // класс view holder-а родителя
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgCardType;
        public TextView txtLastNumbers;
        public TextView txtFullname;

        public ViewHolder(View v) {
            super(v);
            imgCardType = (ImageView) v.findViewById(R.id.imgCardTypeIcon);
            txtLastNumbers = (TextView) v.findViewById(R.id.txtLastNumbers);
            txtFullname = (TextView) v.findViewById(R.id.txtFullName);
        }
    }

    // для обычной карты
    public static class ViewHolderNormal extends ViewHolder {
        public TextView txtCashLimit;
        public ProgressBar pbCashLimit;

        public ViewHolderNormal(View v) {
            super(v);
            txtCashLimit = (TextView) v.findViewById(R.id.txtCashLimit);
            pbCashLimit = (ProgressBar) v.findViewById(R.id.pbCashLimit);
        }
    }

    // для заблокированной карты
    public static class ViewHolderBlocked extends ViewHolder {
        public ViewHolderBlocked(View v) {
            super(v);
        }
    }

    public IssuedCardsAdapter(Card[] cards) {
        this.cards = cards;
    }

    @Override
    public int getItemViewType(int position) {
        Card card = cards[position];
        if (card.isBlocked()){
            return VIEW_TYPE_BLOCKED;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }

    @Override
    public IssuedCardsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        ViewHolder vh;
        switch (viewType){
            case VIEW_TYPE_NORMAL:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_issued_card, parent, false);
                vh = new ViewHolderNormal(v);
                return vh;
            case VIEW_TYPE_BLOCKED:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_issued_card_blocked, parent, false);
                vh = new ViewHolderBlocked(v);
                return vh;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(IssuedCardsAdapter.ViewHolder holder, int position) {
        Card card = cards[position];
        if (card.getType()==Card.TYPE_MASTERCARD){
            holder.imgCardType.setImageResource(R.drawable.card_mastercard);
        } else
        if (card.getType()==Card.TYPE_VISA){
            holder.imgCardType.setImageResource(R.drawable.card_visa);
        }

        String cardNum = card.getCardNumber();
        holder.txtLastNumbers.setText("* "+cardNum.substring(cardNum.length()-4));

        holder.txtFullname.setText(card.getFullname());

        if (!card.isBlocked()){
            String cashLimitStr = "осталось ";
            cashLimitStr += card.getCashBalance();
            cashLimitStr += " Р из ";
            cashLimitStr += card.getCashLimit();
            cashLimitStr += " Р";
            ((ViewHolderNormal)holder).txtCashLimit.setText(cashLimitStr);

            int cashPercent = (int)((double)(card.getCashBalance())/(double)(card.getCashLimit())*100.0);
            ((ViewHolderNormal)holder).pbCashLimit.setProgress(cashPercent);
        }
    }

    @Override
    public int getItemCount() {
        return cards.length;
    }
}
