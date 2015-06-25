package th.in.pureapp.irin;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ChatFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private ChatAdapter chatAdapter;
    private  WebView webView;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ChatFragment newInstance(int sectionNumber) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ChatFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chat, container, false);

        webView = new WebView(getActivity());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webView.loadUrl("file:///android_asset/irin/android.html");
        webView.addJavascriptInterface(this,"ChatFragment");

        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.chatDisplay);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        chatAdapter = new ChatAdapter();
        ChatDataSet chatDataSet = new ChatDataSet();
        chatDataSet.setMsg("สวัสดีค่ะ หนูชื่อไอริน ยินดีที่พบกันนะคะ");
        chatDataSet.setType(AppConfig.CHATBOX_TYPEIRIN);
        chatAdapter.mData.add(chatDataSet);
        mRecyclerView.setAdapter(chatAdapter);

        final EditText chatInput = (EditText)rootView.findViewById(R.id.chatInput);
        ImageView btnSend = (ImageView) rootView.findViewById(R.id.imageViewSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("javascript:getReply('"+chatInput.getText().toString()+"')");
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @JavascriptInterface
    public void setReady(){
        Toast.makeText(getActivity(),"Tigger",Toast.LENGTH_LONG).show();
        //EditText chatInput = (EditText)getView().findViewById(R.id.chatInput);
        //chatInput.setHint(getString(R.string.write_something));
        //chatInput.setFocusable(true);
    }

    @JavascriptInterface
    public void setError(String err){
        Toast.makeText(getActivity(),"ERROR",Toast.LENGTH_LONG).show();
        //
    }

    @JavascriptInterface
    public void setIrinMessage(String msg){
        chatAdapter.mData.add(new ChatDataSet(msg, AppConfig.CHATBOX_TYPEIRIN));
        chatAdapter.notifyDataSetChanged();
    }
}