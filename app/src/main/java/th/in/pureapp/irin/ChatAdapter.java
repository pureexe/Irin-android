package th.in.pureapp.irin;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Vector;

/**
 * Created by Pakkapon on 3/6/2558.
 */
public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Vector<ChatDataSet> mData;

    ChatAdapter(){
        mData = new Vector<ChatDataSet>();
    }

    public static class IrinViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public IrinViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.irinchat);
        }
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public UserViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.userchat);
        }
    }

    @Override
    public int getItemViewType(int position) {
        super.getItemViewType(position);
        return mData.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewRoot;
        RecyclerView.ViewHolder viewHolder;
        if(viewType == AppConfig.CHATBOX_TYPEUSER) {
            viewRoot = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chatmsg_user, parent, false);
            viewHolder = new UserViewHolder(viewRoot);
        }else{
            viewRoot = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chatmsg_irin, parent, false);
            viewHolder = new IrinViewHolder(viewRoot);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(mData.get(position).getType()==AppConfig.CHATBOX_TYPEUSER){
            UserViewHolder userHolder = (UserViewHolder) holder;
            userHolder.textView.setText(mData.get(position).getMsg());
        }else{
            IrinViewHolder irinrHolder = (IrinViewHolder) holder;
            irinrHolder.textView.setText(mData.get(position).getMsg());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
