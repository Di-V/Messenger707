package app.di_v.messenger707;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import app.di_v.messenger707.data.model.UserMessages;


public class MessagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int TYPE_CONTACT_MESSAGE = 0;
    private final static int TYPE_USER_MESSAGE = 1;
    private UserMessages mMessagesList;
    private final LayoutInflater mInflater;

    public MessagesAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void setUsers(UserMessages messages) {
        mMessagesList = messages;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (mMessagesList.getMessagesList().get(position).getStatus() == 0) {
            return TYPE_USER_MESSAGE;
        } else return TYPE_CONTACT_MESSAGE;
    }

    // сообщаем адаптеру сколько элементов
    @Override
    public int getItemCount() {
        if (mMessagesList != null) {
            return mMessagesList.getMessagesList().size();
        } else return 0;
    }

    // Вызывается, когда RecyclerView требуется новый RecyclerView.ViewHolder
    // данного типа для представления элемента.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        if (viewType == TYPE_CONTACT_MESSAGE) {
            CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_contact_message, parent, false);
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

        if (holder instanceof ContactMessageHolder) {
            //((ContactMessageHolder) holder).mContactImgView
            //        .setImageResource(mMessagesList.get(0).getUserImg());
            ((ContactMessageHolder) holder).mContactMsgView
                    .setText(mMessagesList.getMessagesList().get(position).getMessage());
            //((ContactMessageHolder) holder).mContactMsgStatusView.setText(mMessages.get(position).getStatus());
        } else if (holder instanceof UserMessageHolder) {
            //((UserMessageHolder) holder).mUserImgView
            //        .setImageResource(mMessagesList.get(1).getUserImg());
            ((UserMessageHolder) holder).mUserMsgView
                    .setText(mMessagesList.getMessagesList().get(position).getMessage());
            //((UserMessageHolder) holder).mUserMsgStatusView.setText(mMessages.get(position).getStatus());
        }
    }

    /**
     * Holder - определяет представление для каждого элемена RecyclerView
     */
    public class ContactMessageHolder extends RecyclerView.ViewHolder {
        private ImageView mContactImgView;
        private TextView mContactMsgView;
        private TextView mContactMsgStatusView;

        public ContactMessageHolder(CardView v) {
            super(v);
            mContactImgView = v.findViewById(R.id.img_contact);
            mContactMsgView = v.findViewById(R.id.contact_message);
            mContactMsgStatusView = v.findViewById(R.id.message_status);
        }
    }

    public class UserMessageHolder extends RecyclerView.ViewHolder {
        private ImageView mUserImgView;
        private TextView mUserMsgView;
        private TextView mUserMsgStatusView;

        public UserMessageHolder(CardView v) {
            super(v);
            mUserImgView = v.findViewById(R.id.img_user);
            mUserMsgView = v.findViewById(R.id.user_message);
            mUserMsgStatusView = v.findViewById(R.id.user_message_status);
        }
    }
}
