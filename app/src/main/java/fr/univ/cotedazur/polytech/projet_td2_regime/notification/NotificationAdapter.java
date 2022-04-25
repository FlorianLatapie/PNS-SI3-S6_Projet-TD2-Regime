package fr.univ.cotedazur.polytech.projet_td2_regime.notification;

import android.app.Notification;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.IListner;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.User;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.UserManager;

public class NotificationAdapter extends BaseAdapter {
    private Context context;
    private List<Notif> notificationList;
    private LayoutInflater inflater;
    private IListner listener;
    private User user;

    public NotificationAdapter(Context context){
        this.context= context;
        this.user = UserManager.getInstance().getCurrentUser();
        this.notificationList = user.getNotifications();
        Collections.reverse(this.notificationList);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return notificationList.size();
    }

    @Override
    public Notif getItem(int position) {
        return notificationList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.notification_layout, null);

        Notif currentNotification = getItem(i);
        System.out.println(currentNotification+"\n notification: "+ user.getNotifications());

        ((TextView)view.findViewById( R.id.title)).setText(currentNotification.getTitle());
        ((TextView)view.findViewById( R.id.description)).setText(currentNotification.getDescription());

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        ((TextView)view.findViewById( R.id.date)).setText(dateFormat.format(currentNotification.getDate()));


        return view;
    }


    public void addListener(IListner listener) {
        this.listener = listener;
    }
}
