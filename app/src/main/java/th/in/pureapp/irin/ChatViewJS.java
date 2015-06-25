package th.in.pureapp.irin;

import android.content.Context;
import android.webkit.JavascriptInterface;

/**
 * Created by Pakkapon on 6/6/2558.
 */
public class ChatViewJS {

    Context context;

    ChatViewJS(Context c){
        context = c;
    }

    @JavascriptInterface
    public void setReady(){

    }

    @JavascriptInterface
    public void setError(String err){

    }
}
