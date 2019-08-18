package app.di_v.messenger707;

public class Contacts {
    private String mContact;
    private int mImgContact;

    // for test
    public static final Contacts[] cont = {
            new Contacts("User 1", R.drawable.ic_insert_emoticon_black_24dp),
            new Contacts("User 2", R.drawable.ic_insert_emoticon_black_24dp),
            new Contacts("User 1", R.drawable.ic_insert_emoticon_black_24dp),
            new Contacts("User 2", R.drawable.ic_insert_emoticon_black_24dp),
            new Contacts("User 1", R.drawable.ic_insert_emoticon_black_24dp),
            new Contacts("User 2", R.drawable.ic_insert_emoticon_black_24dp),
            new Contacts("User 1", R.drawable.ic_insert_emoticon_black_24dp),
            new Contacts("User 2", R.drawable.ic_insert_emoticon_black_24dp),
            new Contacts("User 1", R.drawable.ic_insert_emoticon_black_24dp),
            new Contacts("User 2", R.drawable.ic_insert_emoticon_black_24dp),
            new Contacts("User 1", R.drawable.ic_insert_emoticon_black_24dp),
            new Contacts("User 2", R.drawable.ic_insert_emoticon_black_24dp),
            new Contacts("User 1", R.drawable.ic_insert_emoticon_black_24dp),
            new Contacts("User 2", R.drawable.ic_insert_emoticon_black_24dp),
            new Contacts("User 1", R.drawable.ic_insert_emoticon_black_24dp),
            new Contacts("User 2", R.drawable.ic_insert_emoticon_black_24dp),
            new Contacts("User 1", R.drawable.ic_insert_emoticon_black_24dp),
            new Contacts("User 2", R.drawable.ic_insert_emoticon_black_24dp)
    };

    public Contacts(String contact, int imgContact) {
        mContact = contact;
        mImgContact = imgContact;
    }

    public String getContact() {
        return mContact;
    }

    public void setContact(String contact) {
        mContact = contact;
    }

    public int getImgContact() {
        return mImgContact;
    }

    public void setImgContact(int imgContact) {
        mImgContact = imgContact;
    }
}
