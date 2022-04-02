package fr.univ.cotedazur.polytech.projet_td2_regime.Interactions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.User;

/** class issue de : https://www.youtube.com/watch?v=e3MDWAkWTyQ&ab_channel=Graven-D%C3%A9veloppement **/

public class CommentsAdapter extends BaseAdapter {
    private Context context;
    private List<Comment> comments;
    private LayoutInflater inflater;

    public CommentsAdapter(Context context, List<Comment> comments){
        this.context= context;
        this.comments = comments;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Comment getItem(int position) {
        return comments.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.comment_layout, null);

        Comment currentComment = getItem(i);
        User author = currentComment.getAuthor();
        ((TextView)view.findViewById( R.id.commentText)).setText(currentComment.getText());
        ((ImageView)view.findViewById( R.id.authorCommentPicture)).setImageResource(author.getImageProfile());
        ((TextView)view.findViewById( R.id.authorCommentPseudo)).setText(author.getLastName() );

        return view;
    }
}
