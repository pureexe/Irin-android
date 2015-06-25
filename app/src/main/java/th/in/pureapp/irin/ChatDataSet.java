package th.in.pureapp.irin;

/**
 * Created by Pakkapon on 4/6/2558.
 */
public class ChatDataSet {
    int type;
    String msg;
    ChatDataSet(){

    }
    ChatDataSet(String m,int t){
        type = t;
        msg = m;
    }
    public void setType(int t){
        type = t;
    }
    public void setMsg(String m){
        msg = m;
    }
    public int getType(){
        return type;
    }
    public String getMsg(){
        return msg;
    }
}
