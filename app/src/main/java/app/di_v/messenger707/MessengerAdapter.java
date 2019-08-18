package app.di_v.messenger707;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MessengerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int TYPE_CONTACT = 0;
    private final static int TYPE_CONTACT_MESSAGE = 1;
    private final static int TYPE_USER_MESSAGE = 2;
    private String[] mContacts;
    private int[] mImgContacts;
    private Listener mListener;

    interface Listener {
        void onClick(int position);
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    public MessengerAdapter(final String[] contacts, final int[] imgContact) {
        mContacts = contacts;
        mImgContacts = imgContact;
    }

    @Override
    public int getItemViewType(int position) {
        if (true) {
            return TYPE_CONTACT;
        } else if (false) {
            return TYPE_USER_MESSAGE;
        } else return TYPE_USER_MESSAGE;
    }

    // сообщаем адаптеру сколько элементов
    @Override
    public int getItemCount() {
        return mContacts.length;
    }

    // Вызывается, когда RecyclerView требуется новый RecyclerView.ViewHolder
    // данного типа для представления элемента.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        if (viewType == TYPE_CONTACT) {
            CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_contact, parent, false);
            return new ContactHolder(cv);
        } else if (viewType == TYPE_CONTACT_MESSAGE) {
            CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_contact, parent, false);
            return new ContactMessageHolder(cv);
        } else if (viewType == TYPE_USER_MESSAGE) {
            CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_user_message, parent, false);
            return new UserMessageHolder(cv);
        }

        throw new RuntimeException("there is no type that matches the type " + viewType
                + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ContactHolder) {
            final CardView cardView = ((ContactHolder) holder).cardView;
            ImageView imageView = cardView.findViewById(R.id.img_contact);
            Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), mImgContacts[position]);
            imageView.setImageDrawable(drawable);
            imageView.setContentDescription(mContacts[position]);
            TextView textView = cardView.findViewById(R.id.contact_user_name);
            textView.setText(mContacts[position]);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(cardView.getContext(), ChatActivity.class);
                    //intent.putExtra(ChatActivity.E)
                    cardView.getContext().startActivity(intent);
                }
            });
        } else if (holder instanceof ContactMessageHolder) {
            final CardView cardView = ((ContactMessageHolder) holder).cardView;
            ImageView imageView = cardView.findViewById(R.id.img_contact);
            Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), mImgContacts[position]);
            imageView.setImageDrawable(drawable);
            imageView.setContentDescription(mContacts[position]);
            TextView textView = cardView.findViewById(R.id.contact_user_name);
            textView.setText(mContacts[position]);


        } else if (holder instanceof UserMessageHolder) {
            final CardView cardView = ((UserMessageHolder) holder).cardView;

        }
    }

    /**
     * Holder - определяет представление для каждого элемена RecyclerView
     */
    public class ContactHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ContactHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    public class ContactMessageHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ContactMessageHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    public class UserMessageHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public UserMessageHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }
}
