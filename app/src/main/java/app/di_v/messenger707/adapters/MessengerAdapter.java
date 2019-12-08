package app.di_v.messenger707.adapters;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.di_v.messenger707.R;
import app.di_v.messenger707.ui.dialog.DialogActivity;
import app.di_v.messenger707.data.model.ListUsers;

public class MessengerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ListUsers> mContacts;

    public void setUsers(List<ListUsers> users) {
        mContacts = users;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mContacts != null) {
            return mContacts.size();
        } else return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_contact, parent, false);

        return new ContactHolder(cardView);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        ((ContactHolder) holder).mUserNameTextView.setText(mContacts.get(position).getUserName());
        ((ContactHolder) holder).mUserMessage.setText(mContacts.get(position).getLastMessage());

        ((ContactHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = DialogActivity.newIntent(((ContactHolder) holder).cardView.getContext(),
                        mContacts.get(position).getId());
                ((ContactHolder) holder).cardView.getContext().startActivity(intent);
            }
        });
    }

    public class ContactHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView mUserImageView;
        private TextView mUserNameTextView;
        private TextView mUserMessage;

        public ContactHolder(CardView v) {
            super(v);
            cardView = v;
            mUserImageView = v.findViewById(R.id.img_contact);
            mUserNameTextView = v.findViewById(R.id.contact_user_name);
            mUserMessage = v.findViewById(R.id.contact_message);
        }
    }
}
