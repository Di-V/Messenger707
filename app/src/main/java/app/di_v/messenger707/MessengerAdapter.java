package app.di_v.messenger707;

import android.content.Context;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.di_v.messenger707.activity.ChatActivity;
import app.di_v.messenger707.data.model.ListUsers;


public class MessengerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static String EXTRA_CONTACT_ID = "userId";
    private List<ListUsers> mContacts;
    private final LayoutInflater mInflater;

    public MessengerAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void setUsers(List<ListUsers> users) {
        mContacts = users;
        notifyDataSetChanged();
    }

    // сообщаем адаптеру сколько элементов
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
                Intent intent = new Intent( ((ContactHolder) holder).cardView.getContext(), ChatActivity.class);
                intent.putExtra(EXTRA_CONTACT_ID, mContacts.get(position).getId());
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
